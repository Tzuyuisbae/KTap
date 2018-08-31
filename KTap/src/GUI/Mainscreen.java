package GUI;

import javax.swing.JFrame;

public class Mainscreen extends JFrame{

	public Mainscreen(int width, int height) {
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
