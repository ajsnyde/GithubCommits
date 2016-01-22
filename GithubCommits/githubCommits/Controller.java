package githubCommits;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	
	private GUI gui;
	
	Controller(GUI gui){
		this.gui = gui;
		//this.gui.addCreateListener(new createListener());
	}
	
	class createListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
