package application;

import java.io.File;

import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;

public class SoundManager {
	public SoundManager() {
		
	}
	public void playSound(String soundUrl) {
		AudioClip sound = new AudioClip(new File(soundUrl).toURI().toString());
		sound.play();
		System.out.println("2");
	}
}
