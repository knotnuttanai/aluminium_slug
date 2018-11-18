package character;

import application.GameEntity;
import environment.Foreground;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import weapon.Bullet;

public class Hero extends Person implements Shootable{
	Image marco;
	Image[] shoot;
	int i = 0;

	public Hero(double posX, double posY, int health) {
		super(posX, posY, health);
		// TODO Auto-generated constructor stub
		height = 78;
		width = 68;
				
		marco = new Image("file:res/images/marco2.png", width, height, false, false);
		shoot = new Image[10];
		
		for(int i = 1; i <= 10; i++) {
			shoot[i-1] = new Image("file:res/images/shoot" + i + ".png");
		}
		
		GameEntity.createHero(this);
	}

	
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(isShoot) {
			gc.drawImage(shoot[i], posX, posY);
			i++;
			if(i==10) {
				i = 0;
				isShoot = false;
			}
		}
		else gc.drawImage(marco, posX, posY);
		//gc.setFill(Color.BISQUE);
		//gc.fillRect(posX, posY, 30, 60);
		
	}
	
	public void shoot() {
		isShoot = true;
		Bullet bullet = new Bullet(this);
		bullet.addBullet();
	}

	

}
