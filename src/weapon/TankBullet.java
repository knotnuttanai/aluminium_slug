package weapon;

import character.Person;
import javafx.scene.image.Image;

public class TankBullet extends HeroBullet {

	public TankBullet(Person p) {
		super(p);
		width = 40;
		height = 20;
		damage = 5;
		bullet = new Image("file:res/images/tankbullet.png");
		bulletShootUp = new Image("file:res/images/tankbulletup.png");
		bulletShootDown = new Image("file:res/images/tankbulletdown.png");
		if(p.isLookUp()) {
			posX -= 10;
			posY -= 20;
		}
		if(p.isLookDown()) {
			posX -= 10;
			posY -= 30;
		}
	}

}
