package cryptoTrader.priceData;

import java.util.*;
/**
 * This class is the interface between strategy servers and priceDAO object
 * @author Ido
 *
 */
public interface PriceDAO {

	public CoinsPrice getPrice(String ID);
	public void addPrice(CoinsPrice data);
	public HashMap<String, Double> readPrices();	
	public void updatePrice(String ID, Double Price);
	public void deletePrice(String ID);
	
	
}
