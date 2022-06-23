package cryptoTrader.priceData;
/**
 * This is the constructor of the coinPrice object
 * @author Ido
 *
 */
public class CoinsPrice{
	
	private String ID;
	private Double price;
	
	public CoinsPrice() {
		
	}

	public CoinsPrice(String iD, Double price) {
		ID = iD;
		this.price = price;
	}

	public String getID() {
		return ID;
	}

	public Double getPrice() {
		return price;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setPrice(Double price) {
		this.price = price;
	};
	
	

}
