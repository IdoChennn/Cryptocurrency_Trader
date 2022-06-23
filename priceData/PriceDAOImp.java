package cryptoTrader.priceData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Similar to brokerDAO, this class also implements a sort of container that
 * stores all the coin object.
 * 
 * @author Ido
 *
 */
public class PriceDAOImp implements PriceDAO {

	Map<String, Double> priceMap;

	/**
	 * we will implement coinPrice container by using hashmap data structure.
	 * Initialize it
	 */
	public PriceDAOImp() {
		priceMap = new HashMap<>();
	}

	/**
	 * Access to certain price of coin stored in coinPrice container.
	 */
	public CoinsPrice getPrice(String ID) {
		if (priceMap.containsKey(ID))
			return new CoinsPrice(ID, priceMap.get(ID));
		return null;
	}

	/**
	 * Add price to certain coin stored in coinPrice container
	 */
	public void addPrice(CoinsPrice data) {
		if (data == null)
			return;
		if (getPrice(data.getID()) == null)
			priceMap.put(data.getID(), data.getPrice());
	}

	/**
	 * Access to the content of priceContainer
	 */
	public HashMap<String, Double> readPrices() {
		return (HashMap<String, Double>) this.priceMap;
	}

	/**
	 * Update certain coin's price
	 */
	public void updatePrice(String ID, Double Price) {
		priceMap.put(ID, Price);
	}

	/**
	 * Remove certain coin in the coinPrice container
	 */
	public void deletePrice(String ID) {
		priceMap.remove(ID);

	}

}
