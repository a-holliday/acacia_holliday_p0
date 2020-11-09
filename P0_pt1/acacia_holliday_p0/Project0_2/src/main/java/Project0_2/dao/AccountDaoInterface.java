package Project0_2.dao;

public interface AccountDaoInterface {
	public String insertAccount(String username, String bank, String password);
	
	public String checkBalance(String username, String password);
	
	public boolean withdraw(String username, String password, double withdrawal);
	
	public boolean deposit(String username, String password, double deposit);
	
	public boolean authenticate(String username, String password);
	
	public String removeUser(String username, String password);
	
	public String transfer(String payUser, String payUserPass, String recvUser, double amount);

	
	//Do this if I have time
	public String updatePassword(String username, String password );
	
}
