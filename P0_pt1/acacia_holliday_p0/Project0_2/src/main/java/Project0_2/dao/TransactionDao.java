package Project0_2.dao;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Project0_2.util.ConnectionUtil;

public class TransactionDao implements TransactionDaoInterface {

	ConnectionUtil connectUtil = new ConnectionUtil();
	private static Logger log = Logger.getRootLogger();

	
	public String printStatement(String username, String password) {
		StringBuilder statement = new StringBuilder();
		String checkUserString = "select * from accounts where username = ? and credentials =?";
		try(Connection connection = connectUtil.createConnection();
			PreparedStatement checkUser = connection.prepareStatement(checkUserString) ){
			checkUser.setString(1, username);
			checkUser.setString(2, password);
			ResultSet rs = checkUser.executeQuery();
			if (!rs.next()) {
				return "Invalid credentials";
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			return "Database Error";
		}
		String bankStatementString = "select * from public.bank_statement(?)";
		try(Connection connection = connectUtil.createConnection();
			PreparedStatement bankStatement = connection.prepareStatement(bankStatementString)){
			bankStatement.setString(1, username);
			ResultSet rs = bankStatement.executeQuery();
			while(rs.next()) {
				statement.append("||Transaction type: " + rs.getString(1));
				statement.append(" ||Amount: " + rs.getDouble(2));
				statement.append(" ||Balance: " + rs.getDouble(3));
				statement.append(" ||Timestamp " + rs.getDate(4) + "\n");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		return statement.toString();
	}
}
