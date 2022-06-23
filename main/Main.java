package cryptoTrader.main;


import cryptoTrader.gui.Proxy;
import cryptoTrader.gui.UIImage;

/**
 * Main class of the program
 * @author Ido
 *
 */
public class Main {
	
	
	
	/**
	 * Calling the Proxy login to display the mainUi
	 * @param args
	 */
	public static void main(String[] args) {
		UIImage main = new Proxy();
		main.displayUI();

	}
	
}
