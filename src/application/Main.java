package application;

import character.Hero;
import character.Soldier;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import weapon.MachineGun;

public class Main extends Application {
	
	private GameLoop loop;
	private EventManager ev;
	@Override
	public void start(Stage primaryStage) {
		
		loop = new GameLoop();
		Hero marco = new Hero();
		Soldier bot = new Soldier();
		
		MachineGun wow = new MachineGun(marco); 
	    EventManager ev = new EventManager();
	    ev.playerKeyEvent(marco);
		
		
		
 		
 		primaryStage.setScene(GameScene.scene);
 		primaryStage.show();
 		loop.run();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
