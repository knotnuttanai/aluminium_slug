package application;

import character.Hero;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class EventManager {
	private Scene scene;
	private Hero hero;
	public EventManager(Scene scene, Hero hero) {
		this.scene = scene;
		this.hero = hero;
	}
	public void setPlayerControl() {
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				hero.Jump();
			}
				
		});
	}
}
