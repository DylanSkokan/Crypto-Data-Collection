package investing.crypto.datacollection;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;

import javax.swing.SwingWorker;

/**
 * <h2>TrackerRobots</h2> Contains robots that track the price of a crypto.
 * 
 * @author Dylan Skokan
 * @since 2/23/22
 */
public class TrackerRobots {
	public static void bitcoinTrackerRobot() {
		SwingWorker<Void, Void> trackerWorker = new SwingWorker<Void, Void>(){
			boolean bitSold = false;
			String trackCoin = "neither", testForCoin = "null";
			@Override
			protected Void doInBackground() throws Exception {
				System.out.println(TimeManager.getCurrentTime() + "Entering bitcoinTrackerRobot");
				Var vars = Var.getVars();
				if(vars.trySellBitcoin == false && vars.switchToBitcoin.equals("null")) {
				if(!vars.currentlyWatchingBitcoin.equals("null")) {
					trackCoin = vars.currentlyWatchingBitcoin;
				} else if(!vars.bitcoinNoBuyBack.equals("null")){
					trackCoin = vars.bitcoinNoBuyBack;
				}
				System.out.println("trackCoin: " + trackCoin);
					
				FileIO.saveProgramState();
				Robot robot = new Robot();
				robot.setAutoDelay(200);
				int waitingCounter = 0;
				
				//open chrome
				RF.openChrome(robot);
				robot.setAutoDelay(vars.delayNum);
				robot.delay(1000);
				
				//open second tab
				RF.clickSecondTab(robot);
				robot.setAutoDelay(vars.delayNum);
				robot.delay(1000);
				
				//check for blue banner at top and close it
				if(robot.getPixelColor(995, 90).getRed() < 2 && robot.getPixelColor(995, 90).getGreen() > 70
						&& robot.getPixelColor(995, 90).getBlue() > 250) {
					robot.mouseMove(1514, 110);
					
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);
					robot.delay(1000);
				}
				
				//if current bitcoin is null, find it
				if(trackCoin.equals("neither")) {
					System.out.println("trackCoin.equals(\"neither\")");
					//click on orders
					robot.mouseMove(1154, 98);
					robot.delay(200);
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);
					
					//wait for page to load (white dollar sign)
					waitingCounter = 0;
					robot.mouseMove(431, 251);
					while(robot.getPixelColor(431, 251).getRed() < 150 && robot.getPixelColor(431, 251).getGreen() < 150
							&& robot.getPixelColor(431, 251).getBlue() < 150) {
						robot.delay(200);
						waitingCounter++;
						if(waitingCounter >= 100) {
							break;
						}
					}
					
					//click on filled tab
					robot.mouseMove(571, 344);
					robot.delay(200);
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);
					
					waitingCounter = 0;
					robot.mouseMove(451, 426);
					while(robot.getPixelColor(451, 426).getRed() < 45 && robot.getPixelColor(451, 426).getGreen() < 45
							&& robot.getPixelColor(451, 426).getBlue() < 45) {
						robot.delay(200);
						robot.mouseMove(10, 10);
						robot.delay(15);
						robot.mouseMove(451, 426);
						
						waitingCounter++;
						if(waitingCounter >= 100) {
							break;
						}
					}
					
					//check if top option under "side" says buy or sell, if it says buy then sell that coin, if it says sell
					robot.mouseMove(458, 428);
					robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					robot.mouseMove(501, 428);
					robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					
					RF.copySelection(robot);
					robot.setAutoDelay(vars.delayNum);
					
					String tradeType = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
					
					System.out.println("tradeType: " + tradeType);
					
					if(tradeType.equals("Sell")) {
						//click to collapse details
						robot.mouseMove(521, 426);
						RF.click(robot);
						robot.delay(500);
						robot.setAutoDelay(vars.delayNum);
						
						//highlight under "market" to get name of coin (doesnt have to replace/remove any chars)
						//click trade
						robot.mouseMove(536, 428);
						robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						robot.mouseMove(624, 428);
						robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						
						RF.copySelection(robot);
						robot.setAutoDelay(vars.delayNum);
						
						String checkCoin = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
						checkCoin = checkCoin.replace("/USD", "");
						checkCoin = checkCoin.replace("\n", "");
						
						//trackCoin = checkCoin;
						//vars.currentlyWatchingBitcoin = checkCoin;
						System.out.println("checkCoin: " + checkCoin);
					} else {
						Var.resetBitcoinSell();
						bitSold = true;
					}
				}
				
				if(bitSold == false) {
				System.out.println("bitSold == false");
				//click trade tab at top
				robot.mouseMove(324, 99);
				RF.click(robot);
				robot.delay(200);
				robot.setAutoDelay(vars.delayNum);
				
				//refresh page
				robot.keyPress(116);
				robot.keyRelease(116);
				robot.delay(1000);
				
				waitingCounter = 0;
				robot.mouseMove(82, 734);
				while(robot.getPixelColor(82, 734).getRed() < 15 && robot.getPixelColor(82, 734).getGreen() < 20
						&& robot.getPixelColor(82, 734).getBlue() < 30) {
					waitingCounter++;
					robot.delay(200);
					if(waitingCounter >= 100) {
						break;
					}
				}
				
				//Needs to check if at right spot
				robot.mouseMove(59, 155);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseMove(134, 155);
				
				RF.copySelection(robot);
				robot.setAutoDelay(vars.delayNum);
				
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				
				testForCoin = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
				
				System.out.println("testForCoin: " + testForCoin);

				if(!testForCoin.contains(trackCoin)) {
					
					//click in field
					robot.mouseMove(71, 227);
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);
					
					System.out.println("in !testForCoin.contains(trackCoin)");
					System.out.println("trackCoin: " + trackCoin);
					System.out.println("vars.currentlyWatchingBitcoin: " + vars.currentlyWatchingBitcoin);
					
					//paste currentlywatchingcoin in field
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(trackCoin), null);
					
					//paste name of coin from market section
					RF.paste(robot);
					robot.setAutoDelay(vars.delayNum);
					robot.delay(500);
					
					//click USD
					robot.mouseMove(86, 270);
					RF.click(robot);
					robot.delay(200);
					robot.setAutoDelay(vars.delayNum);
					
					//click top option
					robot.mouseMove(138, 379);
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);
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
					
				} else {
					robot.mouseMove(144, 157);
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);
				}
				
				//highlight and copy "last trade price" at top
				robot.mouseMove(325, 150);
				robot.delay(200);
				RF.click(robot);
				RF.click(robot);
				robot.setAutoDelay(vars.delayNum);
				
				RF.copySelection(robot);
				robot.setAutoDelay(vars.delayNum);
				
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

				} else {
					RF.openProgram(robot);
					robot.setAutoDelay(vars.delayNum);
					robot.delay(3000);
				}
			}
				return null;
			}

			@Override
			protected void done() {
				FileIO.saveProgramState();
				Var vars = Var.getVars();
				
				if(!testForCoin.contains(trackCoin)) {
					//do not accept input
					vars.acceptBitcoinInput = false;
				} else {
					vars.acceptBitcoinInput = true;
				}
				/*
				//String timeChecker = TimeManager.getCurrentTime();
				//check for new hour for lists/restart
				
				if(vars.currentHour != Integer.parseInt(timeChecker.substring(7, 9)) && 
						Integer.parseInt(timeChecker.substring(10, 12)) > 2) {
					vars.isNewHour = true;
					vars.triedList = true;
					ListRobot.runListRobot();
				}
				*/
				//check if buy conditions are met
				/*
				else if(vars.madeBitcoinBuy == false && vars.tryBuyBitcoin == true) {
					BuyingRobots.bitcoinBuyRobot();
				}
				*/
				//check if needs to switch to a different bitcoin
				//check if sell conditions are met
				if(vars.trySellBitcoin == true || vars.tryBuyBitcoin == true) {
					SwitchRobot.bitcoinSwitchRobot();
				}
				//if none of the above, enter this robot again OR enter stockTracker if both trades have
				//been made UNLESS it is nightHours then enter this robot again regardless
				else {
					BitGainRobot.gainTracker();
				}
			}
		};
		trackerWorker.execute();
	}
}
