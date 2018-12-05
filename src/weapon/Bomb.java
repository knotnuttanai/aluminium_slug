package weapon;

import application.GameEntity;
import character.Hero;

public class Bomb extends GameObject {
	Hero hero;
	public Bomb(double width, double height, Hero hero) 
	{
		super(0, 0, width, height);
		this.hero = hero;
		posX = hero.getPosX();
		posY = hero.getPosY();
		baseVeloX = 10;
		veloY = -5;
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
			this.veloY = 0;
			baseVeloX  = 0;
			width = 100;
			height = 100;
			posY = posY - height+10;
		}
		veloX = baseVeloX + GameEntity.getCurrentFg().getVeloX();
		posX += veloX;
		posY += veloY;
	}
	
}
