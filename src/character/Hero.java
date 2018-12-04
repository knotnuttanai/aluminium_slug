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
	Image marcoTop;
	Image marcoBottom;
	Image[] shoot;
	Image[] walk;
	int i = 0;
	int j = 0;

	public Hero(double posX, double posY, int health) {
		super(posX, posY, health);
		// TODO Auto-generated constructor stub
		veloX = 0;
		veloY = 0;
		height = 75;
		width = 45;
		marcoTop = new Image("file:res/images/top_marco1.png");
		marcoBottom = new Image("file:res/images/bottom_marco.png");
		shoot = new Image[10];
		walk = new Image[5];
		
		
		for(int i = 1; i <= 10; i++) {
			shoot[i-1] = new Image("file:res/images/shoot" + i + ".png");
		}
		
		for(int i = 1; i <= 5; i++) {
			walk[i-1] = new Image("file:res/images/walk" + i + ".png");
		}
		
		GameEntity.createHero(this);
	}
	
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(isWalk) {
			gc.drawImage(walk[j], posX, posY + 43);
			j++;
			if(j==5) {
				j = 0;
			}
		}
		else gc.drawImage(marcoBottom, posX, posY + 43);
		
		if(isShoot) {
			gc.drawImage(shoot[i], posX, posY);
			i++;
			if(i==10) {
				i = 0;
				isShoot = false;
			}
		}
		else gc.drawImage(marcoTop, posX, posY);
		
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
