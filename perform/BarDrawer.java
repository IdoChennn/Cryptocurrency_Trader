package cryptoTrader.perform;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;

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
 * function of rendering data in bar version
 * 
 * @author Ido
 *
 */
public class BarDrawer extends VisualizerFacade {

	public BarDrawer() {

	}

	/**
	 * Access to the facade
	 * 
	 * @param clientObserver broker names grouped by clientObserver
	 * @param coinObserver   coin IDs grouped by coinObserver
	 * @param brokerDao      inputBroker object
	 */
	public BarDrawer(ClientObserver clientObserver, CoinObserver coinObserver, BrokerDaoImp brokerDao) {
		super(clientObserver, coinObserver, brokerDao);
	}

	/**
	 * draw the bar
	 */
	public void draw() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ArrayList<String> clients = (ArrayList<String>) clientObserver.getClientList();// get all broker entities
		double quantity;
		String strategy;
		StrategyFactory strategyFactory = new StrategyFactory();
		StrategyAbstract clientStrategy;
		tradingStatus clientTradingStatus;

		// going through every brokers and do trade from them
		for (String client : clients) {
			Broker curBroker = brokerDao.getBroker(client);
			strategy = curBroker.getStrategy();

			// use factory to find which strategy going to be used
			clientStrategy = strategyFactory.getStrategy(strategy);
			
			//do trade
			clientTradingStatus = clientStrategy.makeStrategy(curBroker, priceData);
			
			//decide if there is Null result
			if (clientTradingStatus == null) {

				quantity = 0;
			} else
				quantity = clientTradingStatus.getQuantity();
			dataset.setValue(quantity, client, strategy);
		}
		
		//plotting the histogram
		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Strategy");
		plot.setDomainAxis(domainAxis);
		LogAxis rangeAxis = new LogAxis("Actions(Buys or Sells)");
		rangeAxis.setRange(new Range(1.0, 20.0));
		plot.setRangeAxis(rangeAxis);

		JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUI.getInstance().updateStats(chartPanel);
	}

}
