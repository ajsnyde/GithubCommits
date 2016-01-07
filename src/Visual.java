import java.awt.*;
import javax.swing.*;

public class Visual {
	
	public static JPanel displayPanel = new Canvas();      // graphics are drawn here
	static int x = 0;
	static int y = 0;
	static boolean update = true;
	
	public static class Canvas extends JPanel {
		// all drawing on this canvas is auto-scaled
		// based on current size.
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);

			int w = getWidth();
			int h = getHeight();
			
			if(update){
				displayPanel.repaint();
				}
			g.setColor(Color.RED);
			//g.fillRoundRect(0, 0, w, h, 20, 20);
			
			
			update(g,w,h);
		}
		
		public void update(Graphics g, int w, int h){
			
		}
	}
}
