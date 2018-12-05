package Old;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**Note object
 * 
 * @author joe
 *
 */
public class Note extends JLabel{

	public int column; //column note is in
	public double delay; //delay for note to come down
	private int noteX; //note's x coordinate
	public int noteY; //note's y coordinate
	public boolean clicked = false; //whether user clicked on this note
	public boolean inGame = true; //whether note is still in game
	private int noteSpeed = 10; //speed note goes down screen
	private int frameWidth = 1000; //width of frame
	public int height; //height of note
	private double bps = 645; //beats per second

	public Note(int column, double delay, int height) throws IOException {

		//assign note position depending on its column
		if (column ==1) {
			this.column = KeyEvent.VK_D;
		} else if (column ==2) {
			this.column = KeyEvent.VK_F;
		} else if (column ==3) {
			this.column = KeyEvent.VK_J;
		} else if (column ==4) {
			this.column = KeyEvent.VK_K;
		}

		//set wait time 
		this.delay = delay * bps;
		this.height = height;

		//start the notes just above the screen
		noteY = -height;

		//assign note depending on it's column
		if (column == 1) {
			noteX = 30;
		} else if (column == 2) {
			noteX = (frameWidth / 4) + 30;
		} else if (column == 3) {
			noteX = (frameWidth / 2) + 30;
		} else if (column == 4){
			noteX = (frameWidth / 4 * 3) + 30;
		}

		//place note on screen
		setBackground(Color.black);
		setOpaque(true);
		setBounds(noteX, noteY, (frameWidth / 4) - 60 , height);
	}

	public void updateNote(){

		//update location of the note 
		setLocation(noteX,noteY+=noteSpeed);

	}

	//lower wait time
	public void reduceNoteDelay(double d) {
		this.delay -= d;
	}
	public double getNoteDelay() {
		return delay;
	}
	public void setNoteCount(String noteCount) {
		setText(noteCount); //used to place notes with more accuracy
	}
}
