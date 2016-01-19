import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Visual {
	
	final static int BUFFER = 10;
	static int numDays = 60;
	final static Color[][] colors = {	//Just getting a feel for structure of this set of colors, both are red themes
			{new Color(188,188,188),new Color(255,188,188),new Color(255,168,168),new Color(255,126,126),new Color(255,84,84),new Color(255,42,42),new Color(255,0,0)},
			{new Color(188,188,188),new Color(255,188,188),new Color(255,168,168),new Color(255,126,126),new Color(255,84,84),new Color(255,42,42),new Color(255,0,0)}
	};
	static Fetcher fetcher = new Fetcher();
	static JPanel displayPanel = new Canvas();      // graphics are drawn here
	static boolean update = true;
	static ArrayList<Day> days = new ArrayList<Day>();	
	
	@SuppressWarnings("serial")
	public static class Canvas extends JPanel {
		// all drawing on this canvas is auto-scaled
		// based on current size.
		
		public void paintComponent(Graphics g){
			days = fetcher.fetch();
			
			super.paintComponent(g);
			drawDays(g, days);
			int w = getWidth();
			int h = getHeight();
			
			if(update){
				displayPanel.repaint();
				update(g,w,h);
				update = false;
				}
		}
		
		public void drawDays(Graphics g, ArrayList<Day> in) {
			
			int x_size = (int)(.8*(getWidth()/(numDays/7))); // roughly 70 pix.
			int y_size = (int)(.8*(getHeight()/7));
			int size;
			
			if(x_size>y_size)
				size = y_size;
			else
				size = x_size;
				for(int i = in.get(365-numDays).getDay(); i<numDays; ++i) {
					g.setColor(levelToColor(in.get(365-numDays+i).getLevel()));
					g.fillRect(BUFFER+(int)((i/7)*size*1.11),BUFFER+(int)((i%7)*size*1.11),size,size);
				}
		}
		
		
		public void update(Graphics g, int w, int h){
			
		}
		
		public Color levelToColor(int level) {
			int theme = 0;
			return colors[theme][level];
		}
	}
}
