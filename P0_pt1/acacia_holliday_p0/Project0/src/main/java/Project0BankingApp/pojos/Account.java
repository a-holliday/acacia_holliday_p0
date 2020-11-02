package Project0BankingApp.pojos;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Acacia Holliday
 * Account class has String username,
 * int accountID, double balance, List<Transaction> transactions
 * int accountNumber, int bankID and static numOfAccounts
 * 
 * Methods include deposit, withdrawal, printStatement, and transfer
 */

public class Account {



	/**
	 * 
	 * 
	 * 
	 *
	 */
	private String username;
	private double balance;
	private int accountNo;
	private int bankID;
	
	private List<Transaction> transactions;
	
	public Account(String username, int bankID, int accountNo) {
		this.username = username;
		this.balance = 0.0;
		this.accountNo = accountNo;
		this.bankID = bankID;
		this.transactions = new ArrayList<>();
	}
	


	/**
	 * deposit method adds to balance, create transaction
	 * @param deposit - must be greater than 1.0
	 */
	
	public void deposit(double deposit) {
		if (deposit > 1.0) {
			balance += deposit;
			Transaction newTransaction = new Transaction("Deposit", deposit, balance);
			transactions.add(newTransaction);
			System.out.println(newTransaction);

		}
		else {
			System.out.println("Deposit must be greater than a dollar");
		}
	}
	
	/**
	 * withdrawal method subtracts from the balance, creates transaction
	 * @param withdrawal - must be less than or equal to the balance
	 */
	public void withdrawal(double withdrawal) {
		if (withdrawal <= balance) {
			balance -= withdrawal;
			Transaction newTransaction = new Transaction("Withdrawal", withdrawal, balance);
			transactions.add(newTransaction);
			System.out.println(newTransaction);
		}
		else {
			System.out.println("Withdrawal exceeds balance");
		}
	}
	/**
	 * printStatement prints transactions with overidden toString method
	 */
	public void printStatement() {
		System.out.println(transactions);
	}
	/**
	 * TODO - finish documentation for transfer
	 * @param transferAmount
	 * @param bankID
	 * @param accountNo
	 * @return
	 */
	
	/*public double transfer(double transferAmount, String bankName, String recievingUsername) {
		//TODO figure how to implement this with cache
		return transferAmount;	
	}
	
	 */



	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if(!(obj instanceof Account)) {
			return false;
		}
		Account objForCompare = (Account) obj;
		if(objForCompare.accountNo == this.accountNo) {
			return true;
		}else {
			return false;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public int getBankID() {
		return bankID;
	}

	public void setBankID(int bankID) {
		this.bankID = bankID;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
}
