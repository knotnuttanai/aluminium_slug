package weapon;

import application.ImageManager;
import character.Person;

public class TurretBullet extends EnemyBullet {

	public TurretBullet(Person p) {
		super(p);
		posX -= 28;
		posY += 24;
		bullet = ImageManager.buildImage("turretbullet.png", 50, 32.5, false, false);
	}

}
