package application;



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
	private Foreground fg;
	private boolean heroWalkOverBase;
	private boolean canWalk;
	public boolean dIsPressed , aIsPressed;
	public EventManager(Scene scene, Hero hero, Foreground fg) {
		this.scene = scene;
		this.hero = hero;
		this.fg =fg;
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
				/*if(event.getCode() == KeyCode.W) {
					
						//เล็งบนดิสัส
					
					
				}*/
				if(event.getCode() == KeyCode.D ) {
					dIsPressed = true;
					System.out.println("d");
					
				}
				
				else if(event.getCode() == KeyCode.A) {
					aIsPressed = true;
					
				}
				
				else if(event.getCode() == KeyCode.SPACE) {
					hero.Jump();
					if(heroWalkOverBase) {
						continueToWalk();
					}
					
				}
			
			}
			
		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode() == KeyCode.D) {
					dIsPressed = false;
					System.out.println("release d");
					
					
				}
				else if(event.getCode() == KeyCode.A) {
					aIsPressed = false;
					
				}
			}
		});
		
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
			if(canWalk) {
				hero.Walk(2);
			}
			else {
				fg.moveScreen(-3);
				for(Terrain x : GameEntity.terrains) {
					x.walk(-1);
				}
			}
		}
		//end key D
		//start key A
		if(aIsPressed) {
			if(!isAtTheEndOfScreen()) {
				fg.moveScreen(0);
				hero.Walk(-2);
				for(Enemy x: GameEntity.enemies) {
					x.walk(fg.getVeloX());
				}
			}
			else {
				hero.Walk(0);
				
			}
			doneMovingLeft = false;
		}
		else {
			hero.Walk(0);
			fg.stop();
			for(Enemy x: GameEntity.enemies) {
				x.stop();
			}
			
		}
		//end key A
	}
	
	
}
