package repo;

import entity.SauceEntity;

public class SauceRepo extends BaseRepo<SauceEntity> {

	private static SauceRepo instance = null;
	
	public static SauceRepo getInstance() {
		if (instance == null) {
			instance = new SauceRepo();
		}

		return instance;
	}

	private SauceRepo() {
	}
	
	@Override
	protected void initMap() {
		this.map.put("Tomato", new SauceEntity("Tomato", 4.99, 5));
		this.map.put("Cream cheese", new SauceEntity("Cream cheese", 3.99, 5));
		this.map.put("Pesto", new SauceEntity("Pesto", 2.99, 5));
		this.map.put("Alfredo", new SauceEntity("Alfredo", 4.49, 5));
		this.map.put("Bolognese", new SauceEntity("Bolognese", 5.49, 5));
	}

}
