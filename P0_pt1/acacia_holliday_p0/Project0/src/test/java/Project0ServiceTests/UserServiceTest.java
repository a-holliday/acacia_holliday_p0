package Project0ServiceTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import Project0BankingApp.service.UserServiceImpl;

public class UserServiceTest {
	UserServiceImpl userService = new UserServiceImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		userService.getBankService().createBank("Chase");
		userService.createUser("Acacia", "1234", "Chase");
		userService.getBankService().createBank("Wells Fargo");
		userService.createUser("Kirby", "password", "Wells Fargo");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIfNewlyCreatedUserCanGetBalance() {
		assertEquals(userService.authenticate("Acacia", "1234").getAccount().getBalance(), 0.0, 0.001);
	}
	
	@Test
	public void testIfUserCanDepositAndWithdraw() {
		userService.authenticate("Acacia", "1234").getAccount().deposit(100.0);
		assertEquals(userService.authenticate("Acacia", "1234")
				.getAccount().getBalance(), 100.0, 0.001);
		userService.authenticate("Acacia", "1234").getAccount().withdrawal(50.0);
		assertEquals(userService.authenticate("Acacia", "1234")
				.getAccount().getBalance(), 50.0, 0.001);

	
	}
	
	
	
	@Test
	public void testIfUserCanTransfer() {
		userService.authenticate("Acacia", "1234").getAccount().deposit(100.0);
		userService.getBankService().transferAccount
		(userService.getBankService().getBankFromName("Wells Fargo"), "Kirby",
				userService.getBankService().getBankFromName("Chase"), "Acacia", 50.0);
		
		assertEquals(userService.getBankService().getAccount
				(userService.getBankService()
						.getBankFromName("Wells Fargo"), "Kirby").getBalance(), 50.0, 0.001);
	}
	
  @Test
  public void testUserDidntTransferIncorrectCredentials() {
		userService.authenticate("Acacia", "1234").getAccount().deposit(100.0);
		userService.getBankService().transferAccount(userService.getBankService().getBankFromName("Chase"), "Kirby", 
				userService.getBankService().getBankFromName("Chase"), "Acacia", 10.0);
		assertEquals((userService.getBankService().getAccount
				(userService.getBankService()
						.getBankFromName("Wells Fargo"), "Kirby")).getBalance(), 0.0, 0.001);
		assertEquals((userService.authenticate("Acacia", "1234").getAccount().getBalance()), 100.0, 0.001);

  }
  
  @Test
  public void insufficientFundsTransfer() {
	  System.out.println("User Balance before transfer " + userService.authenticate("Acacia", "1234").getAccount().getBalance());

	  userService.getBankService().transferAccount
		(userService.getBankService().getBankFromName("Wells Fargo"), "Kirby",
				userService.getBankService().getBankFromName("Chase"), "Acacia", 50.0);
		assertEquals((userService.authenticate("Kirby", "password").getAccount().getBalance()), 0.0, 0.001);

	  
  }

}
