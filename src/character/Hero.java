package character;
import javafx.geometry.BoundingBox;
import application.GameEntity;
import environment.Foreground;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import weapon.Bullet;

public class Hero extends Person implements Shootable{
	protected boolean isJump;
	Image marco;
	Image[] shoot;
	int i = 0;

	public Hero(double posX, double posY, int health) {
		super(posX, posY, health);
		// TODO Auto-generated constructor stub
		veloX = 0;
		veloY = 0;
		height = 75;
		width = 50;
		marco = new Image("file:res/images/marco2.png");
		shoot = new Image[10];
		
		
		for(int i = 1; i <= 10; i++) {
			shoot[i-1] = new Image("file:res/images/shoot" + i + ".png");
		}
		
		GameEntity.createHero(this);
	}
	
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(isShoot) {
			gc.drawImage(shoot[i], posX, posY);
			i++;
			if(i==10) {
				i = 0;
				isShoot = false;
			}
		}
		else gc.drawImage(marco, posX, posY);
		//gc.setFill(Color.BISQUE);
		//gc.fillRect(posX, posY, 30, 60);
		//gc.fillRect(getBaseX()+width,0 , 640 - getBaseX(), 480);
		}
	@Override	
	public void Walk(int direction) {
		posX += direction*2;
		
	}
	public void update() {
		// TODO Auto-generated method stub
		

		
		if(isJump) {
			System.out.println("p");
		}
		if(posY >=800) {
			setPosX(baseX);
			setPosY(base);
			veloY = 0;
		}
		
		if(veloY > maxVeloY) {
			veloY = maxVeloY;
		}
		else if(!hasVerticalCollition ) {
			veloY += GRAVITY;
			
		
		
		}
		
		else if(hasVerticalCollition && isJump) {
			veloY += GRAVITY;
			
			isJump = false;
		}else if(hasVerticalCollition &&!isJump&& veloY > 0) {
			veloY = 0;
		
			
		}
		posY += veloY;
		
		posX += veloX;
		
	}
	public void shoot() {
		isShoot = true;
		Bullet bullet = new Bullet(this);
		bullet.addBullet();
	}

	
	
	

}
