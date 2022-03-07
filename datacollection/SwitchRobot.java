package investing.crypto.datacollection;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;

import javax.swing.SwingWorker;

/**
 * <h2>SwitchRobot</h2> Performs a mock trade and switches values to accept new crypto
 * 
 * @author Dylan Skokan
 * @since 2/23/22
 */
public class SwitchRobot {
	public static void bitcoinSwitchRobot() {
		FileIO.saveProgramState();
		SwingWorker<Void, Void> checkerRobot = new SwingWorker<Void, Void>(){
			String tradeType = "null";
			String fromCoin = "null";
			boolean failBuy = false;
			@Override
			protected Void doInBackground() throws Exception {
				System.out.println(TimeManager.getCurrentTime() + "Entering bitcoinSwitchRobot");
				Var vars = Var.getVars();
				Robot robot = new Robot();
				robot.setAutoDelay(200);
				
				RF.openChrome(robot);
				robot.setAutoDelay(vars.delayNum);
				robot.delay(1000);
				
				RF.clickSecondTab(robot);
				robot.setAutoDelay(vars.delayNum);
				robot.delay(1000);
				
				//get current coin
				//click on orders
				robot.mouseMove(1154, 98);
				robot.delay(200);
				RF.click(robot);
				robot.setAutoDelay(vars.delayNum);
				
				//wait for page to load (white dollar sign)
				int waitingCounter = 0;
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
				
				tradeType = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
				
				/*
				if(tradeType.equals("Sell")) {
					//get amount of money in portfolio
					robot.mouseMove(465, 258);
					robot.delay(100);
					RF.click(robot);
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);

					RF.copySelection(robot);
					robot.setAutoDelay(vars.delayNum);
					
					String STotalMoney = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
					totalMoney = Double.parseDouble(STotalMoney);
					totalMoney = totalMoney * 0.99;
				}
				*/
				if(tradeType.equals("Buy")) {
					//click to collapse details
					robot.mouseMove(521, 426);
					RF.click(robot);
					robot.delay(500);
					robot.setAutoDelay(vars.delayNum);
					
					//highlight under "market" to get name of coin (doesnt have to replace/remove any chars)
					robot.mouseMove(536, 428);
					robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					robot.mouseMove(624, 428);
					robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					
					RF.copySelection(robot);
					robot.setAutoDelay(vars.delayNum);
					
					fromCoin = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
					fromCoin = fromCoin.replace("/USD", "");
					fromCoin = fromCoin.replace("\n", "");
					
					//click trade tab at top
					robot.mouseMove(324, 99);
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);
					robot.delay(1000);
					
					//Checks for buy/sell button (Possibly needs to check for something else if limit is selected from start)
					waitingCounter = 0;
					robot.mouseMove(82, 734);
					while(robot.getPixelColor(82, 734).getRed() < 22 && robot.getPixelColor(82, 734).getGreen() < 32
							&& robot.getPixelColor(82, 734).getBlue() < 42) {
						waitingCounter++;
						robot.delay(200);
						if(waitingCounter >= 100) {
							break;
						}
					}
					
					//select market for name of coin
					robot.mouseMove(204, 156);
					RF.click(robot);
					robot.delay(500);
					robot.setAutoDelay(vars.delayNum);
					
					robot.mouseMove(86, 271);
					RF.click(robot);
					robot.delay(500);
					robot.setAutoDelay(vars.delayNum);
					
					robot.mouseMove(71, 227);
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);
					
					//paste name of coin from market section
					RF.paste(robot);
					robot.setAutoDelay(vars.delayNum);
					robot.delay(500);
					
					//click top option (needs to check if thats the right one)
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
					
					///CHECKING to see if right one
					//highlight under "market" to get name of coin (doesnt have to replace/remove any chars)
					robot.mouseMove(57, 158);
					robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					robot.mouseMove(158, 158);
					robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					
					RF.copySelection(robot);
					robot.setAutoDelay(vars.delayNum);
					
					String testSpot = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
					testSpot = testSpot.replaceAll("-USD", "");
					testSpot = testSpot.replaceAll("\n", "");
					
					System.out.println("Searching for bitcoin in list:");
					System.out.println("currentlyWatchingBitcoin: " + fromCoin);
					System.out.println("testSpot: " + testSpot);
					
					int[] yPos = {429, 475, 519, 567, 624, 677, 718};
					for(int i = 0; i < 10; ++i) {
						
						System.out.println("Searching for bitcoin in list:");
						System.out.println("fromCoin: " + fromCoin);
						System.out.println("testSpot: " + testSpot);
						
						if(testSpot.equals(fromCoin)) {
							System.out.println(testSpot + " equals " + fromCoin);
							//the name at the top matches
							//exit out of trade box
							robot.mouseMove(204, 156);
							RF.click(robot);
							robot.delay(500);
							robot.setAutoDelay(vars.delayNum);
							break;
						}
						else if(!testSpot.equals(fromCoin)) {
							System.out.println(testSpot + " does not equal " + fromCoin);
						//click USD
						robot.mouseMove(86, 271);
						RF.click(robot);
						robot.delay(500);
						robot.setAutoDelay(vars.delayNum);
						
						//click in bitcoin finder box
						robot.mouseMove(71, 227);
						RF.click(robot);
						robot.setAutoDelay(vars.delayNum);
						
						//paste TO COIN from market section
						StringSelection pasteCoin = new StringSelection(fromCoin);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pasteCoin, null);
						
						//paste name of coin from market section
						RF.paste(robot);
						robot.setAutoDelay(vars.delayNum);
						robot.delay(500);
						
						//click option
						robot.mouseMove(138, yPos[i]);
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
						
						///CHECKING to see if right one
						//highlight under "market" to get name of coin (doesnt have to replace/remove any chars)
						robot.mouseMove(57, 158);
						robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
						robot.mouseMove(158, 158);
						robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						
						RF.copySelection(robot);
						robot.setAutoDelay(vars.delayNum);
						
						testSpot = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
						testSpot = testSpot.replaceAll("-USD", "");
						testSpot = testSpot.replaceAll("\n", "");
						}
					}
					
					//click sell
					robot.mouseMove(191, 482);
					robot.delay(200);
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);
					
