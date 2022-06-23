package cryptoTrader.gui;

import javax.swing.table.DefaultTableModel;

import cryptoTrader.broker.*;

/**
 * This class will implement the feature of checking the correctness of
 * everything user typed in input window validate duplicated broker, null input
 * in any field
 * 
 * @author Ido
 *
 */
public class BrokerListCheckServer {

	private boolean brokerStatus;// correctness of the broker input, false -> pass, true -> not pass
	private String client;
	private String[] cryptoList;
	private String strategy;

	/**
	 * initialize the flag to be false at start
	 */
	public BrokerListCheckServer() {
		brokerStatus = false;
	}

	// check duplicated broker
	// each time when user click addrow, two operation will be performed:
	// 1.check the correctness of the current row
	// 2.add the current row into the brokers arrayList
	/**
	 * This method will check if the name of the broker is repeated
	 * 
	 * @param broker  the current input broker
	 * @param brokers the container (arrayList) of all previous input brokers
	 * @return
	 */
	public boolean checkBroker(Broker broker, BrokerDaoImp brokers) {

		if (broker == null)
			return false;
		// not find broker
		if (brokers.getBroker(broker.getName()) == null)
			brokerStatus = true;
		else
			brokerStatus = false;
		return brokerStatus;
	}
	// the following three method will check if the emptiness of any of the three
	// field

	/**
	 * This method will check if the field of broker name is empty
	 * 
	 * @param count certain row
	 * @param dtm   certain column
	 * @return the correctness of the row
	 */
	// check client is null or not
	public boolean checkClient(int count, DefaultTableModel dtm) {// this method will read every row of input window

		Object traderObject = dtm.getValueAt(count, 0);
		if (traderObject == null) {
			brokerStatus = false;
		} else {
			brokerStatus = true;
			client = traderObject.toString();
		}
		return brokerStatus;
	}

	/**
	 * This method will check if the field of coins is empty
	 * 
	 * @param count certain row
	 * @param dtm   certain column
	 * @return the correctness of the row
	 */
	// check crypto list is null or not
	public boolean checkCrypto(int count, DefaultTableModel dtm) {
		Object traderObject = dtm.getValueAt(count, 1);
		if (traderObject == null) {
			brokerStatus = false;
		} else {
			brokerStatus = true;
			cryptoList = traderObject.toString().split(",");
		}
		return brokerStatus;
	}

	/**
	 * This method will check if the field of strategy is empty
	 * 
	 * @param count
	 * @param dtm
	 * @return the correctness of the row
	 */
	// check strategy is null or not
	public boolean checkStrategy(int count, DefaultTableModel dtm) {
		Object strategyObject = dtm.getValueAt(count, 2);
		if (strategyObject == null) {
			brokerStatus = false;
		} else {
			brokerStatus = true;
			strategy = strategyObject.toString();
		}
		return brokerStatus;
	}

	/**
	 * This method will return the correctness of the broker (row)
	 * 
	 * @return the correctness flag
	 */
	public boolean isBrokerStatus() {
		return brokerStatus;
	}
	// the following three methods will get the specific field of the broker (row)

	/**
	 * get coins
	 * 
	 * @return coin list
	 */
	public String[] getCryptoList() {
		return cryptoList;
	}

	/**
	 * get strategy
	 * 
	 * @return strategy
	 */
	public String getStrategy() {
		return strategy;
	}

	/**
	 * get the broker name
	 * 
	 * @return broker name
	 */
	public String getClient() {
		return this.client;
	}
}
