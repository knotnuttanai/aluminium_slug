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
	Image marco;
	private double baseVeloX;
	public Enemy(double posX, double posY, int health) {
		super(posX, posY, health);
		// TODO Auto-generated constructor stub
		
		height = 75;
		width = 56;
		marco = new Image("file:res/images/marco2.png");
		veloX = -2;
		walkDirection = -1;
		baseVeloX = veloX;
		
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(marco, posX, posY);
		//gc.fillRect(posX, posY, width, height);
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
		veloX = baseVeloX+GameScene.getFgSpeed();
		this.posY += this.veloY;
		
		this.posX += this.veloX;
		
	}
}
