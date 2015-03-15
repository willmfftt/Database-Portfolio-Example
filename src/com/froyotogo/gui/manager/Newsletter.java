/**
 * Newsletter for sending to customers
 * 
 * @author William Moffitt <willmfftt@hotmail.com>
 * @version 1.0 12/10/13
 */

package com.froyotogo.gui.manager;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Newsletter {

	public static void showGUI() {

		final String[] options = { "Send", "Cancel" };
		final JPanel panel = new JPanel();
		final JTextArea letterArea = new JTextArea(30, 50);

		panel.add(letterArea);

		JOptionPane.showOptionDialog(null, panel, "Newsletter",
				JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				options, options[0]);

	}

}
