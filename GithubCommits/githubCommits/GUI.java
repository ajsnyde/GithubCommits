package githubCommits;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;

public class GUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField username;
	private HashMap<String, Thread> visuals = new HashMap<String, Thread>();

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

	public GUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Github Widget Manager");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Admin");
		menuBar.add(mnNewMenu);

		JMenuItem mntmExit = new JMenuItem("Exit All..");
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmExit);

		JMenu mnGadgets = new JMenu("Widgets");
		menuBar.add(mnGadgets);

		JMenuItem mntmNewWidget = new JMenuItem("New Widget..");
		mntmNewWidget.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		mnGadgets.add(mntmNewWidget);

		ImageIcon icon = new ImageIcon("githubCommits/GithubCommits/icon.gif");
		Image iconImage = icon.getImage();

		frame.setIconImage(iconImage);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JLabel lblUsername = new JLabel("*Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 1;
		frame.getContentPane().add(lblUsername, gbc_lblUsername);

		username = new JTextField();
		username.setText("ajsnyde");
		GridBagConstraints gbc_txtAjsnyde = new GridBagConstraints();
		gbc_txtAjsnyde.insets = new Insets(0, 0, 5, 0);
		gbc_txtAjsnyde.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAjsnyde.gridx = 2;
		gbc_txtAjsnyde.gridy = 1;
		frame.getContentPane().add(username, gbc_txtAjsnyde);
		username.setColumns(10);

		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 2;
		frame.getContentPane().add(lblName, gbc_lblName);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Apply!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (visuals.get(username.getText()) != null) {
					visuals.get(username.getText()).stop();
					Thread thread = new Thread(create());
					visuals.put(username.getText(), thread);
					thread.start();
				} else {
					visuals.put(username.getText(), new Thread(create()));
					visuals.get(username.getText()).start();
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 7;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);

		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				frame.setVisible(false);
			}
		});

		if (SystemTray.isSupported()) {
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

			final SystemTray systemTray = SystemTray.getSystemTray();

			PopupMenu popup = new PopupMenu();
			MenuItem exit = new MenuItem("Exit");
			exit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			popup.add(exit);

			final TrayIcon trayIcon = new TrayIcon(iconImage, "Github Widget Manager", popup);
			trayIcon.setImageAutoSize(true);
			trayIcon.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2 && !e.isConsumed())
						frame.setVisible(true);
				}
			});

			try {
				systemTray.add(trayIcon);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	Visual create() {
		Visual visual = new Visual();
		visual.username = username.getText();
		return visual;
	}
}
