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
		for(Hero h : hero) {
			h.setHasHorizontalCollision(false);
			h.setHasVerticalCollition(false);
		}
		for(Enemy e : enemies) {
			e.setHasHorizontalCollision(false);
			e.setHasVerticalCollition(false);
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
			/*boolean check = false;
			for(Terrain t : terrains) {
				if(t.isInteract()) {
					check = true;
				}
			}
			if(!check) {
				for(Hero h : hero) {
					h.setHasHorizontalCollision(false);
					h.setHasVerticalCollition(false);
				}
				for(Enemy e : enemies) {
					e.setHasHorizontalCollision(false);
					e.setHasVerticalCollition(false);
				}
				
			}*/
			
			
		
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
