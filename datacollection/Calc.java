package investing.crypto.datacollection;

import java.util.ArrayList;

/**
 * <h2>Calc</h2> Provides a variety of calculations frequently used in the
 * program.
 * 
 * @author Dylan Skokan
 * @since 2/23/22
 */
public class Calc {

	/**
	 * <h2>calcChange</h2> Calculates the percent difference from the starting
	 * number to the current number.
	 * 
	 * @param startingPriceRef the number to calculate the difference from.
	 * @param currentPriceRef  the number to calculate the difference to.
	 * @return the result of the calculation.
	 */
	public static double calcChange(double startingPriceRef, double currentPriceRef) {
		double topHalf = 0.0;
		double bottomHalf = 0.0;
		double times100 = 0.0;

		topHalf = (currentPriceRef - startingPriceRef);
		bottomHalf = Math.abs(startingPriceRef);
		times100 = (topHalf / bottomHalf) * 100;

		return times100;
	}

	/**
	 * <h2>calcBitcoinGL</h2> Calculates a mock account balance after all the trades
	 * that have happened.
	 * 
	 * @param percentChange the newest trade to be accounted for.
	 * @return the new percent change in the mock account.
	 */
	public static double calcBitcoinGL(double currentGL) {
		var vars = Var.getVars();
		
		//account for trade fees
		double percentChange = Calc.calcChange(vars.startingBitcoinPrice, vars.bitcoinPriceGlobal * 0.995);
		
		//calc new current GL
		return currentGL = currentGL * ((percentChange / 100) + 1);
	}

	/**
	 * <h2>calcDoubleArrayMean</h2> Calculates a mean value from a array list of
	 * doubles.
	 * 
	 * @param percentChange the newest trade to be accounted for.
	 * @return the mean value of the calculation.
	 */
	public static double calcDoubleArrayMean(ArrayList<Double> DoubleList) {
		int size = DoubleList.size();
		double totalChange = 0;
		for (int i = 0; i < size; i++) {
			totalChange = totalChange + DoubleList.get(i);
		}
		return totalChange / size;
	}
}