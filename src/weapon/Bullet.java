package weapon;

import application.GameEntity;
import application.GameScene;
import character.Hero;
import character.Person;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Bullet extends GameObject{
	protected double posX;
	protected double posY;
	protected double veloX, baseVeloX;
	protected double veloY, baseVeloY;
	protected boolean bulletUp, bulletDown;
	public boolean isHit;
	protected double width;
	protected double height;
	protected int damage;
	Image bullet;
	public Bullet(Person p) {
		super(p.getPosX() + 40,p.getPosY(),50,10);
		//posX = p.getPosX() + 40;
		//posY = p.getPosY() + 10;
		posX = p.getPosX()+40;
		posY = p.getPosY()+10;
		bulletUp = p.isLookUp();
		bulletDown = p.isLookDown();
		if(bulletUp) {
			veloX = 0;
			veloY = -10;
			posX = p.getPosX() + 20;
			posY = p.getPosY() ;
		}
		else if(bulletDown) {
			veloX = 0;
			veloY = 10;
			posX = p.getPosX() + 20;
			posY = p.getPosY() +30 ;
		}
		else {
			veloX = 10;
			veloY = 0;
		}
		baseVeloX = veloX;
		baseVeloY = veloY;
		isHit = false;
		damage = 10;
		bullet = new Image("file:res/images/Glenos-G_160_bullet.png",50, 32.5, false, false);
		
		
				
		
	}
	public void update() 
	{	
		
		if(this.posY >=800||this.posX +this.width < -10) {
			setHit();
		}
		calculateRelaSpeed();
		posX += veloX;
		posY += veloY;
	}
	public void calculateRelaSpeed() {
		if(!bulletUp) {
			veloX = baseVeloX + GameEntity.getCurrentFg().getVeloX();
		}
	}
	public void render(GraphicsContext gc) {
		//gc.clearRect(posX-5, posY, 5, 5);
		/*gc.setFill(Color.RED);
		gc.setStroke(Color.BLACK);
		
		gc.fillRect(posX+30, posY+30, 8, 8);
		*/
		gc.drawImage(bullet, posX, posY);
		//gc.fillRect(posX, posY, width, height);
		
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
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
}
