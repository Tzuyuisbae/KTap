package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mainscreen extends JFrame{

	// get the screen size
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	// constructor with input sizes
	public Mainscreen(int width, int height) throws IOException {
		this.setSize(width, height);
		setUp(width, height);
	}
	
	// default constructor
	public Mainscreen() throws IOException {
		this.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
		setUp((int) screenSize.getWidth(), (int) screenSize.getHeight());
	}
	
	public void setUp(int width, int height) throws IOException {
				
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new BorderLayout());
		
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    InputStream input = classLoader.getResourceAsStream("cover.jpg");
	    Image image = ImageIO.read(input);
	    		
		JLabel background=new JLabel(new ImageIcon(image));
	    JPanel backgroundPanel = new JPanel();

	    backgroundPanel.add(background);
		//background.setOpaque(false);
	    background.setBounds(0, 0, width, height);
		
		backgroundPanel.setBounds(0, 0, width, height);
		//background.setLayout(new FlowLayout());
		backgroundPanel.setVisible(true);
		JButton b1 = new JButton("I am a button");
		b1.setVisible(true);
		b1.setBounds(width/2, height/2, 200, 50);
		background.add(b1);
		
		System.out.println(width + " " +  height);
		
		this.add(backgroundPanel);
		this.setVisible(true);
		
	}
}
