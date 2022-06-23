package cryptoTrader.perform;

import cryptoTrader.broker.BrokerDaoImp;

import cryptoTrader.selection.ClientObserver;
import cryptoTrader.selection.CoinObserver;

/**
 * This class implements facade design pattern. Instead of letting graph
 * maker to draw bar and table individually, graph maker will ask its child
 * barDrawer and tableDrawer to do the rendering job.
 * 
 * @author Ido
 *
 */

public class GraphMaker {
	private VisualizerFacade table;
	private VisualizerFacade bar;

	public GraphMaker(ClientObserver clientObserver, CoinObserver coinObserver, BrokerDaoImp brokerDao) {
		table = new TableDrawer(clientObserver, coinObserver, brokerDao);
		bar = new BarDrawer(clientObserver, coinObserver, brokerDao);
	}

	public GraphMaker() {
	}

	public void draw() {// facade
		bar.draw();
		table.draw();
	}

}
