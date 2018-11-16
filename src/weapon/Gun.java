package weapon;

import character.Person;
import javafx.scene.shape.Rectangle;

public class Gun {
	protected int damage;
	protected int amount;
	
	protected Rectangle gun;
	public Gun(int damage, int amount) {
		this.damage = damage;
		this.amount = amount;
		
	}
	
	
}
