package character;

import application.GameEntity;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import weapon.EnemyBullet;
import weapon.HeroBullet;

public class Enemy extends Person {

	protected double baseVeloX;
	private double fireRate;

	public Enemy(double posX, double posY, int health) {
		super(posX, posY, health);
		height = 75;
		width = 56;
		veloX = -2;
		walkDirection = -1;
		baseVeloX = veloX;
		fireRate = 0.005;
		damage = 0;
	}

	@Override
	public void render(GraphicsContext gc) {
	}

	public void addEnemy() {
		GameEntity.createEnemy(this);
	}

	public void walk(double fgVeloX) {
		veloX = -2 + fgVeloX;
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
		bullet.setDamage(damage + bullet.getDamage());
		bullet.addBullet();
	}

	public void update() {
		if (!isAlive) {
			baseVeloX = 0;
		}
		veloX = baseVeloX + GameEntity.getCurrentFg().getVeloX();
		if (this.posY >= 800 || this.posX + this.width < -10||this.posX > 3000) {
			this.isAnimatedDead = true;
		}
		super.update();

	}

	public double getFireRate() {
		return fireRate;
	}

	public void setFireRate(double fireRate) {
		this.fireRate = fireRate;
	}

}
