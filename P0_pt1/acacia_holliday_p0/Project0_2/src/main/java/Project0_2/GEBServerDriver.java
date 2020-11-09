package Project0_2;

import Project0_2.controllers.AccountController;
import Project0_2.controllers.BankController;
import Project0_2.controllers.TransactionController;
import io.javalin.Javalin;
import org.apache.log4j.Logger;


public class GEBServerDriver {
	
	private static Logger log = Logger.getRootLogger();
	private static BankController bankController = new BankController();
	private static AccountController accountController = new AccountController();
	private static TransactionController transactController = new TransactionController();
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(9090); //sets up and starts our server
		log.info("Application Started");
		app.get("/geb", ctx -> ctx.html("Welcome to Good Enough Banking"));
		app.post("geb/createBank", ctx -> bankController.createBank(ctx));
		app.get("/geb/banks", ctx -> bankController.getAllBanks(ctx));
		app.post("/geb/createAccount", ctx -> accountController.createAccount(ctx));
		app.delete("/geb/deleteAccount", ctx -> accountController.deleteAccount(ctx));
		app.get("/geb/getBalance", ctx -> accountController.checkBalance(ctx));
		app.patch("/geb/deposit", ctx -> accountController.deposit(ctx));
		app.patch("/geb/withdrawal", ctx -> accountController.withdraw(ctx));
		app.patch("/geb/transfer", ctx -> accountController.transfer(ctx));
		app.get("/geb/printStatement", ctx -> transactController.printStatement(ctx));
		
	}


}
