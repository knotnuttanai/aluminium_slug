package application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import character.Enemy;
import character.Hero;
import environment.Foreground;
import environment.Terrain;
import weapon.Bullet;
import weapon.EnemyBullet;
import weapon.GameObject;
import weapon.HeroBullet;

public class GameEntity {
	public static SpawnManager spawnManager = new SpawnManager();
	public static ArrayList<Hero> hero = new ArrayList<>();
	public static List<Bullet> bullets = new CopyOnWriteArrayList<>();
	public static ArrayList<Enemy> enemies = new ArrayList<>();
	public static ArrayList<Terrain> terrains = new ArrayList<>();
	public static ArrayList<GameObject> gameObjects = new ArrayList<>();
	public static List<Foreground> fgs = new ArrayList<>();
	public static void createHero(Hero h) {
		hero.add(h);
	}
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
		}catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
		
	}
	public static void increaseEnemyPower() {
		/*for(Enemy e : enemies) {
			e.setHealth(e.getHealth()+hp);
			e.setDmg(dmg);
		}*/
		spawnManager.increaseEnemyPower();
		spawnManager.setLimitNumber(spawnManager.getLimitNumber()+1);
	}
	public static void restoreHeroHp() {
		for(Hero h : hero) {
			h.setHealth(h.getMaxHealth());
		}
	}
	public static void checkStand() {
		for(Hero h : hero) {
			h.setHasHorizontalCollision(false);
			h.setHasVerticalCollition(false);
			h.setStandOnMainTerrain(false);
		}
		for(Enemy e : enemies) {
			e.setHasHorizontalCollision(false);
			e.setHasVerticalCollition(false);
			e.setStandOnMainTerrain(false);
		}
		for(GameObject g : gameObjects) {
			g.setHasVerticalCollition(false);
		}
		for(Enemy e : enemies) {
			for(Terrain t : terrains) {
				/*if(h.getPosX() >= t.posX && h.getPosX() <= t.posX + t.width) {*/
				if(e.checkInteract(t)) {
					t.isSomeOneHitHere(e);
					t.standVertical(e);
				}
		}
		}
		for(Hero h : hero) {
			for(Terrain t : terrains) {
				if(h.checkInteract(t)) {
					t.isSomeOneHitHere(h);
					t.standVertical(h);
					 
				}
				
			}
		}
		for(GameObject g : gameObjects) {
			for(Terrain t : terrains) {
				t.gunStandVertical(g);
			}
		}
	}
	public static void calculateHit() {
		for(Enemy e : enemies) {
			for(Bullet b : bullets) {
				if(b instanceof HeroBullet) {
					HeroBullet b1 = (HeroBullet) b;
					if(e.isHitByBullet(b1)) {
						e.takeDamage(b1.getDamage());
						b.setHit();
					}
				}
			}
		}
		for(Hero h: hero) {
			for(Bullet b : bullets) {
				if(b instanceof EnemyBullet) {
					EnemyBullet b1 = (EnemyBullet) b;
					if(h.isHitByBullet(b1)) {
						h.takeDamage(b1.getDamage());
						b.setHit();
						
					}
				}
			}
			for(GameObject g : GameEntity.gameObjects) {
				if(h.checkInteract(g)) {
					h.setGun(1);
					g.setHit(true);
				}
			}
		}
	}
	public static void clearDead() {
		for(int i = 0; i < enemies.size(); i++) {
			if(!enemies.get(i).isAlive()) {
				enemies.remove(i);
				ScorePane.addScore(200);
			}
		}
		for(int i = 0; i < bullets.size(); i++) {
			if(bullets.get(i).isHit) {
				bullets.remove(i);
			}
		}
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i).isHit()) {
				gameObjects.remove(i);
			}
		}
	}
	
}
