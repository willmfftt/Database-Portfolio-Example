package com.froyotogo.gui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.froyotogo.User;
import com.froyotogo.db.CRUD;
import com.froyotogo.db.DBConnection;
import com.froyotogo.db.databases.UserDB;
import com.froyotogo.encrypt.Encryption;
import com.froyotogo.gui.employee.EmployeeMenu;
import com.froyotogo.gui.manager.ManagerMenu;

/**
 * Login screen
 *
 * @author William Moffitt <willmfftt@gmail.com>
 * @version 1.0 12/10/13
 */
public class LoginScreen {
	
	private static String userType;
	private static String username;
	private static byte salt;
	
	public static User user;
    
    public static void showLoginScreen() {
        
        String password;

        final String[] options = {"OK", "Cancel"};

        JPanel userPane = new JPanel();
        JPanel passPane = new JPanel();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel userLabel = new JLabel("Enter Username: ");
        JTextField userField = new JTextField(10);
        userPane.add(userLabel);
        userPane.add(userField);

        JLabel passLabel = new JLabel("Enter Password: ");
        JPasswordField passField = new JPasswordField(10);
        passPane.add(passLabel);
        passPane.add(passField);

        panel.add(userPane);
        panel.add(passPane);

        int option = JOptionPane.showOptionDialog(null, panel, "FroYo ToGo - Login", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        
        if (option == 0) {
            
            username = userField.getText();
            password = String.valueOf(passField.getPassword());
            
            boolean verified = checkUser(username, password);
            
            if (verified) {
            	
            	if (userType.equals("m"))
            		ManagerMenu.showGUI();
            	else
            		EmployeeMenu.showGUI();
            	
            }
            
            else {
            	
            	JOptionPane.showMessageDialog(null, "Login Failed, Please Try Again");
            	showLoginScreen();
            	
            }
            
        }
        
        else
        	System.exit(0);

    }
    
    private static boolean checkUser(String username, String password) {
    	
    	DBConnection connect = new DBConnection();
    	Connection connection = connect.connect();
    	CRUD crud = new CRUD(connection);
    	
    	// Pull password and salt from database for entered username
    	String sql = "SELECT " + UserDB.PASSWORD + ", " + UserDB.SALT + ", " + UserDB.TYPE + " FROM " + UserDB.DB_NAME + "." + UserDB.TABLE_NAME
    			+ " WHERE " + UserDB.USERNAME + " = '" + username + "'";
    	ResultSet rs = crud.read(sql);
    	
    	salt = 0;
    	String dbPass = "";
    	
		try {
			
			if (rs.next()) {
				
				salt = rs.getByte(UserDB.SALT);
				dbPass = rs.getString(UserDB.PASSWORD);
				userType = rs.getString(UserDB.TYPE);
			
			}
			
		} catch (SQLException e) {
			
			System.err.println("Error getting salt from database: " + e.toString());
			
		}
    	
    	// Encrypt entered password with salt, check if matches
    	String encPass = Encryption.encrypt(password, salt);
    	
    	if (dbPass.equals(encPass)) {
    		
    		user = new User(username, encPass, userType, String.valueOf(salt), "");
    		
    	}
        
        return dbPass.equals(encPass);
        
    }
    
}
