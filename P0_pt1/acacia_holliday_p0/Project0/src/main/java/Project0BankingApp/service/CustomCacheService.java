package Project0BankingApp.service;

import java.util.Set;

public interface CustomCacheService <T>{
	/**
	 * 
	 * 
	 *
	 * @param <T> - takes in generic which would be our pojos creates contract for
	 * methods to remove object from cache, retrieve object, empty cache, check if
	 * object is contained in cache, retrieve all items, and retrieve matching items
	 */


		public void addToCache(T obj);
		
		public void removeFromCache(T obj);
		
		public T retrieveItem(T obj);
		
		public void emptyCache();
		
		public boolean contains(T obj);
				
		public Set<T> retrieveAllItems();
		

		
		
		
	}

