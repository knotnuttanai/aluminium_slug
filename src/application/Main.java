package application;

import character.Hero;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private GameLoop loop;
	
	@Override
	public void start(Stage primaryStage) {
		Hero hero = new Hero(50, 250, 50);
		
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
