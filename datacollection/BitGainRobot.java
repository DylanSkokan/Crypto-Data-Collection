package investing.crypto.datacollection;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;

import javax.swing.SwingWorker;

/**
 * <h2>BitGainRobot</h2> Tracks the current top gaining crypto in the last 24
 * hours.
 * 
 * @author Dylan Skokan
 * @since 2/23/22
 */
public class BitGainRobot {
	/**
	 * <h2>gainTracker</h2> Runs a SwingWorker in a background thread to carry out
	 * mouse and keyboard controls for information gathering
	 */
	public static void gainTracker() {
		SwingWorker<Void, Void> bitWorker = new SwingWorker<Void, Void>() {
			boolean validBit = true;
			String bit = "null";

			@Override
			protected Void doInBackground() throws Exception {
				FileIO.saveProgramState();
				var vars = Var.getVars();

				System.out.println(TimeManager.getCurrentTime() + "Entering bitGainRobot");
				Robot robot = new Robot();
				robot.setAutoDelay(vars.delayNum);

				// open chrome
				RF.openChrome(robot);
				robot.delay(500);
				robot.setAutoDelay(vars.delayNum);

				// open coinbase tab
				RF.clickFirstTab(robot);
				robot.delay(500);
				robot.setAutoDelay(vars.delayNum);

				// click trade tab on left
				robot.mouseMove(92, 250);
				RF.click(robot);
				robot.delay(2000);
				robot.setAutoDelay(vars.delayNum);

				// refresh page
				robot.keyPress(116);
				robot.keyRelease(116);
				robot.delay(1000);

				// waiting for blue color in yellow of bitcoin at top of list
				int waitingCounter = 0;
				robot.mouseMove(302, 499);
				while (robot.getPixelColor(302, 499).getBlue() != 26) {
					waitingCounter++;
					robot.delay(200);
					if (waitingCounter >= 100) {
						break;
					}
				}
				
				// click tradable assets
				robot.mouseMove(975, 363);
				RF.click(robot);
				robot.delay(200);
				robot.setAutoDelay(vars.delayNum);
				
				robot.mouseMove(978, 522);
				RF.click(robot);
				robot.delay(200);
				robot.setAutoDelay(vars.delayNum);
				
				// click change twice to get the greatest gain in the past hour
				// needs to find change button, it moves sometimes
				robot.delay(1000);

				int xPos = 789;
				robot.mouseMove(xPos, 443);
				robot.setAutoDelay(20);
				while (robot.getPixelColor(xPos, 443).getRed() > 250 && robot.getPixelColor(xPos, 443).getGreen() > 250
						&& robot.getPixelColor(xPos, 443).getBlue() > 250) {
					xPos--;
					robot.mouseMove(xPos, 443);
					if (xPos == 0) {
						break;
					}
				}

				robot.setAutoDelay(vars.delayNum);
				RF.click(robot);
				robot.setAutoDelay(vars.delayNum);

				// wait for blue buy button to appear both times
				waitingCounter = 0;
				robot.mouseMove(976, 510);
				while (robot.getPixelColor(976, 510).getRed() > 15) {
					waitingCounter++;
					robot.delay(200);
					if (waitingCounter >= 100) {
						break;
					}
				}

				robot.setAutoDelay(vars.delayNum);
				robot.mouseMove(xPos, 443);
				RF.click(robot);

				waitingCounter = 0;
				robot.mouseMove(976, 510);
				while (robot.getPixelColor(976, 510).getRed() > 15) {
					waitingCounter++;
					robot.delay(200);
					if (waitingCounter >= 100) {
						break;
					}
				}

				robot.setAutoDelay(vars.delayNum);

				// click top option
				robot.mouseMove(313, 507);
				RF.click(robot);
				robot.delay(4000);
				robot.setAutoDelay(vars.delayNum);

				// look for bitcoin acronym
				xPos = 1089;
				robot.mouseMove(xPos, 230);

				robot.setAutoDelay(3);
				while (robot.getPixelColor(xPos, 230).getRed() > 240 && robot.getPixelColor(xPos, 230).getGreen() > 240
						&& robot.getPixelColor(xPos, 230).getBlue() > 240) {
					xPos = xPos - 2;
					robot.mouseMove(xPos, 230);
				}

				// mouse is now over bitcoin acronym
				robot.setAutoDelay(vars.delayNum);

				// quad click to for sure highlight the name
				RF.click(robot);
				RF.click(robot);
				RF.click(robot);
				RF.click(robot);
				robot.setAutoDelay(vars.delayNum);
				robot.delay(1000);

				// copy
				RF.copySelection(robot);
				robot.setAutoDelay(vars.delayNum);
				robot.delay(1000);

				String bitcoin = (String) Toolkit.getDefaultToolkit().getSystemClipboard()
						.getData(DataFlavor.stringFlavor);

				// remove spaces
				bitcoin = bitcoin.replaceAll("\\s", "");

				for (int i = 0; i < bitcoin.length(); ++i) {
					if (!Character.isUpperCase(bitcoin.charAt(i)) && !Character.isDigit(bitcoin.charAt(i))) {
						System.out.println("Char not uppercase: " + bitcoin.charAt(i));
						validBit = false;
						break;
					}
				}

				if (bitcoin.equals(null) || bitcoin.equals("null")) {
					validBit = false;
				}

				if (validBit == true) {
					bit = bitcoin;
				}

				System.out.println("validBit: " + validBit);
				System.out.println("bit: " + bit);

				// click program
				RF.openProgram(robot);
				robot.delay(3000);
				robot.setAutoDelay(vars.delayNum);

				return null;
			}

			@Override
			protected void done() {
				System.out.println("IN DONE OF BitGainRobot");
				FileIO.saveProgramState();
				var vars = Var.getVars();

				TimeManager.midnightCheck();

				int diffBitThreshold = vars.diffBitThreshold;
				String currentlyWatchingBitcoin = vars.currentlyWatchingBitcoin, currentTopBit = vars.currentTopBit;

				System.out.println("currentlyWatchingBitcoin: " + currentlyWatchingBitcoin);
				System.out.println("currentTopBit: " + currentTopBit);

				// validBit checks if top bitcoin just gotten does not contain anything but
				// uppercase letters
				if (validBit == true) {
					System.out.println("vars.diffBitCounter: " + vars.diffBitCounter);
					// count how many times in a row a different bit is at the top spot
					if (!currentlyWatchingBitcoin.equals(bit) && currentTopBit.equals(bit)) {
						vars.diffBitCounter++;
					} else {
						vars.currentTopBit = bit;
						vars.diffBitCounter = 0;
					}
					// if top bit moves back to currently watching, we want top bit to equal this
					// again
					if (currentlyWatchingBitcoin.equals(bit)) {
						vars.currentTopBit = bit;
					}

					// if it is the first bitcoin, switch to it without counting diffBitCounter
					if (vars.firstBitGain == true) {
						vars.firstBitGain = false;
						if (vars.cutoffMode == true) {
							vars.bitcoinNoBuyBack = bit;
							TrackerRobots.bitcoinTrackerRobot();
						} else {
							vars.tryBuyBitcoin = true;
							vars.currentlyWatchingBitcoin = bit;
							SwitchRobot.bitcoinSwitchRobot();
						}
					}
					// if bitcoin is not the currentlyWatchingBitcoin and is the same 10 times
					else if (vars.diffBitCounter >= diffBitThreshold) {
						SwitchRobot.bitcoinSwitchRobot();
					}
					// if it has not reached the bit counter
					else if (vars.diffBitCounter < diffBitThreshold) {
						TrackerRobots.bitcoinTrackerRobot();
					}
				} else {
					System.out.println("validBit == false");
					if (vars.madeBitcoinBuy == false && vars.bitcoinNoBuyBack.equals("null")) {
						gainTracker();
					} else {
						TrackerRobots.bitcoinTrackerRobot();
					}
				}
			}
		};
		bitWorker.execute();
	}
}