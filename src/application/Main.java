package application;

import character.Person;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		Person marco = new Person(200,500,200);
		Group root = new Group();
		Rectangle marcoMan = marco.getMan();
		root.getChildren().add(marcoMan);
		
		marcoMan.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode() == KeyCode.D) {
					marco.Walk(1);
					System.out.println(marco.getPosX());
				}
			}
		});
				
		Scene scene = new Scene(root,1280,720);
		
 		primaryStage.setScene(scene);
 		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
