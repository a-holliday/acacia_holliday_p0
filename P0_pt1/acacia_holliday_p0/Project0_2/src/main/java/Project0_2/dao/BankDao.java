package Project0_2.dao;

import java.sql.Connection;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Project0_2.util.ConnectionUtil;

public class BankDao implements BankDaoInterface {
	
	private ConnectionUtil connectUtil = new ConnectionUtil();
	private static Logger log = Logger.getRootLogger();

	

	public void setConnectUtil(ConnectionUtil connectUtil) {
		this.connectUtil = connectUtil;
	}
	
	public boolean insertBank(String bankName) {
		
		String insertString = "insert into banks (bank_name)"
				+ " values(?)";
		
		try (Connection connection = connectUtil.createConnection();
				PreparedStatement insertBank = 
				connection.prepareStatement(insertString)){
				insertBank.setString(1, bankName);
				insertBank.executeUpdate();
				log.info("Bank " + bankName + " created");
				return true;
		}
		catch(SQLException e){
			log.error("Could not create bank", e);
			return false;
		}
		
	}
	
public boolean insertBankTest(String bankName) {
		
		String insertString = "insert into test_banks (bank_name)"
				+ " values(?)";
		try (Connection connection = connectUtil.createConnection();
				PreparedStatement insertBank = 
				connection.prepareStatement(insertString)){
				insertBank.setString(1, bankName);
				insertBank.executeUpdate();
				log.info("Bank " + bankName + " created");
				return true;
		}
		catch(SQLException e){
			log.error("Could not create bank", e);
			return false;
		}
		
	}
	
	public boolean readBank(String bankName) {
		String readString = "select * from banks where bank_name = ?";
		try (Connection connection = connectUtil.createConnection();
				PreparedStatement readBank = 
				connection.prepareStatement(readString)){
				readBank.setString(1, bankName);
				ResultSet rs = readBank.executeQuery();
				return rs.next();
		}catch(SQLException e) {
			log.error(e);
		}
		
		return false;
	}
	
	public boolean readBankTest(String bankName) {
		String readString = "select * from test_banks where bank_name = ?";
		try (Connection connection = connectUtil.createConnection();
				PreparedStatement readBank = 
				connection.prepareStatement(readString)){
				readBank.setString(1, bankName);
				ResultSet rs = readBank.executeQuery();
				return rs.next();
		}catch(SQLException e) {
			log.error(e);
		}
		
		return false;
	}
	
	public boolean updateBank(String oldBankName, String newBankName) {
		String updateString = "update banks set bank_name = ? where bank_name = ?";
		try (Connection connection = connectUtil.createConnection();
				PreparedStatement updateBank = connection.prepareStatement(updateString)
				){updateBank.setString(1, newBankName);
				  updateBank.setString(2, oldBankName);
				  updateBank.executeUpdate();
				  log.info("Bank " + oldBankName + " updated to " + newBankName);
				  return true;
		}catch(SQLException e) {
			log.error(e);
		}
		return false;
	}
	
	public boolean updateBankTest(String oldBankName, String newBankName) {
		String updateString = "update test_banks set bank_name = ? where bank_name = ?";
		try (Connection connection = connectUtil.createConnection();
				PreparedStatement updateBank = connection.prepareStatement(updateString)
				){updateBank.setString(1, newBankName);
				  updateBank.setString(2, oldBankName);
				  updateBank.executeUpdate();
				  log.info("Bank " + oldBankName + " updated to " + newBankName);
				  return true;
		}catch(SQLException e) {
			log.error(e);

		}
		return false;
	}
	
	



	public StringBuilder readAllBanks() {
		StringBuilder  response = new StringBuilder();
		ResultSet rs;
		String readAllString = "select bank_name from banks";
		try (Connection connection = connectUtil.createConnection();
				PreparedStatement readAllBanks = connection.prepareStatement(readAllString)
				){rs = readAllBanks.executeQuery();
				while(rs.next()){
					response.append(rs.getString(1) +"\n");
				}
			return response;
		}catch(SQLException e) {
			log.error(e);
		}
		return response;
	}
	
	public StringBuilder readAllBanksTest() {
		StringBuilder  response = new StringBuilder();
		ResultSet rs;
		String readAllString = "select bank_name from test_banks";
		try (Connection connection = connectUtil.createConnection();
				PreparedStatement readAllBanks = connection.prepareStatement(readAllString)
				){rs = readAllBanks.executeQuery();
				while(rs.next()){
					response.append(rs.getString(1) +"\n");
				}
			return response;
		}catch(SQLException e) {
			log.error(e);
		}
		return response;
	}
	
}
