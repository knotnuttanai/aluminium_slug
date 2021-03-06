package character;

import application.ImageManager;
import application.SoundManager;
import exception.NoMoreArmoException;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import weapon.Bomb;
import weapon.EnemyBullet;
import weapon.MachineGunBullet;
import weapon.PistolBullet;
import weapon.TankBullet;

public class Hero extends Person implements Shootable {

	private int gun;
	// 0 is pistol
	// 1 is machine gun
	// 3 is tank
	public int maxGrenade;
	private int moveSpeed;
	private int useGunBullet;
	private Image marcoTop;
	private Image marcoBottom;
	private Image marcoMachine;
	private Image marcoLookUp;
	private Image marcoLookDown;
	private Image marcoMachUp;
	private Image marcoMachDown;
	private Image[] machShoot;
	private Image[] machShootUp;
	private Image[] machShootDown;
	private Image[] shootUp;
	private Image[] shootDown;
	private Image[] ThrowingBomb;
	private Image[] MachThrowBomb;
	private int shootUpFrame;
	private int shootDownFrame;
	private int machFrame;
	private int machUpFrame;
	private int machDownFrame;
	private int throwingBombFrame;
	private int machBombFrame;
	private int firerate;
	private boolean isInTheTank;
	private boolean requestToEnterTank;
	private boolean isThrowingBomb;

	public Hero(double posX, double posY, int health) {
		super(posX, posY, health);
		maxGrenade = 10;
		damage = 0;
		gun = 0;
		this.firerate = 200;
		moveSpeed = -5;
		useGunBullet = 0;
		veloX = 0;
		veloY = 0;
		height = 75;
		width = 45;
		isInTheTank = false;
		requestToEnterTank = false;
		isThrowingBomb = false;
		marcoTop = ImageManager.buildImage("top_marco1.png");
		marcoMachine = ImageManager.buildImage("machgun.png");
		marcoBottom = ImageManager.buildImage("bottom_marco.png");
		marcoLookUp = ImageManager.buildImage("marcoUp.png");
		marcoLookDown = ImageManager.buildImage("marcodown.png");
		marcoMachUp = ImageManager.buildImage("machup.png");
		marcoMachDown = ImageManager.buildImage("machdown.png");
		shoot = new Image[10];
		shootUp = new Image[10];
		shootDown = new Image[6];
		machShootUp = new Image[4];
		machShootDown = new Image[4];
		walk = new Image[10];
		machShoot = new Image[4];
		ThrowingBomb = new Image[5];
		MachThrowBomb = new Image[6];
		dead = new Image[19];
		walkFrame = 0;
		shootFrame = 0;
		shootUpFrame = 0;
		shootDownFrame = 0;
		machFrame = 0;
		machUpFrame = 0;
		machDownFrame = 0;
		throwingBombFrame = 0;
		machBombFrame = 0;
		deadFrame = 0;
		for (int i = 1; i <= 10; i++) {
			shoot[i - 1] = ImageManager.buildImage("shoot" + i + ".png");
		}
		for (int i = 1; i <= 10; i++) {
			shootUp[i - 1] = ImageManager.buildImage("shootup" + i + ".png");
		}
		for (int i = 1; i <= 6; i++) {
			shootDown[i - 1] = ImageManager.buildImage("shootdown" + i + ".png");
		}
		for (int i = 1; i <= 10; i++) {
			walk[i - 1] = ImageManager.buildImage("walk" + i + ".png");
		}
		for (int i = 1; i <= 4; i++) {
			machShoot[i - 1] = ImageManager.buildImage("mach" + i + ".png");
		}
		for (int i = 1; i <= 4; i++) {
			machShootUp[i - 1] = ImageManager.buildImage("machshootup" + i + ".png");
		}
		for (int i = 1; i <= 4; i++) {
			machShootDown[i - 1] = ImageManager.buildImage("machshootdown" + i + ".png");
		}
		for (int i = 1; i <= 5; i++) {
			ThrowingBomb[i - 1] = ImageManager.buildImage("throwbomb" + i + ".png");
		}
		for (int i = 1; i <= 6; i++) {
			MachThrowBomb[i - 1] = ImageManager.buildImage("machbomb" + i + ".png");
		}
		for (int i = 1; i <= 19; i++) {
			dead[i - 1] = ImageManager.buildImage("marcodead" + i + ".png");
		}
	}

	public int getFirerate() {
		return firerate;
	}

	public void setFirerate(int firerate) {
		this.firerate = firerate;
	}

