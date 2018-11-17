package character;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Person implements Movable{
	protected int health;
	protected double posX;
	protected double posY;
	protected boolean isAlive;
	
	
	public Person(double posX, double posY , int health) {
		this.posX = posX;
		this.posY = posY;
		this.health = health;
		
	}

	@Override
	public void Walk(int direction) {
		posX += direction*2;
	}

	@Override
	public void Jump() {
		while(posY > 400) posY -= 2;
		while(posY < 500) posY += 2;
		
	}

	public double getPosX() {
		return posX;
	}

	public double getPosY() {
		return posY;
	}
	public abstract void update();
	
		
	
	
	
	

}
