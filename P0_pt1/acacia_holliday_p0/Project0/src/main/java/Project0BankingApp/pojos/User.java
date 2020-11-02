package Project0BankingApp.pojos;

public class User {
	
	/**
	 * User has String username and password, Account account object, 
	 * int Bank
	 */
	
	private String username;
	private String password;
	private Account account;
	private int  bankID;
	private String bankName;
	
	public User(String username, String password, Account account, int bankID, String bankName){
		this.username = username;
		this.password = password;
		this.account = account;
		this.bankID = bankID;
		this.setBankName(bankName);

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getBank() {
		return bankID;
	}

	public void setBank(int bID) {
		this.bankID = bID;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
