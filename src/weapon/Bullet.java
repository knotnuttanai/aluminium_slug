package weapon;

import application.GameEntity;
import application.ImageManager;
import character.Person;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet extends GameObject {

	protected double posX;
	protected double posY;
	protected double veloX, baseVeloX;
	protected double veloY, baseVeloY;
	protected boolean bulletUp, bulletDown;
	public boolean isHit;
	protected double width;
	protected double height;
	protected int damage;
	protected Image bullet;
	protected Image bulletShootUp;
	protected Image bulletShootDown;

	public Bullet(Person p) {
		super(p.getPosX() + 40, p.getPosY(), 50, 10);
		posX = p.getPosX() + 54;
		posY = p.getPosY() - 2;
		bulletUp = p.isLookUp();
		bulletDown = p.isLookDown();
		if (bulletUp) {
			veloX = 0;
			veloY = -10;
			posX = p.getPosX() + 6;
			posY = p.getPosY() - 80;
		} else if (bulletDown) {
			veloX = 0;
			veloY = 10;
			posX = p.getPosX() + 8;
			posY = p.getPosY() + 80;
		} else {
			veloX = 10;
			veloY = 0;
		}
		baseVeloX = veloX;
		baseVeloY = veloY;
		isHit = false;
		damage = 10;
		bullet = ImageManager.buildImage("Glenos-G_160_bullet.png", 50, 32.5, false, false);
		bulletShootUp = ImageManager.buildImage("bulletup.png", 32.5, 50, false, false);
		bulletShootDown = ImageManager.buildImage("bulletdown.png", 32.5, 50, false, false);
	}

	@Override
	public void update() {
		if (this.posY >= 1000 || this.posX + this.width < -10) {
			setHit();
		}
		calculateRelaSpeed();
		posX += veloX;
		posY += veloY;
	}

	public void calculateRelaSpeed() {
		if (!bulletUp && !bulletDown) {
			veloX = baseVeloX + GameEntity.getCurrentFg().getVeloX();
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		if (bulletUp) {
			gc.drawImage(bulletShootUp, posX, posY);
		} else if (bulletDown) {
			gc.drawImage(bulletShootDown, posX, posY);
		} else {
			gc.drawImage(bullet, posX, posY);
		}

	}

	public void addBullet() {
		GameEntity.createBullet(this);
	}

	@Override
	public double getPosX() {
		return posX;
	}

	@Override
	public void setPosX(double posX) {
		this.posX = posX;
	}

	@Override
	public double getPosY() {
		return posY;
	}

	@Override
	public void setPosY(double posY) {
		this.posY = posY;
	}

	@Override
	public double getVeloX() {
		return veloX;
	}

	@Override
	public void setVeloX(double veloX) {
		this.veloX = veloX;
	}

	@Override
	public double getVeloY() {
		return veloY;
	}

	@Override
	public void setVeloY(double veloY) {
		this.veloY = veloY;
	}

	public void setHit() {
		isHit = true;
	}

	@Override
	public boolean isHit() {
		return isHit;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public void setWidth(double width) {
		this.width = width;
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public void setHeight(double height) {
		this.height = height;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

}
