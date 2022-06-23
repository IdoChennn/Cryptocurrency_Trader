package cryptoTrader.strategy;
/**
 * This class is the constructor of trading result object
 * @author Ido
 *
 */
public class tradingStatus {
	// True stands for buying
	//false stands for selling
	private boolean trade;
	private double quantity;
	private String coinID;

	public tradingStatus(String coinID, boolean trade, double quantity) {
		this.trade = trade;
		this.quantity = quantity;
		this.coinID = coinID;
	}

	public tradingStatus() {
	}

	public boolean isTrade() {
		return trade;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setTrade(boolean trade) {
		this.trade = trade;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getCoinID() {
		return coinID;
	}

	public void setCoinID(String coinID) {
		this.coinID = coinID;
	}

}
