package character;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Soldier extends Person {
	private final static int health = 20;
	private final static int direction = -1;
	private static Circle Bot;

	public Soldier() {
		super(health, direction);
		Bot = new Circle(800,500,15);
		Bot.setFill(Color.DARKGREEN);
	}
	
	public Circle getSoldier() {
		return Bot;
	}
}
