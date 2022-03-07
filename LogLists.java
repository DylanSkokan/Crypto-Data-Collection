package investing.crypto.datacollection;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <h2>LogLists</h2> Handles the singleton for the log files. Saved separately
 * from other values in case a reset is needed for the save state but the log
 * area should be kept the same.
 * 
 * @author Dylan Skokan
 * @since 2/23/22
 */
public class LogLists implements Serializable {

	private static final long serialVersionUID = -3686636171482976804L;
	private static LogLists single_instance = null;

	private LogLists() {
	}

	/**
	 * <h2>getLists</h2> gets the singleton for the log lists.
	 * 
	 * @return single_instance the singleton for the log lists.
	 */
	public static LogLists getLists() {
		if (single_instance == null) {
			single_instance = new LogLists();
		}
		return single_instance;
	}

	protected ArrayList<String> bitcoinLogList = new ArrayList<>();
}