					//click market
					robot.mouseMove(71, 521);
					robot.delay(200);
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);
					
					//click max
					robot.mouseMove(51, 599);
					RF.click(robot);
					robot.delay(500);
					robot.setAutoDelay(vars.delayNum);
					
					/*
					//click place order
					robot.mouseMove(138, 733);
					robot.delay(200);
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);
					*/
					
					//if says market orders are disabled do limit
					robot.mouseMove(94, 778);
					if(robot.getPixelColor(94, 778).getRed() > 230 && robot.getPixelColor(94, 778).getGreen() > 80
							&& robot.getPixelColor(94, 778).getBlue() < 52) {

						//click limit
						robot.mouseMove(147, 522);
						robot.delay(200);
						RF.click(robot);
						robot.setAutoDelay(vars.delayNum);
						
						//click max
						robot.mouseMove(50, 600);
						robot.delay(200);
						RF.click(robot);
						robot.setAutoDelay(vars.delayNum);
						
						//highlight and copy "last trade price" at top
						robot.mouseMove(325, 150);
						robot.delay(200);
						RF.click(robot);
						RF.click(robot);
						robot.setAutoDelay(vars.delayNum);
						
						RF.copySelection(robot);
						robot.setAutoDelay(vars.delayNum);
						
						String SPrice = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
						double price = Double.parseDouble(SPrice);
						
						//calculate 98 percent of the last trade price
						price = price * 0.98;
						
						double roundOff = Math.round(price * 100.0) / 100.0;
						
						SPrice = Double.toString(roundOff);

						//paste in limit price field
						StringSelection clipPrice = new StringSelection(SPrice);
						Toolkit.getDefaultToolkit().getSystemClipboard().setContents(clipPrice, null);
						
						//move mouse and click limit price box
						robot.mouseMove(58, 671);
						robot.delay(200);
						RF.click(robot);
						robot.setAutoDelay(vars.delayNum);
						
						RF.paste(robot);
						robot.delay(200);
						robot.setAutoDelay(vars.delayNum);
						
						//push page down
						robot.keyPress(34);
						robot.keyRelease(34);
						robot.delay(300);

						/*
						//click place sell order
						robot.mouseMove(73, 728);
						robot.delay(200);
						RF.click(robot);
						robot.setAutoDelay(vars.delayNum);
						*/
						
					}
				}
				
				if(vars.trySellBitcoin == false) {
				//BUY COIN FROM COINBASE GAIN TAB
				//click trade tab at top
				robot.mouseMove(324, 99);
				RF.click(robot);
				robot.setAutoDelay(vars.delayNum);
				
				//Checks for buy/sell button (Possibly needs to check for something else if limit is selected from start)
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
				
				//select market for name of coin
				robot.mouseMove(204, 156);
				RF.click(robot);
				robot.delay(500);
				robot.setAutoDelay(vars.delayNum);
				
				robot.mouseMove(86, 271);
				RF.click(robot);
				robot.delay(500);
				robot.setAutoDelay(vars.delayNum);
				
				robot.mouseMove(71, 227);
				RF.click(robot);
				robot.setAutoDelay(vars.delayNum);
				
				//paste TO COIN from market section
				StringSelection toCoin = new StringSelection(vars.currentlyWatchingBitcoin);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(toCoin, null);
				
				RF.paste(robot);
				robot.setAutoDelay(vars.delayNum);
				robot.delay(500);
				
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
				
				///CHECKING to see if right one
				//highlight under "market" to get name of coin (doesnt have to replace/remove any chars)
				robot.mouseMove(57, 158);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseMove(158, 158);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				
				RF.copySelection(robot);
				robot.setAutoDelay(vars.delayNum);
				
				String testSpot = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
				testSpot = testSpot.replaceAll("-USD", "");
				testSpot = testSpot.replaceAll("\n", "");
				
				System.out.println("Searching for bitcoin in list:");
				System.out.println("currentlyWatchingBitcoin: " + vars.currentlyWatchingBitcoin);
				System.out.println("testSpot: " + testSpot);
				
				
				int[] yPos = {429, 475, 519, 567, 624, 677, 718};
				for(int i = 0; i < 10; ++i) {
					
					System.out.println("Searching for bitcoin in list:");
					System.out.println("currentlyWatchingBitcoin: " + vars.currentlyWatchingBitcoin);
					System.out.println("testSpot: " + testSpot);
					
					if(testSpot.equals(vars.currentlyWatchingBitcoin)) {
						System.out.println(testSpot + " equals " + vars.currentlyWatchingBitcoin);
						//the name at the top matches
						//exit out of trade box
						robot.mouseMove(204, 156);
						RF.click(robot);
						robot.delay(500);
						robot.setAutoDelay(vars.delayNum);
						break;
					}
					else if(!testSpot.equals(vars.currentlyWatchingBitcoin)) {
						System.out.println(testSpot + " does not equal " + vars.currentlyWatchingBitcoin);
					//click USD
					robot.mouseMove(86, 271);
					RF.click(robot);
					robot.delay(500);
					robot.setAutoDelay(vars.delayNum);
					
					//click in bitcoin finder box
					robot.mouseMove(71, 227);
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);
					
					//paste TO COIN from market section
					toCoin = new StringSelection(vars.currentlyWatchingBitcoin);
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(toCoin, null);
					
					//paste name of coin from market section
					RF.paste(robot);
					robot.setAutoDelay(vars.delayNum);
					robot.delay(500);
					
					//click option
					robot.mouseMove(138, yPos[i]);
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
					
					///CHECKING to see if right one
					//highlight under "market" to get name of coin (doesnt have to replace/remove any chars)
					robot.mouseMove(57, 158);
					robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					robot.mouseMove(158, 158);
					robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					
					RF.copySelection(robot);
					robot.setAutoDelay(vars.delayNum);
					
					testSpot = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
					testSpot = testSpot.replaceAll("-USD", "");
					testSpot = testSpot.replaceAll("\n", "");
					}
				}
				
				//click buy
				robot.mouseMove(83, 482);
				RF.click(robot);
				robot.delay(500);
				robot.setAutoDelay(vars.delayNum);
				
				//click market
				robot.mouseMove(71, 521);
				robot.delay(200);
				RF.click(robot);
				robot.setAutoDelay(vars.delayNum);
				
				//click max
				robot.mouseMove(53, 598);
				robot.delay(200);
				RF.click(robot);
				robot.setAutoDelay(vars.delayNum);
				
				/*
				//click place buy order
				robot.mouseMove(138, 733);
				robot.delay(1000);
				RF.click(robot);
				robot.setAutoDelay(vars.delayNum);
				*/
				
				//if says market orders are disabled do limit
				robot.mouseMove(94, 778);
				if(robot.getPixelColor(94, 778).getRed() > 230 && robot.getPixelColor(94, 778).getGreen() > 80
						&& robot.getPixelColor(94, 778).getBlue() < 52) {
					
					//click limit
					robot.mouseMove(148, 521);
					robot.delay(200);
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);
					
					//highlight and copy "last trade price" at top
					robot.mouseMove(325, 150);
					robot.delay(200);
					RF.click(robot);
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);
					
					RF.copySelection(robot);
					robot.setAutoDelay(vars.delayNum);
					
					String SPrice = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
					double price = Double.parseDouble(SPrice);
					
					//calculate 102 percent of the last trade price
					price = price * 1.02;
					
					double roundOff = Math.round(price * 100.0) / 100.0;
					
					SPrice = Double.toString(roundOff);
					
					//put new price to clipboard
					StringSelection clipPrice = new StringSelection(SPrice);
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(clipPrice, null);
					
					//move mouse and click limit price box
					robot.mouseMove(58, 671);
					robot.delay(200);
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);
					
					RF.paste(robot);
					robot.delay(200);
					robot.setAutoDelay(vars.delayNum);
					
					//click max
					robot.mouseMove(50, 600);
					robot.delay(200);
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);
					
					//push page down
					robot.keyPress(34);
					robot.keyRelease(34);
					robot.delay(300);
					
					/*
					//click place buy order
					robot.mouseMove(73, 728);
					robot.delay(200);
					RF.click(robot);
					robot.setAutoDelay(vars.delayNum);
					*/
					
					//if still cannot buy
					robot.mouseMove(38, 785);
					robot.delay(400);
					if(robot.getPixelColor(38, 785).getRed() > 230 && robot.getPixelColor(38, 785).getGreen() > 80
							&& robot.getPixelColor(38, 785).getBlue() < 52) {
						failBuy = true;
						System.out.println("failBuy: " + failBuy);
					}
					
				}
				
			}
				
				robot.delay(500);
				RF.openProgram(robot);
				robot.delay(2000);

				return null;
			}
			@Override
			protected void done() {
				FileIO.saveProgramState();
				Var vars = Var.getVars();
				var logLists = LogLists.getLists();
				System.out.println("IN DONE OF SWITCHROBOT");
				if(vars.trySellBitcoin == true) {
					System.out.println("vars.trySellBitcoin == true");
					vars.bitcoinNoBuyBack = vars.currentlyWatchingBitcoin;
					System.out.println("vars.bitcoinNoBuyBack: " + vars.bitcoinNoBuyBack);

		            //Estimating trading fees
		            vars.bitcoinPriceGlobal = vars.bitcoinPriceGlobal * 0.995;
		            vars.calcBitcoinChange = Calc.calcChange(vars.startingBitcoinPrice, vars.bitcoinPriceGlobal);
					
					GUI.bitcoinTotalChangeField.setText(Double.toString(Calc.calcBitcoinGL(vars.calcBitcoinChange)));
					
					//record values for cutoff points
					if(vars.bitcoinPcHigh > 2) {
						//record high point, low point, and calculate means
						vars.gainHighpoints.add(vars.bitcoinPcHigh);
						vars.gainLowpoints.add(vars.bitcoinPcLow);
						
						vars.averageGainHighpoint = Calc.calcDoubleArrayMean(vars.gainHighpoints);
						vars.averageGainLowpoint = Calc.calcDoubleArrayMean(vars.gainLowpoints);;
					} else {
						//record high point, low point, and calculate means
						vars.lossHighpoints.add(vars.bitcoinPcHigh);
						vars.lossLowpoints.add(vars.bitcoinPcLow);
						
						vars.averageLossHighpoint = Calc.calcDoubleArrayMean(vars.lossHighpoints);
						vars.averageLossLowpoint = Calc.calcDoubleArrayMean(vars.lossLowpoints);;
					}
					
					String logEntry = TimeManager.getCurrentTime() + "Sold " + vars.currentlyWatchingBitcoin +
						"\nSold for: " + vars.calcBitcoinChange + "%" + " at " + vars.bitcoinPriceGlobal +
				        "\nPC high point: " + vars.bitcoinPcHigh + "%" + " at " + vars.highestBitcoinPrice +
				        "\nPC low point: " + vars.bitcoinPcLow + "%" + " at " + vars.lowestBitcoinPrice + 
				        "\nAverage gain highpoint: " + vars.averageGainHighpoint +
				        "\nAverage gain lowpoint: " + vars.averageGainLowpoint +
				        "\nAverage loss highpoint: " + vars.averageLossHighpoint +
				        "\nAverage loss lowpoint: " + vars.averageLossLowpoint +
				        "\nTotal G/L reference num: " + vars.bitcoinCurrentGL +
			            "\n------------------------------------";
					System.out.println(logEntry);
					System.out.println();
					GUI.bitcoinLogArea.append(logEntry + "\n");
					logLists.bitcoinLogList.add(logEntry);
					
					FileIO.saveLogs();

					Var.resetBitcoinSell();
					FileIO.saveProgramState();
					
					BitGainRobot.gainTracker();
				}
				//vars.tryBuyBitcoin == true
				else if(vars.currentlyWatchingBitcoin.equals("null")) {
					System.out.println("vars.tryBuyBitcoin == true");
					//enters here if buying
					
					Var.resetBitcoinBeforeBuy();
					
					vars.madeBitcoinBuy = true;
					
					String logEntry = TimeManager.getCurrentTime() + "Bought " + vars.currentlyWatchingBitcoin;
					System.out.println(logEntry);
					System.out.println();
					GUI.bitcoinLogArea.append(logEntry + "\n");
					logLists.bitcoinLogList.add(logEntry);
					
					FileIO.saveLogs();
					
					GUI.bitcoinCurrentlyWatchingField.setText(vars.currentlyWatchingBitcoin);
					
					TrackerRobots.bitcoinTrackerRobot();
				} 
				else {
					System.out.println("else statement");
					//enters here if switching
					vars.madeBitcoinBuy = true;
					
		            //Estimating trading fees
					System.out.println("vars.bitcoinPriceGlobal: " + vars.bitcoinPriceGlobal);
		            vars.bitcoinPriceGlobal = vars.bitcoinPriceGlobal * 0.995;
		            vars.calcBitcoinChange = Calc.calcChange(vars.startingBitcoinPrice, vars.bitcoinPriceGlobal);

					GUI.bitcoinTotalChangeField.setText(Double.toString(Calc.calcBitcoinGL(vars.calcBitcoinChange)));
		          
					//record values for cutoff points
					if(vars.bitcoinPcHigh > 2) {
						//record high point, low point, and calculate means
						vars.gainHighpoints.add(vars.bitcoinPcHigh);
						vars.gainLowpoints.add(vars.bitcoinPcLow);
						
						vars.averageGainHighpoint = Calc.calcDoubleArrayMean(vars.gainHighpoints);
						vars.averageGainLowpoint = Calc.calcDoubleArrayMean(vars.gainLowpoints);;
					} else {
						//record high point, low point, and calculate means
						vars.lossHighpoints.add(vars.bitcoinPcHigh);
						vars.lossLowpoints.add(vars.bitcoinPcLow);
						
						vars.averageLossHighpoint = Calc.calcDoubleArrayMean(vars.lossHighpoints);
						vars.averageLossLowpoint = Calc.calcDoubleArrayMean(vars.lossLowpoints);;
					}
					
					String logEntry = TimeManager.getCurrentTime() + "Switched from " + vars.currentlyWatchingBitcoin +
							" to " + vars.currentTopBit +
						"\nSold for: " + vars.calcBitcoinChange + "%" + " at " + vars.bitcoinPriceGlobal +
			            "\nPC high point: " + vars.bitcoinPcHigh + "%" + " at " + vars.highestBitcoinPrice +
			            "\nPC low point: " + vars.bitcoinPcLow + "%" + " at " + vars.lowestBitcoinPrice + 
			            "\nAverage gain highpoint: " + vars.averageGainHighpoint +
			            "\nAverage gain lowpoint: " + vars.averageGainLowpoint +
			            "\nAverage loss highpoint: " + vars.averageLossHighpoint +
			            "\nAverage loss lowpoint: " + vars.averageLossLowpoint +
			            "\nTotal G/L reference num: " + vars.bitcoinCurrentGL +
			            "\n------------------------------------";
					System.out.println(logEntry);
					System.out.println();
					GUI.bitcoinLogArea.append(logEntry + "\n");
					logLists.bitcoinLogList.add(logEntry);
					
					FileIO.saveLogs();

					Var.moveBitcoinValues();
					
					GUI.bitcoinCurrentlyWatchingField.setText(vars.currentlyWatchingBitcoin);
					
					TrackerRobots.bitcoinTrackerRobot();
				}
			}
		};
		checkerRobot.execute();
	}
}
