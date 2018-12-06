package character;

import application.GameEntity;
import application.GameScene;
import environment.Foreground;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import weapon.Bullet;
import weapon.EnemyBullet;
import weapon.HeroBullet;

public class Enemy extends Person {
	
	Image soldierStand;
	Image soldierAim;
	Image[] soldierShoot;
	int shootFrame;
	protected double baseVeloX;
	private double fireRate;
	
	public Enemy(double posX, double posY, int health) {
		super(posX, posY, health);
		// TODO Auto-generated constructor stub
		
		height = 75;
		width = 56;
		soldierStand = new Image("file:res/images/soldierstand.png");
		soldierAim = new Image("file:res/images/soldieraim.png");
		soldierShoot = new Image[4];
		for(int i = 1; i <= 4; i++) {
			soldierShoot[i-1] = new Image("file:res/images/soldiershoot" + i + ".png");
		}
		shootFrame = 0;
		veloX = -2;
		walkDirection = -1;
		baseVeloX = veloX;
		fireRate = 0.005;
		dmg = 0;
		
	}

	@Override
	public void render(GraphicsContext gc) {
		if(isShoot) {

			gc.drawImage(soldierShoot[shootFrame/2], posX, posY);
			shootFrame++;
			if(shootFrame == 8) {
				shootFrame = 0;
				isShoot = false;
			}
		}
		else gc.drawImage(soldierAim, posX, posY);
	}
	
	public void addEnemy() {
		GameEntity.createEnemy(this);
	}
	public void walk(double fgVeloX) {
		veloX = -2 + fgVeloX ;
	}
	public void stop() {
		veloX = -1;
	}
	public boolean isHitByBullet(HeroBullet b) {
		BoundingBox b1 = new BoundingBox(posX, posY, width, height);
		BoundingBox b2 = new BoundingBox(b.getPosX(), b.getPosY(), b.getWidth(), b.getHeight());
		return b1.intersects(b2);
	}
	public void shoot() {
		isShoot = true;
		EnemyBullet bullet = new EnemyBullet(this);
		bullet.setDamage(dmg+bullet.getDamage());
		bullet.addBullet();
	}
	public void update() {
		
		if(this.posY >=800||this.posX +this.width < -10) {
			this.setDead();
		}
		if(isJump || !isHasVerticalCollition()) {
			this.veloY += GRAVITY;
	
		}
		else if(hasVerticalCollition && veloY > 0) {
			this.veloY = 0;
		}
		veloX = baseVeloX+GameEntity.getCurrentFg().getVeloX();
		this.posY += this.veloY;
		
		this.posX += this.veloX;
		
	}

	public double getFireRate() {
		return fireRate;
	}

	public void setFireRate(double fireRate) {
		this.fireRate = fireRate;
	}
	
}
