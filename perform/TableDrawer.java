package cryptoTrader.perform;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import cryptoTrader.broker.Broker;
import cryptoTrader.broker.BrokerDaoImp;
import cryptoTrader.gui.MainUI;
import cryptoTrader.selection.ClientObserver;
import cryptoTrader.selection.CoinObserver;
import cryptoTrader.strategy.StrategyAbstract;
import cryptoTrader.strategy.StrategyFactory;
import cryptoTrader.strategy.tradingStatus;

/**
 * This class extends from the rendering facade class, and it implements the
 * function of rendering data in table version
 * 
 * @author Ido
 *
 */
public class TableDrawer extends VisualizerFacade {
	public TableDrawer() {

	}

	/**
	 * Access to the facade
	 * 
	 * @param clientObserver broker names grouped by clientObserver
	 * @param coinObserver   coin IDs grouped by coinObserver
	 * @param brokerDao      inputBroker object
	 */
	public TableDrawer(ClientObserver clientObserver, CoinObserver coinObserver, BrokerDaoImp brokerDao) {
		super(clientObserver, coinObserver, brokerDao);
	}

	/**
	 * draw the table
	 */
	public void draw() {

		ArrayList<String> clients = (ArrayList<String>) clientObserver.getClientList();

		Object[] columnNames = { "Trader", "Strategy", "CryptoCoin", "Action", "Quantity", "Price", "Date" };

		// For table, it is a little bit different from histogram in terms of
		// implementation since table requires more information, so here we will use a
		// double array [broker][brokerInfo] to buffer the rendered result, rather like
		// in histogram we directly use trading result.

		String[][] data = new String[clients.size()][7];
		int count = 0;
		StrategyFactory strategyFactory = new StrategyFactory();
		StrategyAbstract clientStrategy;
		tradingStatus clientTradingStatus;
		for (String client : clients) {
			Broker curBroker = brokerDao.getBroker(client);

			data[count][0] = client;

			data[count][1] = curBroker.getStrategy();

			clientStrategy = strategyFactory.getStrategy(data[count][1]);
			clientTradingStatus = clientStrategy.makeStrategy(curBroker, priceData);
			
			if (clientTradingStatus == null) {//extract rendered result
				System.out.println("Current User " + client + " Does Not Provid Enough Coin Selection");
				data[count][2] = "null";
				data[count][3] = "null";
				data[count][4] = "null";
				data[count][5] = "null";
			} else {
				data[count][2] = clientTradingStatus.getCoinID();

				if (clientTradingStatus.isTrade())
					data[count][3] = "Buy";
				else
					data[count][3] = "Sell";

				data[count][4] = "" + clientTradingStatus.getQuantity();

				data[count][5] = "" + priceData.getPrice(data[count][2]).getPrice();
			}
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			data[count][6] = formatter.format(date);

			count++;
		}
		
		//draw the table
		JTable table = new JTable(data, columnNames);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trader Actions",
				TitledBorder.CENTER, TitledBorder.TOP));

		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);
		;

		MainUI.getInstance().updateStats(scrollPane);//use mainUi's singleton object

	}

}
