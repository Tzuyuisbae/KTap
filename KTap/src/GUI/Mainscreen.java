package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Mainscreen extends JFrame{

	// get the screen size
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	// constructor with input sizes
	public Mainscreen(int width, int height) {
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	// default constructor
	public Mainscreen() {
		this.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
