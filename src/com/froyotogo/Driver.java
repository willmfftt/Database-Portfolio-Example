package com.froyotogo;

import com.froyotogo.db.DBConnection;
import com.froyotogo.db.databases.CustomerDB;
import com.froyotogo.db.databases.Database;
import com.froyotogo.db.databases.UserDB;
import com.froyotogo.gui.LoginScreen;

/**
 * Starting point of the program
 *
 * @author William Moffitt <willmfftt@gmail.com>
 * @version 1.0 12/10/13
 */
public class Driver {
    
    public static void main(String[] args) {
    	
    	DBConnection connect = new DBConnection();
    	Database userDB = new UserDB(connect);
    	userDB.createDB();
    	
    	Database customerDB = new CustomerDB(connect);
    	customerDB.createDB();
    	
    	LoginScreen.showLoginScreen();
        
    }

}
