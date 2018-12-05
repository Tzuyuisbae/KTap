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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**End Screen Panel 
 * 
 * - displays when game is over and shows the player's performance
 * 
 * @author joe
 *
 */
public class EndScreen extends JPanel implements ActionListener{

	//Integer values
	static int score;
	static int highscore;
	private int perfecthits;
	private int goodhits;
	private int okayhits;
	private int misses;

	//name labels
	private JLabel scoreLabel = new JLabel("Your Score:");
	private JLabel performanceLabel = new JLabel("");
	private JLabel highscoreLabel = new JLabel("Highscore:");
	private JLabel perfectLabel = new JLabel("Perfect:");
	private JLabel goodLabel = new JLabel("Good:");
	private JLabel okayLabel = new JLabel("Okay:");
	private JLabel missLabel = new JLabel("Misses:");

	//number labels
	private JLabel scoreNumLabel = new JLabel("" + score);
	private JLabel highscoreNumLabel = new JLabel("" + highscore);
	private JLabel perfectNumLabel = new JLabel("" + perfecthits);
	private JLabel goodNumLabel = new JLabel("" + goodhits);
	private JLabel okayNumLabel = new JLabel("" + okayhits);
	private JLabel missNumLabel = new JLabel("" + misses);

	//background label
	JLabel background = new JLabel();

	//Panels
	private JPanel scorePanel = new JPanel();
	private JPanel performancePanel = new JPanel();

