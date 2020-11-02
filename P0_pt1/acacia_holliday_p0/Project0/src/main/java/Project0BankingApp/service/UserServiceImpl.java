package Project0BankingApp.service;

import Project0BankingApp.pojos.Bank;
import Project0BankingApp.pojos.User;

public class UserServiceImpl implements UserService {
	
	CustomCacheImpl<User> cache = new CustomCacheImpl<>();
	BankServiceImpl bankService = new BankServiceImpl();
	/**
	 * @return the cache
	 */
	public CustomCacheService<User> getCache() {
		return cache;
	}


	/**
	 * @param cache the cache to set
	 */
	public void setCache(CustomCacheImpl<User> cache) {
		this.cache = cache;
	}


	/**
	 * @return the bankService
	 */
	public BankService getBankService() {
		return bankService;
	}


	/**
	 * @param bankService the bankService to set
	 */
	public void setBankService(BankServiceImpl bankService) {
		this.bankService = bankService;
	}


	/**
	 * creates new user and adds them to cache
	 */
	@Override
	public void createUser(String username, String password, String bankName) {
		Bank fetchedBank = bankService.getBankFromName(bankName);
		bankService.createAccount(username, fetchedBank);
		User newUser = new User(username, password, bankService.getAccount(fetchedBank, username), fetchedBank.getBankID(), bankName );
		cache.addToCache(newUser);
	}

	
	/**
	 * authenticates user and returns user from cache
	 */
	@Override
	public User authenticate(String username, String password) {
		for (User userInCache : cache.retrieveAllItems()) {
			if (userInCache.getUsername().equals(username) 
			&& userInCache.getPassword().equals(password)) {
				return userInCache;
			}
		}
		System.out.println("Incorrect username and password");
		return null;
	}
	/**
	 * authenticates and then deletes user from cache
	 */
	
	@Override
	public void removeUser(String username, String password) {
		try {
			User userToRemove = authenticate(username, password);
			cache.removeFromCache(userToRemove);
			System.out.println("User removed");

		}catch (NullPointerException e){
			System.out.println("Couldn't validate user");
			
		}
		
	}






}
