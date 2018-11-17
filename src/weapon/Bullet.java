package weapon;

import application.GameEntity;
import character.Hero;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bullet {
	private double posX;
	private double posY;
	private double veloX;
	private double veloY;
	public boolean isHit;
	
	public Bullet(Hero hero) {
		posX = hero.getPosX();
		posY = hero.getPosY();
		veloX = 10;
		veloY = 0;
		isHit = false;
		System.out.println("shoot");
		
				
		
	}
	public void update() {
		posX += veloX;
		posY += veloY;
	}
	public void render(GraphicsContext gc) {
		//gc.clearRect(posX-5, posY, 5, 5);
		gc.setFill(Color.RED);
		gc.setStroke(Color.BLACK);
		
		gc.fillRect(posX+30, posY+30, 8, 8);
		
	}
	public void addBullet() {
		GameEntity.createBullet(this);
	}
	public double getPosX() {
		return posX;
	}
	public void setPosX(double posX) {
		this.posX = posX;
	}
	public double getPosY() {
		return posY;
	}
	public void setPosY(double posY) {
		this.posY = posY;
	}
	public double getVeloX() {
		return veloX;
	}
	public void setVeloX(double veloX) {
		this.veloX = veloX;
	}
	public double getVeloY() {
		return veloY;
	}
	public void setVeloY(double veloY) {
		this.veloY = veloY;
	}
	public void setHit() {
		isHit = true;
	}
	public boolean isHit() {
		return isHit;
	}

}
