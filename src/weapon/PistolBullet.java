package weapon;

import application.Sound;
import character.Person;
import javafx.scene.image.Image;

public class PistolBullet extends HeroBullet {
	public static Sound bulletSound = new Sound("res/sounds/pistolbullet.wav");
	public PistolBullet(Person p) {
		super(p);
		this.damage = 10;
		width = 20;
		height = 20;
		bullet = new Image("file:res/images/Glenos-G_160_bullet.png",20, 50, false, false);
		
		// TODO Auto-generated constructor stub
	}

}
