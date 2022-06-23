package cryptoTrader.strategy;

import cryptoTrader.priceData.*;

import cryptoTrader.broker.Broker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

/**
 * This class implements strategy design pattern, where Strategy server A,B and
 * C are all the child of this abstract strategy class and each of these
 * strategy server are different but follow the same format
 * 
 * @author Ido
 *
 */
public abstract class StrategyAbstract {

	protected HashSet<String> coinList;
	protected PriceDAOImp data;
	protected DataReader fetcher;

	public StrategyAbstract() {
	}
	
	public StrategyAbstract(PriceDAOImp priceData) {
		this.data = priceData;
	}

	public abstract tradingStatus makeStrategy(Broker broker, PriceDAOImp data);

	public abstract boolean checkBrokerList(Broker broker);

}