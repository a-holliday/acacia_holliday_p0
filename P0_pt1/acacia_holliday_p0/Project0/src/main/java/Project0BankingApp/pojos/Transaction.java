package Project0BankingApp.pojos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * 
 * @author Acacia Holliday
 * Transaction class that keeps up with transactions made in the bank program
 * 
 *
 */


public class Transaction {
	
	


	private String transactionType;
	private double transactionAmount;
	private double balance;
	private String timeStamp;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
	 
	 
	

	public Transaction(String tType, double tAmount, 
			double balance ) {
		
		this.transactionType = tType;
		this.transactionAmount = tAmount;
		this.timeStamp = LocalDateTime.now().format(formatter);
		this.balance = balance;
	}
	
	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}


	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}


	/**
	 * @return the transactionAmount
	 */
	public double getTransactionAmount() {
		return transactionAmount;
	}


	/**
	 * @param transactionAmount the transactionAmount to set
	 */
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}


	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}


	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}


	/**
	 * @return the timeStamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}


	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}


	/**
	 * Overloads to String method with a string that gives information about 
	 * tranasaction type amount and balance
	 */
	@Override
	public String toString() {
		return "Transaction [transactionType=" + transactionType + ", transactionAmount=" + transactionAmount
				+ ", balance=" + balance + ", timeStamp=" + timeStamp + "]\n";
	} 
	
	
}
