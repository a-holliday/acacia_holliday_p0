package Project0BankingApp.service;

import java.util.HashSet;

import Project0BankingApp.pojos.Account;
import Project0BankingApp.pojos.Bank;

public class BankServiceImpl implements BankService {
	
	
	
	private CustomCacheImpl<Bank> cache = new CustomCacheImpl<>();
	
	/**
	 * @param cache the cache to set
	 */
	public void setCache(CustomCacheImpl<Bank> cache) {
		this.cache = cache;
	}

	

	/**
	 * @return the cache
	 */
	public CustomCacheImpl<Bank> getCache() {
		return cache;
	}



	/**
	 * creates a new bank and adds it to the cache
	 */

	@Override
	public void createBank(String bankName) {
		Bank newBank = new Bank(bankName,  new HashSet<Account>());
		cache.addToCache(newBank);
		
	}
	
	/**
	 * removes bank if exists
	 */

	@Override
	public void removeBank(Bank bank) {
		if(cache.contains(bank)) {
			cache.removeFromCache(bank);
			System.out.println("Bank removed");
		}
		else {
			System.out.println("The bank does not exist");
		}
		
		
	}
	/**
	 * using the account Number and bank, returns an account
	 * @return - can return null
	 */

	@Override
	public Account getAccount(Bank bank, String username) {
		if (cache.contains(bank)){
			for (Account accountInBank : bank.getAccounts()) {
				if (accountInBank.getUsername().equals(username)) {
					return accountInBank;
				}
			}
		}
		return null;
	}

	/**
	 * Add an account to bank
	 */
	@Override
	public void addAccount(Bank bank, Account account) {
		
		bank.addAccount(account);
	}
	/**
	 *  from the bank ID return a bank object
	 *  @return - can return null
	 */

	@Override
	public Bank getBank(int bankID) {
		for (Bank bankInSet : cache.retrieveAllItems()) {
			if (bankInSet.getBankID() == bankID){
				return bankInSet;
			}
		}
		return null;
	}
	
	
	
	/**
	 * 
	 * create new Account and add account to bank
	 */
	@Override
	public void createAccount(String username, Bank bank) {
		try {
		Account newAccount = new Account(username, bank.getBankID(), bank.getNumOfAccounts()+1);
		bank.setNumOfAccounts(bank.getNumOfAccounts()+1);
		addAccount(bank, newAccount);
		}catch(NullPointerException e) {
			System.out.println("Could not find bank");
		}
		
	}
	
	/**
	 * @return - a Bank object is returned from String input
	 */

	@Override
	public Bank getBankFromName(String bankName) {
		
		for (Bank bankInSet : cache.retrieveAllItems()) {
			if (bankInSet.getBankName().equals(bankName)){
				return bankInSet;
			}
		}
		return null;
	}
	/**
	 * transfers funds across accounts or banks
	 */

	@Override
	public void transferAccount(Bank recievingBank, String recievingUser,
			Bank payingBank, String payingUser, double amount) {
		try {
			if (getAccount(payingBank, payingUser).getBalance() >= amount) {
			getAccount(payingBank, payingUser).withdrawal(amount);
			}
			else {
				System.out.println("Not enough funds");
				return;
			}
			}catch(NullPointerException e){
				System.out.println("User not found");
			}
		
		try {
		getAccount(recievingBank, recievingUser).deposit(amount);
		}
		catch(NullPointerException e) {
			System.out.println("User not found");
			getAccount(payingBank, payingUser).deposit(amount);
		}
	}
		


}
