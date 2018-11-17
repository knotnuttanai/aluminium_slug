package character;

import application.GameEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import weapon.Bullet;

public class Hero extends Person {

	public Hero(double posX, double posY, int health) {
		super(posX, posY, health);
		// TODO Auto-generated constructor stub
		GameEntity.createHero(this);
	}

	
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
		gc.setFill(Color.RED);
		gc.fillRect(posX, posY, 10, 10);
		
	}
	public void shoot() {
		Bullet bullet = new Bullet(this);
		bullet.addBullet();
	}

	

}
