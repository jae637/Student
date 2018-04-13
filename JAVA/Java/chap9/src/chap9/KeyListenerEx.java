package chap9;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class KeyListenerEx extends JFrame{
	JPanel contentPane = new JPanel();
	JLabel [] keyMessage;
	
	KeyListenerEx() {
		// TODO Auto-generated constructor stub
		setTitle("KeyListener Ex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(contentPane);
		contentPane.addKeyListener(new MyKeyListener());
		
		keyMessage= new JLabel[3];
		keyMessage[0]=new JLabel("getKeyCode()");
		keyMessage[1]=new JLabel("getKeyCode()");
		keyMessage[2]=new JLabel("getKeyCode()");
		
		for(int i=0; i<keyMessage.length;i++) {
			contentPane.add(keyMessage[i]);
			keyMessage[i].setOpaque(true);
			keyMessage [i].setBackground(Color.CYAN);
		}
		
		setSize(300, 150);
		setVisible(true);
		
		contentPane.requestFocus();
	}
	
	class MyKeyListener extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			super.keyPressed(e);
			int keyCode = e.getKeyCode();
			char keyChar = e.getKeyChar();
			keyMessage[0].setText(Integer.toString(keyCode));
			keyMessage[1].setText(Character.toString(keyChar));
			keyMessage[2].setText(e.getKeyText(keyCode));
			System.out.println("keyPressed");
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			super.keyReleased(e);
			System.out.println("keyReleased");
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			super.keyTyped(e);
			System.out.println("KeyTyped");
		}
	}
	
	public static void main (String[] args) {
		new KeyListenerEx();
	}
}
