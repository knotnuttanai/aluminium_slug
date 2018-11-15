package character;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Hero extends Person {
	private final static int health = 200;
	private final static int direction = 1;
	private static Rectangle Marco;
	
	
	public Hero() {
		super(health, direction);
		Marco = new Rectangle(200,500,30,30);
		this.PosX = 200;
		this.PosY = 500;
		Marco.setFill(Color.BLUE);
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
	
	public Rectangle getMarco() {
		return Marco;
	}

}
