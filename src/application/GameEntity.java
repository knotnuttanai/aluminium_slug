package application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import character.Enemy;
import character.Hero;
import environment.Foreground;
import environment.Terrain;
import weapon.Bomb;
import weapon.Bullet;
import weapon.EnemyBullet;
import weapon.GameObject;
import weapon.Gun;
import weapon.HeroBullet;
import weapon.Tank;
import weapon.TankBullet;

public class GameEntity {
	public static SpawnManager spawnManager = new SpawnManager();
	public static List<Bullet> bullets = new CopyOnWriteArrayList<>();
	public static ArrayList<Enemy> enemies = new ArrayList<>();
	public static ArrayList<Terrain> terrains = new ArrayList<>();
	public static List<GameObject> gameObjects = new CopyOnWriteArrayList<>();
	public static List<Foreground> fgs = new ArrayList<>();
	public static Hero hero = new Hero(200, 200, 200);

	public static void createBullet(Bullet b) {
		bullets.add(b);
	}

	public static void createTerrain(Terrain t) {
		terrains.add(t);
	}

	public static void createEnemy(Enemy e) {
		enemies.add(e);
	}

	public static void createGameObject(GameObject g) {
		gameObjects.add(g);
	}

	public static void createFg(Foreground fg) {
		fgs.add(fg);
	}

	public static Foreground getCurrentFg() {
		try {
			return fgs.get(fgs.size() - 1);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public static void checkStand() {
		try {
			GameEntity.hero.setHasHorizontalCollision(false);
			GameEntity.hero.setHasVerticalCollition(false);
			GameEntity.hero.setStandOnMainTerrain(false);
			for (Enemy e : enemies) {
				e.setHasHorizontalCollision(false);
				e.setHasVerticalCollition(false);
				e.setStandOnMainTerrain(false);
			}
			for (GameObject g : gameObjects) {
				g.setHasVerticalCollition(false);
			}
			for (Enemy e : enemies) {
				for (Terrain t : terrains) {
					if (e.checkInteract(t)) {
						t.isSomeOneHitHere(e);
						t.standVertical(e);
					}
				}
			}
			for (Terrain t : terrains) {
				if (GameEntity.hero.checkInteract(t)) {
					t.isSomeOneHitHere(GameEntity.hero);
					t.standVertical(GameEntity.hero);
				}
			}
			for (GameObject g : gameObjects) {
				for (Terrain t : terrains) {
					t.gunStandVertical(g);
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	public static void calculateHit() {
		try {
			for (Enemy e : enemies) {
				for (Bullet b : bullets) {
					if (b instanceof HeroBullet) {
						HeroBullet b1 = (HeroBullet) b;
						if (e.isHitByBullet(b1)) {
							e.takeDamage(b1.getDamage());
							if (!(b1 instanceof TankBullet)) {
								b.setHit();
							}
						}
					}
				}
				for (GameObject g : GameEntity.gameObjects) {
					if (e.checkInteract(g)) {
						if (g instanceof Bomb) {
							if (((Bomb) g).isIgnited()) {
								e.takeDamage(10);
							}
						}
					}
				}
			}
			for (Bullet b : bullets) {
				if (b instanceof EnemyBullet) {
					EnemyBullet b1 = (EnemyBullet) b;
					if (GameEntity.hero.isHitByBullet(b1)) {
						GameEntity.hero.takeDamage(b1.getDamage());
						System.out.println(GameEntity.hero.getHealth());
						System.out.println("dmg");
						b.setHit();
					}
				}
			}
			for (GameObject g : GameEntity.gameObjects) {
				if (GameEntity.hero.checkInteract(g)) {
					if (g instanceof Gun) {
						if (!GameEntity.hero.isInTheTank()) {
							SoundManager.play("HeavyMachineGun", 0.2);
							GameEntity.hero.setGun(1);
							GameEntity.hero.setUseGunBullet(GameEntity.hero.getUseGunBullet() + 256);
						}
						g.setHit(true);
					}
					if (g instanceof Tank) {
						Tank tank = (Tank) g;
						if (GameEntity.hero.isRequestToEnterTank()) {
							if (!GameEntity.hero.isInTheTank()) {
								tank.setUsed(true);
								GameEntity.hero.setInTheTank(true);
							}
						}
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	public static void clearDead() {
		try {
			for (int i = 0; i < enemies.size(); i++) {
				if (enemies.get(i).isAnimatedDead()) {
					enemies.remove(i);
				}
			}
			for (int i = 0; i < bullets.size(); i++) {
				if (bullets.get(i).isHit) {
					bullets.remove(i);
				}
			}
			for (int i = 0; i < gameObjects.size(); i++) {

				if (gameObjects.get(i).isHit()) {
					gameObjects.remove(i);
				}
			}
			for (int i = 0; i < terrains.size(); i++) {
				if (terrains.get(i).isDead()) {
					terrains.remove(i);
				}
			}
			for (int i = 0; i < fgs.size(); i++) {
				if (fgs.get(i).isDead()) {
					fgs.remove(i);
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	public static void restart() {
		GameLoop.an.stop();
		enemies.clear();
		bullets.clear();
		gameObjects.clear();
		fgs.clear();
		terrains.clear();
		hero.setPosX(hero.getBaseX());
		hero.setPosY(hero.getBaseY());
		spawnManager.initWorld(0);
		hero.Walk(0);
		hero.setIsWalk(false);
		hero.setInTheTank(false);
		hero.setHealth(200);
		hero.setMaxHealth(200);
		hero.setMaxGrenade(10);
		ScorePane.score = 0;
		ExpBar.level = 0;
		HeroStatusPane.statusPoint = 0;
		hero.setUseGunBullet(0);
		hero.setDamage(0);
		hero.setMoveSpeed(-5);
		GameLoop.an.start();
	}
}
