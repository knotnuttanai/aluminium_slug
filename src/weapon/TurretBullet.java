package weapon;

import character.Person;
import javafx.scene.image.Image;

public class TurretBullet extends EnemyBullet {

	public TurretBullet(Person p) {
		super(p);
		posX -= 28;
		posY += 24;
		bullet = new Image(ClassLoader.getSystemResource("turretbullet.png").toString(), 50, 32.5, false, false);
	}

}
