package datacollection.crypto;

import java.io.Serializable;

public class BitcoinMethod implements Serializable, Comparable<BitcoinMethod> {
	private static final long serialVersionUID = 1L;
	double gainCutoffPercent = 0;
	double lossCutoffPercent = 0;
	double currentGL = 5000;
	boolean sold = false;

	BitcoinMethod(double gain, double loss) {
		gainCutoffPercent = gain;
		lossCutoffPercent = loss;
	}

	@Override
	public int compareTo(BitcoinMethod o) {
		return o.currentGL > this.currentGL ? 1 : -1;
	}

	@Override
	public String toString() {
		return "Gain: " + this.gainCutoffPercent + ", Loss: " + this.lossCutoffPercent + "\n" + this.currentGL + "\n";
	}
}
