package cryptoTrader.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

/**
 * This class is the Proxy login server by using Proxy design pattern
 * 
 * @author Ido
 *
 */
public class Proxy implements UIImage {

	private MainUI realUi;

	/**
	 * This method will get mainUi singleton object
	 */
	public Proxy() {
		realUi = MainUI.getInstance();
	}

	/**
	 * Display the mainUi by using the interface provided by mainUi class
	 */
	public void displayUI() {
		Login myLogin = new Login();
		LoginCheckServer myCheckServer = new LoginCheckServer();
		myCheckServer.isValid(myLogin, realUi);

	}

}
