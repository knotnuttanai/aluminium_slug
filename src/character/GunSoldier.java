package character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import weapon.EnemyBullet;

public class GunSoldier extends Enemy implements Shootable {
	private Thread thread;
	private int count;
	private Image[] grabGun;
	private int grabGunFrame;
	private boolean normalCondition;
	private boolean grabGunCondition;
	private boolean shootCondition;
	final private int walkCount = (int) (Math.random() * 2);

	public GunSoldier(double posX, double posY, int health) {
		super(posX, posY, health);
		count = 0;
		walk = new Image[12];
		for (int i = 1; i <= 12; i++) {
			walk[i - 1] = new Image("file:res/images/normalsoldier" + i + ".png");
		}
		walkFrame = 0;
		normalCondition = true;

		grabGun = new Image[8];
		for (int i = 1; i <= 8; i++) {
			grabGun[i - 1] = new Image("file:res/images/grabgun" + i + ".png");
		}
		grabGunFrame = 0;
		grabGunCondition = false;

		shoot = new Image[5];
		for (int i = 0; i <= 4; i++) {
			shoot[i] = new Image("file:res/images/soldiershoot" + i + ".png");
		}
		shootFrame = 0;
		shootCondition = false;

		dead = new Image[6];
		for (int i = 1; i <= 6; i++) {
			dead[i - 1] = new Image("file:res/images/gundead" + i + ".png");
		}
		deadFrame = 0;

	}

	@Override
	public void shoot() {
		EnemyBullet bullet = new EnemyBullet(this);
		bullet.setDamage(damage + bullet.getDamage());
		bullet.addBullet();
	}

	public void update(long now) {
		super.update();
		if (normalCondition) {
			if (walkFrame >= walkCount * 36) {
				normalCondition = false;
				grabGunCondition = true;
			}
		}
		if (grabGunCondition) {
			veloX = 0;
			walkDirection = 0;
			baseVeloX = veloX;
			if (grabGunFrame == 24) {
				grabGunCondition = false;
				shootCondition = true;
			}
		}
		if (shootCondition) {
			if ((int) (Math.random() * 99) + 1 < 2) {
				count = 0;
			}
		}
	}

	@Override
	public void render(GraphicsContext gc) {

		if (!isAlive) {
			gc.drawImage(dead[deadFrame / 5], posX, posY + deadFrame * 1.1);
			deadFrame++;
			if (deadFrame == 30)
				isAnimatedDead = true;
		} else {

			if (normalCondition) {
				gc.drawImage(walk[(walkFrame / 3) % 12], posX, posY);
				walkFrame++;
			}
			if (grabGunCondition) {
				gc.drawImage(grabGun[grabGunFrame / 3], posX, posY);
				grabGunFrame++;
			}
			if (shootCondition) {
				if (count <= 1) {
					gc.drawImage(shoot[(shootFrame / 3) % 5], posX - adjustShootPosX(), posY + adjustShootPosY());
					shootFrame++;
				} else {
					gc.drawImage(shoot[0], posX, posY);
				}
				thread = new Thread(() -> {
					try {

						if (shootFrame >= 15) {
							shootFrame = 0;
							if (count <= 1) {
								shoot();
								count++;
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {
					}
				});
				thread.start();
				if (!isAlive) {
					try {
						thread.interrupt();
						thread.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private int adjustShootPosX() {
		if (shootFrame / 3 == 1) {
			return 44;
		}
		if (shootFrame / 3 == 2 || shootFrame / 3 == 3) {
			return 40;
		}
		if (shootFrame / 3 == 4) {
			return 42;
		}
		return 0;
	}

	private int adjustShootPosY() {
		if (shootFrame / 3 == 2) {
			return 2;
		}
		if (shootFrame / 3 == 3 || shootFrame / 3 == 4) {
			return 6;
		}
		return 0;
	}

}
