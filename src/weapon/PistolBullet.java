package weapon;

import application.ImageManager;
import character.Person;

public class PistolBullet extends HeroBullet {

	public PistolBullet(Person p) {
		super(p);
		this.damage = 10;
		width = 20;
		height = 20;
		bullet = ImageManager.buildImage("Glenos-G_160_bullet.png", 20, 50, false, false);
	}

}
