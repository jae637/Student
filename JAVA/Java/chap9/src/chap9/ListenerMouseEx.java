package chap9;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

class MyMouseListener implements MouseListener {
	public void mouseEntered(MouseEvent e) {
		JButton btn = (JButton) e.getSource();
		btn.setBackground(Color.RED);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getSource();
		btn.setBackground(Color.YELLOW);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

@SuppressWarnings("serial")
public class ListenerMouseEx extends JFrame {
	public ListenerMouseEx() {
		// TODO Auto-generated constructor stub
		setTitle("��ư�� ���콺 �̺�Ʈ ������ �ۼ�");
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btn = new JButton("���콺 �̺�Ʈ �׽�Ʈ ��ư");
		btn.setBackground(Color.YELLOW);
		MyMouseListener listener = new MyMouseListener();
		btn.addMouseListener(listener);

		add(btn);

		setSize(300, 150);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ListenerMouseEx();
	}

}
