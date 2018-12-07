package weapon;

import character.Person;
import javafx.scene.image.Image;

public class TankBullet extends HeroBullet {

	public TankBullet(Person p) {
		super(p);
		width = 40;
		height = 20;
		damage = 2;
		bullet = new Image("file:res/images/tankbullet.png");
	}

}
