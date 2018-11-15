package character;

public class Character {
	protected int direction;
	protected int health;

	public Character(int health, int direction) {
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

}
