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
	private Image tankUp;
	private Image tankDown;
	private Image[] movingTank;
	private Image[] shootGun;
	private Image[] shootUp;
	private Image[] shootDown;
	
	private int tankFrame,shootFrame,upFrame,downFrame ;
	
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
		
		tankUp = new Image("file:res/images/tankup0.png");
		shootUp = new Image[5];
		for(int i = 0; i<= 4; i++) {
			shootUp[i] = new Image("file:res/images/tankup" + i + ".png");
		}
		upFrame = 0;
		
		tankDown = new Image("file:res/images/tankdown0.png");
		shootDown = new Image[5];
		for(int i = 0; i<= 4; i++) {
			shootDown[i] = new Image("file:res/images/tankdown" + i + ".png");
		}
		upFrame = 0;
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
				if(hero.isLookUp()) {
					int k = 0;
					int t = 0;
					if(upFrame/3 == 1) {k=8;t=48;}
					if(upFrame/3 == 2) {k=4;t=62;}
					if(upFrame/3 == 3) {k=0;t=74;}
					if(upFrame/3 == 4) {k=0;t=76;}
					
					gc.drawImage(shootUp[(upFrame/3)%5], posX - k , posY - 26 - t);
					upFrame++;
					if(upFrame >= 15) {
						upFrame = 0;
						hero.setIsShoot(false);
					}
				}
				else if(hero.isLookDown()) {
					int k = 0;
					if(downFrame/3 == 1) k=6;
					if(downFrame/3 == 2) k=4;
					
					gc.drawImage(shootDown[(downFrame/3)%5], posX - k , posY);
					downFrame++;
					if(downFrame >= 15) {
						downFrame = 0;
						hero.setIsShoot(false);
					}
				}
				else {
					gc.drawImage(shootGun[(shootFrame/3)%5], posX, posY - adjustShootPos());
					shootFrame++;
					if(shootFrame >= 15) {
						shootFrame = 0;
						hero.setIsShoot(false);
					}
				}
			}
			else {
				if(hero.isLookUp()) gc.drawImage(tankUp, posX , posY - 26);
				else if(hero.isLookDown()) gc.drawImage(tankDown, posX, posY);
				else gc.drawImage(tankGun, posX, posY);
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
