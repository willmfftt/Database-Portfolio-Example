package com.froyotogo.gui.manager;

import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.froyotogo.User;
import com.froyotogo.db.databases.UserDB;
import com.froyotogo.encrypt.Encryption;
import com.froyotogo.encrypt.Salt;

/**
 * Screen for manager to add new users to the database
 * 
 * @author William Moffitt <willmfftt@gmail.com>
 * @version 1.0 12/10/13
 */

public class UserAddScreen {

	public static boolean showGUI() {

		String[] options = { "Add", "Cancel" };

		JPanel userPane = new JPanel();
		JPanel passPane = new JPanel();
		JPanel typePane = new JPanel();
		JPanel salaryPane = new JPanel();
		JPanel panel = new JPanel();

		JLabel userLabel = new JLabel("Username:");
		JLabel passLabel = new JLabel("Password:");
		JLabel typeLabel = new JLabel("Type:");
		JLabel salaryLabel = new JLabel("Salary:");

		JTextField userField = new JTextField(10);

		JPasswordField passField = new JPasswordField(10);

		JComboBox<String> typeSelection = new JComboBox<String>(
				new DefaultComboBoxModel<String>(new String[] { "Employee", "Manager" }));
		
		DecimalFormat fmt = new DecimalFormat("##.##");
		JFormattedTextField salaryField = new JFormattedTextField(fmt);
		salaryField.setColumns(10);
		
		userPane.add(userLabel);
		userPane.add(userField);
		passPane.add(passLabel);
		passPane.add(passField);
		typePane.add(typeLabel);
		typePane.add(typeSelection);
		salaryPane.add(salaryLabel);
		salaryPane.add(salaryField);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(userPane);
		panel.add(passPane);
		panel.add(typePane);
		panel.add(salaryPane);
		
		int option = JOptionPane.showOptionDialog(null, panel, "Add User", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		
		if (option == 0) {
			
			String username = userField.getText();
			String password = String.valueOf(passField.getPassword());
			byte salt = Salt.generateSalt();
			String type = typeSelection.getSelectedItem().equals("Employee") ? "e" : "m";
			String salary = salaryField.getText();
			
			// Encrypt password
			password = Encryption.encrypt(password, salt);
			
			User user = new User(username, password, type, String.valueOf(salt), salary);
			
			boolean success = false;
			
			try {
				
				success = UserDB.createUser(user);
				
			} catch (SQLException e) {
				
				JOptionPane.showMessageDialog(null, "Couldn't add user");
				
			}
			
			if (!success)
				JOptionPane.showMessageDialog(null, "Couldn't add user");
			
			return success;
			
		}
		
		return false;

	}

}
