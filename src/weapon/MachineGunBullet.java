package weapon;

import character.Person;

public class MachineGunBullet extends HeroBullet {
	public int maxBullets;

	public MachineGunBullet(Person p) {
		super(p);
		damage = 10;
		maxBullets = 256;

		if (bulletDown) {
			posX -= 10;
			posY += 10;
		} else if (!bulletUp) {
			posX += 4;
			posY += 18;
		}

	}

}
