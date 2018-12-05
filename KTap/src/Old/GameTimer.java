package Old;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer extends Timer{

	Task timerTask;
	int frameHeight = 800;

	public GameTimer() {

		timerTask = new Task();
		//running timer task as daemon thread
		scheduleAtFixedRate(timerTask, 0, 11);
	}

	class Task extends TimerTask {

		public void run() {

			//lower wait time for each note
			GamePanel.reduceWait();

			//loop through notes
			for (Note note: GamePanel.noteList) {

				//if note is still in game
				if (note.noteY < frameHeight - 150 && note.getNoteDelay() <= 0 && note.inGame) {

					//move note
					note.updateNote();
				} 

				//if note leaves screen unhit
				else if (note.noteY + note.height >= frameHeight - 150 && note.clicked == false && note.inGame) {

					//hide note
					note.setBounds(0,0,0,0);

					//reset combo
					GamePanel.combo = 0;

					//lose hp
					GamePanel.loseHealth();

					//update labels
					GamePanel.hitAccuracy = "Miss";
					GamePanel.updateScore();

					//add one more missed note
					GamePanel.misses++;

					//note is no longer in game
					note.inGame = false;

				}
			}
		}
	}
}