package weapon;

import character.Person;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MachineGun extends Gun {
	private final static int damage = 10;
	private static Rectangle gun;
	public MachineGun(Person A) {
		super(damage, 200);
		gun = new Rectangle(A.getPosX() + 20, A.getPosY() + 10, 20, 5);
		gun.setFill(Color.BLACK);
	}
	
	public void shoot() {
		this.amount--;
		
	}
	
	public Rectangle getGun() {
		return gun;
	}
	
}
