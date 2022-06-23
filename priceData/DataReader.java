package cryptoTrader.priceData;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * This class will get price from coinGeko API. Data fetcher
 * 
 * @author Ido
 *
 */
public class DataReader {
	
	/**
	 * 
	 * @param id
	 * @param date
	 * @return
	 */
	private JsonObject getDataForCrypto(String id, String date) {

		String urlString = String.format("https://api.coingecko.com/api/v3/coins/%s/history?date=%s", id, date);

		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				JsonObject jsonObject = new JsonParser().parse(inline).getAsJsonObject();
				return jsonObject;
			}

		} catch (IOException e) {
			System.out.println("Something went wrong with the API call.");
		}
		return null;
	}
	/**
	 * 
	 * @param id
	 * @param date
	 * @return
	 */
	public double getPriceForCoin(String id, String date) {
		double price = 0.0;

		JsonObject jsonObject = getDataForCrypto(id, date);
		if (jsonObject != null) {
			JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
			JsonObject currentPrice = marketData.get("current_price").getAsJsonObject();
			price = currentPrice.get("cad").getAsDouble();
		}

		return price;
	}

}
