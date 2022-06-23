package cryptoTrader.strategy;

import java.util.Arrays;

import java.util.HashSet;

import cryptoTrader.priceData.*;

import cryptoTrader.broker.Broker;

/**
 * This class implements concrete strategy server A extended from the abstraction class of
 * strategy
 * 
 * @author Ido
 *
 */

public class StrategyA extends StrategyAbstract {

	public StrategyA() {
	}

	/**
	 * Pass the price of coins to strategy A
	 * 
	 * @param priceData
	 */
	public StrategyA(PriceDAOImp priceData) {
		super(priceData);
	}

	/**
	 * Strategy states: If the price of BTC is less or equal 50,000$ and the price
	 * of ADA more than 2$ then buy 10 ADA coins, else sell 10 ADA coins
	 * 
	 * @param broker brokerInput object
	 * @param data   coinPrice object
	 */
	public tradingStatus makeStrategy(Broker broker, PriceDAOImp data) {
		CoinsPrice btc = data.getPrice("BTC");
		CoinsPrice ada = data.getPrice("ADA");
		if (btc == null || ada == null)
			return null;
		if (!checkBrokerList(broker))
			return null;
		if (btc.getPrice() <= 50000 && ada.getPrice() > 2) {
			return new tradingStatus("ADA", true, 10);
		} else
			return new tradingStatus("ADA", false, 10);
	}

	/**
	 * This method will check if enough coins types is contained in brokerInput
	 * object
	 * 
	 * @param broker brokerInput object
	 * @return the result of existence of coins BTC and ADA
	 */
	public boolean checkBrokerList(Broker broker) {// Adequate input information

		boolean adaCheck = Arrays.stream(broker.getSelectedCypto()).anyMatch("ADA"::equals);
		boolean btcCheck = Arrays.stream(broker.getSelectedCypto()).anyMatch("BTC"::equals);
		return adaCheck && btcCheck;
	}

}
