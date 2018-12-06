package application;

import character.Enemy;
import character.GunSoldier;
import character.HandSoldier;
import character.Hero;
import environment.Foreground;
import environment.StairTerrain;
import environment.Terrain;

public class SpawnManager {
	private int numberOfEnemy;
	private int limitNumber;
	private double baseOfHero;
	private double spawnRate;
	private int dmg;
	private int hp;
	public SpawnManager() {
		limitNumber = 3;
		numberOfEnemy = 0;
		spawnRate = 0.01;
		dmg = 0;
		hp = 0;
		/*for(Hero x : GameEntity.hero) {
			baseOfHero = x.getBaseX();
		}*/
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
			HandSoldier enemy = new HandSoldier(640+100*Math.random(), 100, 50);
			enemy.setDmg(dmg+enemy.getDmg());
			enemy.setHealth(hp+enemy.getHealth());
			enemy.addEnemy();
			System.out.println("added");
		}
		
	}
	public void increaseEnemyPower() {
		dmg += 5;
		hp += 10;
		spawnRate += 0.002;	
	}
	public void initWorld(double start) {
		Foreground fg = new Foreground(start);
		fg.addFg();
		Terrain terrain1 = new Terrain(start, 350, 1800, 10);
		GameEntity.createTerrain(terrain1);
		createStair(start+1600, 346, 20,60 ,45,8,8,-2,0);
		createStair(start+1800, 350, 15,60 ,60,8,0,2,1);
		Terrain terrain2 = new Terrain(start+1960, 256, 670, 10);
		GameEntity.createTerrain(terrain2);
		Terrain terrain3 = new Terrain(start+1960, 470, 4300, 10);
		GameEntity.createTerrain(terrain3);
		Terrain terrain4 = new Terrain(start+2716, 256, 650, 10);
		GameEntity.createTerrain(terrain4);
		Terrain terrain5 = new Terrain(start+2440, 350, 112, 10);
		GameEntity.createTerrain(terrain5);
		Terrain terrain6 = new Terrain(start+2772, 370, 104, 10);
		GameEntity.createTerrain(terrain6);
		Terrain terrain7 = new Terrain(start+3464, 256, 200 , 10);
		GameEntity.createTerrain(terrain7);
		Terrain terrain8 = new Terrain(start+3660, 200, 420 , 10);
		GameEntity.createTerrain(terrain8);
		Terrain terrain9 = new Terrain(start+4452, 200, 420 , 10);
		GameEntity.createTerrain(terrain9);
		Terrain terrain10 = new Terrain(start+4872, 256, 228 , 10);
		GameEntity.createTerrain(terrain10);
		Terrain terrain11 = new Terrain(start+5185, 256, 647 , 10);
		GameEntity.createTerrain(terrain11);
		Terrain terrain12 = new Terrain(start+5940, 256, 684 , 10);
		GameEntity.createTerrain(terrain12);
		createStair(start+6612, 256, 2,60 ,40,8,0,2,0);
		createStair(start+6252, 470, 20,60 ,62,8,8,-2,0);
		Terrain terrain13 = new Terrain(start+6740, 350, 1800, 10);
		GameEntity.createTerrain(terrain13);
	}
	public void createStair(double posX, double posY, double width, double height,int count, int x, int xPrime , int y,int priority) {
		for(int i = 0; i < count; i++) {
			StairTerrain terrain2 = new StairTerrain(posX + (i*x), posY + (i*y), width - xPrime, height);
			terrain2.setPriority(priority);
			GameEntity.createTerrain(terrain2);
		}
	}
	public double getSpawnRate() {
		return spawnRate;
	}
	public void setSpawnRate(double spawnRate) {
		this.spawnRate = spawnRate;
	}
	public int getLimitNumber() {
		return limitNumber;
	}
	public void setLimitNumber(int limitNumber) {
		this.limitNumber = limitNumber;
	}
	
}
