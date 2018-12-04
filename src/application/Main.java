package application;

import character.Enemy;
import character.Hero;
import environment.Terrain;
import environment.StairTerrain;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private GameLoop loop;
	private SpawnManager SpawnManager;
	@Override
	public void start(Stage primaryStage) {
		SpawnManager = new SpawnManager();
		Hero hero = new Hero(200, 200, 100);
		Terrain terrain1 = new Terrain(200, 350, 2000, 10);
		GameEntity.createTerrain(terrain1);
		SpawnManager.createStair();
		
		
		
		loop = new GameLoop();
		
		
		primaryStage = loop.getGameScene().getStage();
 		primaryStage.show();
 		loop.start();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
