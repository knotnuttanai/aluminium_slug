package weapon;


import character.Person;
import javafx.scene.canvas.GraphicsContext;

public class MachineGunBullet extends HeroBullet {
	public int maxBullets;
	public MachineGunBullet(Person p) {
		super(p);
		damage = 10;
		maxBullets = 256;

		if(bulletDown) {
			posX -= 10;
			posY += 10;
		}
		else if(!bulletUp) {
			posX += 4;
			posY += 18;
		}
		
	}
	
	/*@Override
	public void render(GraphicsContext gc) {
		if(bulletUp) gc.drawImage(bulletShootUp, posX, posY);
		else if(bulletDown) gc.drawImage(bulletShootDown, posX, posY);
		else gc.drawImage(bullet, posX, posY);
		
	}*/
	

}
