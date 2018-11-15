package application;

import character.Hero;
import character.Soldier;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import weapon.MachineGun;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		Hero marco = new Hero();
		Soldier bot = new Soldier();
		MachineGun wow = new MachineGun(marco); 
		Group root = new Group();
		root.getChildren().addAll(marco.getMarco(),bot.getSoldier(),wow.getGun());
		root.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode() == KeyCode.SPACE) {
					//shoot
					
				}
			}
			
		});
		
 		Scene scene = new Scene(root, 1280, 720);
 		primaryStage.setScene(scene);
 		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
