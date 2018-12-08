package weapon;

import application.ImageManager;
import character.Person;

public class EnemyBullet extends Bullet {

	public EnemyBullet(Person p) {
		super(p);
		width = 15;
		height = 10;
		veloX = -4;
		damage = 10;
		baseVeloX = veloX;
		baseVeloY = veloY;
		posX -= 100;
		posY += 8;
		bullet = ImageManager.buildImage("enemybullet.png", 15, 32.5, false, false);
	}

}
