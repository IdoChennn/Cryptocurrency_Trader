package cryptoTrader.selection;

import java.util.*;

import cryptoTrader.broker.Broker;

/**
 * This class implements observer design pattern. When there is new incoming
 * brokerInput (the calling of setState method), this class will notify client
 * observer and coin observer that they need to update themselves. The reason
 * why we have this selection package is that upon user press addrow button, not
 * only strategy servers will receive brokerInput, but also the rendering server
 * will receive a copy of brokerInput to let rendering server to pre-process the
 * result. Note that, rendering server will only receive brokerInput from
 * observers, and to reduce coupling the copy of brokerInput for rendering
 * server will be stored in another container rather than directly send
 * BrokerDao object or PriceDAO object to rendering server.
 * 
 * @author Ido
 *
 */

public class UserSelection {

	private List<SelectionObserver> myObserver;
	private Broker state;

	/**
	 * The container that stores all child observers. In our case, we only have two
	 * child observers - Client and Coin.
	 * object to rendering server
	 */
	public UserSelection() {
		myObserver = new ArrayList<>();
	}

	public Broker getState() {
		return state;
	}

	/**
	 * the constructor for set the state of inputBroker
	 * 
	 * @param state new inputBroker
	 */
	public void setState(Broker state) {
		if (state == null)
			return;
		this.state = state;
		notifyObservers();
	}

	/**
	 * Add new observers
	 * 
	 * @param observer new observers
	 */
	public void attach(SelectionObserver observer) {
		myObserver.add(observer);
	}

	/**
	 * running a loop to notify all the attached observers. In our case, we will ask
	 * client and coin observer to update themselves
	 */
	public void notifyObservers() {
		for (SelectionObserver observer : myObserver) {
			observer.update();
		}
	}

}
