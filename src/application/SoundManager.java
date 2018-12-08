package application;

import javafx.scene.media.AudioClip;

public class SoundManager {
	public static void play(String name, double volumne) {
		AudioClip clip = new AudioClip(ClassLoader.getSystemResource(name + ".wav").toString());
		clip.setVolume(volumne);
		clip.play();
	}

	public static void playMediaLoop(String name) {
		AudioClip clip = new AudioClip(ClassLoader.getSystemResource(name + ".wav").toString());
		clip.setCycleCount(AudioClip.INDEFINITE);
		clip.setVolume(0.2);
		clip.play();
	}
}
