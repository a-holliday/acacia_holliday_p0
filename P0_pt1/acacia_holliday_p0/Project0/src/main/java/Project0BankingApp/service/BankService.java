package Project0BankingApp.service;


import Project0BankingApp.pojos.Account;
import Project0BankingApp.pojos.Bank;

public interface BankService {
	
	public void createBank(String bankName);
	
	public CustomCacheImpl<Bank> getCache();
	
	public void createAccount(String username, 
			Bank bank);
	
	public void removeBank(Bank bank);
	
	public Bank getBank(int bankID);
	
	public Bank getBankFromName(String Bank);
	
	public Account getAccount(Bank bank, String username);
	
	public void addAccount(Bank bank, Account account);
	
	public void transferAccount(Bank recievingBank, String recievingUser, 
			Bank payingBank, String payingUser, double amount);
}
