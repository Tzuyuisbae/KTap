import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class KTap {
	
	public static void main(String[] args) {

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
