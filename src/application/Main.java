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
		Tank tank = new Tank(500, 200, 100, 100, GameEntity.hero);
		tank.addObject();
		
		
		loop = new GameLoop();
		
		
		primaryStage = loop.getGameScene().getStage();
 		primaryStage.show();
 		loop.start();
 		Sound s = new Sound("res/sounds/BGM.wav");
		s.loop();
		Sound start = new Sound("res/sounds/Mission1.wav");
		start.play();
		/*AudioClip clip = new AudioClip(this.getClass().getResource("/aluminium_slug/res/sounds/HeavyMachineGun.wav").toString());
		clip.play();*/
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
