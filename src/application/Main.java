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
		Hero hero = new Hero(200, 200, 5000);
		Terrain terrain1 = new Terrain(200, 350, 2000, 10);
		GameEntity.createTerrain(terrain1);
		for(int i = 0; i < 40; i++) {
			Terrain terrain2 = new Terrain(600 + (i*4), 346 - (i*4), 1050, 10);
			GameEntity.createTerrain(terrain2);
		}
		
		
		loop = new GameLoop();
		
		
		primaryStage = loop.getGameScene().getStage();
 		primaryStage.show();
 		loop.start();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
