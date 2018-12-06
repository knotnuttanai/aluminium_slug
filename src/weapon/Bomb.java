package weapon;

import application.GameEntity;
import character.Hero;
import javafx.geometry.BoundingBox;

public class Bomb extends GameObject {
	Hero hero;
	private boolean isIgnited;
	public Bomb(double width, double height, Hero hero) 
	{
		super(0, 0, width, height);
		this.hero = hero;
		posX = hero.getPosX();
		posY = hero.getPosY();
		baseVeloX = 10;
		veloY = -5;
		isIgnited = false;
		// TODO Auto-generated constructor stub
	}
	public void update() {
		if(!hasVerticalCollition) {
			this.veloY += GRAVITY;
			/*if(baseVeloX > 2) {
				baseVeloX = baseVeloX -2;
			}
			else if(baseVeloX < 0) {
				baseVeloX = 0;
			}*/
		}
		else if(hasVerticalCollition && veloY > 0) {
			isIgnited = true;
			this.veloY = 0;
			baseVeloX  = 0;
			width = 100;
			height = 100;
			posY = posY - height+10;
			
			
			Thread thread = new Thread(()->{
				try {
					Thread.sleep(200);
					isHit = true;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch(ArrayIndexOutOfBoundsException e) {
					
				}
			});thread.start();
		}
		veloX = baseVeloX + GameEntity.getCurrentFg().getVeloX();
		posX += veloX;
		posY += veloY;
		b = new BoundingBox(posX, posY, width, height);
	}
	public boolean isIgnited() {
		return isIgnited;
	}
	public void setIgnited(boolean isIgnited) {
		this.isIgnited = isIgnited;
	}
	
}