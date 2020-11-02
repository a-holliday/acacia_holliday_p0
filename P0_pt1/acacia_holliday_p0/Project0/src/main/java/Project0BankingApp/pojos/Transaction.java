package Project0BankingApp.pojos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * 
 * @author Acacia Holliday
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


	@Override
	public String toString() {
		return "Transaction [transactionType=" + transactionType + ", transactionAmount=" + transactionAmount
				+ ", balance=" + balance + ", timeStamp=" + timeStamp + "]\n";
	} 
	
	
}
