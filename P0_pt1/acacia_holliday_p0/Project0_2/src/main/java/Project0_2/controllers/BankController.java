package Project0_2.controllers;

import Project0_2.dao.BankDao;
import io.javalin.http.Context;


public class BankController {
	BankDao bankDao = new BankDao();
	
	public void createBank(Context ctx) {
		String bankName = ctx.formParam("bankName");
		
			if(bankDao.insertBank(bankName)) {
				ctx.html("Created bank " + bankName);

			}

	}
	
	public void getAllBanks(Context ctx) {
		StringBuilder response = new StringBuilder("No available banks");
		if( bankDao.readAllBanks() != null) {
			response = bankDao.readAllBanks();
		}
		ctx.html(response.toString());
	}

}
