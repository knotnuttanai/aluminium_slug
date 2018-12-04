package weapon;

import character.Person;
import javafx.scene.image.Image;

public class EnemyBullet extends Bullet {

	public EnemyBullet(Person p) {
		super(p);
		width = 15;
		height = 10;
		veloX = -4;
		baseVeloX = veloX;
		baseVeloY = veloY;
		bullet = new Image("file:res/images/Glenos-G_160_bullet.png",15, 32.5, false, false);
		// TODO Auto-generated constructor stub
	}
	

}
