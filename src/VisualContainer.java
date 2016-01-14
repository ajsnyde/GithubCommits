import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;

public class VisualContainer {

	private JFrame vis;
	
	public VisualContainer() {
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
		
		new Visual();
		vis.getContentPane().add(Visual.displayPanel, BorderLayout.CENTER);
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
