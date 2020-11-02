package Project0ServiceTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Project0BankingApp.service.BankService;
import Project0BankingApp.service.BankServiceImpl;

public class BankServiceTest {
	BankService bankService = new BankServiceImpl();


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		bankService.createBank("Chase");
		bankService.createAccount("Acacia", bankService.getBankFromName("Chase"));
	}

	@After
	public void tearDown() throws Exception {
		bankService.removeBank(bankService.getBank(1));
	}

	@Test
	/**
	 * Want to assert that created Bank and created account exist
	 */
	public void testBankWithAccount() {
		assertEquals("Username acacia should match a created account of the same name",
				bankService.getAccount(bankService.getBankFromName("Chase"), "Acacia")
				.getUsername(), "Acacia");
	}
	
	@Test
	/**
	 * Test bank existing in cache
	 */
	public void testBankExists() {
		assertEquals("Bank 1 matches with bankID 1", bankService.getBank(1).getBankID(), 1);
	}
	/**
	 * Test for non-existing bank
	 */
	
	@Test
	public void testBankDoesNotExist() {
		assertEquals("Bank 2 should return null", bankService.getBankFromName("Wells Fargo"), null);
		
	}
	/**
	 * Test for non-existing account
	 */
	
	@Test
	public void testInvalidAccountWithValidBank() {
		assertEquals("Account should return null", bankService.getAccount(bankService.getBankFromName("Chase"),"John"), null);
	}

}
