package cryptoTrader.gui;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is basically a login server that checks user's input credentials
 * The two vars IDString and pwDString represent the username and user password
 * respectively
 * 
 * @author Ido
 *
 */
public class LoginCheckServer {

	boolean status;
	// these two vars are the user's credential
	// You have to enter both of them in input window to login
	private final String IDString = "westernCS";// username
	private final String pwDString = "cs2212";// password

	/**
	 * This method will do the user validation job and display the main UI (note
	 * that the main UI is not really displayed by this class, rather the main UI is
	 * displayed the Proxy login server. Please refer to Proxy.java)
	 * 
	 * @param myLogin 
	 * @param realUi the singleton object of MainUi class
	 */
	public void isValid(Login myLogin, MainUI realUi) {
		myLogin.blogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File file = new File("userPass.txt");
					Scanner scan = new Scanner(file);
					;
					String line = null;
					FileWriter filewrite = new FileWriter(file, true);

					String usertxt = " ";
					String passtxt = " ";
					String usernames = myLogin.txuser.getText();
					String pwd = myLogin.pass.getText();
					System.out.println("userInput: " + usernames);
					System.out.println("pwdInput: " + pwd);
					if (usernames.equals(IDString) && pwd.equals(pwDString))
						status = true;
					else
						status = false;

					if (status) {
						System.out.println("VALID USER");
						realUi.displayUI();
					} else {
						System.out.println("INVALID USER");

					}

					while (scan.hasNext()) {
						usertxt = scan.nextLine();
						passtxt = scan.nextLine();
					}

				}

				catch (IOException d) {
					d.printStackTrace();
				}

			}

		});

	}
}
