package application;

import character.Enemy;
import character.Hero;
import environment.Foreground;
import environment.Terrain;
import javafx.animation.AnimationTimer;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.Canvas;
import weapon.Bullet;

public class GameLoop implements Runnable{
	private boolean running;
	private GameScene gameScene;
	private Canvas canvas;
	private long previousTime;
	private double multiplyer;
	private Thread thread;
	private EventManager ev;
	private double timePass;
	
	public GameLoop() {
		canvas = new Canvas(640 ,480);
		
		gameScene = new GameScene(canvas);
		for(Hero x : GameEntity.hero) {
			ev = new EventManager(gameScene.getScene(), x, gameScene.getFg());
		}
		ev.setPlayerControl();
	
	}
	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(running) {
			AnimationTimer an = new AnimationTimer() {
				
				@Override
				public void handle(long now) {
					// TODO Auto-generated method stub
					
					gameScene.blink();
					
					updateContent();
					renderContent();
					
					
					
					
				}

				
			};
			an.start();
		}
		
	}
	private void stop() {
		// TODO Auto-generated method stub
		try {
			thread.join();
			running = false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private void updateContent() {
		// TODO Auto-generated method stub
		
		ev.keyHandle();
		GameEntity.checkStand();
		if(heroWalkOverBase()) {
			ev.setHeroWalkOverBase(true);
			setWalk(false);
		}
		else {
			ev.setHeroWalkOverBase(false);
			setWalk(true);
		}
		
		GameEntity.calculateHit();
		GameEntity.clearDead();
		gameScene.getFg().update();
		for(Hero x: GameEntity.hero) {
			x.update();
		}
		for(Bullet x: GameEntity.bullets) {
			x.update();
		}
		for(Enemy x : GameEntity.enemies) {
			x.update();
			
			if(Math.random() < 0.01) {
				x.Jump();
			}
		}
		for(Terrain x : GameEntity.terrains) {
			x.update();
		}
		if(Math.random() < 0.01) {
			Enemy enemy = new Enemy(800*Math.random(), 50, 50);
			enemy.addEnemy();
			System.out.println("added");
		}
		
	}
	private void renderContent() {
		gameScene.blink();
		gameScene.getFg().render(gameScene.getView());
		for(Hero x: GameEntity.hero) {
			x.render(gameScene.getView());
		}
		for(Bullet x: GameEntity.bullets) {
			x.render(gameScene.getView());
		}
		for(Enemy x : GameEntity.enemies) {
			x.render(gameScene.getView());
		}
		for(Terrain x : GameEntity.terrains) {
			x.render(gameScene.getView());
		}
		
		
		
		
	}
	public GameScene getGameScene() {
		return gameScene;
	}
	public void setGameScene(GameScene gameScene) {
		this.gameScene = gameScene;
	}
	public Foreground getFg() {
		return gameScene.getFg();
	}
	public EventManager getEv() {
		return ev;
	}
	public void setEv(EventManager ev) {
		this.ev = ev;
	}
	public boolean heroWalkOverBase() {
		BoundingBox b1 = new BoundingBox(ev.getHero().getPosX()	, ev.getHero().getPosY(), ev.getHero().getWidth(), ev.getHero().getHeight());
		return b1.intersects(ev.getHero().getBaseX()+ev.getHero().getWidth(),0 , 640 - ev.getHero().getBaseX(), 480);
	}
	public void setWalk(boolean canWalk) {
		ev.setCanWalk(canWalk);
	}
	
	

}
