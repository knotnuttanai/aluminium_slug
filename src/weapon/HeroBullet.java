package weapon;

import application.GameEntity;
import character.Person;

public class HeroBullet extends Bullet {

	public HeroBullet(Person p) {
		super(p);
	}

	@Override
	public void calculateRelaSpeed() {
		if (!bulletUp && !bulletDown) {
			veloX = baseVeloX + GameEntity.getCurrentFg().getVeloX() + 5;
		}
	}

}
