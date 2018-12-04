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
	private SpawnManager SpawnManager;
	@Override
	public void start(Stage primaryStage) {
		SpawnManager = new SpawnManager();
		Hero hero = new Hero(200, 200, 100);
		Terrain terrain1 = new Terrain(200, 350, 1600, 10);
		GameEntity.createTerrain(terrain1);
		SpawnManager.createStair(1600, 346, 20,10 ,45,8,8,-2,0);
		SpawnManager.createStair(1800, 350, 15,10 ,60,8,0,2,1);
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