	private int WalkAdjustPos() {
		if (walkFrame / 5 == 0) {
			return 2;
		}
		if (walkFrame / 5 == 1) {
			return 6;
		}
		if (walkFrame / 5 == 2) {
			return 7;
		}
		if (walkFrame / 5 == 3) {
			return 0;
		}
		if (walkFrame / 5 == 4) {
			return -4;
		}
		if (walkFrame / 5 == 5) {
			return -3;
		}
		if (walkFrame / 5 == 6) {
			return -1;
		}
		if (walkFrame / 5 == 7) {
			return 4;
		}
		if (walkFrame / 5 == 8) {
			return 5;
		}
		if (walkFrame / 5 == 9) {
			return 0;
		}
		return 0;
	}

	private int ShootAdjustPos() {
		if (shootUpFrame / 2 == 0 || shootUpFrame / 2 == 1) {
			return 82;
		}
		if (shootUpFrame / 2 == 2) {
			return 86;
		}
		if (shootUpFrame / 2 == 3) {
			return 32;
		}
		if (shootUpFrame / 2 == 4 || shootUpFrame / 2 == 5 || shootUpFrame / 2 == 7) {
			return 34;
		}
		if (shootUpFrame / 2 == 6) {
			return 36;
		}
		if (shootUpFrame / 2 == 8) {
			return 28;
		}
		if (shootUpFrame / 2 == 9) {
			return 8;
		}
		return 0;
	}

	private int MachAdjustPos() {
		if (machUpFrame / 2 == 0) {
			return 92;
		}
		if (machUpFrame / 2 == 1) {
			return 96;
		}
		if (machUpFrame / 2 == 2) {
			return 94;
		}
		if (machUpFrame / 2 == 3) {
			return 98;
		}
		return 0;
	}

	private int DeadAdjustPos() {
		if (deadFrame / 5 < 5) {
			return 48;
		}
		if (deadFrame / 5 < 9) {
			return 48 - 4 * (deadFrame / 5 - 4);
		}
		if (deadFrame / 5 == 9) {
			return 36;
		}
		if (deadFrame / 5 == 10) {
			return 32;
		}
		if (deadFrame / 5 < 15) {
			return 30 - 4 * (deadFrame / 5 - 11);
		}
		if (deadFrame / 5 == 15) {
			return 10;
		}
		if (deadFrame / 5 == 17) {
			return 2;
		}
		return 0;
	}

	private void finishShoot(String frame) {
		isShoot = false;
		if (frame.equals("shootUpFrame")) {
			shootUpFrame = 0;
		} else if (frame.equals("shootDownFrame")) {
			shootDownFrame = 0;
		} else if (frame.equals("shootFrame")) {
			shootFrame = 0;
		} else if (frame.equals("machUpFrame")) {
			machUpFrame = 0;
		} else if (frame.equals("machDownFrame")) {
			machDownFrame = 0;
		} else if (frame.equals("machFrame")) {
			machFrame = 0;
		}
	}

	@Override
	public void render(GraphicsContext gc) {
		if (!isAlive) {
			gc.drawImage(dead[(deadFrame / 5) % 19], posX, posY + 38 - DeadAdjustPos());
			deadFrame++;
			if (deadFrame == 95) {
				isAnimatedDead = true;
				deadFrame = 0;
			}
			return;
		}

		if (isInTheTank) {
			return;
		}

		int walkPos = 0;

		if (isWalk) {
			gc.drawImage(walk[(walkFrame / 5) % 10], posX, posY + 38);
			walkPos = WalkAdjustPos();
			walkFrame++;
			if (walkFrame == 50) {
				walkFrame = 0;
			}
		} else {
			gc.drawImage(marcoBottom, posX, posY + 38);
		}

		if (isShoot) {
			if (gun == 0) {
				if (isLookUp) {
					gc.drawImage(shootUp[(shootUpFrame / 2) % 10], posX + walkPos, posY - ShootAdjustPos());
					shootUpFrame++;
					if (shootUpFrame == 20) {
						finishShoot("shootUpFrame");
					}
				} else if (isLookDown) {
					gc.drawImage(shootDown[(shootDownFrame / 2) % 6], posX + walkPos, posY);
					shootDownFrame++;
					if (shootDownFrame == 12) {
						finishShoot("shootDownFrame");
					}
				} else {
					gc.drawImage(shoot[(shootFrame / 2) % 10], posX + walkPos, posY);
					shootFrame++;
					if (shootFrame == 20) {
						finishShoot("shootFrame");
					}
				}
			} else {
				if (isLookUp) {
					gc.drawImage(machShootUp[(machUpFrame / 2) % 4], posX + walkPos, posY - MachAdjustPos());
					machUpFrame++;
					if (machUpFrame == 8) {
						finishShoot("machUpFrame");
					}
				} else if (isLookDown) {
					gc.drawImage(machShootDown[(machDownFrame / 2) % 4], posX - 8 + walkPos, posY);
					machDownFrame++;
					if (machDownFrame == 8) {
						finishShoot("machDownFrame");
					}
				} else {
					gc.drawImage(machShoot[(machFrame / 2) % 4], posX + walkPos, posY);
					machFrame++;
					if (machFrame == 8) {
						finishShoot("machFrame");
					}
				}
			}
		} else if (isThrowingBomb) {
			if (gun == 0) {
				gc.drawImage(ThrowingBomb[throwingBombFrame % 5], posX + walkPos, posY);
				throwingBombFrame++;
				if (throwingBombFrame == 5) {
					throwingBombFrame = 0;
					isThrowingBomb = false;
				}
			} else {
				gc.drawImage(MachThrowBomb[(machBombFrame / 2) % 6], posX + walkPos, posY);
				machBombFrame++;
				if (machBombFrame == 12) {
					machBombFrame = 0;
					isThrowingBomb = false;
				}
			}
		} else {
			if (gun == 0) {
				if (isLookUp) {
					gc.drawImage(marcoLookUp, posX + walkPos, posY - 6);
				} else if (isLookDown) {
					gc.drawImage(marcoLookDown, posX + walkPos, posY);
				} else {
					gc.drawImage(marcoTop, posX + walkPos, posY);
				}
			} else {
				if (isLookUp) {
					gc.drawImage(marcoMachUp, posX + walkPos, posY - 20);
				} else if (isLookDown) {
					gc.drawImage(marcoMachDown, posX - 8 + walkPos, posY);
				} else {
					gc.drawImage(marcoMachine, posX + walkPos, posY);
				}
			}
		}
	}

