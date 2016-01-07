import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

public class Visual {
	
	final static int BUFFER = 10;
	public static JPanel displayPanel = new Canvas();      // graphics are drawn here
	static boolean update = true;
	static Color[][] colors = {{new Color(188,188,188),new Color(255,188,188),new Color(255,168,168),new Color(255,126,126),new Color(255,84,84),new Color(255,42,42),new Color(255,0,0)}};
	
	public static class Canvas extends JPanel {
		// all drawing on this canvas is auto-scaled
		// based on current size.
		
		public void paintComponent(Graphics g){
			
			ArrayList<Day> calendar = new ArrayList<Day>();
			for(int i = 0; i<58; ++i)
				calendar.add(new Day(new Date(), 2));
			
			super.paintComponent(g);
			
			drawDays(g, calendar);
			int w = getWidth();
			int h = getHeight();
			
			if(update){
				displayPanel.repaint();
				update(g,w,h);
				update = false;
				}
			g.setColor(Color.RED);
			//g.fillRoundRect(0, 0, w, h, 20, 20);
			
		}
		
		public void drawDays(Graphics g, ArrayList<Day> in) {
			
			int x_size = (int)(.8*(getWidth()/(in.size()/7))); // roughly 70 pix.
			int y_size = (int)(.8*(getHeight()/7));
			int size;
			
			if(x_size>y_size)
				size = y_size;
			else
				size = x_size;
				for(int i = in.get(0).getDay(); i<in.size(); ++i) {
					g.setColor(levelToColor(in.get(i).getLevel()));
					g.fillRect(BUFFER+(int)((i/7)*size*1.11),BUFFER+(int)((i%7)*size*1.11),size,size);
				}
			
			//g.fillRect(10,10,40,40);
			//g.fillRect(10,10,40,40);
			
			
			/*
			int x = 5;
			int y;
			int i = 0; 
			int x_inc = (getWidth()/(in.size()/7))-5;
			int y_inc = ((getHeight()-40)/7)-5;
			for(Day day:in) {
				i++;
				y = (y_inc*day.getDay())+5;
				x = (x_inc*(i/7))+5;
				
				g.setColor(levelToColor(day.getLevel()));
				g.fillRect(x, y, x_inc, x_inc);
			}
			*/
		}
		
		
		public void update(Graphics g, int w, int h){
			
		}
		
		public Color levelToColor(int level) {
			int theme = 0;
			return colors[theme][level];
		}
	}
}
