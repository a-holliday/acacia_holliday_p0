package Project0_2.dao;

public interface BankDaoInterface {
	
	public boolean insertBank(String bankName);
	
	public boolean readBank(String bankName);
	
	public StringBuilder readAllBanks();
	
	public boolean updateBank(String oldBankName, String newBankName);

}
