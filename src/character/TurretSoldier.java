package character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import weapon.EnemyBullet;

public class TurretSoldier extends Enemy implements Shootable {
	
	private Image Turret;
	private Image[] TurretShoot;

	public TurretSoldier(double posX, double posY, int health) {
		super(posX, posY - 10, health);
		veloX = 0;
		baseVeloX = 0;
		walkDirection = 0;
		Turret = new Image("file:res/images/turret.png");
		TurretShoot = new Image[3];
		for(int i = 1; i <=3; i++) {
			TurretShoot[i-1] = new Image("file:res/images/turretfiring" + i + ".png");
		}
	}

	@Override
	public void shoot() {
		EnemyBullet bullet = new EnemyBullet(this);
		bullet.setDamage(dmg+bullet.getDamage());
		bullet.addBullet();

	}
	
	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(Turret, posX, posY);
		
	}

}