	@Override
	public void walk(int direction) {
		posX += direction * 2;
	}

	@Override
	public void shoot() throws NoMoreArmoException {
		isShoot = true;
		if (isInTheTank) {
			gun = 0;
			firerate = 300;
			TankBullet bullet = new TankBullet(this);
			bullet.setDamage(damage + bullet.getDamage());
			bullet.addBullet();
			SoundManager.play("Tank", 0.5);
			return;
		}
		if (gun == 1) {
			if (useGunBullet == 0) {
				gun = 0;
				firerate = 200;
				PistolBullet bullet = new PistolBullet(this);
				bullet.setDamage(damage + bullet.getDamage());
				bullet.addBullet();
				SoundManager.play("pistolbullet", 1);
				throw new NoMoreArmoException();
			}
			firerate = 80;
			MachineGunBullet bullet = new MachineGunBullet(this);
			bullet.setDamage(damage + bullet.getDamage());
			bullet.addBullet();
			SoundManager.play("machbullet", 1);
			useGunBullet--;
		}
		if (gun == 0) {
			firerate = 200;
			PistolBullet bullet = new PistolBullet(this);
			bullet.setDamage(damage + bullet.getDamage());
			bullet.addBullet();
			SoundManager.play("pistolbullet", 1);
		}
	}

	public void throwBomb() throws NoMoreArmoException {
		if (isThrowingBomb) {
			if (maxGrenade == 0) {
				throw new NoMoreArmoException();
			}
			Bomb bomb = new Bomb(50, 50, this);
			bomb.addObject();
			maxGrenade--;
		}
	}

	public boolean isHitByBullet(EnemyBullet b) {
		BoundingBox b1 = new BoundingBox(posX, posY, width, height);
		BoundingBox b2 = new BoundingBox(b.getPosX(), b.getPosY(), b.getWidth(), b.getHeight());
		return b1.intersects(b2);
	}

	public int getGun() {
		return gun;
	}

	public void setGun(int gun) {
		this.gun = gun;
	}

	public boolean isInTheTank() {
		return isInTheTank;
	}

	public void setInTheTank(boolean isInTheTank) {
		this.isInTheTank = isInTheTank;
	}

	public boolean isRequestToEnterTank() {
		return requestToEnterTank;
	}

	public void setRequestToEnterTank(boolean requestToEnterTank) {
		this.requestToEnterTank = requestToEnterTank;
	}

	public void increaseHp(int heal) {
		int hp = health + heal;
		if (hp >= maxHealth) {
			hp = maxHealth;
		}
		health = hp;
	}

	public int getUseGunBullet() {
		return useGunBullet;
	}

	public void setUseGunBullet(int useGunBullet) {
		this.useGunBullet = useGunBullet;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public void setThrowingBomb(boolean throwingBomb) {
		isThrowingBomb = throwingBomb;
	}

	public boolean isShoot() {
		return isShoot;
	}

	public void setIsShoot(boolean isShoot) {
		this.isShoot = isShoot;
	}

	public int getMaxGrenade() {
		return maxGrenade;
	}

	public void setMaxGrenade(int maxGrenade) {
		this.maxGrenade = maxGrenade;
	}

}
