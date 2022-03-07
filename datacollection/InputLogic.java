package investing.crypto.datacollection;

/**
 * <h2>InputLogic</h2> Handles the input from the input box.
 * 
 * @author Dylan Skokan
 * @since 2/23/22
 */
public class InputLogic {

	/**
	 * Parses the input passed to it into a value that can be processed and makes
	 * decisions based on the values given.
	 * 
	 * @param userInput the input from the the input box to be processed.
	 */
	public static void bitcoinInput(String userInput) {
		System.out.println("bitcoinInput: " + userInput + "\n");

		var vars = Var.getVars();
		var logLists = LogLists.getLists();
		String dateTime = TimeManager.getCurrentTime();
		boolean acceptInput = true;
		double bitcoinPrice = 0;
		int inputLength = userInput.length();
		int i = 0;

		// reset codes for resetting values if something goes wrong or if testing
		// success of new methods
		if (userInput.equals("resetCode1")) {
			vars.bitcoinCurrentGLPercent = 0;
			vars.bitcoinCurrentGL = 5000;
			FileIO.saveProgramState();
		}
		if (userInput.equals("resetCode2")) {
			vars.bitcoinCurrentGLPercent = 0;
			vars.bitcoinCurrentGL = 5000;

			vars.gainHighpoints.clear();
			vars.gainLowpoints.clear();

			vars.averageGainHighpoint = 0;
			vars.averageGainLowpoint = 0;

			vars.lossHighpoints.clear();
			vars.lossLowpoints.clear();

			vars.averageLossHighpoint = 0;
			vars.averageLossLowpoint = 0;
			FileIO.saveProgramState();
		}
		if (userInput.equals("resetCode3")) {
			Var.setVars(null);
			FileIO.saveProgramState();
		}

		if (userInput.contains(".com")) {
			System.out.println("userInput contains .com, must be URL, did not accept input\n");
			acceptInput = false;
		}
		if (inputLength < 4) {
			System.out.println("inputLength < 4, did not accept input\n");
			acceptInput = false;
		}
		if (!userInput.contains(".")) {
			System.out.println("userInput does not contain a period, not accepting input\n");
			acceptInput = false;
		}

		// if there is anything besides a period or a digit, delete it
		String newPrice = "";

		for (i = 0; i < inputLength; ++i) {
			char priceChar = userInput.charAt(i);
			if (Character.isDigit(priceChar) || priceChar == '.') {
				newPrice = newPrice + priceChar;
			}
		}

		// check if input can be parsed into a double
		try {
			bitcoinPrice = Double.parseDouble(newPrice);
		} catch (Exception e) {
			System.out.println("bitcoinPrice error: " + newPrice + "\n" + e.getMessage());
			acceptInput = false;
		}

		if (bitcoinPrice != 0 && acceptInput == true) {
			vars.bitcoinPriceGlobal = bitcoinPrice;

			// check if stockPrice is too different from previousPrice to be real
			double priceComparison = (vars.previousBitcoinPrice != 0.0)
					? Calc.calcChange(vars.previousBitcoinPrice, bitcoinPrice)
					: 0;

			System.out.println("Percent change in price compared to previous: " + priceComparison + "\n");

			// check if input is at least 3 digits long, and it is a number, and it has a
			// decimal, and the bitcoin value is for the currently watching bitcoin
			if (acceptInput == true && vars.acceptBitcoinInput == true) {
				if (priceComparison < 65 && priceComparison > -65) {
					System.out.println("bitcoinPrice: " + bitcoinPrice + "\n");

					if (vars.madeBitcoinBuy == true) {
						if (vars.startingBitcoinPrice == 0 && bitcoinPrice != 0) {
							String logEntry = dateTime + "Starting price: " + bitcoinPrice;
							System.out.println(logEntry + "\n");
							GUI.bitcoinLogArea.append(logEntry + "\n");
							logLists.bitcoinLogList.add(logEntry);

							vars.startingBitcoinPrice = bitcoinPrice * 1.005;
						}
						if (vars.startingBitcoinPrice != 0) {
							if (vars.lowestBitcoinPrice == 0) {
								vars.lowestBitcoinPrice = bitcoinPrice;
							}
							if (vars.highestBitcoinPrice == 0) {
								vars.highestBitcoinPrice = bitcoinPrice;
							}

							vars.calcBitcoinChange = Calc.calcChange(vars.startingBitcoinPrice, bitcoinPrice);
							GUI.bitcoinIncreaseSinceStartField.setText(Double.toString(vars.calcBitcoinChange));

							if (vars.calcBitcoinChange >= vars.bitcoinPcHigh) {
								vars.bitcoinPcHigh = vars.calcBitcoinChange;
								vars.highestBitcoinPrice = bitcoinPrice;
							}

							GUI.bitcoinPercentChangeHighPointField.setText(Double.toString(vars.bitcoinPcHigh));

							if (vars.calcBitcoinChange <= vars.bitcoinPcLow) {
								vars.bitcoinPcLow = vars.calcBitcoinChange;
								vars.lowestBitcoinPrice = bitcoinPrice;
							}

							GUI.bitcoinScoreDifferenceField.setText(Double.toString(vars.bitcoinPcLow));

							System.out.println("vars.bitcoinPcHigh: " + vars.bitcoinPcHigh);
							System.out.println("vars.calcBitcoinChange: " + vars.calcBitcoinChange);

							if (vars.bitcoinPcHigh > 7 && vars.calcBitcoinChange < vars.bitcoinPcHigh * 0.6) {
								System.out.println(
										"vars.bitcoinPcHigh > 7 && vars.calcBitcoinChange < vars.bitcoinPcHigh * 0.6");

								vars.trySellBitcoin = true;
							}
							if (vars.bitcoinPcHigh > 12 && vars.calcBitcoinChange < vars.bitcoinPcHigh) {
								System.out.println("vars.calcBitcoinChange > vars.averageGainHighpoint");

								vars.trySellBitcoin = true;
							}
							if (vars.calcBitcoinChange < -2) {
								System.out.println("vars.calcBitcoinChange < vars.averageGainLowpoint");

								vars.trySellBitcoin = true;
							}
						}
					}
				}
				// saves the previous price for price comparisons
				vars.previousBitcoinPrice = bitcoinPrice;
			}
		}
		FileIO.saveProgramState();
	}
}