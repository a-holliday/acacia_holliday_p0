package Project0BankingApp.service;

import Project0BankingApp.pojos.User;
/**
 * 
 * @author Acacia
 * Interface UserService creates contract for methods to create user, 
 * authenticate user and remove user
 *
 */

public interface UserService {
	public void createUser(String username, String password, 
			String bankName);
	
	public User authenticate(String username, String password);
	
	public void removeUser(String username, String password);
	
	

}
