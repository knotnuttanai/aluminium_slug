package application;

import character.Enemy;
import character.Hero;
import environment.Foreground;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import weapon.Bullet;

public class GameLoop implements Runnable{
	private boolean running;
	private GameScene gameScene;
	private Canvas canvas;
	private long previousTime;
	private double multiplyer;
	private Thread thread;
	
	private double timePass;
	public GameLoop() {
		canvas = new Canvas(640 ,480);
		
		gameScene = new GameScene(canvas);
		
		previousTime = System.nanoTime();
		multiplyer = 1000000000/60;
		timePass = 0;
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
		}
		if(Math.random()<0.01) {
		Enemy enemy = new Enemy(Math.random()*400+400, 250, 50);
		
		enemy.addEnemy();
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
	
	

}
