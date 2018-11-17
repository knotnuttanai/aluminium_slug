package application;

import java.util.ArrayList;

import character.Hero;
import weapon.Bullet;

public class GameEntity {
	public static ArrayList<Hero> hero = new ArrayList<>();
	public static ArrayList<Bullet> bullets = new ArrayList<>();
	public static void createHero(Hero h) {
		hero.add(h);
	}
	public static void createBullet(Bullet b) {
		bullets.add(b);
	}
}
