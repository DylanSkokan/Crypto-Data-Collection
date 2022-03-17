package investing.crypto.datacollection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * <h2>FileIO</h2> Responsible for saving and loading program save states and
 * log information
 * 
 * @author Dylan Skokan
 * @since 2/23/22
 */
public class FileIO implements Serializable {

	private static final long serialVersionUID = 8163610090819096179L;

	/**
	 * Checks if the folders exist for the logs and save states, if they do not
	 * create them.
	 */
	public static void checkForFolders() {
		if (!Files.isDirectory(Paths.get("saveStates"))) {
			try {
				Files.createDirectories(Paths.get("saveStates"));
			} catch (IOException e) {
				System.out.println("Error in creating save state folder:\n" + e.getMessage());
				e.printStackTrace();
			}
		}
		if (!Files.isDirectory(Paths.get("logFiles"))) {
			try {
				Files.createDirectories(Paths.get("logFiles"));
			} catch (IOException e) {
				System.out.println("Error in creating log folder:\n" + e.getMessage());
				e.printStackTrace();
			}
		}
		if (!Files.isDirectory(Paths.get("methodSuccess"))) {
			try {
				Files.createDirectories(Paths.get("methodSuccess"));
			} catch (IOException e) {
				System.out.println("Error in creating methodSuccess folder:\n" + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/**
	 * Sets the output of log information to a log file which is named after the
	 * current time and date.
	 * 
	 * @param logFileName the name of the file to output log information to.
	 */
	public static void setLogOutput(String logFileName) {
		PrintStream consoleOut;
		File logFileLists = new File(logFileName);

		try {
			consoleOut = new PrintStream(logFileLists);
			System.setOut(consoleOut);
			System.setErr(consoleOut);
		} catch (FileNotFoundException e) {
			System.out.println("Error in creating log file:\n" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Saves the current state of the log area to its file.
	 */
	public static void saveLogs() {
		LogLists lists = LogLists.getLists();

		try {
			FileOutputStream fos = new FileOutputStream("saveStates/bitcoinLogList.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(lists.bitcoinLogList);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Loads and writes to the log area what is in the log list file.
	 */
	@SuppressWarnings("unchecked")
	public static void loadLogs() {
		LogLists lists = LogLists.getLists();

		File bitcoinStockFile = new File("saveStates/bitcoinLogList.ser");
		if (bitcoinStockFile.exists()) {
			try {
				FileInputStream fileIn = new FileInputStream("saveStates/bitcoinLogList.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				lists.bitcoinLogList = (ArrayList<String>) in.readObject();
				in.close();
				fileIn.close();
			} catch (IOException | ClassNotFoundException i1) {
				i1.printStackTrace();
				return;
			}
			for (int i = 0; i < lists.bitcoinLogList.size(); ++i) {
				GUI.bitcoinLogArea.append(lists.bitcoinLogList.get(i) + "\n");
			}
		}
	}

	/**
	 * Saves the current state of the variables in the program to a file.
	 */
	public static void saveProgramState() {
		try {
			FileOutputStream fos = new FileOutputStream("saveStates/programState.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(Var.getVars());

			oos.close();
			fos.close();
		} catch (IOException ioe) {
			System.out.println("Error in saving programState:\n" + ioe.getMessage());
			ioe.printStackTrace();
		}
	}

	/**
	 * Loads the current state of the program variables from a file.
	 */
	public static boolean loadProgramState() {
		File programStateFile = new File("saveStates/programState.ser");
		if (programStateFile.exists()) {
			try {
				FileInputStream fileIn = new FileInputStream("saveStates/programState.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);

				Var.setVars((Var) in.readObject());

				in.close();
				fileIn.close();

				return true;
			} catch (IOException | ClassNotFoundException i1) {
				System.out.println("Error in reading programState.ser:\n" + i1.getMessage());
				i1.printStackTrace();
			}
		} else {
			return false;
		}
		return false;
	}
}