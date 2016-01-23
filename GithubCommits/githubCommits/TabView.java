package githubCommits;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TabView extends JPanel {
	
	static int ID = 0;
	public final int id = ID++;
	
	final public JTextField txtUsername = new JTextField();
	final JButton btnApply = new JButton("Apply!");
	final JButton btnKillTab = new JButton("Kill Tab/Visual");
	
	public TabView() {
		initialize();
	}
	
	private void initialize() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblGithubUsername = new JLabel("Github username:");
		add(lblGithubUsername);
		lblGithubUsername.setToolTipText("Commits will be fetched from the supplied username.");
		add(txtUsername);
		txtUsername.setText("ajsnyde");
		txtUsername.setColumns(10);
		
		final JCheckBox chckbxEnable = new JCheckBox("Enabled");
		add(chckbxEnable);
		add(btnApply);
		add(btnKillTab);
	}
	
	@Override
	public String toString(){
		return txtUsername.getText();
	}
	
	void addApplyListener(ActionListener createListener) {
		btnApply.addActionListener(createListener);
	}
	void addKillListener(ActionListener createListener) {
		btnKillTab.addActionListener(createListener);
	}
}
