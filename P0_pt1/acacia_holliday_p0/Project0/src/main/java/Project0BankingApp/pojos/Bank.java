package Project0BankingApp.pojos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bank {
	
	/**
	 * Bank pojo that keeps up with number of accounts and accounts for each bank instance
	 */
	
	private int numOfBanks = 0;
	private int numOfAccounts = 0;
	private Set<Account> accounts;
	private int bankID;
	private String bankName;
	private static List<String> availableBanks = new ArrayList<String>(Arrays.asList("CHASE", "WELLS FARGO", "FIFTH THIRD BANK", 
			"BANK OF AMERICA"));
	
	/**
	 * @return the numOfBanks
	 */
	public int getNumOfBanks() {
		return numOfBanks;
	}



	/**
	 * @param numOfBanks the numOfBanks to set
	 */
	public void setNumOfBanks(int numOfBanks) {
		this.numOfBanks = numOfBanks;
	}



	/**
	 * @return the availableBanks
	 */
	public static List<String> getAvailableBanks() {
		return availableBanks;
	}



	/**
	 * @param availableBanks the availableBanks to set
	 */
	public static void setAvailableBanks(List<String> availableBanks) {
		Bank.availableBanks = availableBanks;
	}



	public Bank(String bName, HashSet<Account> accounts) {
		numOfBanks++;
		this.bankName = bName;
		this.bankID = numOfBanks;
		this.accounts = accounts;
		
		
	}
	
	 
	
	public Set<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	public int getBankID() {
		return bankID;
	}
	public void setBankID(int bankID) {
		this.bankID = bankID;
	}
	public  String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	
	
	/**
	 * addAccount adds account object to accounts HashSet of bank instance
	 * @param account -- account must not be in accounts
	 */
	
	public void  addAccount(Account account) {
		if (!accounts.contains(account)) {
			setNumOfAccounts(getNumOfAccounts() + 1);
			accounts.add(account);
		}
		else {
			System.out.println("Account already exists");
		}
	}


	public int getNumOfAccounts() {
		return numOfAccounts;
	}


	public void setNumOfAccounts(int numOfAccounts) {
		this.numOfAccounts = numOfAccounts;
	}
}
