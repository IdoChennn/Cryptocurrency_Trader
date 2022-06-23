package cryptoTrader.perform;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import cryptoTrader.broker.BrokerDaoImp;
import cryptoTrader.priceData.CoinsPrice;
import cryptoTrader.priceData.DataReader;
import cryptoTrader.priceData.PriceDAOImp;
import cryptoTrader.selection.ClientObserver;
import cryptoTrader.selection.CoinObserver;

/**
 * This class implements facade design pattern, and this abstract class also
 * provides a format for rendering components such as barDrawer and tableDrawer
 * 
 * @author Ido
 *
 */
public abstract class VisualizerFacade {
	protected ClientObserver clientObserver;
	protected CoinObserver coinObserver;
	protected BrokerDaoImp brokerDao;
	protected static PriceDAOImp priceData;
	Map<String, String> coinIDMap = new HashMap<>();

	/**
	 * 
	 * @param clientObserver
	 * @param coinObserver
	 * @param brokerDao
	 */

	public VisualizerFacade(ClientObserver clientObserver, CoinObserver coinObserver, BrokerDaoImp brokerDao) {
		this.clientObserver = clientObserver;
		this.brokerDao = brokerDao;
		this.coinObserver = coinObserver;
		coinIDMap.put("BTC", "bitcoin");
		coinIDMap.put("ADA", "cardano");
		coinIDMap.put("ETH", "ethereum");
		setPriceData();

	}

	public VisualizerFacade() {

	}

	/**
	 * This methods will retrieve the price from coinGeko's API by using priceData
	 * package
	 */
	private void setPriceData() {
		if (priceData == null || priceData.readPrices().size() == 0)
			priceData = new PriceDAOImp();
		HashSet<String> coinList = coinObserver.getCoinList();
		DataReader fetcher = new DataReader();
		Double price = 0.0;
		for (String coin : coinList) {
			if (priceData.getPrice(coin) != null)
				continue;

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			price = fetcher.getPriceForCoin(coinIDMap.get(coin), formatter.format(date));

			priceData.addPrice(new CoinsPrice(coin, price));
		}
	}

	/**
	 * drawer of the facade that can be either used by bar or table
	 */
	abstract void draw();
}
