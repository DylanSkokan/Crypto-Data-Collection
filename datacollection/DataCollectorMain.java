package investing.crypto.datacollection;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * <h2>DataCollectorMain</h2> Creates a GUI to start the program which then
 * starts a chain of SwingWorkers to carry out program action.
 * 
 * @author Dylan Skokan
 * @since 2/23/22
 */
public class DataCollectorMain {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				
				GUI frame = new GUI();

				frame.pack();
				frame.setVisible(true);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
}