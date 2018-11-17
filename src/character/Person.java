package character;

import environment.Foreground;
import weapon.Bullet;

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
	protected boolean isJump;
	
	
	public Person(double posX, double posY , int health) {
		this.posX = posX;
		this.posY = posY;
		this.health = health;
		veloX = 0;
		veloY = 0;
		isJumpUp = false;
		base = posY;
		isJump = false;
		isAlive = true;
		
	}

	@Override
	public void Walk(int direction) {
		veloX = direction*2;
		
	}

	@Override
	public void Jump() {
		if(!isJump) {
		isJumpUp = true;
		veloY = -15;
		isJump = true;
		}
		
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
			if(veloY > 0 && veloY < 10) {
				veloY += GRAVITY;
			}
			else if(veloY == 10) {
				veloY = 0;
				posY = base;
				isJump = false;
				return;
			}
		}
		
		
		
		posX += veloX;
		posY += veloY;
	}
	public boolean isHitByBullet(Bullet b) {
		if(this.posX <= b.getPosX()+10 && this.posX >= b.getPosX()-10 && (this.posY <= b.getPosY()+20 && this.posY >= b.getPosY())) {
			System.out.println("HIT!!");
			return true;
		}else {
			return false;
		}
	}
	public void setDead() {
		isAlive = false ;
	}
	public boolean isAlive() {
		return isAlive;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public double getVeloY() {
		return veloY;
	}

	public void setVeloY(double veloY) {
		this.veloY = veloY;
	}

	public double getVeloX() {
		return veloX;
	}

	public void setVeloX(double veloX) {
		this.veloX = veloX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}
	
	
}

