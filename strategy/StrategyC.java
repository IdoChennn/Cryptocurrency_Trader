package cryptoTrader.strategy;

import java.util.Arrays;
import java.util.HashSet;

import cryptoTrader.priceData.*;
import cryptoTrader.broker.Broker;

/**
 * This class implements concrete strategy server B
 * 
 * @author Ido
 *
 */
public class StrategyC extends StrategyAbstract {
	public StrategyC() {

	}

	/**
	 * Pass the price of coins to strategy C
	 * 
	 * @param priceData
	 */
	public StrategyC(PriceDAOImp data) {
		super(data);
	}

	/**
	 * If the price of BTC is less or equal 50000$ and the price of ETH is less that
	 * 3,500$ then buy ADA coins worth of 1,000$
	 */
	public tradingStatus makeStrategy(Broker broker, PriceDAOImp data) {
		CoinsPrice btc = data.getPrice("BTC");
		CoinsPrice eth = data.getPrice("ETH");
		CoinsPrice ada = data.getPrice("ADA");
		double quantity;
		if (eth == null || ada == null || btc == null)
			return null;
		if (!checkBrokerList(broker))
			return null;
		quantity = 1000 / ada.getPrice();
		if (btc.getPrice() <= 50000 && eth.getPrice() <= 3500) {

			return new tradingStatus("ADA", true, quantity);
		} else
			return new tradingStatus("ADA", true, quantity);
	}

	/**
	 * This method will check if enough coins types is contained in brokerInput
	 * object
	 * 
	 * @param broker brokerInput object
	 * @return the result of existence of coins ADA and ETH
	 */
	public boolean checkBrokerList(Broker broker) {
		boolean btcCheck = Arrays.stream(broker.getSelectedCypto()).anyMatch("BTC"::equals);
		boolean ethCheck = Arrays.stream(broker.getSelectedCypto()).anyMatch("ETH"::equals);
		return btcCheck && ethCheck;
	}

}
