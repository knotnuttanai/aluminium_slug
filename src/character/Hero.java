package character;

public class Hero extends Character {
	private final static int health = 200;
	private final static int direction = 1;
	
	public Hero() {
		super(health, direction);
	}
	
	@Override
	public void dead() {
		//disappear from screen
		reBirth();
	}
	
	public void reBirth() {
		super.health = health;
		//appear on screen;
	}

}
