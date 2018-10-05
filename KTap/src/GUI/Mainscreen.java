package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Mainscreen extends JFrame{

	// get the screen size
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	// constructor with input sizes
	public Mainscreen(int width, int height) {
		this.setSize(width, height);
		setUp(width, height);
	}
	
	// default constructor
	public Mainscreen() {
		this.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
		setUp((int) screenSize.getWidth(), (int) screenSize.getHeight());
	}
	
	public void setUp(int width, int height) {
		
		System.out.println("testt");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new BorderLayout());
		JLabel background=new JLabel();
		
		background.setOpaque(false);
		
		background.setIcon(new ImageIcon("background.jpg"));
		background.setBounds(0, 0, width, height);
		//background.setLayout(new FlowLayout());
		background.setVisible(true);
		JButton b1 = new JButton("I am a button");
		b1.setVisible(true);
		b1.setBounds(50, 50, 500, 50);
		background.add(b1);
		
		this.add(background);
		
	}
}
