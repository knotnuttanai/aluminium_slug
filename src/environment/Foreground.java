package environment;

import application.GameEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Foreground implements Deleteable {
	private Image fg;
	private double posX;
	private double posY;
	private double veloX;
	private boolean isDead;

	public Foreground(double posX) {
		fg = new Image(ClassLoader.getSystemResource("foreground2.png").toString());
		veloX = 0;
		this.posX = posX;
		isDead = false;
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(fg, posX, 0);
	}

	public void update() {
		checkSelfDelete();
		posX += veloX;
	}

	public void moveScreen(int direction) {
		veloX = direction;
	}

	public void stop() {
		veloX = 0;
	}

	public double getVeloX() {
		return veloX;
	}

	public void addFg() {
		GameEntity.createFg(this);
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

	@Override
	public void checkSelfDelete() {
		if (posX <= -10000) {
			setDead();
		}
	}

	@Override
	public boolean isDead() {
		return isDead;
	}

	@Override
	public void setDead() {
		isDead = true;
	}

}