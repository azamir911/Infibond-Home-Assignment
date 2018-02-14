package service;

import java.util.Collection;

import repo.SauceRepo;

public class SauceService {

	private static SauceService instance = null;
	
	private static SauceRepo sauceRepo = SauceRepo.getInstance(); 
	
	public static SauceService getInstance() {
		if (instance == null) {
			instance = new SauceService();
		}

		return instance;
	}

	private SauceService() {
		
	}
	
	public Collection<String> getAllSauceTypes() {
		return sauceRepo.getAllKeys();
	}

}
