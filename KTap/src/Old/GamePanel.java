package Old;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/** Game Panel where the game is played
 *
 */
public class GamePanel extends JPanel implements ActionListener {

	//scoring values
	static int score;
	static int hp = 1000;
	static int noteAmount;
	static int combo = 0;

	//highscore
	static int highscore;

	//labels
	static JLabel scoreLabel = new JLabel("Score: " + score);
	static JLabel accuracyLabel = new JLabel("");
	static JLabel startLabel = new JLabel("Press S to start the game");
	static JLabel comboLabel = new JLabel(combo + " combo");
	static JLabel hpLabel = new JLabel("HP Bar");
	private JLabel dLabel = new JLabel("D");
	private JLabel fLabel = new JLabel("F");
	private JLabel jLabel = new JLabel("J");
	private JLabel kLabel = new JLabel("K");
	private JLabel dBorder = new JLabel(); 
	private JLabel fBorder = new JLabel();
	private JLabel jBorder = new JLabel();
	private JLabel background = new JLabel();

	//list of notes
	static ArrayList<Note> noteList = new ArrayList<Note>();

	//frame properties
	private static int frameWidth = 1000;
	private int frameHeight = 800;

	//check if game started
	private boolean startGame = false;
	static boolean endGame = false;

	//how well the player hits a note
	static String hitAccuracy = "";

	//where the "line" for the hitting is measured
	private int yBound = (int) (frameHeight - 150);

	//ranges for each hit
	private int perfectRange = 50;
	private int goodRange = 100;
	private int rangeClick = 300;

	//counter for each hit
	static int perfecthits;
	static int goodhits;
	static int okayhits;
	static int misses;

	//timers
	Timer checkTimer = new Timer(1000,this); //check for game completion
	Timer holdTimer = new Timer(10,this); //add points while user holds down note
	static GameTimer timer;
	
	//music
	public static InputStream in ;
	public static AudioStream audioStream;

