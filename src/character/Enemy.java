package character;

import application.GameEntity;
import environment.Foreground;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Enemy extends Person {
	Image marco;
	public Enemy(double posX, double posY, int health) {
		super(posX, posY, health);
		// TODO Auto-generated constructor stub
		
		height = 75;
		width = 56;
		marco = new Image("file:res/images/marco2.png");
		veloX = -1;
		walkDirection = -1;
	}

	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(marco, posX, posY);
		//gc.fillRect(posX, posY, width, height);
	}
	public void update() {
		// TODO Auto-generated method stub
		
		if(!this.hasVerticalCollition ) {
			veloY += GRAVITY;
			System.out.println("555");
			
		}else {
			veloY = 0;
		}
		
		posY += veloY;
		
		posX += veloX;
		
		
	}
	public void addEnemy() {
		GameEntity.createEnemy(this);
	}
	public void walk(double fgVeloX) {
		veloX = -1 + fgVeloX ;
	}
	public void stop() {
		veloX = -1;
	}
}
