package cryptoTrader.strategy;

import cryptoTrader.priceData.PriceDAOImp;

/**
 * This class implements factory design pattern
 * 
 * @author Ido
 *
 */
public class StrategyFactory {

	private PriceDAOImp priceData;

	public StrategyFactory() {

	}

	public StrategyFactory(PriceDAOImp priceData) {
		priceData = this.priceData;
	}

	public StrategyAbstract getStrategy(String strategy) {
		switch (strategy) {
		case "Strategy-A":
			return (StrategyAbstract) new StrategyA(priceData);

		case "Strategy-B":
			return (StrategyAbstract) new StrategyB(priceData);

		case "Strategy-C":
			return (StrategyAbstract) new StrategyC(priceData);

		default:
			return null;
		}
	}
}
