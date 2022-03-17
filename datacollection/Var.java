package investing.crypto.datacollection;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <h2>Var</h2> Handles the singleton for the variables
 * 
 * @author Dylan Skokan
 * @since 2/23/22
 */
public class Var implements Serializable {

	private static final long serialVersionUID = 7470754319110506773L;
	private static Var single_instance = null;

	private Var() {
	}

	public static Var getVars() {
		if (single_instance == null) {
			single_instance = new Var();
		}
		return single_instance;
	}

	public static void setVars(Var vars) {
		single_instance = vars;
	}

	public static void resetBitcoinFail() {
		Var vars = Var.getVars();
		System.out.println();
		System.out.println("Reseting bitcoin values due to fail check");
		System.out.println();

		vars.currentlyWatchingBitcoin = "null";
		vars.foundBitcoin = false;
		vars.tryBuyBitcoin = false;

		GUI.bitcoinCurrentlyWatchingField.setText("");
	}

	public static void resetBitcoinBeforeBuy() {
		Var vars = Var.getVars();
		System.out.println();
		System.out.println("Reseting bitcoin values before rebuy");
		System.out.println();

		vars.currentlyWatchingBitcoin = vars.currentTopBit;
		vars.startingBitcoinPrice = 0;
		vars.trySellBitcoin = false;
		vars.madeBitcoinBuy = true;
		vars.tryBuyBitcoin = false;
		vars.listedBitcoinWatch = false;
		vars.bitcoinSwitchCondition = false;
		vars.bitcoinFirstSellCondition = false;
		vars.lowBitCounter = 0;
		vars.lowestBitcoinPrice = 0;
		vars.highestBitcoinPrice = 0;
		vars.gettingBitcoinStartPrice = true;
		vars.previousBitcoinPrice = 0.0;
		vars.bitcoinPriceGlobal = 0.0;
		vars.calcBitcoinChange = 0.0;
		vars.bitcoinPcHighDay = 0.0;
		vars.bitcoinPcHigh = 0.0;
		vars.bitcoinPcLowDay = 0.0;
		vars.bitcoinPcLow = 0.0;
		vars.bitcoinDaysPassed = 0;
		vars.bitcoinListedDaysPassedThreshold = false;

		vars.localBitcoinPrices.clear();
		vars.localBitcoinPriceChanges.clear();

		GUI.bitcoinIncreaseSinceStartField.setText("");
		GUI.bitcoinPcHighDayField.setText("");
		GUI.bitcoinPercentChangeHighPointField.setText("");
		GUI.bitcoinPcLowDayField.setText("");
		GUI.bitcoinScoreDifferenceField.setText("");
		GUI.bitcoinCutoffValField.setText("");
	}

	public static void resetBitcoinSell() {
		Var vars = Var.getVars();
		System.out.println();
		System.out.println("Reseting bitcoin values due bitcoin sell");
		System.out.println();

		vars.currentlyWatchingBitcoin = "null";
		vars.foundBitcoin = false;
		vars.startingBitcoinPrice = 0;
		vars.trySellBitcoin = false;
		vars.madeBitcoinBuy = false;
		vars.tryBuyBitcoin = false;
		vars.listedBitcoinWatch = false;
		vars.bitcoinSwitchCondition = false;
		vars.bitcoinFirstSellCondition = false;
		vars.lowBitCounter = 0;
		vars.lowestBitcoinPrice = 0;
		vars.highestBitcoinPrice = 0;
		vars.gettingBitcoinStartPrice = true;
		vars.previousBitcoinPrice = 0.0;
		vars.bitcoinPriceGlobal = 0.0;
		vars.calcBitcoinChange = 0.0;
		vars.bitcoinPcHighDay = 0.0;
		vars.bitcoinPcHigh = 0.0;
		vars.bitcoinPcLowDay = 0.0;
		vars.bitcoinPcLow = 0.0;
		vars.bitcoinDaysPassed = 0;
		vars.bitcoinSellCounter++;
		vars.bitcoinListedDaysPassedThreshold = false;

		vars.localBitcoinPrices.clear();
		vars.localBitcoinPriceChanges.clear();

		GUI.bitcoinCurrentlyWatchingField.setText("");
		GUI.bitcoinIncreaseSinceStartField.setText("");
		GUI.bitcoinPcHighDayField.setText("");
		GUI.bitcoinPercentChangeHighPointField.setText("");
		GUI.bitcoinPcLowDayField.setText("");
		GUI.bitcoinScoreDifferenceField.setText("");
		GUI.bitcoinCutoffValField.setText("");
	}

