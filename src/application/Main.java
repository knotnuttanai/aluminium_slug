package application;

import character.Enemy;
import character.Hero;
import environment.Terrain;
import environment.StairTerrain;
import javafx.application.Application;
import javafx.stage.Stage;
import weapon.Gun;

public class Main extends Application {
	private GameLoop loop;
	private SpawnManager spawnManager;
	@Override
	public void start(Stage primaryStage) {
		spawnManager = new SpawnManager();
		Hero hero = new Hero(200, 200, 10000);
		spawnManager.initWorld(0);
		Gun gun = new Gun(400, 200, 10, 10);
		gun.addGun();
		
		
		
		loop = new GameLoop();
		
		
		primaryStage = loop.getGameScene().getStage();
 		primaryStage.show();
 		loop.start();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
