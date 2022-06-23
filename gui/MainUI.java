package cryptoTrader.gui;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.sql.rowset.JoinRowSet;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import cryptoTrader.broker.Broker;
import cryptoTrader.broker.BrokerDaoImp;
import cryptoTrader.perform.GraphMaker;
import cryptoTrader.selection.ClientObserver;
import cryptoTrader.selection.CoinObserver;
import cryptoTrader.selection.UserSelection;

/**
 * This class sets the appearance of the mainUI and implements all the features
 * that mainUi has, such as perform trade, addrow/removeRow. Also, singleton
 * design pattern is used here.
 * 
 * @author Ido
 *
 */
public class MainUI extends JFrame implements ActionListener, UIImage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static MainUI instance;// the singleton object of the MainUi class
	private JPanel stats, chartPanel, tablePanel;

	private List<String> selectedList;

	private JTextArea selectedTickerList;
	private JTextArea tickerText;
	private JTextArea BrokerText;
	private JComboBox<String> strategyList;
	private Map<String, List<String>> brokersTickers = new HashMap<>();
	private Map<String, String> brokersStrategies = new HashMap<>();
	private List<String> selectedTickers = new ArrayList<>();
	private String selectedStrategy = "";
	private DefaultTableModel dtm;
	private JTable table;

	// broker informations
	private BrokerDaoImp brokerDao;
	private ArrayList<Broker> brokerList;
	private BrokerListCheckServer brokerCheck;
	private UserSelection userSelection;
	private ClientObserver clientObserver;
	private CoinObserver coinObserver;

	/**
	 * This class will return the only object that mainUi class has - the mainUi
	 * object
	 * 
	 * @return mainUi singleton object
	 */

	public static MainUI getInstance() {// singleton
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

	/**
	 * set the appearance of the mainUi
	 */
	private MainUI() {

		// Set window title
		super("Crypto Trading Tool");

		// initialize brokers' list
		brokerDao = new BrokerDaoImp();
		brokerCheck = new BrokerListCheckServer();

		userSelection = new UserSelection();
		clientObserver = new ClientObserver(userSelection);
		coinObserver = new CoinObserver(userSelection);

		// Set top bar

		JPanel north = new JPanel();

		JButton trade = new JButton("Perform Trade");
		trade.setActionCommand("refresh");
		trade.addActionListener(this);

		JPanel south = new JPanel();

		south.add(trade);

		dtm = new DefaultTableModel(new Object[] { "Trading Client", "Coin List", "Strategy Name" }, 1);
		table = new JTable(dtm);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"Trading Client Actions", TitledBorder.CENTER, TitledBorder.TOP));

		// Here to add strategies
		Vector<String> strategyNames = new Vector<String>();
		strategyNames.add("None");
		strategyNames.add("Strategy-A");
		strategyNames.add("Strategy-B");
		strategyNames.add("Strategy-C");

		TableColumn strategyColumn = table.getColumnModel().getColumn(2);
		JComboBox comboBox = new JComboBox(strategyNames);
		strategyColumn.setCellEditor(new DefaultCellEditor(comboBox));

		// the button of add and delete rows
		JButton addRow = new JButton("Add Row");
		JButton remRow = new JButton("Remove Row");
		addRow.setActionCommand("addTableRow");
		addRow.addActionListener(this);
		remRow.setActionCommand("remTableRow");
		remRow.addActionListener(this);

		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);

		JPanel east = new JPanel();

		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));

		east.add(scrollPane);
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(addRow);
		buttons.add(remRow);
		east.add(buttons);

		// Set data rendering region
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(1250, 650));
		stats = new JPanel();
		stats.setLayout(new GridLayout(2, 2));

		west.add(stats);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(west, BorderLayout.CENTER);
		getContentPane().add(south, BorderLayout.SOUTH);

	}

	public void updateStats(JComponent component) {
		stats.add(component);
		stats.revalidate();
	}

	/**
	 * Track the operation done by the user
	 * 
	 * @param e the specific operation
	 */
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ("refresh".equals(command)) {

			stats.removeAll();

			// initialize the framework of the result window
			GraphMaker creator = new GraphMaker(clientObserver, coinObserver, brokerDao);
			creator.draw();

		}

		// add new rows only if there is no empty field in the previous row
		else if ("addTableRow".equals(command)) {
			// find current selected row and its values
			int selectedRow = table.getSelectedRow();
			if (selectedRow == -1)
				selectedRow = 0;

			if (dtm.getRowCount() == 0) {
				dtm.addRow(new String[3]);
			}

			Broker curBroker = brokerChecking(selectedRow, 1);
			boolean brokerFlag;
			brokerFlag = brokerCheck.checkBroker(curBroker, brokerDao);

			if (brokerFlag) {

				brokerDao.addBroker(curBroker);

				// Observer design pattern is used here to notify client observer
				userSelection.setState(curBroker);
				System.out.println("clientObserver items: " + clientObserver.getClientList().toString());
				System.out.println("coinObserver items: " + coinObserver.getCoinList().toString());
				System.out.println("brokerContainer items: " + brokerDao.getBrokers().toString());
				dtm.addRow(new String[3]);
			} else {
				if (curBroker != null)
					JOptionPane.showMessageDialog(this, "duplicate broker on line " + (selectedRow + 1));
			}

		}

		// delete new row operation
		else if ("remTableRow".equals(command)) {
			System.out.println("line current at: " + dtm.getRowCount());

			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
				Broker curBroker = brokerChecking(selectedRow, 0);
				if (curBroker != null) {
					userSelection.setState(curBroker);// Observer design pattern is used here to notify client observer
														// and coin observer that the current broker object is removed

					brokerDao.delete(curBroker);
					System.out.println("clientObserver items: " + clientObserver.getClientList().toString());
					System.out.println("brokerContainer items: " + brokerDao.getBrokers().toString());
				}
				dtm.removeRow(selectedRow);
				if (dtm.getRowCount() == 0) {
					dtm.addRow(new String[3]);
				}
			}
		}
	}

	/**
	 * This method will check the correctness of input broker information using
	 * brokerCheckServer
	 * 
	 * @param selectedRow the row currently selected
	 * @param mod         the correctness flag
	 * @return the broker object
	 */
	public Broker brokerChecking(int selectedRow, int mod) {
		boolean clientCheck, CoinCheck, StrategyCheck;

		clientCheck = brokerCheck.checkClient(selectedRow, dtm);
		if (!clientCheck && mod == 1)
			JOptionPane.showMessageDialog(this, "please fill in Trader name on line " + (selectedRow + 1));
		String traderName = brokerCheck.getClient();

		// check empty coinList
		CoinCheck = brokerCheck.checkCrypto(selectedRow, dtm);
		if (!CoinCheck && mod == 1)
			JOptionPane.showMessageDialog(this, "please fill in Trader Coin Selection on line " + (selectedRow + 1));
		String[] coinNames = brokerCheck.getCryptoList();

		// check empty strategy
		StrategyCheck = brokerCheck.checkStrategy(selectedRow, dtm);
		if (!StrategyCheck && mod == 1)
			JOptionPane.showMessageDialog(this, "please fill in Trader Strategy on line " + (selectedRow + 1));
		String strategyName = brokerCheck.getStrategy();

		if (!clientCheck && !CoinCheck && !StrategyCheck)
			return null;
		// after going though all the checks, create a broker object for the row
		Broker curBroker = new Broker(traderName, coinNames, strategyName);
		return curBroker;
	}

	/**
	 * This method will display the mainUI by modify the singleton object of the
	 * mainUI class
	 */
	public void displayUI() {
		JFrame frame = MainUI.getInstance();
		frame.setSize(300, 300);
		frame.pack();
		frame.setVisible(true);
	}
}
