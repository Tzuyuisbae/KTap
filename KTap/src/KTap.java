import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.Timer;

import GUI.Mainscreen;

public class KTap {
	
	public static void main(String[] args) throws IOException {

		// create the main screen
		Mainscreen mainScreen = new Mainscreen(1700, 1000);
		
		// set the delay
		int delay = 100;
		
		// create an action listener
		ActionListener action = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// move the notes
				moveNote();
			}
			
		    public void keyPressed(KeyEvent e) {
		        System.out.println("KEY PRESSED: ");
		    }
			
		};
		
		mainScreen.playButton.addActionListener(action);
		
		// read the notes
		
		// start the game
		new Timer(delay, action).start();
		
//		Timer timer = new Timer(40, new ActionListener() {
//		    @Override
//		    public void actionPerformed(ActionEvent ae) {
//
//		    }
//		});
		
	}
	
	public static void moveNote() {
		
	}

}
