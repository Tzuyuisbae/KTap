package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

@SuppressWarnings("serial")
public class Mainscreen extends JFrame{

	// get the screen size
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public JButton playButton;
	
	// constructor with input sizes
	public Mainscreen(int width, int height) throws IOException {
		setUp(width, height);
	}
	
	// default constructor
	public Mainscreen() throws IOException {
		setUp((int) screenSize.getWidth(), (int) screenSize.getHeight());
	}
	
	public void setUp(int width, int height) throws IOException {
		
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		// read in the background image
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    InputStream input = classLoader.getResourceAsStream("../images/background/background.gif");
	    Image image = ImageIO.read(input);
	    // resize the image to match the frame
	    image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	    
	    // create a new jlabel for the background image
		JLabel background=new JLabel(new ImageIcon(image));
	    // create a new jpanel for the background
		JPanel backgroundPanel = new JPanel();

		// add the background image to the background panel
	    backgroundPanel.add(background);
	    // set the size of the panel
		backgroundPanel.setBounds(0, 0, width, height);
		//background.setLayout(new FlowLayout());
		backgroundPanel.setVisible(true);
		
		// create  a new button to go the the song select screen
		playButton = new JButton("Play");
		playButton.setVisible(true);
		playButton.setBounds(width/2 - 100, height/4 * 3, 200, 50);
		background.add(playButton);
				
		this.add(backgroundPanel);
		this.setVisible(true);
		
	}
}
