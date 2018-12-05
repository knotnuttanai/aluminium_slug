package weapon;

import character.Person;

public class MachineGunBullet extends HeroBullet {
	public int maxBullets;
	public MachineGunBullet(Person p) {
		super(p);
		damage = 10;
		maxBullets = 256;
		// TODO Auto-generated constructor stub
	}

}
