package application;

import character.Enemy;
import character.Hero;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private GameLoop loop;
	
	@Override
	public void start(Stage primaryStage) {
		Hero hero = new Hero(200, 250, 50);
		
			Enemy enemy = new Enemy(450, 250, 50);
			enemy.addEnemy();
			Enemy enemy2 = new Enemy(400, 250, 50);
			enemy2.addEnemy();
		
		loop = new GameLoop();
		EventManager ev = new EventManager(loop.getGameScene().getScene(), hero, loop.getFg());
		ev.setPlayerControl();
		
		primaryStage = loop.getGameScene().getStage();
 		primaryStage.show();
 		loop.start();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
