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
				if(event.getCode() == KeyCode.W) {
					
						hero.Jump();
					
					
					
				}
				if(event.getCode() == KeyCode.D) {
					if(!done) {
						fg.moveScreen(-1);
						for(Enemy x: GameEntity.enemies) {
						x.walk(fg.getVeloX());	
						}
					}
					else if(done && hero.getPosX() < hero.getBaseX()) {
						hero.Walk(1);
					}
					else if(hero.getPosX() >= hero.getBaseX()) {
						hero.Walk(0);
						fg.moveScreen(-1);
						for(Enemy x: GameEntity.enemies) {
						x.walk(fg.getVeloX());	
						}
						done = false;
					}
				}
				else if(event.getCode() == KeyCode.A) {
					if(hero.getPosX() > 0) {
						fg.moveScreen(0);
						hero.Walk(-1);
						for(Enemy x: GameEntity.enemies) {
							x.walk(fg.getVeloX());
						}
					}
					else {
						hero.Walk(0);
					}
					done = true;
				}
				
				else if(event.getCode() == KeyCode.SPACE) {
					hero.shoot();
				}
			
			}
			
		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode() == KeyCode.D) {
					if(!done) {
						
						fg.stop();
						for(Enemy x: GameEntity.enemies) {
							x.stop();
						}
					}
					else {
						if(hero.getPosX() < hero.getBaseX()) {
						hero.Walk(0);
						}
						
					}
					
				}
				else if(event.getCode() == KeyCode.A) {
					hero.Walk(0);
					fg.stop();
					for(Enemy x: GameEntity.enemies) {
						x.stop();
					}
					
				}
			}
		});
		
	}
}
