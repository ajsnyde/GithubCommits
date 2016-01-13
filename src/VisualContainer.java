import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;

public class VisualContainer {

	private JFrame vis;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualContainer window = new VisualContainer();
					window.vis.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
        vis.addComponentListener(new ComponentAdapter() {
            // Give the window an elliptical shape.
            // If the window is resized, the shape is recalculated here.
            @Override
            public void componentResized(ComponentEvent e) {
                vis.setShape(new RoundRectangle2D.Double(0, 0, vis.getBounds().width, vis.getBounds().height, 20, 20));
            }
        });
		
		vis.setBackground(Color.WHITE);
		vis.setUndecorated(true);
		vis.setOpacity(0.5f);
		vis.setBounds((int) (screenWidth - offset - width), offset, width, height);
		vis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		new Visual();
		vis.getContentPane().add(Visual.displayPanel, BorderLayout.CENTER);
	}
}
