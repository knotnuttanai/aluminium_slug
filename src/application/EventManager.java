package application;


import character.Person;
import character.Enemy;
import character.Hero;
import environment.Foreground;
import environment.Terrain;
import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class EventManager {
	private Scene scene;
	private boolean doneMovingLeft;
	private Hero hero;
	private boolean heroWalkOverBase;
	private boolean canWalk;
	public boolean dIsPressed , aIsPressed;
	Thread thread;
	public EventManager(Scene scene, Hero hero) {
		this.scene = scene;
		this.hero = hero;
		doneMovingLeft = true;
		heroWalkOverBase = true;
		canWalk = false;
		dIsPressed = false;
		aIsPressed = false;
		
	}
	public boolean isCanWalk() {
		return canWalk;
	}
	public void setCanWalk(boolean canWalk) {
		this.canWalk = canWalk;
	}
	public void setPlayerControl() {
		/*scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				hero.shoot();
			}
				
		});*/
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				 thread = new Thread(() -> {
				
				try {
					while(true) {
					hero.shoot();
					Thread.sleep(hero.getFirerate());
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				});thread.start();
				//System.out.println("shoot");
			}
				
		});
		scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				try {
					thread.interrupt();
					thread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
		});
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode() == KeyCode.W) {
					
						hero.setIsLookUp(true);
					
					
				}
				if(event.getCode() == KeyCode.D ) {
					dIsPressed = true;
					
					
				}
				
				else if(event.getCode() == KeyCode.A) {
					aIsPressed = true;
					
				}
				
				else if(event.getCode() == KeyCode.S) {
					hero.setIsLookDown(true);
					
				}
				
			  else if(event.getCode() == KeyCode.SPACE) {
				
						hero.Jump();
						/*if(heroWalkOverBase) {
							continueToWalk();
						}*/
						
				  
			  }
			
			}
			
		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode() == KeyCode.W) {
					
					hero.setIsLookUp(false);
				
				
			}
				if(event.getCode() == KeyCode.D) {
					dIsPressed = false;
					for(Enemy e: GameEntity.enemies) {
						e.stop();
					}
					//GameEntity.getCurrentFg().moveScreen(0);
					for(Foreground fg : GameEntity.fgs) {
						fg.moveScreen(0);
					}
					
				}
				else if(event.getCode() == KeyCode.A) {
					aIsPressed = false;
					for(Enemy x: GameEntity.enemies) {
						x.stop();
					}
				}
				
				else if(event.getCode() == KeyCode.S) {
					hero.setIsLookDown(false);
					
				}
				
			}
		});
		
	}
	
	public void continueToWalk() {
		if(!doneMovingLeft) {
		hero.Walk(0);
		for(Foreground fg : GameEntity.fgs) {
			fg.moveScreen(-1);
		}
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
	public Hero getHero() {
		return hero;
	}
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	public boolean isHeroWalkOverBase() {
		return heroWalkOverBase;
	}
	public void setHeroWalkOverBase(boolean heroWalkOverBase) {
		this.heroWalkOverBase = heroWalkOverBase;
	}
	public void keyHandle() {
		//start key D
		if(dIsPressed) {
			hero.setIsWalk(true);
			hero.setWalkDirection(1);
			if(hero.isHasHorizontalCollision()) {
				
				return;
			}
			if(canWalk) {
				hero.Walk(2);
			}
			else {
				//GameEntity.getCurrentFg().moveScreen(-6);
				for(Foreground fg : GameEntity.fgs) {
					fg.moveScreen(-5);
					
				}
				
				
			}
		}
		else {
			hero.setIsWalk(false);
			
			
		}
		//end key D
		//start key A
		if(aIsPressed) {
			hero.setIsWalk(true);
			hero.setWalkDirection(-1);
			if(hero.isHasHorizontalCollision()) {
				//return;
			}
			if(!isAtTheEndOfScreen()) {
				//GameEntity.getCurrentFg().moveScreen(0);
				hero.Walk(-2);
				/*for(Enemy x: GameEntity.enemies) {
					x.walk(fg.getVeloX());
				}*/
			}
			else {
				hero.Walk(0);
				
			}
			doneMovingLeft = false;
		}
		else {
			
			//hero.setIsWalk(false);
			hero.Walk(0);
			//fg.stop();
			
			
		}
		//end key A
		
		
	}
	
	
}