	public static void moveBitcoinValues() {
		Var vars = Var.getVars();
		System.out.println();
		System.out.println("Changing certain values due to bitcoin swap");
		System.out.println();

		vars.currentlyWatchingBitcoin = vars.currentTopBit;
		vars.switchToBitcoin = "null";
		vars.startingBitcoinPrice = 0;
		vars.trySellBitcoin = false;
		vars.bitcoinFirstSellCondition = false;
		vars.bitcoinSwitchCondition = false;

		vars.lowestBitcoinPrice = 0;
		vars.highestBitcoinPrice = 0;
		vars.gettingBitcoinStartPrice = true;
		vars.previousBitcoinPrice = 0.0;
		vars.bitcoinPriceGlobal = 0.0;
		vars.calcBitcoinChange = 0.0;
		vars.bitcoinPcHighDay = 0.0;
		vars.bitcoinPcHigh = 0.0;
		vars.bitcoinPcLowDay = 0.0;
		vars.bitcoinPcLow = 0.0;
		vars.bitcoinDaysPassed = 0;
		vars.diffBitCounter = 0;
		vars.bitcoinSellCounter++;
		vars.bitcoinListedDaysPassedThreshold = false;

		vars.localBitcoinPriceChanges.clear();
		vars.localBitcoinPrices.clear();

		GUI.bitcoinCurrentlyWatchingField.setText(vars.currentlyWatchingBitcoin);
		GUI.bitcoinIncreaseSinceStartField.setText("");
		GUI.bitcoinPcHighDayField.setText("");
		GUI.bitcoinPercentChangeHighPointField.setText("");
		GUI.bitcoinPcLowDayField.setText("");
		GUI.bitcoinScoreDifferenceField.setText("");
		GUI.bitcoinCutoffValField.setText("");
	}

	// GENERAL
	protected int delayNum = 20;
	protected int diffBitThreshold = 8;
	protected boolean isMidnight;
	protected boolean cutoffMode = true;

	// BITCOIN
	protected String switchToBitcoin = "null";
	protected String currentlyWatchingBitcoin = "null";
	protected String currentTopBit = "null";
	protected String bitcoinNoBuyBack = "null";

	protected int bitcoinDaysPassed = 0;
	protected int bitcoinSellCounter = 0;
	protected int diffBitCounter = 0;
	protected int lowBitCounter = 0;
	protected int differenceCounter = 0;

	protected double highestBitcoinPrice = 0;
	protected double bitcoinCurrentGL = 5000;
	protected double bitcoinCurrentGLPercent = 0;
	protected double lowestBitcoinPrice = 0;
	protected double startingBitcoinPrice = 0.0;
	protected double previousBitcoinPrice = 0.0;
	protected double bitcoinPriceGlobal = 0.0;
	protected double calcBitcoinChange = 0.0;
	protected double bitcoinPcHighDay = 0.0;
	protected double bitcoinPcHigh = 0.0;
	protected double bitcoinPcLowDay = 0.0;
	protected double bitcoinPcLow = 0.0;
	protected double averageGainHighpoint = 0;
	protected double averageGainLowpoint = 0;
	protected double averageLossHighpoint = 0;
	protected double averageLossLowpoint = 0;

	protected boolean bitcoinListedDaysPassedThreshold = false;
	protected boolean listedBitcoinBuy = false;
	protected boolean bitcoinFirstSellCondition = false;
	protected boolean tryBuyBitcoin = false;
	protected boolean firstBitGain = true;
	protected boolean gettingBitcoinStartPrice = true;
	protected boolean madeBitcoinBuy = false;
	protected boolean foundBitcoin = false;
	protected boolean bitcoinSwitchCondition = false;
	protected boolean listedBitcoinWatch = false;
	protected boolean madeBitcoinSell = false;
	protected boolean trySellBitcoin = false;
	protected boolean bitcoinsNeedChecking = false;
	protected boolean acceptBitcoinInput = false;
	protected boolean needsToBuyBitcoin = false;

	protected ArrayList<Double> bitcoinSells = new ArrayList<Double>();
	protected ArrayList<Double> localBitcoinPrices = new ArrayList<Double>();
	protected ArrayList<Double> localBitcoinPriceChanges = new ArrayList<Double>();
	protected ArrayList<Double> gainHighpoints = new ArrayList<Double>();
	protected ArrayList<Double> gainLowpoints = new ArrayList<Double>();
	protected ArrayList<Double> lossHighpoints = new ArrayList<Double>();
	protected ArrayList<Double> lossLowpoints = new ArrayList<Double>();
	
	// Methods to test
	protected ArrayList<Methods> methodResults = new ArrayList<Methods>();
}