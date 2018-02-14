package service;

import java.util.Collection;

import repo.PastaRepo;
import entity.PastaEntity;


public class PastaService {

	private static PastaService instance = null;
	
	private static PastaRepo pastaRepo = PastaRepo.getInstance(); 
	
	public static PastaService getInstance() {
		if (instance == null) {
			instance = new PastaService();
		}

		return instance;
	}

	private PastaService() {
		
	}
	
	public Collection<String> getAllPastaTypes() {
		return pastaRepo.getAllKeys();
	}
	
	public PastaEntity get(String name) {
		return pastaRepo.get(name);
	}
	
}
