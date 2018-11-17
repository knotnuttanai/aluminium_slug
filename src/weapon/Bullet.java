package weapon;

import application.GameEntity;
import character.Hero;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet {
	private double posX;
	private double posY;
	private double veloX;
	private double veloY;
	
	public Bullet(Hero hero) {
		posX = hero.getPosX();
		posY = hero.getPosY();
		veloX = 5;
		veloY = 0;
		System.out.println("shoot");
		
				
		
	}
	public void update() {
		posX += veloX;
		posY += veloY;
	}
	public void render(GraphicsContext gc) {
		//gc.clearRect(posX-5, posY, 5, 5);
		gc.setFill(Color.BLACK);
		gc.fillRect(posX, posY, 5, 5);
		
	}
	public void addBullet() {
		GameEntity.createBullet(this);
	}

}