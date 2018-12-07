package application;

import java.net.URL;

import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;

public class SoundManager {
	public SoundManager() {
		
	}
	public void playSound() {
		URL url = getClass().getResource("HeavyMachineGun.wav");
		AudioClip sound = new AudioClip(url.toString());
		sound.play();
		System.out.println("2");
	}
}
