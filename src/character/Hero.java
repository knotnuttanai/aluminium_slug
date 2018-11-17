package character;

import application.GameEntity;
import environment.Foreground;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import weapon.Bullet;

public class Hero extends Person {
	Image marco;

	public Hero(double posX, double posY, int health) {
		super(posX, posY, health);
		// TODO Auto-generated constructor stub
		marco = new Image("file:res/images/marco2.png");
		
		GameEntity.createHero(this);
	}

	
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(marco, posX, posY);
		//gc.setFill(Color.BISQUE);
		//gc.fillRect(posX, posY, 30, 60);
		
	}
	
	public void shoot() {
		Bullet bullet = new Bullet(this);
		bullet.addBullet();
	}

	

}
