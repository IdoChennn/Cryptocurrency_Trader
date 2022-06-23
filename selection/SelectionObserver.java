package cryptoTrader.selection;

/**
 * This abstract class represents the format of any new observer
 * 
 * @author Ido
 *
 */
public abstract class SelectionObserver {
	protected UserSelection subject;

	/**
	 * update the new child observer
	 */
	public abstract void update();
}
