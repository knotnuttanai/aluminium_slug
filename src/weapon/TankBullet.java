package weapon;

import character.Person;
import javafx.scene.image.Image;

public class TankBullet extends HeroBullet {

	public TankBullet(Person p) {
		super(p);
		width = 40;
		height = 20;
		damage = 5;
		bullet = new Image(ClassLoader.getSystemResource("tankbullet.png").toString());
		bulletShootUp = new Image(ClassLoader.getSystemResource("tankbulletup.png").toString());
		bulletShootDown = new Image(ClassLoader.getSystemResource("tankbulletdown.png").toString());
		if (p.isLookUp()) {
			posX -= 10;
			posY -= 20;
		}
		if (p.isLookDown()) {
			posX -= 10;
			posY -= 30;
		}
	}

}
