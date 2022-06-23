package cryptoTrader.selection;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;

import cryptoTrader.broker.Broker;

/**
 * This class implements coin observer. Note that this observer will create a
 * coin container that only stores the ID of certain coin that is going to be
 * used by rendering server.
 * 
 * @author Ido
 *
 */

public class CoinObserver extends SelectionObserver {

	private HashSet<String> coinList;

	public CoinObserver(UserSelection subject) {
		this.subject = subject;
		this.subject.attach(this);
		coinList = new HashSet<String>();
	}

	/**
	 * update coins in coin ID container
	 */
	public void update() {
		Broker curBroker = subject.getState();
		if (curBroker.getSelectedCypto() == null)
			return;
		for (String curCoin : curBroker.getSelectedCypto()) {// get broker's name from brokerDao object
			coinList.add(curCoin);
		}
	}

	/**
	 * get entire coin ID container
	 * 
	 * @return coin ID contaienr
	 */
	public HashSet<String> getCoinList() {
		return coinList;
	}

	/**
	 * set coin ID container
	 * 
	 * @param coinList current coin ID container
	 */
	public void setCoinList(HashSet<String> coinList) {
		this.coinList = coinList;
	}
}
