package application;

import character.Enemy;
import character.Hero;
import environment.Terrain;
import environment.StairTerrain;
import javafx.application.Application;
import javafx.stage.Stage;
import weapon.GameObject;
import weapon.Gun;
import weapon.Tank;

public class Main extends Application {
	private GameLoop loop;
	private SpawnManager spawnManager;
	@Override
	public void start(Stage primaryStage) {
		
		
		Hero hero = new Hero(200, 200, 100000);

		GameEntity.spawnManager.initWorld(0);
		Gun gun = new Gun(400, 200, 50, 50);
		gun.addObject();
		Tank tank = new Tank(500, 200, 100, 100, hero);
		tank.addObject();
		
		
		
		loop = new GameLoop();
		
		
		primaryStage = loop.getGameScene().getStage();
 		primaryStage.show();
 		loop.start();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
