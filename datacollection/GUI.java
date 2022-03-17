package investing.crypto.datacollection;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * <h2>GUI</h2> Creates the GUI for user interactivity and information
 * availability. At the end of the constructor, it runs another function which
 * starts the chain of robot actions.
 * 
 * @author Dylan Skokan
 * @since 2/23/22
 */
public class GUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	static JButton startRobotButton;

	// Bitcoin side
	static JButton bitcoinInputButton;
	static JTextField bitcoinInputField;
	static JTextField bitcoinCurrentlyWatchingField;
	static JTextField bitcoinScoreDifferenceField;
	static JTextField bitcoinIncreaseSinceStartField;
	static JTextField bitcoinPercentChangeHighPointField;
	static JTextField bitcoinDaysPassedField;
	static JTextField bitcoinCutoffValField;
	static JTextField bitcoinPcHighDayField;
	static JTextField bitcoinPcLowDayField;
	static JTextField bitcoinTotalChangeField;
	static JLabel bitcoinCurrentlyWatchingLabel;
	static JLabel bitcoinScoreDifferenceLabel;
	static JLabel bitcoinIncreaseSinceStartLabel;
	static JLabel bitcoinLogLabel;
	static JLabel bitcoinPercentChangeHighPointLabel;
	static JLabel bitcoinDaysPassedLabel;
	static JLabel bitcoinCutoffValLabel;
	static JLabel bitcoinPcHighDayLabel;
	static JLabel bitcoinPcLowDayLabel;
	static JLabel bitcoinRobotLabel;
	static JLabel bitcoinTotalChangeLabel;
	static JScrollPane bitcoinLogScrollPane;
	static JTextArea bitcoinLogArea;

	// Top side
	static JButton listsInputButton;
	static JTextField listsInputField;
	static JLabel listsLabel;
	static JScrollPane listsLogScrollPane;
	static JTextArea listsLogArea;

	/**
	 * This constructor creates the GUI frame and all the elements inside of it, as
	 * well as setting up program files and folders.
	 */
	GUI() {
		FileIO.checkForFolders();
		boolean fileCheck = FileIO.loadProgramState();
		var vars = Var.getVars();
		var logLists = LogLists.getLists();
		
		// populate methods to see if this is empty
		if(vars.methodResults.isEmpty()) {
			for(int i = 1; i < 150; i++) {
				for(int j = -100; j < -1; j++) {
					Methods newMethod = new Methods(i, j);
					vars.methodResults.add(newMethod);
				}
			}
		}

		FileIO.setLogOutput("logFiles/" + TimeManager.getCurrentTimeFileFormat() + ".txt");
		
		System.out.println(Arrays.asList(vars.methodResults));

		setTitle("Crypto Data Collection");
		setResizable(true);

		// top side
		startRobotButton = new JButton("Pause");
		startRobotButton.addActionListener(this);

		listsInputButton = new JButton("Input");
		listsInputButton.addActionListener(this);

		listsInputField = new JTextField(1);
		listsInputField.setEditable(true);

		listsLabel = new JLabel("LIST COLLECTION");

		listsLogArea = new JTextArea(18, 1);
		listsLogArea.setEditable(false);
		listsLogArea.setLineWrap(true);

		// Bitcoin Section
		bitcoinInputButton = new JButton("Input");
		bitcoinInputButton.addActionListener(this);

		bitcoinInputField = new JTextField(50);
		bitcoinInputField.setEditable(true);

		bitcoinCurrentlyWatchingField = new JTextField(20);
		bitcoinCurrentlyWatchingField.setEditable(false);

		bitcoinScoreDifferenceField = new JTextField(20);
		bitcoinScoreDifferenceField.setEditable(false);

		bitcoinIncreaseSinceStartField = new JTextField(20);
		bitcoinIncreaseSinceStartField.setEditable(false);

		bitcoinPercentChangeHighPointField = new JTextField(20);
		bitcoinPercentChangeHighPointField.setEditable(false);

		bitcoinDaysPassedField = new JTextField(20);
		bitcoinDaysPassedField.setEditable(false);

		bitcoinCutoffValField = new JTextField(20);
		bitcoinCutoffValField.setEditable(false);

		bitcoinPcHighDayField = new JTextField(20);
		bitcoinPcHighDayField.setEditable(false);

		bitcoinPcLowDayField = new JTextField(20);
		bitcoinPcLowDayField.setEditable(false);

		bitcoinTotalChangeField = new JTextField(20);
		bitcoinTotalChangeField.setEditable(false);

		bitcoinRobotLabel = new JLabel("BITCOIN SECTION");
		bitcoinCurrentlyWatchingLabel = new JLabel("Currently Watching:");
		bitcoinIncreaseSinceStartLabel = new JLabel("P/C Since Buy:");
		bitcoinPercentChangeHighPointLabel = new JLabel("P/C High:");
		bitcoinScoreDifferenceLabel = new JLabel("P/C Low:");
		bitcoinLogLabel = new JLabel("Sell/Buy Log:");
		bitcoinDaysPassedLabel = new JLabel("Days Since Buy:");
		bitcoinCutoffValLabel = new JLabel("Cutoff Percent:");
		bitcoinPcHighDayLabel = new JLabel("P/C High Today:");
		bitcoinPcLowDayLabel = new JLabel("P/C Low Today:");
		bitcoinTotalChangeLabel = new JLabel("Total G/L:");

		bitcoinLogArea = new JTextArea(36, 130);
		bitcoinLogArea.setEditable(false);
		bitcoinLogArea.setLineWrap(true);

		GridBagConstraints positionConst = null;

		// print GUI errors to log
		try {
			// Layout
			setLayout(new GridBagLayout());
			positionConst = new GridBagConstraints();
			positionConst.insets = new Insets(3, 3, 3, 3);

			// Bitcoin side
			// Layout for input button
			positionConst.anchor = GridBagConstraints.WEST;
			positionConst.gridwidth = 1;
			positionConst.gridx = 9;
			positionConst.gridy = 5;
			add(bitcoinInputButton, positionConst);

			// Layout for list button
			positionConst.anchor = GridBagConstraints.EAST;
			positionConst.gridwidth = 1;
			positionConst.gridx = 9;
			positionConst.gridy = 5;
			add(startRobotButton, positionConst);

			// Layout for input field
			positionConst.fill = GridBagConstraints.HORIZONTAL;
			positionConst.gridwidth = 4;
			positionConst.gridx = 5;
			positionConst.gridy = 5;
			add(bitcoinInputField, positionConst);

			// Layout for currently watching label
			positionConst.anchor = GridBagConstraints.LINE_START;
			positionConst.gridwidth = 1;
			positionConst.gridx = 5;
			positionConst.gridy = 6;
			add(bitcoinCurrentlyWatchingLabel, positionConst);

			// Layout for currently watching field
			positionConst.anchor = GridBagConstraints.LINE_START;
			positionConst.gridwidth = 4;
			positionConst.gridx = 6;
			positionConst.gridy = 6;
			add(bitcoinCurrentlyWatchingField, positionConst);

			// Layout for score difference label
			positionConst.anchor = GridBagConstraints.LINE_START;
			positionConst.gridwidth = 1;
			positionConst.gridx = 5;
			positionConst.gridy = 9;
			add(bitcoinScoreDifferenceLabel, positionConst);

			// Layout for score difference field
			positionConst.anchor = GridBagConstraints.LINE_START;
			positionConst.gridwidth = 4;
			positionConst.gridx = 6;
			positionConst.gridy = 9;
			add(bitcoinScoreDifferenceField, positionConst);

			// Layout for increase since start label
			positionConst.anchor = GridBagConstraints.LINE_START;
			positionConst.gridwidth = 1;
			positionConst.gridx = 5;
			positionConst.gridy = 7;
			add(bitcoinIncreaseSinceStartLabel, positionConst);

			// Layout for increase since start field
			positionConst.anchor = GridBagConstraints.LINE_START;
			positionConst.gridwidth = 4;
			positionConst.gridx = 6;
			positionConst.gridy = 7;
			add(bitcoinIncreaseSinceStartField, positionConst);

			// Layout for P/C high point label
			positionConst.anchor = GridBagConstraints.LINE_START;
			positionConst.gridwidth = 1;
			positionConst.gridx = 5;
			positionConst.gridy = 8;
			add(bitcoinPercentChangeHighPointLabel, positionConst);

			// Layout for P/C high point field
			positionConst.anchor = GridBagConstraints.LINE_START;
			positionConst.gridwidth = 4;
			positionConst.gridx = 6;
			positionConst.gridy = 8;
			add(bitcoinPercentChangeHighPointField, positionConst);

			// Layout for log label
			positionConst.anchor = GridBagConstraints.LINE_START;
			positionConst.gridwidth = 1;
			positionConst.gridx = 5;
			positionConst.gridy = 10;
			add(bitcoinLogLabel, positionConst);

			// Average G/L
			positionConst.anchor = GridBagConstraints.LINE_START;
			positionConst.gridwidth = 1;
			positionConst.gridx = 8;
			positionConst.gridy = 10;
			add(bitcoinTotalChangeLabel, positionConst);

			positionConst.anchor = GridBagConstraints.LINE_START;
			positionConst.gridwidth = 1;
			positionConst.gridx = 9;
			positionConst.gridy = 10;
			add(bitcoinTotalChangeField, positionConst);

			// Layout for log area
			positionConst.fill = GridBagConstraints.BOTH;
			positionConst.gridwidth = 5;
			positionConst.gridx = 5;
			positionConst.gridy = 11;
			add(bitcoinLogArea, positionConst);

			// Layout for log area scroll pane
			positionConst.gridwidth = 5;
			positionConst.gridx = 5;
			positionConst.gridy = 11;
			bitcoinLogScrollPane = new JScrollPane(bitcoinLogArea);
			bitcoinLogScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			new SmartScroller(bitcoinLogScrollPane);
			add(bitcoinLogScrollPane, positionConst);

		} catch (Exception e) {
			if (e instanceof IllegalArgumentException) {
				System.out.println("Error in creating GUI:");
				System.out.println();
				System.out.println(e.getMessage());
			}

		}

		FileIO.loadLogs();

		bitcoinTotalChangeField.setText(Double.toString(vars.bitcoinCurrentGLPercent));
		if (vars.madeBitcoinBuy == true) {
			bitcoinCurrentlyWatchingField.setText(vars.currentlyWatchingBitcoin);
			bitcoinScoreDifferenceField.setText(Double.toString(vars.bitcoinPcLow));
			bitcoinIncreaseSinceStartField.setText(Double.toString(vars.calcBitcoinChange));
			bitcoinPercentChangeHighPointField.setText(Double.toString(vars.bitcoinPcHigh));
			bitcoinDaysPassedField.setText(Integer.toString(vars.bitcoinDaysPassed));
			bitcoinPcHighDayField.setText(Double.toString(vars.bitcoinPcHighDay));
			bitcoinPcLowDayField.setText(Double.toString(vars.bitcoinPcLowDay));
		}

		String startMessage = TimeManager.getCurrentTime() + "Program start:";
		bitcoinLogArea.append(startMessage + "\n");
		logLists.bitcoinLogList.add(startMessage);
		FileIO.saveLogs();

		RF.startPrograms(fileCheck);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == bitcoinInputButton) {
			InputLogic.bitcoinInput(bitcoinInputField.getText());
		}

		/*
		 * This program only allows for pausing at the very start of the program, so if
		 * a user wants to see the information in the program, they must close it and
		 * re-open it, then push pause within six seconds. All of the information will
		 * still be there since this program consistently auto saves its information.
		 */
		if (event.getSource() == startRobotButton) {
			RF.pauseMode = true;
		}
	}
}