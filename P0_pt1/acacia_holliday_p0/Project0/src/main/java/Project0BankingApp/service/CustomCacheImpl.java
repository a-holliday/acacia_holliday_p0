package Project0BankingApp.service;


import java.util.HashSet;

import java.util.Set;

public class CustomCacheImpl<T> implements CustomCacheService<T> {
	private Set<T> cache = new HashSet<T>();
/**
 *  adds object to cache
 */
	@Override
	public void addToCache(T obj) {
		cache.add(obj);
	}
/**
 * checks if object is in cache
 * 
 */
	@Override
	public boolean contains(T obj) {
		if (cache.contains(obj))
			return true;
		else
			return false;
	}
	/**
	 * empties the cache
	 */

	@Override
	public void emptyCache() {
		cache.clear();
	}
	/**
	 * removes object form cache
	 */

	@Override
	public void removeFromCache(T obj) {
		cache.remove(obj);
	}

	/**
	 * returns all items in cache
	 */
	@Override
	public Set<T> retrieveAllItems() {
		return cache;
	}
	/**
	 * returns object if in set else returns null;
	 */

	@Override
	public T retrieveItem(T obj) {
		if(cache.contains(obj)) {
			for (T objInSet : cache) {
				if (objInSet.equals(obj)) {
					return objInSet;
				}
			}
		}
		return null;
	}

}

	

	


