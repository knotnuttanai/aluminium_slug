package weapon;

import application.EntityDataBase;
import application.GameScene;
import character.Hero;
import character.Person;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MachineGun extends Gun {
	private final static int damage = 10;
	private double posX;
	private double posY;
	private Hero A;
	public MachineGun(Hero A) {
		super(damage, 200);
		this.A = A;
		
		
		posX = A.getPosX();
		posY = A.getPosY();
		gun = new Rectangle(posX+6,posY+5,20, 5);
		gun.setFill(Color.RED);
		EntityDataBase.addGun(this);
		GameScene.addView(gun);
	}
	
	public void shoot() {
		this.amount--;
		
	}
	
	public Rectangle getGun() {
		return gun;
	}
	public void render() {
		gun.setTranslateX(posX);
		gun.setTranslateY(posY);
	}
	public void update() {
		posX = A.getPosX();
		posY = A.getPosY();
	}
	
}
