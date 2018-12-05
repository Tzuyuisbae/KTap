package Old;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * Frame that contains all panels
 * 
 * @author joe
 *
 */
public class Frame extends JFrame implements ActionListener{

	///private ImageIcon icon = new ImageIcon("dumbbell.png");
	MainScreenPanel mainScreen = new MainScreenPanel(); 
	static EndScreen endScreen = new EndScreen();
	static GamePanel gamePanel = new GamePanel();

	//screen dimensions
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int frameWidthPosition = (screenSize.width / 2) - 500;
	private int frameWidth = 1000;

	public Frame(){

		setLayout(null);
		setBounds(frameWidthPosition,0,frameWidth,800);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Superstar Bighit Entertainment");
		setVisible(true);
		
		//main screen
		mainScreen.setBounds(0,0,frameWidth,800);   			 
		mainScreen.setVisible(true);
		mainScreen.playButton.addActionListener(this);		
		mainScreen.exitButton.addActionListener(this);
		mainScreen.hardButton.addActionListener(this);
		mainScreen.easyButton.addActionListener(this);
		mainScreen.helpButton.addActionListener(this);
		add(mainScreen); 

		//game screen
		gamePanel.setBounds(0,0,frameWidth,800);
		gamePanel.setVisible(false);
		add(gamePanel); 

		//end screen
		endScreen.setBounds(0,0,frameWidth,800);
		endScreen.setVisible(false);
		add(endScreen); 
		endScreen.exitButton.addActionListener(this);

		//key listeners for user input
		setFocusable(true);
		addKeyListener(new KeyListener() {

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				gamePanel.keyRelease(e.getKeyCode());
				//System.out.println(e.getKeyCode());
			}

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				gamePanel.keyPress(e.getKeyCode());
				//System.out.println(e.getKeyCode());
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	//show end screen
	public static void finishGame() {
		endScreen.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		//if user presses play
		if(e.getSource() == mainScreen.playButton){

			mainScreen.playButton.setVisible(false);
			mainScreen.exitButton.setVisible(false);
			mainScreen.hardButton.setVisible(true);
			mainScreen.easyButton.setVisible(true);
		}

		//if user selects hard difficulty
		if (e.getSource() == mainScreen.hardButton) {
			remove(mainScreen);
			gamePanel.setVisible(true);
			gamePanel.loadNotes(1);
		}

		//if user selects easy difficulty
		if (e.getSource() == mainScreen.easyButton){
			remove(mainScreen);
			gamePanel.setVisible(true);
			gamePanel.loadNotes(2);
		}

		//if user selects how to play
		if (e.getSource() == mainScreen.helpButton) {
	        JOptionPane.showMessageDialog(null, "Press the dfjk keys (depending on which column the note is in) when the notes cross the line, and hold down until the note fully crosses the line. \n After the game, your performance will be calculated and displayed with a grade from S+ to D (S+ is best, D is worst)", "Help", JOptionPane.INFORMATION_MESSAGE);
		}

		//read in a returning user's username
		if(e.getSource() == mainScreen.exitButton){

			System.exit(0);

		}

		//if user clicks exit button
		if(e.getSource() == endScreen.exitButton){

			System.exit(0);
		}

	}

}
