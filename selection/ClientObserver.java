package cryptoTrader.selection;

import java.util.ArrayList;

import java.util.List;

import cryptoTrader.broker.Broker;

/**
 * This class implements client observer. That is, whenever this client observer
 * gets notified, it will update the clientList(brokerNameList) that is going to
 * be used by rendering server
 * 
 * @author Ido
 *
 */

public class ClientObserver extends SelectionObserver {

	private List<String> clientList;

	/**
	 * initialize the container for broker names
	 * 
	 * @param subject
	 */
	public ClientObserver(UserSelection subject) {
		this.subject = subject;
		this.subject.attach(this);
		clientList = new ArrayList<String>();
	}

	/**
	 * update the broker name container.
	 */
	public void update() {
		Broker curBroker = subject.getState();
		if (clientList.contains(curBroker.getName()))
			clientList.remove(curBroker.getName());
		else
			clientList.add(curBroker.getName());
	}

	/**
	 * get entire broker name container
	 * 
	 * @return return the container
	 */
	public List<String> getClientList() {
		return clientList;
	}

	/**
	 * set broker name container
	 * 
	 * @param clientList current broker name container
	 */

	public void setClientList(List<String> clientList) {
		this.clientList = clientList;
	}

}
