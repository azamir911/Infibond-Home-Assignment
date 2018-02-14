package service;

import response.RecipeEntity;
import response.RecipeReponseEntity;
import response.SearchResponseEntity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entity.BaseEntity;
import entity.OrderEntity;
import entity.PastaEntity;
import entity.SauceEntity;

public class StoreService {

	private static StoreService instance = null;

	public static StoreService getInstance() {
		if (instance == null) {
			instance = new StoreService();
		}

		return instance;
	}

	private StoreService() {

	}

	public OrderEntity doOrder(PastaEntity pastaEntity) throws Exception {
		if (pastaEntity.getAmount() == 0) {
			notify(pastaEntity);
			throw new Exception(pastaEntity.getName() + " has not amout to order");
		}
		
		for (SauceEntity s : pastaEntity.getSauces()) {
			if (s.getAmount() == 0) {
				notify(s);
				throw new Exception(s.getName() + " has not amout to order");
			}
		}
		
		String searchResult = RemoteService.getInstance().getSearchResult();

		SearchResponseEntity searchResponseEntity = parse(searchResult,
				SearchResponseEntity.class);

		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setHowToCook(pastaEntity.getDescription());
		double totalPrice = pastaEntity.getPrice();

		for (SauceEntity s : pastaEntity.getSauces()) {
			totalPrice += s.getPrice();
		}

		orderEntity.setTotalPrice(totalPrice);

		if (searchResponseEntity.getCount() > 0) {
			// I don't remember how to find relevant item, so I will get the
			// first one
			RecipeReponseEntity recipeReponseEntity = searchResponseEntity
					.getRecipes().get(0);
			orderEntity.setImageUrl(recipeReponseEntity.getImage_url());

			String recipe = RemoteService.getInstance().getRecipe(
					recipeReponseEntity.getRecipe_id());
			RecipeEntity parseRecipe = parse(recipe, RecipeEntity.class);

			orderEntity.setIngredients(parseRecipe.getRecipe().getIngredients());
		}
		
		pastaEntity.setAmount(pastaEntity.getAmount() - 1);
		
		for (SauceEntity s : pastaEntity.getSauces()) {
			s.setAmount(s.getAmount() - 1);
		}
		
		
		if (pastaEntity.getAmount() <= 3) {
			notify(pastaEntity);
		}

		return orderEntity;
	}

	private void notify(BaseEntity entity) {
		// We should do it in a new thread
		RemoteService.getInstance().notifyAmout(entity);
	}

	private <T> T parse(String json, Class<T> c) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		T entity = gson.fromJson(json, c);
		return entity;
	}

}
