package repo;

import java.util.ArrayList;
import java.util.List;

import entity.PastaEntity;
import entity.SauceEntity;

public class PastaRepo extends BaseRepo<PastaEntity> {

	private static PastaRepo instance = null;
	
	private static SauceRepo sauceService = SauceRepo.getInstance(); 

	public static PastaRepo getInstance() {
		if (instance == null) {
			instance = new PastaRepo();
		}

		return instance;
	}

	private PastaRepo() {
		
	}
	
	@Override
	protected void initMap() {
		List<SauceEntity> list = new ArrayList<>();
		list.add(sauceService.get("Tomato"));
		list.add(sauceService.get("Pesto"));
		this.map.put("Spaghetti", new PastaEntity("Spaghetti", list, 11.99, "boil 9 minutes", 5));
		
		list = new ArrayList<>();
		list.add(sauceService.get("Cream cheese"));
		list.add(sauceService.get("Pesto"));
		this.map.put("Farfalle", new PastaEntity("Farfalle", list, 3.99, "boil 9 minutes", 5));
		
		list = new ArrayList<>();
		list.add(sauceService.get("Tomato"));
		list.add(sauceService.get("Alfredo"));
		this.map.put("Lasagna", new PastaEntity("Lasagna", list, 2.99, "in the oven", 5));
		
		list = new ArrayList<>();
		list.add(sauceService.get("Cream cheese"));
		list.add(sauceService.get("Alfredo"));
		list.add(sauceService.get("Balognese"));
		this.map.put("Fettuccine", new PastaEntity("Fettuccine", list, 4.49, "boil 9 minutes", 5));
	}

}
