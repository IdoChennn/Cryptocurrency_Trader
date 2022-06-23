package cryptoTrader.gui;

import javax.swing.*;
import java.io.*;


/**
 * This class sets the appearance of actual login window
 * 
 * @author Ido
 *
 */
public class Login extends JFrame {
	JButton blogin;
	JPanel loginpanel;
	JTextField txuser;
	JTextField pass;
	JButton newUSer;
	JLabel username;
	JLabel password;
	String usernames;
	String pwd;

	public Login() {
		super("Login CryptocurrencyTrader");

		blogin = new JButton("Login");
		loginpanel = new JPanel();
		txuser = new JTextField(15);
		pass = new JPasswordField(15);
		username = new JLabel("User : ");
		password = new JLabel("Pass : ");

		setSize(300, 200);
		setLocation(500, 280);
		loginpanel.setLayout(null);

		txuser.setBounds(70, 30, 150, 20);
		pass.setBounds(70, 65, 150, 20);
		blogin.setBounds(110, 100, 80, 20);
		username.setBounds(20, 28, 80, 20);
		password.setBounds(20, 63, 80, 20);

		loginpanel.add(blogin);
		loginpanel.add(txuser);
		loginpanel.add(pass);
		loginpanel.add(username);
		loginpanel.add(password);

		getContentPane().add(loginpanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		Writer writer = null;
		File check = new File("userPass.txt");
		if (check.exists()) {

			// Checks if the file exists. will not add anything if the file does exist.
		} else {
			try {
				File texting = new File("userPass.txt");
				writer = new BufferedWriter(new FileWriter(texting));
				writer.write("message");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}