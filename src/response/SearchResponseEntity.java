package response;

import java.util.List;

public class SearchResponseEntity {

	private int count;
	private List<RecipeReponseEntity> recipes;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<RecipeReponseEntity> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<RecipeReponseEntity> recipes) {
		this.recipes = recipes;
	}

}
