package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private GameLoop loop;

	@Override
	public void start(Stage primaryStage) throws Exception {
		GameEntity.spawnManager.initWorld(0);
		loop = new GameLoop();
		SoundManager.playMediaLoop("BGM");
		primaryStage = loop.getGameScene().getStage();
		primaryStage.show();
		loop.start();
		
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