	//exit button
	JButton exitButton = new JButton("Exit");

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public EndScreen() {

		setLayout(null);
		setBackground(Color.BLACK);

		Font font = new Font("Agency FB", Font.BOLD, 50);
		Font performanceFont = new Font("Agency FB", Font.BOLD, 300);

		//set background image
		Image img = null;
		try {
			img = ImageIO.read(new File("anime background.jpg"));
			img = img.getScaledInstance(1000, (int) screenSize.getHeight(),Image.SCALE_SMOOTH);
			background.setBounds(0,0,1000,(int) screenSize.getHeight());
			background.setIcon(new ImageIcon(img));
			background.setVisible(true);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		add(background);

		//score panel
		scorePanel.setBounds(50,100,900,625);
		scorePanel.setBackground(new Color(0, 204, 204, 90));
		background.add(scorePanel);

		//performance panel
		performancePanel.setBounds(600,200,300,300);
		performancePanel.setBackground(new Color(204, 255, 255, 90));
		background.add(performancePanel);

		//score num label
		scoreNumLabel.setFont(font);
		scoreNumLabel.setForeground(Color.black);
		scoreNumLabel.setBounds(400, 550, 1000, 200);
		background.add(scoreNumLabel);

		//perfect num Label
		perfectNumLabel.setFont(font);
		perfectNumLabel.setForeground(Color.orange);
		perfectNumLabel.setBounds(400, 50, 1000, 200);
		background.add(perfectNumLabel);

		//good num Label
		goodNumLabel.setFont(font);
		goodNumLabel.setForeground(Color.blue);
		goodNumLabel.setBounds(400, 150, 1000, 200);
		background.add(goodNumLabel);

		//okay num Label
		okayNumLabel.setFont(font);
		okayNumLabel.setForeground(Color.green);
		okayNumLabel.setBounds(400, 250, 1000, 200);
		background.add(okayNumLabel);

		//miss num Label
		missNumLabel.setFont(font);
		missNumLabel.setForeground(Color.red);
		missNumLabel.setBounds(400, 350, 1000, 200);
		background.add(missNumLabel);

		//highscore num label
		highscoreNumLabel.setFont(font);
		highscoreNumLabel.setForeground(Color.black);
		highscoreNumLabel.setBounds(400, 450, 1000, 200);
		background.add(highscoreNumLabel);

		//score label
		scoreLabel.setFont(font);
		scoreLabel.setForeground(Color.black);
		scoreLabel.setBounds(100, 550, 1000, 200);
		background.add(scoreLabel);

		//perfect Label
		perfectLabel.setFont(font);
		perfectLabel.setForeground(Color.orange);
		perfectLabel.setBounds(100, 50, 1000, 200);
		background.add(perfectLabel);

		//good Label
		goodLabel.setFont(font);
		goodLabel.setForeground(Color.blue);
		goodLabel.setBounds(100, 150, 1000, 200);
		background.add(goodLabel);

		//okay Label
		okayLabel.setFont(font);
		okayLabel.setForeground(Color.green);
		okayLabel.setBounds(100, 250, 1000, 200);
		background.add(okayLabel);

		//miss Label
		missLabel.setFont(font);
		missLabel.setForeground(Color.red);
		missLabel.setBounds(100, 350, 1000, 200);
		background.add(missLabel);

		//highscore label
		highscoreLabel.setFont(font);
		highscoreLabel.setForeground(Color.black);
		highscoreLabel.setBounds(100, 450, 1000, 200);
		background.add(highscoreLabel);

		//performance label
		performanceLabel.setFont(performanceFont);
		performanceLabel.setForeground(Color.blue);
		performanceLabel.setBounds(600, 200, 300, 300);
		performanceLabel.setHorizontalAlignment(JLabel.CENTER);
		performanceLabel.setVerticalAlignment(JLabel.CENTER);
		background.add(performanceLabel);

		//exit button
		exitButton.setFont(font);
		exitButton.setForeground(Color.white);
		exitButton.setBackground(Color.BLACK);
		exitButton.setBounds(650, 625, 200, 50);
		background.add(exitButton);

		//read highscore
		try {
			Scanner input = new Scanner(new File("highscore"));
			highscore = input.nextInt();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//calculate how well the player did
	public void calculatePerformance(int losewin, int noteNum){

		//players performance
		int performance = 0;
		
		//max score
		int maxPerformance = noteNum * 100;
		
		//ranges for each score
		int sPlus = maxPerformance;
		double s = (maxPerformance * 0.95);
		double sMinus = (maxPerformance * 0.90);
		double aPlus = (maxPerformance * 0.85);
		double a = (maxPerformance * 0.80);
		double aMinus = (maxPerformance * 0.75);
		double bPlus = (maxPerformance * 0.65);
		double b = (maxPerformance * 0.60);
		double bMinus = (maxPerformance * 0.55);
		double cPlus = (maxPerformance * 0.50);
		double c = (maxPerformance * 0.45);
		double cMinus = (maxPerformance * 0.40);
		double d = (maxPerformance * 0.35);

		//if player lost game
		if (losewin == 1) {

			//calculate performance
			performance+= perfecthits * 10;
			performance+= goodhits * 5;
			performance+= okayhits * 1;
			performance-= misses * 1;

			//depending on performance
			if (performance == sPlus) {
				
				//set performance text label
				performanceLabel.setText("S+");
				
			}

			else if (performance >= s && performance < sPlus) {
				
				performanceLabel.setText("S");

			}

			else if (performance >= sMinus && performance < s) {

				performanceLabel.setText("S-");

			}
			
			else if (performance >= aPlus && performance < sMinus) {

				performanceLabel.setText("A+");

			}
			
			else if (performance >= a && performance < aPlus) {

				performanceLabel.setText("A");

			}

			else if (performance >= aMinus && performance < a) {

				performanceLabel.setText("A-");

			}
			
			else if (performance >= bPlus && performance < aMinus) {

				performanceLabel.setText("B-");

			}
			
			else if (performance >= b && performance < bPlus) {

				performanceLabel.setText("B");

			}

			else if (performance >= bMinus && performance < b) {

				performanceLabel.setText("B-");

			}
			
			else if (performance >= cPlus && performance < bMinus) {

				performanceLabel.setText("C+");

			}
			
			else if (performance >= c && performance < cPlus) {

				performanceLabel.setText("C");

			}

			else if (performance >= cMinus && performance < c) {

				performanceLabel.setText("C-");

			}
			
			else if (performance < cMinus) {

				performanceLabel.setText("D");

			}
		} 

		//else if player won game
		else if (losewin == 2) {
			
			//calculate performance
			performance+= perfecthits * 100;
			performance+= goodhits * 50;
			performance+= okayhits * 10;
			performance-= misses * 5;
			
			//depending on performance
			if (performance == sPlus) {
				
				//set performance text label
				performanceLabel.setText("S+");
				
			}

			else if (performance >= s && performance < sPlus) {
				
				performanceLabel.setText("S");

			}

			else if (performance >= sMinus && performance < s) {

				performanceLabel.setText("S-");

			}
			
			else if (performance >= aPlus && performance < sMinus) {

				performanceLabel.setText("A+");

			}
			
			else if (performance >= a && performance < aPlus) {

				performanceLabel.setText("A");

			}

			else if (performance >= aMinus && performance < a) {

				performanceLabel.setText("A-");

			}
			
			else if (performance >= bPlus && performance < aMinus) {

				performanceLabel.setText("B-");

			}
			
			else if (performance >= b && performance < bPlus) {

				performanceLabel.setText("B");

			}

			else if (performance >= bMinus && performance < b) {

				performanceLabel.setText("B-");

			}
			
			else if (performance >= cPlus && performance < bMinus) {

				performanceLabel.setText("C+");

			}
			
			else if (performance >= c && performance < cPlus) {

				performanceLabel.setText("C");

			}

			else if (performance >= cMinus && performance < c) {

				performanceLabel.setText("C-");

			}
			
			else if (performance < cMinus) {

				performanceLabel.setText("D");

			}
		}
	}

	//update labels
	public void updateLabels() {

		scoreNumLabel.setText("" + score);
		highscoreNumLabel.setText("" + highscore);
		perfectNumLabel.setText("" + perfecthits);
		goodNumLabel.setText("" + goodhits);
		okayNumLabel.setText("" + okayhits);
		missNumLabel.setText("" + misses);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == exitButton) {
			System.exit(0);
		}
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		EndScreen.score = score;
	}

	public static int getHighscore() {
		return highscore;
	}

	public static void setHighscore(int highscore) {
		EndScreen.highscore = highscore;
	}

	public int getPerfecthits() {
		return perfecthits;
	}

	public void setPerfecthits(int perfecthits) {
		this.perfecthits = perfecthits;
	}

	public int getGoodhits() {
		return goodhits;
	}

	public void setGoodhits(int goodhits) {
		this.goodhits = goodhits;
	}

	public int getOkayhits() {
		return okayhits;
	}

	public void setOkayhits(int okayhits) {
		this.okayhits = okayhits;
	}

	public int getMisses() {
		return misses;
	}

	public void setMisses(int misses) {
		this.misses = misses;
	}

}
