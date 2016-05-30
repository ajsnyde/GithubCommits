package githubCommits;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Window.Type;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Visual implements Runnable{
	
	static Fetcher fetcher = new Fetcher();
	final static Color[][] colors = {	//Just getting a feel for structure of this set of colors, both are red themes
			{new Color(188,188,188),new Color(255,188,188),new Color(255,168,168),new Color(255,126,126),new Color(255,84,84),new Color(255,42,42),new Color(255,0,0)},
			{new Color(188,188,188),new Color(255,188,188),new Color(255,168,168),new Color(255,126,126),new Color(255,84,84),new Color(255,42,42),new Color(255,0,0)}
	};
	public String username = "ajsnyde";
	private ArrayList<Day> days = new ArrayList<Day>();	
	private JFrame vis;								// contains 2DGraphics
	private JPanel displayPanel = new Canvas();		// graphics are drawn here
	
	// Window settings
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int screenWidth = (int) screenSize.getWidth();
	private int screenHeight = (int) screenSize.getHeight();
	private int width = 750;			//	Roughly optimal ratio for w/h is 15:2
	private int height = 100;
	private int xOffset = 25;			// x & y of window position - Windows defaults (0,0) at upper-left corner of screen,
	private int yOffset = 25;			// with top-left of window being the point
	private int xPreferred = width;		// the perfect number to prevent any inconsistant spacing
	private int yPreferred = height;
	
	// Graphics settings
	private int radius = 20;		// (fillet) radius on window
	private int windowBuffer = 10;	// (edge) buffer should be greater than radius; space between edge of window and squares
	private float opacity = 0.5f;
	private Color background = Color.WHITE;
	//Graphics settings - squares
	private int xSquareBuffer = 2;
	private int ySquareBuffer = 2;
	private int xSquareSize = 10;
	private int ySquareSize = 10;
	
	// Day settings
	private long lastUpdated = 0; // time is 0, forcing update for first check
	private long updateInterval = 30000;
	private int numDays = 60;
	
	public Visual() {
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void run() {
		initialize();
		manipulate(true);
	}
	
	private void initialize() {
		vis = new JFrame();
		vis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed())
					manipulate(vis.isUndecorated());
			}
		});
		vis.addComponentListener(new ComponentAdapter() {
		    public void componentMoved(ComponentEvent e) {
		    	vis.setTitle(getCoordinates());
		    }
		});
		vis.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent e) {
		    	vis.setTitle(getCoordinates());
		    }
		});
		vis.setType(Type.UTILITY);
		vis.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		vis.setBackground(background);
		vis.setUndecorated(true);
		vis.setOpacity(opacity);
		vis.setBounds((screenWidth - xOffset - width), yOffset, width, height);		//right-top default
		//vis.setBounds(xOffset, yOffset, width, height);							//left-top default
		vis.setShape(new RoundRectangle2D.Double(0, 0, vis.getBounds().width, vis.getBounds().height, radius, radius));
		vis.getContentPane().add(this.displayPanel, BorderLayout.CENTER);
		vis.setVisible(true);
	}
	
	public class Canvas extends JPanel {
		// all drawing on this canvas is auto-scaled
		// based on current size.
		
		public void paintComponent(Graphics g){
			if(lastUpdated < System.currentTimeMillis()-updateInterval) {
				lastUpdated = System.currentTimeMillis();
				days = fetcher.fetch(username);
			}
			super.paintComponent(g);
			drawDays(g, days);
		}
		
		public void drawDays(Graphics g, ArrayList<Day> in) {
			g.setColor(new Color(255,0,0));
			g.drawLine(0, 0, 1, 1);
			
			
			int x_size = (int)(.8*(getWidth()/(numDays/7))); // roughly 70 pix.
			int y_size = (int)(.8*(getHeight()/7));
			int size;
			
			if(x_size>y_size)
				size = y_size;
			else
				size = x_size;
				for(int i = in.get(365-numDays).getDay(); i<numDays; ++i) {
					g.setColor(levelToColor(in.get(365-numDays+i).getLevel()));
					g.fillRect(windowBuffer+(int)((i/7)*size*1.11),windowBuffer+(int)((i%7)*size*1.11),size,size);
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
			vis.setTitle(getCoordinates());
			vis.setVisible(true);
		}
		else {
			Rectangle bounds = vis.getBounds();
			
			vis.dispose();
			width = bounds.width;
			height = bounds.height;
			initialize();
			vis.setBounds(bounds);
			//vis.setUndecorated(true);
			//vis.setShape(new RoundRectangle2D.Double(0, 0, vis.getBounds().width, vis.getBounds().height, 20, 20));
			//vis.setVisible(true);
		}
	}
	
	public void setOpacity(float in) {
		if(vis.isUndecorated())
			vis.setOpacity(in);
	}
	
	public String getCoordinates() {
		return (username + " - X:" + vis.getX() + " Y:" + vis.getY() + " W:" + vis.getWidth() + " H:" + vis.getHeight());
	}
}
