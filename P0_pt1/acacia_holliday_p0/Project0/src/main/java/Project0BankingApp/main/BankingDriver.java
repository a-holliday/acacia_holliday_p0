package Project0BankingApp.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import Project0BankingApp.pojos.Account;
import Project0BankingApp.pojos.Bank;
import Project0BankingApp.pojos.User;
import Project0BankingApp.service.UserServiceImpl;

public class BankingDriver {
	private static Logger log = Logger.getRootLogger();
	UserServiceImpl userService = new UserServiceImpl();
	Account userAccount;
	User currentUser;
	String choice = "";
	Bank userBank;
	Scanner scan = new Scanner(System.in);

	/**
	 *Drives the main program with the methods, main functionality is through a case statement in 
	 *loop 
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		BankingDriver instance = new BankingDriver();
		log.info("Program has started");
		
		do {
			instance.printMenu();
			instance.choice = instance.scan.nextLine();
			switch(instance.choice) {
			
			case("1"):
				instance.createNewUser();
				break;
			case("2"):
				instance.deposit();
				break;
			case("3"):
				instance.withdrawal();
				break;
			case("4"):
				instance.transfer();
				break;
			case("5"):
				instance.printStatement();
				break;
			case("0"):
				instance.scan.close();
				System.out.println("Goodbye!");
				break;
			default:
				System.out.println("Invalid choice");
			
			}
			
		}while(!instance.choice.equals("0"));
		
		
		
	}
	
	/**
	 * Prints a menu for the user
	 */
	
	public void printMenu() {
		System.out.println("Welcome to the Banking System");
		System.out.println("Options:");
		System.out.println("[1] Create new user");
		System.out.println("[2] Deposit");
		System.out.println("[3] Withdrawal");
		System.out.println("[4] Transfer");
		System.out.println("[5] Print Statement");
		System.out.println("[0] Quit");
		
		
	}
	
	/**
	 * Creates new user
	 * @return
	 */

	public boolean createNewUser() {
		System.out.println("What is your bank called?");
		String bankName = scan.nextLine();
		if(userService.getBankService().getBankFromName(bankName) == null) {
			userService.getBankService().createBank(bankName);
			userBank = userService.getBankService().getBankFromName(bankName);
		}
			System.out.println("Enter a username");
			String username = scan.nextLine();
			if(userService.getBankService().getAccount(userBank, username) != null) {
				System.out.println("User already exists");
				return false;
			}
			System.out.println("Enter a password");
			String password = scan.nextLine();	
			userService.createUser(username, password, bankName);
			userAccount = userService.getBankService().getAccount(userBank, username);
			log.info("Created user: " + username);
			return true;

	}
		
	/**
	 * Deposits into authenticated user account
	 */


 public void deposit() {
	 if(authentication()) {
	 System.out.println("Enter amount to deposit");
	 double amount = scan.nextDouble();
	 scan.nextLine();
	 userAccount.deposit(amount);
	 }
 }
 
 /**
  * Withdraws from authenticated user account
  */
 
 public void withdrawal() {
	if(authentication()) {
	 System.out.println("Enter amount to withdraw");
	 double amount = scan.nextDouble();
	 scan.nextLine();
	 userAccount.withdrawal(amount);
	}
	 
 }
 
 /**
  * Prints all transactions that are alive for the length of the program
  */
 public void printStatement() {
	if(authentication()) {
		 System.out.println("Bank Statment");
		 System.out.println("+++++++++++++++++++++++++");
		 userAccount.printStatement();
		 System.out.println("++++++++++++++++++++++++++");
	}
	 
 }
 
 /**
  * Performs transfer between two  accounts
  */
 
 public void transfer() {
	 if(authentication()) {
		 System.out.println("What is the recieving bank called?");
		 String recievingBankName = scan.nextLine();
		 Bank recievingBank = userService.getBankService().getBankFromName(recievingBankName);
		 System.out.println("What is the recieving username");
		 String recievingUsername = scan.nextLine();
		 System.out.println("What is the amount to transfer?");
		 double amount = scan.nextDouble();
		 scan.nextLine();
		 userService.getBankService().transferAccount(recievingBank, recievingUsername, userBank, userAccount.getUsername(), amount);
	 }
 }
 
 /**
  * Authentication that is run initially before other methods besides createUser
  * @return
  */
 public boolean authentication() {
		 System.out.println("What is your banks name?");
		 String bankName = scan.nextLine();
		 if(userService.getBankService().getBankFromName(bankName) == null) {
			 System.out.println("Couldn't find bank");
			 return false;
		 }
		 userBank = userService.getBankService().getBankFromName(bankName);
		 
		 System.out.println("Enter username");
		 String username = scan.nextLine();
		 System.out.println("Enter password");
		 String password = scan.nextLine();
		 currentUser = userService.authenticate(username, password);
		 userAccount = userService.getBankService().getAccount(userBank, username);

		 if( currentUser == null || userAccount == null ) {
			 System.out.println("Invalid username or password");
			 log.info("Invalid logging attempt for username: " +username);
			 return false;
		 }
	 
	 return true;
 }
 

 
}
