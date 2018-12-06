package weapon;

import application.GameEntity;
import character.Hero;
import character.Shootable;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tank extends GameObject {
	public boolean  isUsed;
	private int health, maxHealth,gunOfHero, bulletOfHero, healthOfHero;
	Hero hero;
	private boolean toggle;
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
	public void render(GraphicsContext gc) {
		if(!isUsed) {
			gc.setFill(Color.PINK);
			gc.fillRect(posX, posY, width, height);
		}
		
		
	}

	

	public boolean isUsed() {
		return isUsed;
	}
	
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	
	

	

	
	
}
