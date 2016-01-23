package githubCommits;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	
	private GUI gui;
	
	Controller(GUI gui){
		this.gui = gui;
		gui.addListener(new createApplyListener());
	}
	
	class createListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	class createApplyListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getSource());
		}
	}
}
