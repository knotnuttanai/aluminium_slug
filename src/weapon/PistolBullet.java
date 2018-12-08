package weapon;

import character.Person;
import javafx.scene.image.Image;

public class PistolBullet extends HeroBullet {

	public PistolBullet(Person p) {
		super(p);
		this.damage = 10;
		width = 20;
		height = 20;
		bullet = new Image(ClassLoader.getSystemResource("Glenos-G_160_bullet.png").toString(), 20, 50, false, false);
	}

}
