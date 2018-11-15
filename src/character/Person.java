package character;

public class Person {
	protected int direction;
	protected int health;
	protected double PosX, PosY;

	public Person(int health, int direction) {
		this.health = health;
		this.direction = direction;
		
	}
	
	public void takeDamage(int damage) {
		health -= damage;
		if(health <= 0) dead();
	}
	
	public void dead() {
		//disappear from screen
		
	}

	public double getPosX() {
		return PosX;
	}
	
	public double getPosY() {
		return PosY;
	}
	
}
