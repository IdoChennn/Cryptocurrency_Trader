package cryptoTrader.strategy;

import java.util.Arrays;
import cryptoTrader.priceData.*;
import cryptoTrader.broker.Broker;

/**
 * This class implements concrete strategy server B
 * 
 * @author Ido
 *
 */
public class StrategyB extends StrategyAbstract {

	public StrategyB() {

	}

	/**
	 * Pass the price of coins to strategy B
	 * 
	 * @param priceData
	 */
	public StrategyB(PriceDAOImp data) {
		super(data);
	}

	/**
	 * Strategy B states: If the price of ADA is less or equal 2$ and the price of
	 * ETH is less that 3,500$ then buy ADA coins worth of 1,000$
	 * 
	 * @param broker brokerInput object
	 * @param data   coinPrice object
	 * @return return the result of the trade
	 */
	public tradingStatus makeStrategy(Broker broker, PriceDAOImp data) {
		CoinsPrice eth = data.getPrice("ETH");
		CoinsPrice ada = data.getPrice("ADA");
		Double quantity;
		quantity = 1000 / ada.getPrice();
		if (eth == null || ada == null)
			return null;
		if (!checkBrokerList(broker))
			return null;
		if (eth.getPrice() <= 3500 && ada.getPrice() <= 2) {
			return new tradingStatus("ADA", true, quantity);
		} else
			return new tradingStatus("ADA", false, quantity);
	}

	/**
	 * This method will check if enough coins types is contained in brokerInput
	 * object
	 * 
	 * @param broker brokerInput object
	 * @return the result of existence of coins ADA and ETH
	 */
	public boolean checkBrokerList(Broker broker) {
		boolean adaCheck = Arrays.stream(broker.getSelectedCypto()).anyMatch("ADA"::equals);
		boolean ethCheck = Arrays.stream(broker.getSelectedCypto()).anyMatch("ETH"::equals);
		return adaCheck && ethCheck;
	}

}
