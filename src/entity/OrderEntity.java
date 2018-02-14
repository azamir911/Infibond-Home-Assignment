package entity;

import java.util.List;

public class OrderEntity {

	private String howToCook;

	private double totalPrice;

	private String imageUrl;

	private List<String> ingredients;

	public String getHowToCook() {
		return howToCook;
	}

	public void setHowToCook(String howToCook) {
		this.howToCook = howToCook;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

}
