package Project0_2Dao.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Project0_2.dao.AccountDao;
import Project0_2.util.ConnectionUtil;

public class AccountDaoTest {
	private AccountDao accountDao = new AccountDao();
	private ConnectionUtil connectUtil = new ConnectionUtil();
	private Statement stmt;
	Connection connection;


	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		connection = connectUtil.createConnection();
		stmt = connection.createStatement();

		String insertString = "insert into test_banks (bank_name)"
				+ " values(?)";
		
		try (
				PreparedStatement insertBank = 
				connection.prepareStatement(insertString)){
				insertBank.setString(1, "TestBank");
				insertBank.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
			System.out.println("Could not create bank");
		}
		
	}

	@After
	public void tearDown() throws Exception {
		
		String deleteStringAc = "delete from test_accounts where username = ?";
		try (
			 PreparedStatement deleteBank = connection.prepareStatement(deleteStringAc)){
			deleteBank.setString(1, "TestUser");
			deleteBank.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		};
		
		String deleteString = "delete from test_banks where bank_name = ?";
		try (
			 PreparedStatement deleteBank = connection.prepareStatement(deleteString)){
			deleteBank.setString(1, "TestBank");
			deleteBank.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		};
		
		connection.close();
	}

	@Test
	public void insertAccountTest() throws Exception {
		
		accountDao.insertAccountTest("TestUser", "TestBank", "testpassword");
		 ResultSet rs = stmt.executeQuery("select * from test_accounts where username = 'TestUser'");
		 assertTrue("Should return true", rs.next());
		
	}
	
	
	

}
