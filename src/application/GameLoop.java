package application;

import character.Hero;
import environment.Foreground;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import weapon.Bullet;

public class GameLoop implements Runnable{
	private boolean running;
	private GameScene gameScene;
	private Canvas canvas;
	Foreground fg;
	public GameLoop() {
		canvas = new Canvas(640 ,480);
		running = true;
		gameScene = new GameScene(canvas);
		fg = new Foreground();
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
	private void updateContent() {
		// TODO Auto-generated method stub
		for(Hero x: GameEntity.hero) {
			x.update();
		}
		for(Bullet x: GameEntity.bullets) {
			x.update();
		}
		
	}
	private void renderContent() {
		fg.render(gameScene.getView());
		for(Hero x: GameEntity.hero) {
			x.render(gameScene.getView());
		}
		for(Bullet x: GameEntity.bullets) {
			x.render(gameScene.getView());
		}
		
		
		
	}
	public GameScene getGameScene() {
		return gameScene;
	}
	public void setGameScene(GameScene gameScene) {
		this.gameScene = gameScene;
	}
	

}
