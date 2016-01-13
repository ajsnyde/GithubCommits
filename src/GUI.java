import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;
import javax.swing.JSlider;

public class GUI {

	private JFrame frame;
	private JTextField txtAjsnyde;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("Entering zero disables updates");
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblGithubUsername = new JLabel("Github username:");
		lblGithubUsername.setToolTipText("Commits will be fetched from the supplied username.");
		lblGithubUsername.setBounds(10, 11, 108, 29);
		panel.add(lblGithubUsername);
		
		txtAjsnyde = new JTextField();
		txtAjsnyde.setText("ajsnyde");
		txtAjsnyde.setBounds(128, 11, 101, 29);
		panel.add(txtAjsnyde);
		txtAjsnyde.setColumns(10);
		
		JLabel lblDaysToDisplay = new JLabel("Days to display:");
		lblDaysToDisplay.setBounds(10, 51, 108, 29);
		panel.add(lblDaysToDisplay);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(90, 1, 365, 1));
		spinner.setBounds(128, 55, 47, 20);
		panel.add(spinner);
		
		JCheckBox chckbxManipulateMode = new JCheckBox("Manipulate Mode");
		chckbxManipulateMode.setToolTipText("Manipulate mode adds conventional window elements and temporarily allows the user to move and resize the app. Opacity is forced to 1.0 for compatibility reasons.");
		chckbxManipulateMode.setBounds(10, 127, 117, 38);
		panel.add(chckbxManipulateMode);
		
		JSlider slider = new JSlider();
		slider.setMinorTickSpacing(5);
		slider.setMajorTickSpacing(20);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setBounds(128, 88, 176, 38);
		panel.add(slider);
		
		JLabel lblOpacity = new JLabel("Opacity:");
		lblOpacity.setBounds(10, 91, 108, 29);
		panel.add(lblOpacity);
		
		JLabel lblUpdateFrequency = new JLabel("Update Frequency (minutes):");
		lblUpdateFrequency.setBounds(10, 166, 143, 29);
		panel.add(lblUpdateFrequency);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(new Integer(300), new Integer(0), null, new Integer(1)));
		spinner_1.setBounds(163, 170, 47, 20);
		panel.add(spinner_1);
	}
}
