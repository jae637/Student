package chap11;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ComboActionEx extends JFrame{
	Container contentPane;
	String[] fruits = {"apple","banana","kiwi","mango"};
	ImageIcon[] images = {new ImageIcon("images/apple.jpg"), new ImageIcon("images/banana.jpg"),new ImageIcon("images/kiwi.jpg"),new ImageIcon("images/mango.jpg")};
	JLabel imgLabel = new JLabel(images[0]);
	
	public ComboActionEx() {
		// TODO Auto-generated constructor stub
		setTitle("리스트 만들기 예제");
		
	}
}
