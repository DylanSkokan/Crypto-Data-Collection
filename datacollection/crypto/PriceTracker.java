package datacollection.crypto;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import javax.swing.SwingWorker;

/**
 * <h2>TrackerRobots</h2> Contains robots that track the price of a crypto.
 * 
 * @author Dylan Skokan
 * @since 2/23/22
 */
public class PriceTracker {
	public static void bitcoinTrackerRobot() {
		SwingWorker<Void, Void> trackerWorker = new SwingWorker<Void, Void>() {
			String trackCoin = "neither";

			@Override
			protected Void doInBackground() throws Exception {
				System.out.println(TimeManager.getCurrentTime() + "Entering bitcoinTrackerRobot");
				Var vars = Var.getVars();
				if (vars.trySellBitcoin == false && vars.switchToBitcoin.equals("null")) {
					if (!vars.currentlyWatchingBitcoin.equals("null")) {
						trackCoin = vars.currentlyWatchingBitcoin;
					} else if (!vars.bitcoinNoBuyBack.equals("null")) {
						trackCoin = vars.bitcoinNoBuyBack;
					}
					System.out.println("trackCoin: " + trackCoin);

					FileIO.saveProgramState();
					Robot robot = new Robot();
					robot.setAutoDelay(200);
					int waitingCounter = 0;

					// open chrome
					RF.openChrome(robot);
					robot.setAutoDelay(vars.delayNum);
					robot.delay(1000);

					// open second tab
					RF.clickSecondTab(robot);
					robot.setAutoDelay(vars.delayNum);
					robot.delay(1000);

					// check for blue banner at top and close it
					if (robot.getPixelColor(995, 90).getRed() < 2 && robot.getPixelColor(995, 90).getGreen() > 70
							&& robot.getPixelColor(995, 90).getBlue() > 250) {
						robot.mouseMove(1514, 110);

						RF.click(robot);
						robot.setAutoDelay(vars.delayNum);
						robot.delay(1000);
					}
					
					//put correct URL in clipboard and paste in search box and enter
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
							new StringSelection("https://pro.coinbase.com/trade/" + trackCoin + "-USD"), null);
					robot.setAutoDelay(vars.delayNum);
					
					// move mouse to website search
					robot.mouseMove(550, 51);

					// click in website search
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);

					// cntrl a and backspace
					RF.backspaceAll(robot);
					robot.setAutoDelay(vars.delayNum);
					
					RF.pasteEnter(robot);
					robot.setAutoDelay(vars.delayNum);
					
					robot.delay(1000);
					
					//wait for page to load
					waitingCounter = 0;
					robot.mouseMove(209, 737);
					while(robot.getPixelColor(209, 737).getRed() < 35 && robot.getPixelColor(209, 737).getGreen() < 35
							&& robot.getPixelColor(209, 737).getBlue() < 35) {
						robot.delay(200);
						waitingCounter++;
						if(waitingCounter >= 100) {
							break;
						}
					}
					
					//highlight price
					robot.mouseMove(325, 150);
					robot.delay(200);
					RF.click(robot);
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);
					
					//copy price
					RF.copySelection(robot);
					robot.setAutoDelay(vars.delayNum);
					
					//put price into program
					RF.openProgram(robot);
					robot.setAutoDelay(vars.delayNum);
					robot.delay(1000);
					
					RF.clickBitcoinInputBox(robot);
					robot.setAutoDelay(vars.delayNum);
					robot.delay(1000);
					
					RF.backspaceAll(robot);
					robot.setAutoDelay(vars.delayNum);
					robot.delay(1000);
					
					RF.paste(robot);
					robot.setAutoDelay(vars.delayNum);
					robot.delay(1000);
					
					RF.clickBitcoinInputButton(robot);
					robot.setAutoDelay(vars.delayNum);
					
					robot.delay(5000);
					
				}
				return null;
			}

			@Override
			protected void done() {
				FileIO.saveProgramState();
				Var vars = Var.getVars();

				// "USD-" +
				/*
				if (!testForCoin.equals(trackCoin)) {
					// do not accept input
					vars.acceptBitcoinInput = false;
				} else {
					vars.acceptBitcoinInput = true;
				}
				*/
				
				// check if needs to switch to a different bitcoin
				// check if sell conditions are met
				if (vars.trySellBitcoin == true || vars.tryBuyBitcoin == true) {
					SwitchCurrentCrypto.bitcoinSwitchRobot();
				}
				// if none of the above, enter this robot again OR enter stockTracker if both
				// trades have
				// been made UNLESS it is nightHours then enter this robot again regardless
				else {
					CheckTopSpot.spotChecker();
				}
			}
		};
		trackerWorker.execute();
	}
}