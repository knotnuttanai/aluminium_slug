package application;

import java.util.ArrayList;

import character.Enemy;
import character.Hero;
import environment.Terrain;
import weapon.Bullet;

public class GameEntity {
	public static ArrayList<Hero> hero = new ArrayList<>();
	public static ArrayList<Bullet> bullets = new ArrayList<>();
	public static ArrayList<Enemy> enemies = new ArrayList<>();
	public static ArrayList<Terrain> terrains = new ArrayList<>();
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
	public static void checkStand() {
		Terrain t;
		for(int i = 0; i < terrains.size(); i++) {
			t = terrains.get(i);
			for(Hero h : hero) {
				if(t.whenSomeOneStandHere(h)) {
					h.setNumberOfTerrain(i);
				}	
			}
		}
		for(Hero h : hero) {
		if(h.getNumberOfTerrain() == -1) {
			h.setHasVerticalCollition(false);
			System.out.println("h");
		}
		else {
			h.setHasVerticalCollition(true);
			h.setNumberOfTerrain(-1);
		}
		
	}
	}
	public static void calculateHit() {
		for(Bullet b : bullets) {
			for(Enemy e : enemies) {
				if(e.isHitByBullet(b)) {
					e.setDead();
					b.setHit();
				}
			}
		}
	}
	public static void clearDead() {
		for(int i = 0; i < enemies.size(); i++) {
			if(!enemies.get(i).isAlive()) {
				enemies.remove(i);
			}
		}
		for(int i = 0; i < bullets.size(); i++) {
			if(bullets.get(i).isHit) {
				bullets.remove(i);
			}
		}
	}
}
