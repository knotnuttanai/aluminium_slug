package weapon;

import application.ImageManager;
import character.Person;

public class TankBullet extends HeroBullet {

	public TankBullet(Person p) {
		super(p);
		width = 40;
		height = 20;
		damage = 5;
		bullet = ImageManager.buildImage("tankbullet.png");
		bulletShootUp = ImageManager.buildImage("tankbulletup.png");
		bulletShootDown = ImageManager.buildImage("tankbulletdown.png");
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
