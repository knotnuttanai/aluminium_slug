package application;

import character.Hero;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class EventManager {
	private Scene scene;
	private Hero hero;
	public EventManager(Scene scene, Hero hero) {
		this.scene = scene;
		this.hero = hero;
	}
	public void setPlayerControl() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				hero.shoot();
				
			}
			

		

		
		});
	}
}