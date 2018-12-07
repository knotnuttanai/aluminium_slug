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
		fg = new Image("file:res/images/foreground2.png");
		veloX = 0;
		this.posX = posX;
		isDead = false;
	}
	
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(fg, posX, 0);
		
	}
	
	public void update() {
		checkSelfDelete();
		posX += veloX;
		//System.out.println(posX);
				
	}
	
	public void moveScreen(int direction) {
		veloX = direction;
	}

	public void stop() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		if(posX <= -10000) {
			System.out.println("dead");
			setDead();
		}
	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return isDead;
	}

	@Override
	public void setDead() {
		// TODO Auto-generated method stub
		isDead = true;
	}
	
}