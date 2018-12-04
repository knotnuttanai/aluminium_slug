package application;

import character.Enemy;
import character.Hero;

public class SpawnManager {
	private int numberOfEnemy;
	private int limitNumber;
	private double baseOfHero;
	public double spawnRate;
	public SpawnManager() {
		limitNumber = 3;
		numberOfEnemy = 0;
		spawnRate = 0.01;
		for(Hero x : GameEntity.hero) {
			baseOfHero = x.getBaseX();
		}
	}
	public void checkEnemyNumber() {
		int count = 0;
		for(Enemy x : GameEntity.enemies) {
			count++;
		}
		numberOfEnemy = count;
	}
	public void spawnEnemy() {
		checkEnemyNumber();
		if(numberOfEnemy >= limitNumber) {
			return;
		}
		if(Math.random() < spawnRate) {
			Enemy enemy = new Enemy(640+100*Math.random(), 100, 50);
			enemy.addEnemy();
			System.out.println("added");
		}
		
	}
}
