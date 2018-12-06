package weapon;

import character.Hero;
import character.Shootable;

public class Tank extends GameObject implements Shootable {
	public boolean isReady, isAlive;
	public Tank(double posX, double posY, double width, double height, Hero hero) {
		super(posX, posY, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}

}
