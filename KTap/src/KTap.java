import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import GUI.Mainscreen;

public class KTap {
	
	public static void main(String[] args) {

		// create the main screen
		Mainscreen mainScreen = new Mainscreen(1700, 1000);
		
		// create an action listener
		ActionListener action = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		// read the notes
		
		// start the game
		new Timer(1000, action).start();
	}

}
