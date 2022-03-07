package investing.crypto.datacollection;

import java.awt.AWTException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <h2>TimeManager</h2> Provides a variety of functions for checking and managing time.
 * 
 * @author Dylan Skokan
 * @since 2/23/22
 */
public class TimeManager {

	public static void midnightCheck() {
		var vars = Var.getVars();

		if (vars.isMidnight == false && TimeManager.checkHoursType("isMidnight") == true) {
			if (vars.madeBitcoinBuy == true) {
				vars.bitcoinDaysPassed++;
			}
			vars.isMidnight = true;
			FileIO.saveProgramState();
			FileIO.saveLogs();
			System.out.println("It is midnight, restarting computer");
			try {
				RF.restartComputer(false);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (vars.isMidnight == true && TimeManager.checkHoursType("isMidnight") == false) {

			System.out.println("It is no longer midnight, setting isMidnight to false");

			vars.isMidnight = false;
		}
	}

	public static String getCurrentTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd hh:mm.ss a EE");
		LocalDateTime now = LocalDateTime.now();
		String dateTime = "[" + dtf.format(now) + "] ";

		return dateTime;
	}

	public static String getCurrentTimeWB() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd hh:mm.ss a EE");
		LocalDateTime now = LocalDateTime.now();
		String dateTime = dtf.format(now);

		return dateTime;
	}

	public static String getCurrentTimeFileFormat() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM.dd hh;mm.ss a EE");
		LocalDateTime now = LocalDateTime.now();
		String dateTime = dtf.format(now);

