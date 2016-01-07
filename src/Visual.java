import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

public class Visual {
	
	final static int BUFFER = 10;
	public static JPanel displayPanel = new Canvas();      // graphics are drawn here
	static boolean update = true;
	
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
				}
			g.setColor(Color.RED);
			//g.fillRoundRect(0, 0, w, h, 20, 20);
			update(g,w,h);
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
			switch (level) {
			case 0:  return new Color(0,0,0);
            case 1:  return new Color(0,0,0);
            case 2:  return new Color(0,0,0);
            case 3:  return new Color(0,0,0);
            case 4:  return new Color(0,0,0);
            case 5:  return new Color(0,0,0);
            default: return new Color(0,0,0);
			}
		}
	}
}
