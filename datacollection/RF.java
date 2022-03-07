package investing.crypto.datacollection;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.io.File;

import javax.swing.SwingWorker;

/**
 * <h2>RF</h2> Provides a variety of robot functions that are frequently used in
 * the SwingWorkers.
 * 
 * @author Dylan Skokan
 * @since 2/23/22
 */
public class RF {

	static boolean pauseMode = false;

	static void click(Robot robot) {
		robot.setAutoDelay(200);

		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	static void websiteSearchBox(Robot robot) {
		robot.setAutoDelay(200);

		robot.mouseMove(190, 59);
	}

	static void highlightAll(Robot robot) {
		robot.setAutoDelay(200);

		robot.keyPress(17);
		robot.keyPress(65);

		robot.keyRelease(17);
		robot.keyRelease(65);
	}

	static void copySelection(Robot robot) {
		robot.setAutoDelay(200);

		robot.keyPress(17);
		robot.keyPress(67);

		robot.keyRelease(17);
		robot.keyRelease(67);
	}

	// ctrl a backspace (clear area)
	static void backspaceAll(Robot robot) {
		robot.setAutoDelay(200);

		robot.keyPress(17);
		robot.keyPress(65);

		robot.keyRelease(17);
		robot.keyRelease(65);

		robot.keyPress(8);
		robot.keyRelease(8);
	}

	// Paste text and enter
	static void pasteEnter(Robot robot) {
		robot.setAutoDelay(200);

		robot.keyPress(17);
		robot.keyPress(86);

		robot.keyRelease(17);
		robot.keyRelease(86);

		robot.keyPress(10);
		robot.keyRelease(10);
	}

	// Paste without enter
	static void paste(Robot robot) {
		robot.setAutoDelay(200);

		robot.keyPress(17);
		robot.keyPress(86);

		robot.keyRelease(17);
		robot.keyRelease(86);
	}

	// click to program
	static void openProgram(Robot robot) {
		robot.setAutoDelay(200);

		robot.mouseMove(514, 846);

		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	// click to chrome
	static void openChrome(Robot robot) {
		robot.setAutoDelay(200);

		robot.mouseMove(562, 846);

		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	// click watchers tab
	static void clickFirstTab(Robot robot) {
		robot.setAutoDelay(200);

		robot.mouseMove(126, 17);

		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	// click coinbase tab
	static void clickSecondTab(Robot robot) {
		robot.setAutoDelay(200);

		robot.mouseMove(364, 18);

		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	// click to obs
	static void openOBS(Robot robot) {
		robot.setAutoDelay(200);

		robot.mouseMove(665, 843);

		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	// click lists input button
	static void clickListInputButton(Robot robot) {
		robot.setAutoDelay(200);

		robot.mouseMove(1386, 81);

		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	// click in lists input box
	static void clickListInputBox(Robot robot) {
		robot.setAutoDelay(200);

		robot.mouseMove(58, 81);

		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	// click stock input button
	static void clickStockInputButton(Robot robot) {
		robot.setAutoDelay(200);

		robot.mouseMove(662, 433);

		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	// click in stock input box
	static void clickStockInputBox(Robot robot) {
		robot.setAutoDelay(200);

		robot.mouseMove(55, 430);

		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	// click bitcoin input button
	static void clickBitcoinInputButton(Robot robot) {
		robot.setAutoDelay(200);

		robot.mouseMove(651, 65);

		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	// click in bitcoin input box
	static void clickBitcoinInputBox(Robot robot) {
		robot.setAutoDelay(200);

		robot.mouseMove(550, 64);

		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	static void checkAndLoginCoinbase(Robot robot) throws AWTException {
		robot.setAutoDelay(200);
		robot.mouseMove(112, 771);

		if (robot.getPixelColor(112, 771).getRed() <= 25 && robot.getPixelColor(112, 771).getGreen() <= 85
				&& robot.getPixelColor(112, 771).getBlue() >= 235) {
			//send message in discord
		}
	}

	// Robot functions that need their own swingworker, and robot that are called in
	// main thread are here
	// start programs
	static void startPrograms(boolean fileCheck) {
		SwingWorker<Void, Void> startProgramsWorker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {

				System.out.println("in startPrograms: fileCheck = " + fileCheck + "\n");

				Robot robot = new Robot();
				robot.setAutoDelay(200);

				robot.delay(6000);

				if (pauseMode == true) {
					while (true) {
						robot.delay(59999);
					}
				}

				// drag PopInvestor to first spot from fifth spot
				robot.mouseMove(714, 844);

				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

				robot.mouseMove(498, 842);

				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

				// open OBS
				openOBS(robot);
				robot.setAutoDelay(200);

				robot.delay(8000);

				// start recording
				robot.keyPress(17);
				robot.keyPress(16);
				robot.keyPress(82);

				robot.keyRelease(82);
				robot.keyRelease(16);
				robot.keyRelease(17);

				robot.delay(5000);

				openChrome(robot);
				robot.setAutoDelay(200);

				// wait for chrome to load and go to watchers
				robot.delay(4000);

				// move mouse to website search
				robot.mouseMove(550, 51);

				// click in website search
				click(robot);
				robot.setAutoDelay(200);

				// cntrl a and backspace
				backspaceAll(robot);
				robot.setAutoDelay(200);

				StringSelection websiteURL = new StringSelection("https://www.coinbase.com/trade");
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(websiteURL, null);

				pasteEnter(robot);
				robot.setAutoDelay(200);

				robot.delay(6000);

				// if coinbase is signed out, sign in
				checkAndLoginCoinbase(robot);
				robot.setAutoDelay(200);

				robot.delay(1000);

				// open new tab and go to coinbase
				robot.mouseMove(270, 17);

				click(robot);
				robot.setAutoDelay(200);

				// move mouse to website search
				robot.mouseMove(550, 51);

				// click in website search
				click(robot);
				robot.setAutoDelay(200);

				StringSelection websiteURLcoinbase = new StringSelection("https://pro.coinbase.com/trade/");
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(websiteURLcoinbase, null);

				pasteEnter(robot);
				robot.setAutoDelay(200);

				robot.delay(6000);

				// check for banner at top of coinbase pro tab
				if (robot.getPixelColor(995, 90).getRed() < 2 && robot.getPixelColor(995, 90).getGreen() > 70
						&& robot.getPixelColor(995, 90).getBlue() > 250) {
					robot.mouseMove(1514, 110);

					RF.click(robot);
					robot.setAutoDelay(200);
					robot.delay(1000);
				}

				// click back to program
				openProgram(robot);
				robot.setAutoDelay(200);

				return null;
			}

			@Override
			protected void done() {
				var vars = Var.getVars();
				var logLists = LogLists.getLists();
				if (fileCheck == true) {
					// if there is a save file, meaning program was closed prematurely due to crash
					// or other reason
					// start program where it left off

					// if computer turned off in the middle of trying to sell stock
					if (vars.trySellBitcoin == true) {
						String logEntry;
						logEntry = "Entering SwitchRobot.bitcoinSwitchRobot(); from startPrograms";
						System.out.println(logEntry + "\n");
						GUI.listsLogArea.append(logEntry + "\n");
						logLists.bitcoinLogList.add(logEntry);
						FileIO.saveLogs();

						SwitchRobot.bitcoinSwitchRobot();
					} else if (vars.madeBitcoinBuy == true) {
						System.out.println("fileCheck == true, program status:" + "\nVar.madeBitcoinBuy == true\n");

						String logEntry;
						logEntry = "Entering bitcoinTrackerRobot from startPrograms";
						System.out.println(logEntry + "\n");
						GUI.listsLogArea.append(logEntry + "\n");
						logLists.bitcoinLogList.add(logEntry);
						FileIO.saveLogs();

						TrackerRobots.bitcoinTrackerRobot();
					} else {
						String logEntry;
						logEntry = "Entering listRobot from startPrograms";
						System.out.println(logEntry + "\n");
						GUI.listsLogArea.append(logEntry + "\n");
						logLists.bitcoinLogList.add(logEntry);
						FileIO.saveLogs();

						BitGainRobot.gainTracker();
					}

				} else if (fileCheck == false) {
					// if there is no save file, meaning program successfully restarted, wait for
					// 9pm
					System.out.println("fileCheck == false && TimeManager.checkHoursType(isNightHours) == false\n");

					String logEntry;
					logEntry = "Entering listRobot from startPrograms";
					System.out.println(logEntry + "\n");
					GUI.listsLogArea.append(logEntry + "\n");
					logLists.bitcoinLogList.add(logEntry);
					FileIO.saveLogs();

					BitGainRobot.gainTracker();
				}
			}
		};
		startProgramsWorker.execute();
	}

	// restart computer
	static void restartComputer(boolean deleteState) throws AWTException {
		if (deleteState == true) {
			try {
				File programStateFile = new File("saveStates/programState.ser");

				if (programStateFile.exists()) {
					programStateFile.delete();

					System.out.println(programStateFile.exists() ? "programStateFile.delete() did not delete file\n"
							: "programState deleted\n");

				} else {
					System.out.println("programStateFile does not exist at path specified\n");
				}
			} catch (Exception e) {
				System.out.println("Error in deleting programState:\n" + e.getMessage());
			}
		}

		Robot robot = new Robot();
		robot.setAutoDelay(600);

		// open OBS
		robot.mouseMove(665, 843);

		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

		robot.delay(10000);

		robot.mouseMove(1200, 812);

		// check if a recording is going
		// needs to scan for red dot
		int xPos = 1200;
		boolean isRecording = true;

		robot.setAutoDelay(20);
		while (!(robot.getPixelColor(xPos, 812).getRed() == 212 && robot.getPixelColor(xPos, 812).getGreen() == 0
				&& robot.getPixelColor(xPos, 812).getBlue() == 0)) {
			xPos = xPos + 2;

			robot.mouseMove(xPos, 812);

			if (xPos > 1350) {
				isRecording = false;
				break;
			}
		}
		robot.setAutoDelay(600);

		if (isRecording == true) {
			// stop recording (cntrl shift r)
			robot.keyPress(17);
			robot.keyPress(16);
			robot.keyPress(82);

			robot.keyRelease(82);
			robot.keyRelease(16);
			robot.keyRelease(17);

			robot.delay(2000);

			robot.mouseMove(xPos, 812);

			// while the red dot is still there, wait
			while (robot.getPixelColor(xPos, 812).getRed() == 212 && robot.getPixelColor(xPos, 812).getGreen() == 0
					&& robot.getPixelColor(xPos, 812).getBlue() == 0) {
				robot.delay(1000);
			}
		}
		// restart computer

		// 524 = windows key
		// 88 = x
		// 85 = u
		// 82 = r
		// 16 = shift

		robot.keyPress(524);
		robot.keyPress(88);

		robot.keyRelease(88);
		robot.keyRelease(524);

		robot.delay(1000);

		robot.keyPress(85);
		robot.keyRelease(85);

		robot.delay(1000);

		robot.keyPress(82);
		robot.keyRelease(82);

		robot.delay(1000);

		robot.keyPress(10);
		robot.keyRelease(10);

		robot.delay(10000);

		// try shift r
		robot.keyPress(16);
		robot.keyPress(82);

		robot.keyRelease(82);
		robot.keyRelease(16);

		robot.delay(10000);

		// click restart in case it does not go through
		robot.mouseMove(309, 768);

		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
}
