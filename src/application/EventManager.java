package application;

import character.Enemy;
import character.Hero;
import environment.Foreground;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class EventManager {
	private Scene scene;
	private boolean done;
	private Hero hero;
	private Foreground fg;
	public EventManager(Scene scene, Hero hero, Foreground fg) {
		this.scene = scene;
		this.hero = hero;
		this.fg =fg;
		done = false;
	}
	public void setPlayerControl() {
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				hero.shoot();
			}
				
		});
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode() == KeyCode.SPACE) {
					
						hero.Jump();
						done = true;
					
					
				}
				else if(event.getCode() == KeyCode.D) {
					fg.moveScreen(-1);
					for(Enemy x: GameEntity.enemies) {
						x.walk(fg.getVeloX());
					}
				}
				else if(event.getCode() == KeyCode.A) {
					fg.moveScreen(1);
					for(Enemy x: GameEntity.enemies) {
						x.walk(fg.getVeloX());
					}
				}
			
			}
			
		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode() == KeyCode.D) {
					fg.stop();
					for(Enemy x: GameEntity.enemies) {
						x.stop();
					}
				}
				else if(event.getCode() == KeyCode.A) {
					fg.stop();
					for(Enemy x: GameEntity.enemies) {
						x.stop();
					}
					
				}
			}
		});
		
	}
}
