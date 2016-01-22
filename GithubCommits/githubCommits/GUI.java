package githubCommits;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class GUI {

	private JFrame frame;
	private final JButton btnNewTab = new JButton("+");
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	ArrayList<TabView> tabs = new ArrayList<TabView>();
	
	public GUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.addTab("", null, new JPanel());
		tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, btnNewTab);

		btnNewTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabs.add(new TabView());
				tabs.get(tabs.size()-1).addKillListener(new createKillListener());
				tabbedPane.addTab(""+tabbedPane.getTabCount(), null, tabs.get(tabs.size()-1));
			}
		});
		this.frame.setVisible(true);
	}
	class createKillListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("Removing: " + tabbedPane.getComponentAt(tabbedPane.getSelectedIndex()));
			tabs.remove(tabbedPane.getComponentAt(tabbedPane.getSelectedIndex()));
			tabbedPane.remove(tabbedPane.getSelectedIndex());
		}
	}
}
