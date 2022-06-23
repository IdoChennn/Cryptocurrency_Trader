package cryptoTrader.broker;

import java.util.Arrays;

/**
 * 
 * @author Ido
 * 
 *
 */

public class Broker {

	private String name;
	private String[] selectedCypto;
	private String strategy;

	/**
	 * The constructor of broker object
	 * 
	 * @param name           the name of input broker name typed by user
	 * @param selectedCrypto the input coins of each broker
	 * @param strategy       the strategy picked by the broker
	 */
	public Broker(String name, String[] selectedCrypto, String strategy) {
		this.name = name;
		this.selectedCypto = selectedCrypto;
		this.strategy = strategy;
	}

	/**
	 * get the name of the broker
	 * 
	 * @return return the name of the broker
	 */
	public String getName() {
		return name;
	}

	/**
	 * get the coins selected by user for the broker
	 * 
	 * @return return the coins
	 */
	public String[] getSelectedCypto() {
		return selectedCypto;
	}

	/**
	 * get the strategy selected by user for the broker
	 * 
	 * @return return the strategy selected
	 */
	public String getStrategy() {
		return strategy;
	}

	/**
	 * set the name of the broker
	 * 
	 * @param name name of the input broker
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * set the coins of the broker
	 * 
	 * @param selectedCypto broker's coins
	 */
	public void setSelectedCypto(String[] selectedCypto) {
		this.selectedCypto = selectedCypto;
	}

	/**
	 * set the strategy of the broker
	 * 
	 * @param strategy strategy selected for broker
	 */
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	@Override
	/**
	 * toString the broker object in the form as follow
	 * 
	 * @return the toString object of the broker
	 */
	public String toString() {
		return "Broker [name=" + name + ", selectedCypto=" + Arrays.toString(selectedCypto) + ", strategy=" + strategy
				+ "]";
	}

	public boolean equals(Broker obj) {

		if (obj == null || name == null)
			return false;
		if (obj.getName().equals(this.name)) {
			return true;
		}

		return false;
	}

}
