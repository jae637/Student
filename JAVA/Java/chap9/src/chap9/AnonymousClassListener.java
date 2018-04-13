package chap9;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AnonymousClassListener extends JFrame{
	public AnonymousClassListener() {
		setTitle("Action Event Listener EX");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		JButton btn= new JButton("Action");
		c.add(btn);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton b = (JButton)e.getSource();
				if(b.getText().equals("Action"))
					b.setText("¾×¼Ç");
				else
					b.setText("Action");
				setTitle(b.getText());
			}
		});
		setSize(350,150);
		setVisible(true);
	}
	public static void main(String[] args) {
		new AnonymousClassListener();
	}
}
