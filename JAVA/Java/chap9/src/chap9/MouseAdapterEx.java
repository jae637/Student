package chap9;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MouseAdapterEx extends JFrame{
	JPanel ContentPane = new JPanel();
	JLabel la;
	
	public MouseAdapterEx() {
		// TODO Auto-generated constructor stub
		setTitle("Mouse Event Ex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(ContentPane);
		ContentPane.setLayout(null);
		ContentPane.addMouseListener(new MyMouseAdapter());
		
		la = new JLabel("hello");
		la.setSize(50,20);
		la.setLocation(30,30);
		ContentPane.add(la);
		setSize(200, 200);
		setVisible(true);
	}
	
	class MyMouseAdapter extends MouseAdapter{
		
		public void mousePressed(MouseEvent e) {
			int x= e.getX();
			int y= e.getY();
			la.setLocation(x,y);
		}
	}
}


