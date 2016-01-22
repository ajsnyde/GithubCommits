package githubCommits;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Visual {
	
	static int buffer = 10;
	static int numDays = 60;
	static Fetcher fetcher = new Fetcher();
	final static Color[][] colors = {	//Just getting a feel for structure of this set of colors, both are red themes
			{new Color(188,188,188),new Color(255,188,188),new Color(255,168,168),new Color(255,126,126),new Color(255,84,84),new Color(255,42,42),new Color(255,0,0)},
			{new Color(188,188,188),new Color(255,188,188),new Color(255,168,168),new Color(255,126,126),new Color(255,84,84),new Color(255,42,42),new Color(255,0,0)}
	};
	
	private String username = "ajsnyde";
	private ArrayList<Day> days = new ArrayList<Day>();	
	private JFrame vis;
	private JPanel displayPanel = new Canvas();      // graphics are drawn here
	
	public Visual() {
		initialize();
	}
	
	private void initialize() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		//double screenHeight = screenSize.getHeight();
		int width = 750;	//	Roughly optimal ratio for w/h is 15:2
		int height = 100;
		int offset = 25;
		
		vis = new JFrame();
		
		vis.setBackground(Color.WHITE);
		vis.setUndecorated(true);
		vis.setOpacity(0.5f);
		vis.setBounds((int) (screenWidth - offset - width), offset, width, height);
		vis.setShape(new RoundRectangle2D.Double(0, 0, vis.getBounds().width, vis.getBounds().height, 20, 20));
		vis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vis.getContentPane().add(this.displayPanel, BorderLayout.CENTER);
	}
	
	public class Canvas extends JPanel {
		// all drawing on this canvas is auto-scaled
		// based on current size.
		
		public void paintComponent(Graphics g){
			days = fetcher.fetch(username);
			super.paintComponent(g);
			drawDays(g, days);
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
					g.fillRect(buffer+(int)((i/7)*size*1.11),buffer+(int)((i%7)*size*1.11),size,size);
				}
		}
		
		public void update(Graphics g, int w, int h){
			
		}
		
		public Color levelToColor(int level) {
			int theme = 0;
			return colors[theme][level];
		}
	}
	
	
	public void setVisible(boolean in) {
		vis.setVisible(in);
	}
	
	public void refresh() {
		vis.dispose();
		vis.setVisible(true);
	}
	
	public void manipulate(boolean in) {
		if(in) {
			vis.setOpacity(1.0f);
			vis.setShape(null);
			vis.dispose();
			vis.setUndecorated(false);
			vis.setVisible(true);
		}
		else {
			vis.dispose();
			vis.setUndecorated(true);
			vis.setShape(new RoundRectangle2D.Double(0, 0, vis.getBounds().width, vis.getBounds().height, 20, 20));
			vis.setVisible(true);
		}
	}
	
	public void setOpacity(float in) {
		if(vis.isUndecorated())
			vis.setOpacity(in);
	}
}
