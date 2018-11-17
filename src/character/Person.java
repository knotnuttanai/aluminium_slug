package character;

import environment.Foreground;

public abstract class Person implements Movable{
	protected int health;
	protected double posX;
	protected double posY;
	protected boolean isAlive;
	protected double veloY;
	protected double veloX;
	protected static final double GRAVITY = 1;
	protected boolean isJumpUp;
	protected double base;
	
	public Person(double posX, double posY , int health) {
		this.posX = posX;
		this.posY = posY;
		this.health = health;
		veloX = 0;
		veloY = 0;
		isJumpUp = false;
		base = posY;
		
	}

	@Override
	public void Walk(int direction) {
		//posX += direction*2;
		Foreground.update();
	}

	@Override
	public void Jump() {
		
		isJumpUp = true;
		veloY = -10;
		
	}

	public double getPosX() {
		return posX;
	}

	public double getPosY() {
		return posY;
	}
	public void update() {
		// TODO Auto-generated method stub
		
		if(isJumpUp) {
			veloY += GRAVITY;
			if(veloY > 0) {
				this.isJumpUp = false;
				
			}
		}
		else if(!isJumpUp) {
			/*if(veloY > 0 && veloY < 10) {
				veloY += GRAVITY;
			}
			else if(veloY == 10) {
				veloY = 0;
				posY = base;
				return;
			}
		}*/
		if(posY == base) {
			veloY += 0;
		}
		else {
			veloY += GRAVITY;
		}
		
		posX += veloX;
		posY += veloY;
	}
	
	}
}
