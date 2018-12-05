package weapon;

import application.GameEntity;
import application.GameScene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameObject {
	protected double width;
	protected double height;
	protected double posX, veloX, baseVeloX;
	protected double posY, veloY, baseVeloY;
	protected boolean hasVerticalCollition, hasHorizontalCollision, isHit;
	private static final double GRAVITY = 1;
	public GameObject(double posX, double posY,double width, double height ) {
		super();
		this.width = width;
		this.height = height;
		this.posX = posX;
		this.posY = posY;
		veloX = 0;
		veloY = 0;
		baseVeloX = veloX;
		baseVeloY = veloY;
		hasVerticalCollition = false;
		hasHorizontalCollision = false;
		isHit = false;
		
	}
	public void update() {
		if(!hasVerticalCollition) {
			this.veloY += GRAVITY;
	
		}
		else if(hasVerticalCollition && veloY > 0) {
			this.veloY = 0;
		}
		veloX = baseVeloX + GameEntity.getCurrentFg().getVeloX();
		posX += veloX;
		posY += veloY;
	}
	public void render(GraphicsContext gc) {
		gc.setFill(Color.AQUA);
		gc.fillRect(posX, posY, width, height);
		
		
		
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
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
	public void addGun() {
		GameEntity.createGameObject(this);
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
	public boolean isHasVerticalCollition() {
		return hasVerticalCollition;
	}
	public void setHasVerticalCollition(boolean hasVerticalCollition) {
		this.hasVerticalCollition = hasVerticalCollition;
	}
	public boolean isHasHorizontalCollision() {
		return hasHorizontalCollision;
	}
	public void setHasHorizontalCollision(boolean hasHorizontalCollision) {
		this.hasHorizontalCollision = hasHorizontalCollision;
	}
	public boolean isHit() {
		return isHit;
	}
	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}
	
}
