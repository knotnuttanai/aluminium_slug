package application;



import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {
	public static void play(String name,double volumne) {
		AudioClip clip = new AudioClip("file:res/sounds/"+name+".wav");
		clip.setVolume(1);
		clip.play();
	}
	public static void playMediaLoop(String name) {
		AudioClip clip = new AudioClip("file:res/sounds/"+name+".wav");
		clip.setCycleCount(clip.INDEFINITE);
		clip.setVolume(0.3);
		clip.play();
	}
}
