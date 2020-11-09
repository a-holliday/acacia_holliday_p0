package Project0_2Dao.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

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
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import Project0_2.dao.BankDao;
import Project0_2.util.ConnectionUtil;
@RunWith(MockitoJUnitRunner.class)

public class BankDaoTest {
	public BankDao bankDao = new BankDao();
	
	@Mock
	private ConnectionUtil connUtil;
	
	@Mock
	private Connection connection;
	
	private Statement stmt;
	
	private Statement spy;
	
	private Connection realConnection;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		realConnection = new ConnectionUtil().createConnection();
		
		//creating a real stmt from a connection
		stmt = realConnection.createStatement(); 
		
		//spying on that real stmt
		spy = Mockito.spy(stmt);
		
		//mock our connection and util, so we will only use the stmt we are spying on
		when(connection.createStatement()).thenReturn(spy);
		when(connUtil.createConnection()).thenReturn(connection);
		
		//set up Dao to use the mocked object
		//bankDao.setConnectUtil(connUtil);
	}

	@After
	public void tearDown() throws Exception {
		String deleteString = "delete from test_banks where bank_name = ?";
		try (PreparedStatement deleteBank = 
				realConnection.prepareStatement(deleteString)){
			deleteBank.setString(1, "TestBank");
			deleteBank.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		};
		
	}

	@Test
	public void createBankTest() throws SQLException {
		String bankName = "TestBank";
		bankDao.insertBankTest(bankName);
		
	 ResultSet rs = stmt.executeQuery("select * from test_banks where bank_name = 'TestBank'");
	 assertTrue("Should return true", rs.next());
	}
	
	@Test
	public void updateBankTest() throws SQLException{
		String insertString = "insert into test_banks (bank_name)"
				+ " values(?)";
		
		try (
				PreparedStatement insertBank = 
				realConnection.prepareStatement(insertString)){
				insertBank.setString(1, "TestBank");
				insertBank.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		bankDao.updateBankTest("TestBank", "TestBank2");
		ResultSet rs = stmt.executeQuery("select * from test_banks where bank_name = 'TestBank2'");
		 assertTrue("Should return true", rs.next());
		 
		 String deleteString = "delete from test_banks where bank_name = ?";
			try (PreparedStatement deleteBank = 
					realConnection.prepareStatement(deleteString)){
				deleteBank.setString(1, "TestBank2");
				deleteBank.executeUpdate();
			}
			catch(SQLException e){
				e.printStackTrace();
			};
		
	}

	@Test
	public void readBankTest() throws SQLException{
		String insertString = "insert into test_banks (bank_name)"
				+ " values(?)";
		
		try (
				PreparedStatement insertBank = 
				realConnection.prepareStatement(insertString)){
				insertBank.setString(1, "TestBank");
				insertBank.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		
		 assertTrue(bankDao.readBank("TestBank"));
		 
	}
	
	@Test
	public void readAllBanksTest() throws SQLException{
		//insert test bank 1, already deleted in teardown
		String insertString = "insert into test_banks (bank_name)"
				+ " values(?)";
		
		try (
				PreparedStatement insertBank = 
				realConnection.prepareStatement(insertString)){
				insertBank.setString(1, "TestBank");
				insertBank.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		// insert testbank2 
		String insertString2 = "insert into test_banks (bank_name)"
				+ " values(?)";
		
		try (
				PreparedStatement insertBank2 = 
				realConnection.prepareStatement(insertString2)){
				insertBank2.setString(1, "TestBank2");
				insertBank2.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		//call readAllBanks and test against expected StringBuilder
		StringBuilder expected = new StringBuilder();
		expected.append("TestBank\nTestBank2\n" );
		
		 assertEquals(bankDao.readAllBanksTest().toString(),expected.toString());
		 
		 //delete test bank 2
		 String deleteString = "delete from test_banks where bank_name = ?";
			try (PreparedStatement deleteBank = 
					realConnection.prepareStatement(deleteString)){
				deleteBank.setString(1, "TestBank2");
				deleteBank.executeUpdate();
			}
			catch(SQLException e){
				e.printStackTrace();
			};
		
		
	}
}
