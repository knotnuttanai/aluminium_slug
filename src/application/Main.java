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
		Hero hero = new Hero(200, 250, 50);
		Terrain terrain1 = new Terrain(200, 400, 50, 50);
		GameEntity.createTerrain(terrain1);
		Terrain terrain2 = new Terrain(50, 350, 50, 50);
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
