package Project0_2.controllers;

import Project0_2.dao.AccountDao;
import io.javalin.http.Context;

public class AccountController {
	
	AccountDao accountDao = new AccountDao();
	
	
	public void createAccount(Context ctx) {
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		String bankName = ctx.formParam("bankName");
		ctx.html(accountDao.insertAccount(username, bankName, password));
		}
	
	public void deleteAccount(Context ctx) {
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		ctx.html(accountDao.removeUser(username, password));
	}
	
	public void deposit(Context ctx) {
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		if (accountDao.authenticate(username, password)) {
			double deposit = Double.parseDouble(ctx.formParam("deposit"));
			if(accountDao.deposit(username, password, deposit)) {
				ctx.html("Succesful deposit, New Balance: "+ accountDao.checkBalance(username, password) );
				return;
			}
		}
		ctx.html("Invalid credentials");
	}
	
	public void withdraw(Context ctx) {
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		double withdrawal = Double.parseDouble(ctx.formParam("withdrawal"));
		if (accountDao.authenticate(username, password)) {
			if (Double.parseDouble(accountDao.checkBalance(username, password)) >= withdrawal) {
				accountDao.withdraw(username, password, withdrawal);
				ctx.html("Succesful withdrawal, New Balance: "+ accountDao.checkBalance(username, password));
				return;
			}
			else {
				ctx.html("Insufficent funds");
				return;
			}
		}
		else {
			ctx.html("Invalid credentials");
		}
		
	}
	
	public void transfer(Context ctx) {
		String payUser = ctx.formParam("payingUser");
		String payUserPass = ctx.formParam("payingUserPassword");
		String recvUser = ctx.formParam("receivingUser");
		double amount = Double.parseDouble(ctx.formParam("amount"));
		ctx.html(accountDao.transfer(payUser, payUserPass, recvUser, amount));
		
	}
	
	public void checkBalance(Context ctx) {
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		if(accountDao.checkBalance(username, password) == null) {
			ctx.html("Invalid credentials");
			return;
		}
		ctx.html("Account balance: " + accountDao.checkBalance(username, password));
	}
}

