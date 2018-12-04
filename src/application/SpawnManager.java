package application;

import character.Enemy;
import character.Hero;
import environment.StairTerrain;

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
	public void createStair(double posX, double posY, double width, double height,int count, int x, int xPrime , int y,int priority) {
		for(int i = 0; i < count; i++) {
			StairTerrain terrain2 = new StairTerrain(posX + (i*x), posY + (i*y), width - xPrime, height);
			terrain2.setPriority(priority);
			GameEntity.createTerrain(terrain2);
		}
	}
}
