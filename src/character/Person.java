package character;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Person implements Movable{
	protected int health;
	protected double posX;
	protected double posY;
	protected boolean isAlive;
	protected Rectangle Man;
	
	public Person(int posX, int posY , int health) {
		this.posX = posX;
		this.posY = posY;
		this.health = health;
		Man = new Rectangle(posX, posY, 10, 30);
		Man.setFill(Color.BLACK);
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

	public Rectangle getMan() {
		return Man;
	}
	
	

}
