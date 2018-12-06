package weapon;

import character.Person;
import javafx.scene.image.Image;

public class EnemyBullet extends Bullet {

	public EnemyBullet(Person p) {
		super(p);
		width = 15;
		height = 10;
		veloX = -4;
		damage = 20;
		baseVeloX = veloX;
		baseVeloY = veloY;
		posX -= 100;
		posY += 8;
		bullet = new Image("file:res/images/enemybullet.png",15, 32.5, false, false);

	}
	

}
