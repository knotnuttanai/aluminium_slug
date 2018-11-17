package application;

import character.Hero;
import character.Person;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import weapon.Bullet;

public class Main extends Application {
	private GameLoop loop;
	private GameScene scene;
	@Override
	public void start(Stage primaryStage) {
		Hero hero = new Hero(50, 50, 50);
		
		loop = new GameLoop();
		EventManager ev = new EventManager(loop.getGameScene().getScene(), hero);
		ev.setPlayerControl();
		
		primaryStage = loop.getGameScene().getStage();
 		primaryStage.show();
 		(new Thread(loop)).start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
