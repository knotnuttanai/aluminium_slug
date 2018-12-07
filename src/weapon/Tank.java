package weapon;

import application.GameEntity;
import character.Hero;
import character.Shootable;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Tank extends GameObject {
	public boolean  isUsed;
	private int health, maxHealth,gunOfHero, bulletOfHero, healthOfHero;
	Hero hero;
	private boolean toggle;
	private Image tankImage;
	private Image tankGun;
	private Image[] movingTank;
	private Image[] shootGun;
	private int tankFrame,shootFrame;
	
	public Tank(double posX, double posY, double width, double height, Hero hero) {
		super(posX, posY, width, height);
		// TODO Auto-generated constructor stub
		isUsed = false;
		this.hero = hero;
		health = 0;
		maxHealth = 20000;
		toggle = false;
		gunOfHero = 0;
		healthOfHero = 0;
		bulletOfHero = 0;
		tankImage = new Image("file:res/images/tank1.png");
		movingTank = new Image[14];
		for(int i = 1; i <= 14; i++) {
			movingTank[i-1] = new Image("file:res/images/tank" + i + ".png");
		}
		tankFrame = 0;
		
		tankGun = new Image("file:res/images/tankgun1.png");
		shootGun = new Image[5];
		for(int i = 1; i<= 5; i++) {
			shootGun[i-1] = new Image("file:res/images/tankgun" + i + ".png");
		}
		shootFrame = 0;
	}

	public void update() {
		
		if(this.posY >=800||this.posX +this.width < -10) {
			isHit = true;;
		} 
		if(isUsed&&!toggle) {
			toggle = true;
			healthOfHero = hero.getHealth();
			gunOfHero = hero.getGun();
			if(gunOfHero!=0) {
				bulletOfHero = hero.getUseGunBullet();
			}
			else bulletOfHero = 0;
			health = hero.getHealth();
			hero.setHealth(health+maxHealth+20000000);
		}
		else if(isUsed) {
			if(hero.getHealth() <= health+20000000) {
				hero.setGun(gunOfHero);
				hero.setHealth(healthOfHero);
				hero.setUseGunBullet(bulletOfHero);
				hero.setInTheTank(false);
				hero.setRequestToEnterTank(false);
				isHit = true;
			}
			else {
				posX = hero.getPosX();
				posY = hero.getPosY(); 
				
			}
		}
		else {
				if(!hasVerticalCollition) {
					this.veloY += GRAVITY;
			
				}
				else if(hasVerticalCollition && veloY > 0) {
					this.veloY = 0;
				}
				veloX = baseVeloX + GameEntity.getCurrentFg().getVeloX();
				posX += veloX;
				posY += veloY;
		}
		b = new BoundingBox(posX, posY, width, height);
	}
	
	private int adjustShootPos() {
		if(shootFrame/3 == 1) return 8;
		if(shootFrame/3 == 2) return 4;
		return 0;
	}
	
	public void render(GraphicsContext gc) {
		if(!isUsed) {
			gc.drawImage(tankImage, posX, posY - 8);
		}
		else {
			gc.drawImage(movingTank[(tankFrame/3)%14], posX - 34, posY - 34);
			
			tankFrame++;
			if(tankFrame >= 42) tankFrame = 0;
			
			if(hero.isShoot()) {
				gc.drawImage(shootGun[(shootFrame/3)%5], posX, posY - adjustShootPos());
				shootFrame++;
				if(shootFrame >= 15) {
					shootFrame = 0;
					hero.setIsShoot(false);
				}
			}
			else {
				gc.drawImage(tankGun, posX, posY);
			}
		}
		
		
	}

	

	public boolean isUsed() {
		return isUsed;
	}
	
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	
	

	

	
	
}
