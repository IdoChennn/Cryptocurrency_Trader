package cryptoTrader.broker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class will pack all the broker object into a single list. Or, we could
 * say it is a container for all input brokers
 * 
 * @author Ido
 *
 */
public class BrokerDaoImp implements BrokerDAO {

	List<Broker> brokers;

	/**
	 * Initializes the arrayList for broker container
	 */
	public BrokerDaoImp() {
		brokers = new ArrayList<Broker>();
	}

	/**
	 * Add new broker into the broker arrayList
	 * 
	 * @param broker the broker that is going to be added into the broker arrayList
	 * 
	 */
	public Broker addBroker(Broker broker) {
		if (broker != null && getBroker(broker.getName()) == null)
			brokers.add(broker);
		return null;
	}

	/**
	 * update the broker currently inside the arrayList
	 * 
	 * @param broker the broker that is going to be updated
	 */
	public Broker updateBroker(Broker broker) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * remove the broker currently inside the arrayList
	 * 
	 * @param broker the broker that is going to be removed
	 */
	public void delete(Broker broker) {
		this.getBroker(broker.getName());
		brokers.remove(this.getBroker(broker.getName()));
	}

	/**
	 * get the broker based on broker's ID from the container arrayList if there is
	 * a match, return the matched broker
	 * 
	 * @param brokerID the ID of broker
	 * @return curBroker the matched broker
	 */
	public Broker getBroker(String brokerID) {
		if (brokerID == null)
			return null;
		Iterator<Broker> theList = this.brokers.iterator();
		while (theList.hasNext()) {
			Broker curBroker = theList.next();
			if (curBroker.getName().equals(brokerID))
				return curBroker;
		}
		return null;
	}

	/**
	 * 
	 */
	public List<Broker> readBrokers() {
		return (ArrayList<Broker>) this.brokers;
	}

	public ArrayList<Broker> getBrokers() {
		return (ArrayList<Broker>) this.brokers;
	}

}
