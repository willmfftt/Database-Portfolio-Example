package com.froyotogo.gui;

import java.sql.Connection;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.froyotogo.db.CRUD;
import com.froyotogo.db.DBConnection;
import com.froyotogo.db.databases.UserDB;
import com.froyotogo.encrypt.Encryption;
import com.froyotogo.encrypt.Salt;

/**
 * Form for changing the logged in users password
 * 
 * @author William Moffitt <willmfftt@gmail.com>
 * @version 1.0 12/10/13
 */

public class ChangePassword {

	public static void showChangePasswordScreen() {

		final String[] options = { "OK", "Cancel" };

		JPanel oldPassPane = new JPanel();
		JPanel newPassPane = new JPanel();
		JPanel confirmPane = new JPanel();

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel oldPassLabel = new JLabel("Old Password:");
		JLabel newPassLabel = new JLabel("New Password:");
		JLabel confirmLabel = new JLabel("Confirm:");

		JPasswordField oldPassField = new JPasswordField(10);
		JPasswordField newPassField = new JPasswordField(10);
		JPasswordField confirmField = new JPasswordField(10);

		oldPassPane.add(oldPassLabel);
		oldPassPane.add(oldPassField);

		newPassPane.add(newPassLabel);
		newPassPane.add(newPassField);

		confirmPane.add(confirmLabel);
		confirmPane.add(confirmField);

		panel.add(oldPassPane);
		panel.add(newPassPane);
		panel.add(confirmPane);

		int option = JOptionPane.showOptionDialog(null, panel,
				"Change Password", JOptionPane.NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		
		if (option == 0) {
			
			String oldPass = String.valueOf(oldPassField.getPassword());
			String newPass = String.valueOf(newPassField.getPassword());
			String confirm = String.valueOf(confirmField.getPassword());
			
			String oldPassEnc = Encryption.encrypt(oldPass, Byte.valueOf(LoginScreen.user.getSalt()));
			
			if (oldPassEnc.equals(LoginScreen.user.getPassword()) && newPass.equals(confirm)) {
				
				byte salt = Salt.generateSalt();
				String newEncPass = Encryption.encrypt(newPass, salt);
				
				String sql = "UPDATE " + UserDB.DB_NAME + "." + UserDB.TABLE_NAME
						+ " SET " + UserDB.PASSWORD + "='" + newEncPass + "', "
						+ UserDB.SALT + "='" + salt 
						+ "' WHERE " + UserDB.USERNAME + "='" + LoginScreen.user.getUsername() + "'";
				
				DBConnection connect = new DBConnection();
				Connection connection = connect.connect();
				CRUD crud = new CRUD(connection);
				
				boolean success = crud.update(sql);
				connect.disconnect();
				
				if (success)				
					JOptionPane.showMessageDialog(null, "Password Successfully Changed");
				else {
					
					JOptionPane.showMessageDialog(null, "Password Couldn't Be Changed");
					
				}
				
			}
			
			else
				JOptionPane.showMessageDialog(null, "Password Couldn't Be Changed");
			
		}

	}

}
