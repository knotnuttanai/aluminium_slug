package application;

import character.Enemy;
import character.Hero;
import environment.Terrain;
import environment.StairTerrain;
import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import weapon.GameObject;
import weapon.Gun;
import weapon.Tank;

public class Main extends Application {
	private GameLoop loop;
	private SpawnManager spawnManager;
	@Override
	public void start(Stage primaryStage) {
		
		
		GameEntity.spawnManager.initWorld(0);
		
		
		
		loop = new GameLoop();
		SoundManager.playMediaLoop("BGM");
		SoundManager.play("Mission1",1);
		/*Sound s = new Sound("res/sounds/BGM.wav");
		s.loop();
		Sound start = new Sound("res/sounds/Mission1.wav");
		start.play();*/
		primaryStage = loop.getGameScene().getStage();
 		primaryStage.show();
 		loop.start();
 		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
