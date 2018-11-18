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
	private boolean doneMovingLeft;
	private Hero hero;
	private Foreground fg;
	public EventManager(Scene scene, Hero hero, Foreground fg) {
		this.scene = scene;
		this.hero = hero;
		this.fg =fg;
		doneMovingLeft = true;
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
						if(heroWalkOverBase()) {
							continueToWalk();
							}
						if(isAtTheEndOfScreen()) {
							hero.Walk(0);
						}
					
					
				}
				if(event.getCode() == KeyCode.D ) {
					if(doneMovingLeft) {
						fg.moveScreen(-1);
						for(Enemy x: GameEntity.enemies) {
						x.walk(fg.getVeloX());	
						}
					}
					else if(!doneMovingLeft && hero.getPosX() < hero.getBaseX()) {
						hero.Walk(1);
					}
					else if(heroWalkOverBase()) {
						hero.Walk(0);
						continueToWalk();
						for(Enemy x: GameEntity.enemies) {
						x.walk(fg.getVeloX());	
						}
						
					}
				}
				if(event.getCode() == KeyCode.A) {
					if(!isAtTheEndOfScreen()) {
						fg.moveScreen(0);
						hero.Walk(-1);
						for(Enemy x: GameEntity.enemies) {
							x.walk(fg.getVeloX());
						}
					}
					else {
						hero.Walk(0);
						
					}
					doneMovingLeft = false;
				}
				
				if(event.getCode() == KeyCode.SPACE) {
					hero.shoot();
					if(!doneMovingLeft) {
					if(heroWalkOverBase()) {
						continueToWalk();
						}
					}
					if(isAtTheEndOfScreen()) {
						hero.Walk(0);
					}
				}
			
			}
			
		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode() == KeyCode.D) {
					if(doneMovingLeft) {
						
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
	public boolean heroWalkOverBase() {
		return hero.getPosX() >= hero.getBaseX();
		
	}
	public void continueToWalk() {
		if(!doneMovingLeft) {
		hero.Walk(0);
		fg.moveScreen(-1);
		doneMovingLeft = true;
		}
	}
	public boolean isAtTheEndOfScreen() {
		if(hero.getPosX() > 10 && hero.getPosX() < 680) {
			return false;
		}
		else {
			return true;
		}
	}
}
