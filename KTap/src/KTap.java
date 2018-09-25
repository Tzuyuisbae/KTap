import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import GUI.Mainscreen;

public class KTap {
	
	public static void main(String[] args) {

		// create the main screen
		Mainscreen mainScreen = new Mainscreen(1700, 1000);
		
		// set the delay
		int delay = 100;
		
		// create an action listener
		ActionListener action = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("test");
			}
			
		    public void keyPressed(KeyEvent e) {
		        System.out.println("KEY PRESSED: ");
		    }
			
		};
		
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
	
	public void moveNote() {
		
	}

}
