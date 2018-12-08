package application;

import character.Enemy;
import character.GunSoldier;
import environment.Foreground;
import environment.Terrain;
import javafx.animation.AnimationTimer;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.Canvas;
import weapon.Bullet;
import weapon.GameObject;

public class GameLoop implements Runnable {
	private boolean running;
	private GameScene gameScene;
	private Canvas canvas;
	private Thread thread;
	private EventManager ev;
	private AnimationTimer an;
	private ScorePane score;
	private BulletPane bullet1;

	public GameLoop() {
		score = new ScorePane();
		canvas = new Canvas(640, 480);
		gameScene = new GameScene(canvas);
		ev = new EventManager(gameScene.getScene(), GameEntity.hero);
		bullet1 = new BulletPane(GameEntity.hero);
		ev.setPlayerControl();
	}

	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		if (running) {
			an = new AnimationTimer() {
				@Override
				public void handle(long now) {
					try {
						updateContent(now);
						renderContent();
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("index out of bound");
						SoundManager.playMediaLoop("BGM");
					}
				}
			};
			an.start();
		}
	}

	private void updateContent(long now) {
		score.update();
		gameScene.getHeroStatusPane().update();
		bullet1.update();
		if (GameEntity.getCurrentFg().getPosX() <= -8550 + 640) {
			GameEntity.spawnManager.initWorld(640);
			GameEntity.spawnManager.increaseEnemyPower();
			if (!GameEntity.hero.isInTheTank()) {
				GameEntity.hero.setHealth(GameEntity.hero.getMaxHealth());
			}
		}
		if (ev.getHero().isAnimatedDead()) {
			gameScene.getRoot().getChildren().clear();
			an.stop();
		}
		ev.keyHandle();
		if (heroWalkOverBase()) {
			ev.setHeroWalkOverBase(true);
			setWalk(false);
		} else {
			ev.setHeroWalkOverBase(false);
			setWalk(true);
		}
		GameEntity.spawnManager.spawnEnemy();
		GameEntity.checkStand();
		GameEntity.calculateHit();
		GameEntity.clearDead();
		for (Foreground fg : GameEntity.fgs) {
			fg.update();
		}
		GameEntity.hero.update();
		gameScene.getHpBar().update();
		for (Bullet x : GameEntity.bullets) {
			x.update();
		}
		for (Enemy x : GameEntity.enemies) {
			if (x instanceof GunSoldier) {
				GunSoldier x1 = (GunSoldier) x;
				x1.update(now);
			} else
				x.update();
		}
		for (Terrain x : GameEntity.terrains) {
			x.update();
		}
		for (GameObject g : GameEntity.gameObjects) {
			g.update();
		}
		gameScene.getExpBar().update();
		GameEntity.spawnManager.spawnMachineGun();
	}

	private void renderContent() {
		gameScene.blink();
		for (Foreground fg : GameEntity.fgs) {
			fg.render(gameScene.getView());
		}
		score.render(gameScene.getView());
		bullet1.render(gameScene.getView());
		GameEntity.hero.render(gameScene.getView());
		for (Enemy x : GameEntity.enemies) {
			x.render(gameScene.getView());
		}
		for (Bullet x : GameEntity.bullets) {
			x.render(gameScene.getView());
		}
		for (Terrain x : GameEntity.terrains) {
			x.render(gameScene.getView());
		}
		for (GameObject g : GameEntity.gameObjects) {
			g.render(gameScene.getView());
		}
	}

	public GameScene getGameScene() {
		return gameScene;
	}

	public void setGameScene(GameScene gameScene) {
		this.gameScene = gameScene;
	}

	public EventManager getEv() {
		return ev;
	}

	public void setEv(EventManager ev) {
		this.ev = ev;
	}

	public boolean heroWalkOverBase() {
		BoundingBox b1 = new BoundingBox(ev.getHero().getPosX(), ev.getHero().getPosY(), ev.getHero().getWidth(),
				ev.getHero().getHeight());
		return b1.intersects(ev.getHero().getBaseX() + ev.getHero().getWidth(), 0, 640 - ev.getHero().getBaseX(), 480);
	}

	public void setWalk(boolean canWalk) {
		ev.setCanWalk(canWalk);
	}

}