package character;

import application.SoundManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import weapon.TurretBullet;

public class TurretSoldier extends Enemy implements Shootable {

	private Image turret;
	private boolean shootCondition;
	private boolean soundIsPlay;
	private boolean needRandomShoot;

	public TurretSoldier(double posX, double posY, int health) {
		super(posX, posY - 10, health);
		veloX = 0;
		baseVeloX = 0;
		dmg = 0;
		walkDirection = 0;
		turret = new Image("file:res/images/turret.png");
		shoot = new Image[3];
		for (int i = 1; i <= 3; i++) {
			shoot[i - 1] = new Image("file:res/images/turretfiring" + i + ".png");
		}
		shootFrame = 0;
		dead = new Image[21];
		for (int i = 1; i <= 21; i++) {
			dead[i - 1] = new Image("file:res/images/Layer " + i + ".png");
		}
		deadFrame = 0;

		shootCondition = false;
		needRandomShoot = true;
	}

	@Override
	public void shoot() {
		TurretBullet bullet = new TurretBullet(this);
		bullet.setDamage(dmg + bullet.getDamage());
		bullet.addBullet();

	}

	private boolean randomShoot() {
		if ((int) (Math.random() * 20) + 1 == 5) {
			return true;
		}
		return false;
	}

	private int adjustShootPosX() {
		if (shootFrame / 3 == 0) {
			return 100;
		}
		if (shootFrame / 3 == 1) {
			return 82;
		}
		if (shootFrame / 3 == 2) {
			return 94;
		}
		return 0;
	}

	@Override
	public void render(GraphicsContext gc) {

		if (health == 0) {
			gc.drawImage(dead[deadFrame], posX, posY - 100);
			deadFrame++;
			if (deadFrame == 21) {
				isAnimatedDead = true;
			}
			if (!soundIsPlay) {
				SoundManager.play("grenade", 1);
				soundIsPlay = true;
			}
			if (deadFrame == 30) {
				isAnimatedDead = true;
			}
		}

		else {
			if (needRandomShoot) {
				shootCondition = randomShoot();
			}
			if (shootCondition) {
				needRandomShoot = false;
				gc.drawImage(shoot[(shootFrame / 3) % 3], posX - adjustShootPosX(), posY);
				shootFrame++;
				Thread thread = new Thread(() -> {
					if (shootFrame == 3)
						shoot();
					if (shootFrame >= 9) {
						shootFrame = 0;
						needRandomShoot = true;
					}
				});
				thread.start();
			} else
				gc.drawImage(turret, posX - 50, posY);
		}
	}
}
