package Project0_2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Project0_2.util.ConnectionUtil;

public class AccountDao implements AccountDaoInterface {
	private ConnectionUtil connectUtil = new ConnectionUtil();
	
	@Override
	/**
	 * Inserts into account table username, bankId, and password, gets bankId from bank name
	 * and checks if username is already taken, returns string on status;
	 */
	public String insertAccount(String username, String bankName, String password) {
		int bankId = 0;
		String getBankIdString = "select bank_id from banks where bank_name = ?";
		try(Connection connection = connectUtil.createConnection();
				PreparedStatement getBankId = 
				connection.prepareStatement(getBankIdString)){
				getBankId.setString(1, bankName);
				ResultSet rs = getBankId.executeQuery();
				if (!rs.next()) {
					return "Not a valid bank";
				}
				else {
					bankId = rs.getInt(1);
				}
					
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String checkUserString = "select username from accounts where username = ?";
		try(Connection connection = connectUtil.createConnection();
			PreparedStatement checkUser = connection.prepareStatement(checkUserString)){
			checkUser.setString(1, username);
			ResultSet rs = checkUser.executeQuery();
			if(rs.next()) {
				return "Username already exists";
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		String insertString = "insert into accounts(bank_id, username, credentials)"
				+ "values(?,?,?)";
		try(Connection connection = connectUtil.createConnection();
			PreparedStatement insertAccount = connection.prepareStatement(insertString)){
			insertAccount.setInt(1,bankId);
			insertAccount.setString(2, username);
			insertAccount.setString(3, password);
			insertAccount.executeUpdate();
			return "User " + username + " created!";
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	/**
	 * insert test duplicate for test database
	 */
	public String insertAccountTest(String username, String bankName, String password) {
		int bankId = 0;
		String getBankIdString = "select bank_id from test_banks where bank_name = ?";
		try(Connection connection = connectUtil.createConnection();
				PreparedStatement getBankId = 
				connection.prepareStatement(getBankIdString)){
				getBankId.setString(1, bankName);
				ResultSet rs = getBankId.executeQuery();
				if (!rs.next()) {
					return "Not a valid bank";
				}
				else {
					bankId = rs.getInt(1);
				}
					
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String checkUserString = "select username from test_accounts where username = ?";
		try(Connection connection = connectUtil.createConnection();
			PreparedStatement checkUser = connection.prepareStatement(checkUserString)){
			checkUser.setString(1, username);
			ResultSet rs = checkUser.executeQuery();
			if(rs.next()) {
				return "Username already exists";
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		String insertString = "insert into test_accounts(bank_id, username, credentials)"
				+ "values(?,?,?)";
		try(Connection connection = connectUtil.createConnection();
			PreparedStatement insertAccount = connection.prepareStatement(insertString)){
			insertAccount.setInt(1,bankId);
			insertAccount.setString(2, username);
			insertAccount.setString(3, password);
			insertAccount.executeUpdate();
			return "User " + username + " created!";
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	//add authentication?
	@Override
	public String checkBalance(String username, String password) {
		if (authenticate(username, password)) {
			String checkBalanceString = "select balance from accounts where username = ? and credentials = ? ";
			try(Connection connection = connectUtil.createConnection();
				PreparedStatement checkBalance = connection.prepareStatement(checkBalanceString)){
				checkBalance.setString(1, username);
				checkBalance.setString(2, password);
				ResultSet rs = checkBalance.executeQuery();
				rs.next();
				return String.valueOf(rs.getDouble(1));
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean withdraw(String username, String password, double withdraw) {
		double balance = Double.parseDouble(checkBalance(username, password));
		if (balance >= withdraw) {
			Double newBalance = balance - withdraw;
			String withdrawString = "update accounts set balance = ? where username = ? and credentials = ?";
			try(Connection connection = connectUtil.createConnection();
				PreparedStatement withdrawStmt = connection.prepareStatement(withdrawString)){
				withdrawStmt.setDouble(1, newBalance);
				withdrawStmt.setString(2, username);
				withdrawStmt.setString(3, password);
				withdrawStmt.executeUpdate();
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	
	}

	@Override
	public boolean deposit(String username, String password, double deposit) {
		double balance = Double.parseDouble(checkBalance(username, password));
	
		Double newBalance = balance + deposit;
		String depositString = "update accounts set balance = ? where username = ? and credentials = ?";
		try(Connection connection = connectUtil.createConnection();
			PreparedStatement depositStmt = connection.prepareStatement(depositString)){
			depositStmt.setDouble(1, newBalance);
			depositStmt.setString(2, username);
			depositStmt.setString(3, password);
			depositStmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return  false;
	
	}



	@Override
	public String transfer(String payUser, String payUserPass, String recvUser, double amount) {
		String transferDepositString = "update accounts set balance = balance + ? where username = ?";
		if (authenticate(payUser, payUserPass)) {
			if (withdraw(payUser, payUserPass, amount)) {
				try(Connection connection = connectUtil.createConnection();
					PreparedStatement transferDeposit = connection.prepareStatement(transferDepositString)){
					transferDeposit.setDouble(1, amount);
					transferDeposit.setString(2, recvUser);
					transferDeposit.executeUpdate();
					return "Succesful Transfer";
				}catch(SQLException e) {
					e.printStackTrace();
					deposit(payUser, payUserPass, amount);
					return "Unsuccessful Transfer returning funds";
				}
			}
			
			return "Not enough funds";
		}
		return "Invalid credentials";
	}

	@Override
	public boolean authenticate(String username, String password) {
		String authenticString = "select * from accounts where username = ? and credentials = ? ";
		try(Connection connection = connectUtil.createConnection();
			PreparedStatement authenticate = connection.prepareStatement(authenticString)){
			authenticate.setString(1, username);
			authenticate.setString(2, password);
			ResultSet rs = authenticate.executeQuery();
			return rs.next();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	//maybe check to see if account balance is 0
	public String removeUser(String username, String password) {
		String checkUserString = "select * from accounts where username = ?";
		try(Connection connection = connectUtil.createConnection();
			PreparedStatement checkUser = connection.prepareStatement(checkUserString)){
			checkUser.setString(1, username);
			ResultSet rs = checkUser.executeQuery();
			if (!rs.next()) {
				return "User does not exist";
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if (authenticate(username, password)) {
		String deleteStringAc = "delete from accounts where username = ?";
		try (Connection connection = connectUtil.createConnection();
			 PreparedStatement deleteBank = connection.prepareStatement(deleteStringAc)){
			deleteBank.setString(1, username);
			deleteBank.executeUpdate();
			return "User " + username + " deleted!";
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return "Database error";
	}
		
		return "Invalid credentials";
	}

	@Override
	public String updatePassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
