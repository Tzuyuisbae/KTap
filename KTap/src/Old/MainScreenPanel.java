package Old;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainScreenPanel extends JPanel{

	private JLabel title = new JLabel("SuperStar BigHit");
	JLabel background = new JLabel();
	JButton playButton = new JButton("Play");
	JButton exitButton = new JButton("Exit");
	JButton helpButton = new JButton("How to Play");
	JButton hardButton = new JButton("Hard");
	JButton easyButton = new JButton("Easy");
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int frameWidth = 1000;

	public MainScreenPanel() {

		setLayout(null);
		setBackground(Color.BLACK);

		Font font = new Font("Agency FB", Font.BOLD, 60);

		//set background image
		Image img = null;
		try {
			img = ImageIO.read(new File("gamebackground.jpg"));
			img = img.getScaledInstance(1000, (int) screenSize.getHeight(),Image.SCALE_SMOOTH);
			background.setBounds(0,0,1000,(int) screenSize.getHeight());
			background.setIcon(new ImageIcon(img));
			background.setVisible(true);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		add(background);
		
		//title
		title.setFont(font);
		title.setForeground(Color.white);
		title.setBounds(frameWidth /2 - 350, 100, 700, 100);
		background.add(title);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.CENTER);
		
		//play button
		playButton.setFont(font);
		playButton.setForeground(Color.WHITE);
		playButton.setOpaque(false);
		playButton.setContentAreaFilled(false);
		playButton.setBorderPainted(false);
		playButton.setBounds((frameWidth /2 - 200), 300, 400, 100);
		background.add(playButton);
		playButton.setHorizontalAlignment(JLabel.CENTER);
		playButton.setVerticalAlignment(JLabel.CENTER);

		//exit button
		exitButton.setFont(font);
		exitButton.setForeground(Color.WHITE);
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		exitButton.setBounds((frameWidth /2 - 200), 400, 400, 100);
		background.add(exitButton);
		exitButton.setHorizontalAlignment(JLabel.CENTER);
		exitButton.setVerticalAlignment(JLabel.CENTER);
		
		//hard difficulty 
		hardButton.setFont(font);
		hardButton.setForeground(Color.WHITE);
		hardButton.setOpaque(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setBorderPainted(false);
		hardButton.setBounds((frameWidth /2 - 200), 300, 400, 100);
		background.add(hardButton);
		hardButton.setVisible(false);
		hardButton.setHorizontalAlignment(JLabel.CENTER);
		hardButton.setVerticalAlignment(JLabel.CENTER);
		
		//easy difficulty
		easyButton.setFont(font);
		easyButton.setForeground(Color.WHITE);
		easyButton.setOpaque(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setBorderPainted(false);
		easyButton.setBounds((frameWidth /2 - 200), 400, 400, 100);
		easyButton.setVisible(false);
		background.add(easyButton);
		easyButton.setHorizontalAlignment(JLabel.CENTER);
		easyButton.setVerticalAlignment(JLabel.CENTER);
		
		//help
		helpButton.setFont(font);
		helpButton.setForeground(Color.white);
		helpButton.setOpaque(false);
		helpButton.setContentAreaFilled(false);
		helpButton.setBorderPainted(false);
		helpButton.setBounds((frameWidth /2 - 200), 600, 400, 100);
		helpButton.setVisible(true);
		background.add(helpButton);
		helpButton.setHorizontalAlignment(JLabel.CENTER);
		helpButton.setVerticalAlignment(JLabel.CENTER);
	}

}
