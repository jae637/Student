import java.awt.Container;
import java.awt.Font;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GreetingApplet extends JApplet{
	public void init() {
		String greeting= getParameter("GREETING");
		String font =getParameter("FONT");
		Container contentPane = getContentPane();
		JLabel label = new JLabel(greeting,SwingConstants.CENTER);
		label.setFont(new Font(font,Font.PLAIN,20));
		contentPane.add(label);
	}
}
