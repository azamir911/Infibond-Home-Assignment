package repo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseRepo<T> {
	
	protected Map<String, T> map;

	public BaseRepo() {
		this.map = new HashMap<>();
		initMap();
	}
	
	protected abstract void initMap(); 
	
	public Collection<String> getAllKeys() {
		return this.map.keySet();
	}
	
	public T get(String name) {
		return this.map.get(name);
	}

}
