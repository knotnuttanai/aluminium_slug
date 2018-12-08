package weapon;

import application.ImageManager;
import application.SoundManager;
import character.Hero;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Tank extends GameObject {
	public boolean isUsed;
	private int health;
	private int maxHealth;
	private int gunOfHero;
	private int bulletOfHero;
	private int healthOfHero;
	private Hero hero;
	private boolean toggle;
	private boolean readyToDead;
	private boolean soundIsPlay;
	private Image tankImage;
	private Image tankGun;
	private Image tankUp;
	private Image tankDown;
	private Image[] movingTank;
	private Image[] shootGun;
	private Image[] shootUp;
	private Image[] shootDown;
	private Image[] dead;
	private int shootFrame;
	private int tankFrame;
	private int upFrame;
	private int downFrame;
	private int deadFrame;

	public Tank(double posX, double posY, double width, double height, Hero hero) {
		super(posX, posY, width, height);
		isUsed = false;
		this.hero = hero;
		health = 0;
		maxHealth = 500;
		toggle = false;
		gunOfHero = 0;
		healthOfHero = 0;
		bulletOfHero = 0;
		tankImage = ImageManager.buildImage("tank1.png");
		movingTank = new Image[14];
		for (int i = 1; i <= 14; i++) {
			movingTank[i - 1] = ImageManager.buildImage("tank" + i + ".png");
		}
		tankFrame = 0;
		tankGun = ImageManager.buildImage("tankgun1.png");
		shootGun = new Image[5];
		for (int i = 1; i <= 5; i++) {
			shootGun[i - 1] = ImageManager.buildImage("tankgun" + i + ".png");
		}
		shootFrame = 0;
		tankUp = ImageManager.buildImage("tankup0.png");
		shootUp = new Image[5];
		for (int i = 0; i <= 4; i++) {
			shootUp[i] = ImageManager.buildImage("tankup" + i + ".png");
		}
		upFrame = 0;
		tankDown = ImageManager.buildImage("tankdown0.png");
		shootDown = new Image[5];
		for (int i = 0; i <= 4; i++) {
			shootDown[i] = ImageManager.buildImage("tankdown" + i + ".png");
		}
		downFrame = 0;
		dead = new Image[21];
		for (int i = 1; i <= 21; i++) {
			dead[i - 1] = ImageManager.buildImage("Layer " + i + ".png");
		}
		deadFrame = 0;
		readyToDead = false;
	}

	public void update() {
		if (this.posY >= 800 || this.posX + this.width < -10) {
			isHit = true;
		}
		if (isUsed && !toggle) {
			toggle = true;
			healthOfHero = hero.getHealth();
			gunOfHero = hero.getGun();
			if (gunOfHero != 0) {
				bulletOfHero = hero.getUseGunBullet();
			} else {
				bulletOfHero = 0;
			}
			
			hero.setHealth(maxHealth + 20000000);
		} else if (isUsed) {
			if (hero.getHealth() <= 20000000) {
				hero.setGun(gunOfHero);
				hero.setHealth(healthOfHero);
				hero.setUseGunBullet(bulletOfHero);
				hero.setInTheTank(false);
				hero.setRequestToEnterTank(false);
				readyToDead = true;
			} else {
				posX = hero.getPosX();
				posY = hero.getPosY();

			}
		} else {
			super.update();
		}
		box = new BoundingBox(posX, posY, width, height);
	}

	private int adjustPosX() {
		if (hero.isLookUp()) {
			if (upFrame / 3 == 1) {
				return 8;
			}
			if (upFrame / 3 == 2) {
				return 4;
			}
			return 0;
		} else {
			if (downFrame / 3 == 1) {
				return 6;
			}
			if (downFrame / 3 == 2) {
				return 4;
			}
		}
		return 0;
	}

	private int adjustPosY() {
		if (hero.isLookUp()) {
			if (upFrame / 3 == 1) {
				return 74;
			}
			if (upFrame / 3 == 2) {
				return 88;
			}
			if (upFrame / 3 == 3) {
				return 100;
			}
			if (upFrame / 3 == 4) {
				return 102;
			}
		} else {
			if (shootFrame / 3 == 1) {
				return 8;
			}
			if (shootFrame / 3 == 2) {
				return 4;
			}
		}
		return 0;
	}

	public void render(GraphicsContext gc) {
		if (readyToDead) {
			gc.drawImage(dead[deadFrame], posX, posY - 100);
			deadFrame++;
			if (deadFrame == 21) {
				isHit = true;
			}
			if (!soundIsPlay) {
				SoundManager.play("grenade", 1);
				soundIsPlay = true;
			}
		}
		if (!isUsed) {
			gc.drawImage(tankImage, posX, posY - 8);
		} else {
			gc.drawImage(movingTank[(tankFrame / 3) % 14], posX - 34, posY - 34);
			tankFrame++;
			if (tankFrame >= 42) {
				tankFrame = 0;
			}
			if (hero.isShoot()) {
				if (hero.isLookUp()) {
					gc.drawImage(shootUp[(upFrame / 3) % 5], posX - adjustPosX(), posY - adjustPosY());
					upFrame++;
					if (upFrame >= 15) {
						upFrame = 0;
						hero.setIsShoot(false);
					}
				} else if (hero.isLookDown()) {
					gc.drawImage(shootDown[(downFrame / 3) % 5], posX - adjustPosX(), posY);
					downFrame++;
					if (downFrame >= 15) {
						downFrame = 0;
						hero.setIsShoot(false);
					}
				} else {
					gc.drawImage(shootGun[(shootFrame / 3) % 5], posX, posY - adjustPosY());
					shootFrame++;
					if (shootFrame >= 15) {
						shootFrame = 0;
						hero.setIsShoot(false);
					}
				}
			} else {
				if (hero.isLookUp()) {
					gc.drawImage(tankUp, posX, posY - 26);
				} else if (hero.isLookDown()) {
					gc.drawImage(tankDown, posX, posY);
				} else {
					gc.drawImage(tankGun, posX, posY);
				}
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
