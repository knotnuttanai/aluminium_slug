package application;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {
	public SoundManager() {
		
	}
	public void playSound() {
		Media sound = new Media(this.getClass().getResource("HeavyMachineGun.mp3").toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
		System.out.println("2");
	}
}
