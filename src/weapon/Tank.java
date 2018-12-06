package weapon;

import application.GameEntity;
import character.Hero;
import character.Shootable;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tank extends GameObject {
	public boolean isAlive, isUsed;
	private int health;
	Hero hero;
	public Tank(double posX, double posY, double width, double height, Hero hero) {
		super(posX, posY, width, height);
		// TODO Auto-generated constructor stub
		isAlive = true;
		isUsed = true;
		this.hero = hero;
		health = 200;
	}

	public void update() {
		if(this.posY >=800||this.posX +this.width < -10) {
			this.setUsed(true);
		} 
		
		if(!hasVerticalCollition) {
			this.veloY += GRAVITY;
	
		}
		else if(hasVerticalCollition && veloY > 0) {
			this.veloY = 0;
		}
		veloX = baseVeloX + GameEntity.getCurrentFg().getVeloX();
		posX += veloX;
		posY += veloY;
		b = new BoundingBox(posX, posY, width, height);
	}
	public void render(GraphicsContext gc) {
		gc.setFill(Color.AQUA);
		gc.fillRect(posX, posY, width, height);
		
		
		
	}

	

	public boolean isUsed() {
		return isUsed;
	}
	
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	

	

	
	
}
