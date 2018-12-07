package weapon;

import character.Person;
import javafx.scene.image.Image;

public class TankBullet extends HeroBullet {

	public TankBullet(Person p) {
		super(p);
		damage = 20;
		bullet = new Image("file:res/images/tankbullet.png");
	}

}