	public GamePanel() {

		//this.frame = frame;
		setLayout(null);
		setBackground(new Color(0, 204, 204, 90));

		//set background image
		Image img = null;
		try {
			img = ImageIO.read(new File("endBackground.jpg"));
			img = img.getScaledInstance(1000,800,Image.SCALE_SMOOTH);
			background.setBounds(0,0,1000,800);
			background.setIcon(new ImageIcon(img));
			background.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		add(background);

		//set font
		Font font = new Font("Agency FB", Font.BOLD, 40);

		//score label
		scoreLabel.setFont(font);
		scoreLabel.setForeground(Color.black);
		scoreLabel.setBounds(frameWidth - 300, 0, 340, 50);
		background.add(scoreLabel);
		scoreLabel.setVerticalAlignment(JLabel.CENTER);

		//accuracy label
		accuracyLabel.setFont(font);
		accuracyLabel.setForeground(Color.black);
		accuracyLabel.setBounds((frameWidth/2) - 50, (int) (frameHeight - 300), 400, 50);
		background.add(accuracyLabel);
		accuracyLabel.setVerticalAlignment(JLabel.CENTER);

		//combo label
		comboLabel.setFont(font);
		comboLabel.setForeground(Color.black);
		comboLabel.setBounds((frameWidth/2) - 50, 100, 400, 50);
		background.add(comboLabel);
		comboLabel.setVerticalAlignment(JLabel.CENTER);

		//start game label
		startLabel.setFont(font);
		startLabel.setForeground(Color.black);
		startLabel.setBounds((frameWidth/2 - 150), (int) (frameHeight/2 - 100), 1000, 50);
		startLabel.setVisible(true);
		background.add(startLabel);
		startLabel.setVerticalAlignment(JLabel.CENTER);

		//hp bar
		hpLabel.setFont(font);
		hpLabel.setForeground(Color.WHITE);
		hpLabel.setBackground(Color.red);
		hpLabel.setBounds(0, 0, hp/2, 50);
		hpLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		hpLabel.setOpaque(true);
		background.add(hpLabel);
		hpLabel.setHorizontalAlignment(JLabel.CENTER);
		hpLabel.setVerticalAlignment(JLabel.CENTER);

		//D label
		dLabel.setFont(font);
		dLabel.setForeground(Color.black);
		dLabel.setBounds(0, (int) (frameHeight - 150),(frameWidth / 4), 50);
		dLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		dLabel.setOpaque(true);
		background.add(dLabel);
		dLabel.setHorizontalAlignment(JLabel.CENTER);
		dLabel.setVerticalAlignment(JLabel.CENTER);

		//F label
		fLabel.setFont(font);
		fLabel.setForeground(Color.black);
		fLabel.setBounds((frameWidth / 4), (int) (frameHeight - 150),(frameWidth / 4), 50);
		fLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		fLabel.setOpaque(true);
		background.add(fLabel);
		fLabel.setHorizontalAlignment(JLabel.CENTER);
		fLabel.setVerticalAlignment(JLabel.CENTER);

		//J label
		jLabel.setFont(font);
		jLabel.setForeground(Color.black);
		jLabel.setBounds((frameWidth / 2), (int) (frameHeight - 150), (frameWidth / 4), 50);
		jLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		jLabel.setOpaque(true);
		background.add(jLabel);
		jLabel.setHorizontalAlignment(JLabel.CENTER);
		jLabel.setVerticalAlignment(JLabel.CENTER);

		//K label
		kLabel.setFont(font);
		kLabel.setForeground(Color.black);
		kLabel.setBounds((frameWidth / 4 * 3), (int) (frameHeight - 150), (frameWidth / 4), 50);
		kLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		kLabel.setOpaque(true);
		background.add(kLabel);
		kLabel.setHorizontalAlignment(JLabel.CENTER);
		kLabel.setVerticalAlignment(JLabel.CENTER);

		//D border
		dBorder.setBounds(0, 0,(frameWidth / 4), (int) frameHeight);
		dBorder.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		background.add(dBorder);

		//F border
		fBorder.setBounds((frameWidth / 4), 0, (frameWidth / 4), (int) frameHeight);
		fBorder.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		background.add(fBorder);

		//J border
		jBorder.setBounds((frameWidth / 4 * 3), 0, (frameWidth / 4), (int) frameHeight);
		jBorder.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		background.add(jBorder);

		//read music
		try {
			in = new FileInputStream("bts.wav");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			audioStream = new AudioStream(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//player loses health
	public static void loseHealth() {

		//if hp reaches 0
		if (hp - 50 <= 0) {
			//lose the game
			finishGame(1);
		}

		//subtract health
		hp -= 50;

		//update health label
		hpLabel.setBounds((frameWidth - 1000), 0, hp/2, 50);
		hpLabel.setHorizontalAlignment(JLabel.CENTER);
		hpLabel.setVerticalAlignment(JLabel.CENTER);
	}

	//update score + hit accuracy labels
	public static void updateScore() {
		scoreLabel.setText("Score: " + score);
		accuracyLabel.setText(hitAccuracy);
		comboLabel.setText("" + combo);
	}

	//game is complete
	public static void finishGame(int winlose) {

		//stop timers
		timer.cancel();
		endGame = true;
		
		//calculate performance
		Frame.endScreen.setPerfecthits(perfecthits);
		Frame.endScreen.setGoodhits(goodhits);
		Frame.endScreen.setOkayhits(okayhits);
		Frame.endScreen.setMisses(misses);
		EndScreen.setScore(score);
		Frame.endScreen.calculatePerformance(winlose,noteAmount);

		//display end screen
		Frame.finishGame();
		Frame.gamePanel.setVisible(false);
		Frame.endScreen.updateLabels();

		//stop music
		AudioPlayer.player.stop(audioStream);

		//read highscore
		try {
			Scanner input = new Scanner(new File("highscore"));
			highscore = input.nextInt();

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//update highscore if needed
		if (score > highscore) {

			BufferedWriter writer;

			try {

				writer = new BufferedWriter(new FileWriter("highscore"));
				writer.write (Integer.toString(score));
				writer.close();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	//start the game
	public void startGame() {

		//hide start label
		startLabel.setVisible(false);
		
		//set startgame boolean true
		startGame = true;	

		//start the timers
		checkTimer.start();
		
		//start music
		AudioPlayer.player.start(audioStream);

		//start note timer
        timer = new GameTimer();

	}

	public void actionPerformed(ActionEvent e) {

		//increase score as user holds down note
		if (e.getSource() == holdTimer) {
			score++;
			updateScore();
		}
		
		//check if game is done
		else if (e.getSource() == checkTimer) {

			boolean gameComplete = true;
			
			//loop through all notes
			for (Note note: noteList) {

				//if there is still a note in game
				if (note.inGame) {

					//game is not over
					gameComplete = false;
				}
			}

			//if all notes are played
			if (gameComplete == true) {

				//end the game
				finishGame(2);
			}
		}
	}

	//reduce wait time for note to come down
	public static void reduceWait() {

		for (int x = 0; x < noteList.size(); x++) {
			noteList.get(x).reduceNoteDelay(10);
		}
	}

	//load the notes
	public void loadNotes(int difficulty) {

		Scanner input = null;

		try {

			//depending on difficulty selected
			if (difficulty == 1){

				input = new Scanner(new File("Notes.txt"));

			} else if (difficulty == 2){

				input = new Scanner(new File("Notes2.txt"));
			}

			//total number of notes
			noteAmount = input.nextInt();

			for (int x = 0; x < noteAmount; x++) {

				//note properties
				int column = input.nextInt();
				double delay = input.nextDouble();
				int duration = input.nextInt();

				try {

					//create the note
					Note newNote = new Note(column, delay, duration);
					newNote.setNoteCount(Integer.toString(x + 2)); //used to place notes with more accuracy
					noteList.add(newNote);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//add notes to screen
				background.add(noteList.get(x));
			}

			input.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} 


	//releasing key
	public void keyRelease(int j) {

		//if game is running
		if (startGame && !endGame) { 

			//stop hold score timer
			holdTimer.stop();

			//loop through notes
			for(Note note : noteList) {

				//if note has been clicked and note missed
				if (note.clicked && note.inGame == true) {

					//depending on column
					if (note.column == j) {

						//calculate hit
						int y = note.noteY;
						int diff = Math.abs(yBound-y);

						if (diff < perfectRange) {// perfect hit

							//increase combo
							combo++;

							//update labels
							hitAccuracy = "Perfect";
							score+= 10 + (combo * 2);
							updateScore();

							//increase perfect note hits
							perfecthits++;


						} else if (diff < goodRange && diff > perfectRange) {// good hit

							//increase combo
							combo++;

							//update labels
							hitAccuracy = "Good";
							score+= 5 + combo;
							updateScore();

							//increase good note hits
							goodhits++;


						} else if (diff < rangeClick && diff > goodRange) {// okay hit

							//increase combo
							combo++;

							//update labels
							hitAccuracy = "Okay";
							score+= 50 + combo;
							updateScore();

							//increase okay note hits
							okayhits++;


						} else if (diff < 1000 && diff > rangeClick){// miss

							//reset combo
							combo = 0;

							//update labels
							hitAccuracy = "Miss";
							updateScore();

							//increase misses
							misses++;

							//lose hp
							loseHealth();
						}

						//remove note
						note.setBounds(0,0,0,0);
						note.inGame = false;
						break;
					}
				}
			}
		}
	}

	public void keyPress(int i) {

		//if game has not started
		if(i == KeyEvent.VK_S && startGame == false){

			//start the game
			startGame();
		}

		//if game is running
		if (startGame && !endGame) { //&& gameTimer.isRunning(

			//loop through all notes
			for(Note note : noteList) {

				//depending on note column
				if (note.column == i) {

					//if note is not clicked and still in game
					if (!note.clicked && note.inGame) {

						//calculate the hit
						int y = note.noteY + note.height;
						int diff = Math.abs(yBound-y);

						//note is clicked
						note.clicked = true;

						if (diff <= perfectRange) {// perfect hit

							//increase combo
							combo++;

							//update labels
							hitAccuracy = "Perfect";
							score+= 10 + (combo * 2);
							updateScore();

							//increase perfect hits
							perfecthits++;

							//start timer to increase score based on holding down note duration
							holdTimer.start();

						} else if (diff <= goodRange && diff > perfectRange) {// good hit

							//increase combo
							combo++;

							//update labels
							hitAccuracy = "Good";
							score+= 5 + combo;
							updateScore();

							//increase good hits
							goodhits++;

							//start timer to increase score based on holding down note duration
							holdTimer.start();

						} else if (diff <= rangeClick && diff > goodRange) {// okay hit

							//increase combo
							combo++;

							//update labels
							hitAccuracy = "Okay";
							score+= 1 + combo;
							updateScore();

							//increase okay hits
							okayhits++;

							//start timer to increase score based on holding down note duration
							holdTimer.start();

						} else if (diff < 1000 && diff > rangeClick){// miss

							//reset combo
							combo = 0;

							//update labels
							hitAccuracy = "Miss";
							updateScore();

							//increase number of notes missed
							misses++;

							//note is no longer in game
							note.inGame=false;

							//hide note
							note.setBounds(0,0,0,0);

							//lose hp
							loseHealth();
						}
						break;
					}
				}
			}
		}
	}
}
