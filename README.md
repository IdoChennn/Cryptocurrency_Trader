# How to run the program - 
	1. To run this crypoTrader program, your java IDE must be 1.8 or higher.
	2. Select src/main/java folder -> put Main.java under crytoTrader.main package as the Main class -> run.
	3. To login the program, the default user credential is "westernCS" userName and "cs2212"password.
	4. To add a new row in "Trading Client Actions" window, you must fill up the previous row.
	5. To perform trade, 
		Step 1). Before press "perform" button, you need to press "Add Row" once. IMPORTANT!
		Step 2). After press "Add Row" once, press perform button, then the program will produce trading result.

# Something about the program -

	1. The running flow in this program is - 

		1). login server check user input credential 

		2). If credential is valid, login window will use Proxy mainUi to open the real mainUi

		3). After user finish inserting brokerInput (brokerName,coins and strategy), 
		    the brokerInput will be first sent to data rendering server to construct a framework for trading result.
		    Then, it will be sent to strategy servers to be traded.

		4). After strategy servers finish trading, they will send trade result to rendering server.


	2. There are three important objects in the implementation of this program:

		1) BrokerDao (Data Access Object) object is an object that stores all the inputBroker information 
		   by using a big arrayList.

		2) PriceDao object is an object that stores all the coin price data including the name of coin and 
		   the price retrieved from coinGeko's API by using hashmap.

		3) TradingStatus object is an object that stores the trade result for specific strategy picked by the broker.

	
	3. Six design pattern are used in the implementation of this program excluding DAO design pattern:

		1). Proxy - it is used in login process in the sense that login window will not directly interact with the MainUi.

		2). Singleton - it is used by mainUi class. That is, the mainUi class will only provide one mainUi object.

		3). Observer - it is used by data rendering server. Everytime when user hits "Add Row" button, 
		    	      clientObserver and coinObserver will be notified to update themselves.

		4). Strategy - it is used in strategy servers. There is one abstract strategy servers, and there are three child
		    	     strategy servers (A,B,C) extended from the abstract class.

		5). Factory - it is used in strategy servers.
		
		6). Facade - it is used in result rendering server. That is, instead of making one big graph-making class, we created a
			   facade class that will call its two child class(barDrawer and tableDrawer) to do the job.
