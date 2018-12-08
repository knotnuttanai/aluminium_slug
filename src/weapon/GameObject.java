package weapon;

import application.GameEntity;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;

public class GameObject {
	protected double width;
	protected double height;
	protected double posX;
	protected double posY;
	protected double veloX;
	protected double veloY;
	protected double baseVeloX;
	protected double baseVeloY;
	protected boolean hasVerticalCollition;
	protected boolean hasHorizontalCollision;
	protected boolean isHit;
	protected static final double GRAVITY = 1;
	protected BoundingBox box;

	public GameObject(double posX, double posY, double width, double height) {
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
		box = new BoundingBox(posX, posY, width, height);
	}

	public void update() {
		if (!hasVerticalCollition) {
			this.veloY += GRAVITY;

		} else if (hasVerticalCollition && veloY > 0) {
			this.veloY = 0;
		}
		veloX = baseVeloX + GameEntity.getCurrentFg().getVeloX();
		posX += veloX;
		posY += veloY;
		box = new BoundingBox(posX, posY, width, height);
	}

	public void render(GraphicsContext gc) {
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

	public void addObject() {
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

	public BoundingBox getB() {
		return box;
	}

	public void setB(BoundingBox box) {
		this.box = box;
	}

	public double getBaseVeloX() {
		return baseVeloX;
	}

	public void setBaseVeloX(double baseVeloX) {
		this.baseVeloX = baseVeloX;
	}

}