		return dateTime;
	}

	public static boolean checkHoursType(String returnType) {
		String dateTime = getCurrentTime();

		boolean isAfterHours = false;
		boolean isNightHours = false;
		boolean isOpenTime = false;
		boolean isMidnight = false;
		boolean is3am = false;

		// Check if it is after hours or night hours or weekend
		if (Character.getNumericValue(dateTime.charAt(7)) == 0 && Character.getNumericValue(dateTime.charAt(8)) == 8
				&& Character.getNumericValue(dateTime.charAt(10)) >= 3 && dateTime.charAt(16) == 'A') {
			isAfterHours = false;
			isNightHours = false;
			isOpenTime = true;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 0
				&& Character.getNumericValue(dateTime.charAt(8)) == 9 && dateTime.charAt(16) == 'A') {
			isAfterHours = false;
			isNightHours = false;
			isOpenTime = true;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 1
				&& Character.getNumericValue(dateTime.charAt(8)) == 0 && dateTime.charAt(16) == 'A') {
			isAfterHours = false;
			isNightHours = false;
			isOpenTime = true;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 1
				&& Character.getNumericValue(dateTime.charAt(8)) == 1 && dateTime.charAt(16) == 'A') {
			isAfterHours = false;
			isNightHours = false;
			isOpenTime = true;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 1
				&& Character.getNumericValue(dateTime.charAt(8)) == 2 && dateTime.charAt(16) == 'P') {
			isAfterHours = false;
			isNightHours = false;
			isOpenTime = true;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 0
				&& Character.getNumericValue(dateTime.charAt(8)) == 1 && dateTime.charAt(16) == 'P') {
			isAfterHours = false;
			isNightHours = false;
			isOpenTime = true;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 0
				&& Character.getNumericValue(dateTime.charAt(8)) == 2 && dateTime.charAt(16) == 'P') {
			isAfterHours = false;
			isNightHours = false;
			isOpenTime = true;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 0
				&& Character.getNumericValue(dateTime.charAt(8)) == 3 && dateTime.charAt(16) == 'P') {
			isAfterHours = true;
			isNightHours = false;
			isOpenTime = false;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 0
				&& Character.getNumericValue(dateTime.charAt(8)) == 4 && dateTime.charAt(16) == 'P') {
			isAfterHours = true;
			isNightHours = false;
			isOpenTime = false;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 0
				&& Character.getNumericValue(dateTime.charAt(8)) == 5 && dateTime.charAt(16) == 'P') {
			isAfterHours = true;
			isNightHours = false;
			isOpenTime = false;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 0
				&& Character.getNumericValue(dateTime.charAt(8)) == 6 && dateTime.charAt(16) == 'P') {
			isAfterHours = true;
			isNightHours = false;
			isOpenTime = false;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 0
				&& Character.getNumericValue(dateTime.charAt(8)) == 7 && dateTime.charAt(16) == 'P') {
			isAfterHours = false;
			isNightHours = true;
			isOpenTime = false;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 0
				&& Character.getNumericValue(dateTime.charAt(8)) == 8 && dateTime.charAt(16) == 'P') {
			isAfterHours = false;
			isNightHours = true;
			isOpenTime = false;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 0
				&& Character.getNumericValue(dateTime.charAt(8)) == 9 && dateTime.charAt(16) == 'P') {
			isAfterHours = false;
			isNightHours = true;
			isOpenTime = false;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 1
				&& Character.getNumericValue(dateTime.charAt(8)) == 0 && dateTime.charAt(16) == 'P') {
			isAfterHours = false;
			isNightHours = true;
			isOpenTime = false;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 1
				&& Character.getNumericValue(dateTime.charAt(8)) == 1 && dateTime.charAt(16) == 'P') {
			isAfterHours = false;
			isNightHours = true;
			isOpenTime = false;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 1
				&& Character.getNumericValue(dateTime.charAt(8)) == 2 && dateTime.charAt(16) == 'A') {
			isAfterHours = false;
			isNightHours = true;
			isOpenTime = false;
			isMidnight = true;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 0
				&& Character.getNumericValue(dateTime.charAt(8)) == 1 && dateTime.charAt(16) == 'A') {
			isAfterHours = false;
			isNightHours = true;
			isOpenTime = false;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 0
				&& Character.getNumericValue(dateTime.charAt(8)) == 2 && dateTime.charAt(16) == 'A') {
			isAfterHours = false;
			isNightHours = true;
			isOpenTime = false;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 0
				&& Character.getNumericValue(dateTime.charAt(8)) == 3 && dateTime.charAt(16) == 'A') {
			isAfterHours = true;
			isNightHours = false;
			isOpenTime = false;
			isMidnight = false;
			is3am = true;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 0
				&& Character.getNumericValue(dateTime.charAt(8)) == 4 && dateTime.charAt(16) == 'A') {
			isAfterHours = true;
			isNightHours = false;
			isOpenTime = false;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 0
				&& Character.getNumericValue(dateTime.charAt(8)) == 5 && dateTime.charAt(16) == 'A') {
			isAfterHours = true;
			isNightHours = false;
			isOpenTime = false;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 0
				&& Character.getNumericValue(dateTime.charAt(8)) == 6 && dateTime.charAt(16) == 'A') {
			isAfterHours = true;
			isNightHours = false;
			isOpenTime = false;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 0
				&& Character.getNumericValue(dateTime.charAt(8)) == 7 && dateTime.charAt(16) == 'A') {
			isAfterHours = true;
			isNightHours = false;
			isOpenTime = false;
			isMidnight = false;
		} else if (Character.getNumericValue(dateTime.charAt(7)) == 0
				&& Character.getNumericValue(dateTime.charAt(8)) == 8
				&& Character.getNumericValue(dateTime.charAt(10)) < 3 && dateTime.charAt(16) == 'A') {
			isAfterHours = true;
			isNightHours = false;
			isOpenTime = false;
			isMidnight = false;
		}
		// check for holidays 2021
		if (dateTime.substring(1, 3).equals("01") && dateTime.substring(4, 6).equals("01")) {
			// new years
			isAfterHours = false;
			isNightHours = true;
			isOpenTime = false;
			isMidnight = false;
		}
		if (dateTime.substring(1, 3).equals("01") && dateTime.substring(4, 6).equals("18")) {
			// mlk day
			isAfterHours = false;
			isNightHours = true;
			isOpenTime = false;
			isMidnight = false;
		}
		if (dateTime.substring(1, 3).equals("02") && dateTime.substring(4, 6).equals("15")) {
			// washingtons birthday
			isAfterHours = false;
			isNightHours = true;
			isOpenTime = false;
			isMidnight = false;
		}
		if (dateTime.substring(1, 3).equals("04") && dateTime.substring(4, 6).equals("02")) {
			// good friday
			isAfterHours = false;
			isNightHours = true;
			isOpenTime = false;
			isMidnight = false;
		}
		if (dateTime.substring(1, 3).equals("05") && dateTime.substring(4, 6).equals("31")) {
			// memorial day
			isAfterHours = false;
			isNightHours = true;
			isOpenTime = false;
			isMidnight = false;
		}
		if (dateTime.substring(1, 3).equals("07") && dateTime.substring(4, 6).equals("05")) {
			// independence day
			isAfterHours = false;
			isNightHours = true;
			isOpenTime = false;
			isMidnight = false;
		}
		if (dateTime.substring(1, 3).equals("09") && dateTime.substring(4, 6).equals("06")) {
			// labor day
			isAfterHours = false;
			isNightHours = true;
			isOpenTime = false;
			isMidnight = false;
		}
		if (dateTime.substring(1, 3).equals("11") && dateTime.substring(4, 6).equals("25")) {
			// thanksgiving
			isAfterHours = false;
			isNightHours = true;
			isOpenTime = false;
			isMidnight = false;
		}
		if (dateTime.substring(1, 3).equals("12") && dateTime.substring(4, 6).equals("24")) {
			// christmas
			isAfterHours = false;
			isNightHours = true;
			isOpenTime = false;
			isMidnight = false;
		}
		// check for weekend
		if (dateTime.charAt(19) == 'S' && (dateTime.charAt(20) == 'a' || dateTime.charAt(20) == 'u')) {
			isAfterHours = false;
			isNightHours = true;
			isOpenTime = false;
		}
		if (dateTime.charAt(19) == 'S' && dateTime.charAt(20) == 'u') {
			isAfterHours = false;
			isNightHours = true;
			isOpenTime = false;
		}
		if (dateTime.charAt(19) == 'F') {

		}

		if (returnType.equals("isAfterHours")) {
			return isAfterHours;
		} else if (returnType.equals("isNightHours")) {
			return isNightHours;
		} else if (returnType.equals("isOpenTime")) {
			return isOpenTime;
		} else if (returnType.equals("isMidnight")) {
			return isMidnight;
		} else if (returnType.equals("is3am")) {
			return is3am;
		}
		return false;
	}
}
