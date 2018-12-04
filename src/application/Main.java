package application;

import character.Enemy;
import character.Hero;
import environment.Terrain;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private GameLoop loop;
	
	@Override
	public void start(Stage primaryStage) {
		Hero hero = new Hero(200, 200, 50);
		Terrain terrain1 = new Terrain(200, 350, 10000, 100);
		GameEntity.createTerrain(terrain1);
		Terrain terrain2 = new Terrain(450, 200, 1050, 10);
		GameEntity.createTerrain(terrain2);
		
		
		
		loop = new GameLoop();
		
		
		primaryStage = loop.getGameScene().getStage();
 		primaryStage.show();
 		loop.start();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
