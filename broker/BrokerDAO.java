package cryptoTrader.broker;

import java.util.*;

/**
 * This is the interface of broker object operation
 * 
 * @author Ido
 *
 */

public interface BrokerDAO {

	public Broker addBroker(Broker broker);

	public Broker updateBroker(Broker broker);

	public void delete(Broker broker);

	public Broker getBroker(String brokerID);

	public List<Broker> readBrokers();

}
