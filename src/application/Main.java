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
		Hero hero = new Hero(200, 200, 10000);
		Terrain terrain1 = new Terrain(200, 350, 1600, 10);
		GameEntity.createTerrain(terrain1);
		SpawnManager.createStair(1600, 346, 20,60 ,45,8,8,-2,0);
		SpawnManager.createStair(1800, 350, 15,60 ,60,8,0,2,1);
		Terrain terrain2 = new Terrain(1960, 256, 670, 10);
		GameEntity.createTerrain(terrain2);
		Terrain terrain3 = new Terrain(1960, 470, 4300, 10);
		GameEntity.createTerrain(terrain3);
		Terrain terrain4 = new Terrain(2716, 256, 650, 10);
		GameEntity.createTerrain(terrain4);
		Terrain terrain5 = new Terrain(2440, 350, 112, 10);
		GameEntity.createTerrain(terrain5);
		Terrain terrain6 = new Terrain(2772, 370, 104, 10);
		GameEntity.createTerrain(terrain6);
		Terrain terrain7 = new Terrain(3464, 256, 200 , 10);
		GameEntity.createTerrain(terrain7);
		Terrain terrain8 = new Terrain(3660, 200, 420 , 10);
		GameEntity.createTerrain(terrain8);
		Terrain terrain9 = new Terrain(4452, 200, 420 , 10);
		GameEntity.createTerrain(terrain9);
		Terrain terrain10 = new Terrain(4872, 256, 228 , 10);
		GameEntity.createTerrain(terrain10);
		Terrain terrain11 = new Terrain(5185, 256, 647 , 10);
		GameEntity.createTerrain(terrain11);
		Terrain terrain12 = new Terrain(5940, 256, 684 , 10);
		GameEntity.createTerrain(terrain12);
		SpawnManager.createStair(6612, 256, 2,60 ,40,8,0,2,0);
		SpawnManager.createStair(6252, 475, 20,60 ,62,8,8,-2,1);
		Terrain terrain13 = new Terrain(6756, 345, 1800, 10);
		GameEntity.createTerrain(terrain13);
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
