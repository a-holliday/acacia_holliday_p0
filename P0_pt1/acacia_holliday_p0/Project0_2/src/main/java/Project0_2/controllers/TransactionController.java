package Project0_2.controllers;

import Project0_2.dao.TransactionDao;
import io.javalin.http.Context;

public class TransactionController {
	
	TransactionDao transactDao = new TransactionDao();
	
	public void printStatement(Context ctx) {
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		ctx.html(transactDao.printStatement(username, password));
		
	}

}
