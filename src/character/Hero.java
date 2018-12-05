package character;
import javafx.geometry.BoundingBox;
import application.GameEntity;
import environment.Foreground;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import weapon.Bullet;
import weapon.EnemyBullet;
import weapon.HeroBullet;
import weapon.MachineGunBullet;
import weapon.PistolBullet;

public class Hero extends Person implements Shootable{
	protected boolean isJump;
	public int gun;
	//gun0 is pistol
	//gun1 is machine gun
	Image marcoTop;
	Image marcoBottom;
	Image marcoMachine;
	Image marcoLookUp;
	Image marcoLookDown;
	Image[] machShoot;
	Image[] shoot;
	Image[] shootUp;
	Image[] shootDown;
	Image[] walk;
	int i = 0;
	int j = 0;
	private int firerate;

	public Hero(double posX, double posY, int health) {
		super(posX, posY, health);
		// TODO Auto-generated constructor stub
		gun = 0;
		this.firerate = 200;
		veloX = 0;
		veloY = 0;
		height = 75;
		width = 45;
		marcoTop = new Image("file:res/images/top_marco1.png");
		marcoMachine = new Image("file:res/images/machgun.png");
		marcoBottom = new Image("file:res/images/bottom_marco.png");
		marcoLookUp = new Image("file:res/images/marcoUp.png");
		marcoLookDown = new Image("file:res/images/marcodown.png");
		shoot = new Image[10];
		shootUp = new Image[10];
		shootDown = new Image[6];
		walk = new Image[10];
		machShoot = new Image[4];
		
		
		for(int i = 1; i <= 10; i++) {
			shoot[i-1] = new Image("file:res/images/shoot" + i + ".png");
		}
		
		for(int i = 1; i <= 10; i++) {
			shootUp[i-1] = new Image("file:res/images/shootup" + i + ".png");
		}
		
		for(int i = 1; i <= 6; i++) {
			shootDown[i-1] = new Image("file:res/images/shootdown" + i + ".png");
		}
		
		for(int i = 1; i <= 10; i++) {
			walk[i-1] = new Image("file:res/images/walk" + i + ".png");
		}
		
		for(int i = 1; i <= 4; i++) {
			machShoot[i-1] = new Image("file:res/images/mach" + i + ".png");
		}
		
		
		GameEntity.createHero(this);
	}
	
	public int getFirerate() {
		return firerate;
	}

	public void setFirerate(int firerate) {
		this.firerate = firerate;
	}

	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		int k = 0;
		int d = 0;
		  if(isWalk) {
		   gc.drawImage(walk[j/5], posX, posY + 38);
		   if(j/5==0) k = 2;
		   else if(j/5==1) k = 6;
		   else if(j/5==2) k = 7;
		   else if(j/5==3) k = 0;
		   else if(j/5==4) k = -4;
		   else if(j/5==5) k = -3;
		   else if(j/5==6) k = -1;
		   else if(j/5==7) k = 4;
		   else if(j/5==8) k = 5;
		   else if(j/5==9) k = 0;
		   else k = 0;
		   j++;
		   
		   if(j==50) {
		    j = 0;
		   }
		  }
		  else gc.drawImage(marcoBottom, posX, posY + 38);
		  
		  if(isShoot) {
			  if(gun == 0) {
				  if(isLookUp) {
					  if(i==0||i==1) d = 82;
					  else if(i==2) d = 86;
					  else if(i==3) d = 32;
					  else if(i==4||i==5||i==7) d = 34;
					  else if(i==6) d = 36;
					  else if(i==8) d = 28;
					  else if(i==9) d = 8;
					  gc.drawImage(shootUp[i], posX + k , posY - d);
					  i++;
					  if(i==10) {
						  i = 0;
						  isShoot = false;
					  }
					  
				  }
				  else if(isLookDown) {
					  gc.drawImage(shootDown[i], posX + k , posY);
					  i++;
					  if(i==6) {
						  i = 0;
						  isShoot = false;
					  }
				  }
				  
				  else {
					  gc.drawImage(shoot[i], posX + k , posY);
					  i++;
					  if(i==10) {
						  i = 0;
						  isShoot = false;
					  }
					  
				  }
				  
			  }
			  else {
				  gc.drawImage(machShoot[i], posX + k , posY);
				  i++;
				  if(i==4) {
					  i = 0;
					  isShoot = false;
				   }
			  }
			  
		  }
		  else {
			  if(gun == 0) {
				  if(isLookUp) gc.drawImage(marcoLookUp, posX + k, posY - 6);
				  else if(isLookDown) gc.drawImage(marcoLookDown, posX + k, posY);
				  else gc.drawImage(marcoTop, posX + k, posY);
			  }
			  else gc.drawImage(marcoMachine, posX + k, posY);
		  }
		  	
		  //gc.setFill(Color.BISQUE);
		  //gc.fillRect(posX, posY, 30, 60);
		  //gc.fillRect(getBaseX()+width,0 , 640 - getBaseX(), 480);
		}
	
	@Override	
	public void Walk(int direction) {
		posX += direction*2;
		
	}
	
	public void shoot() {
		isShoot = true;
		if(gun == 0) {
			firerate = 200;
			PistolBullet bullet = new PistolBullet(this);
			bullet.addBullet();
		}
		if(gun == 1) {
			firerate = 100;
			MachineGunBullet bullet = new MachineGunBullet(this);
			bullet.addBullet();
		}
		
	}
	public boolean isHitByBullet(EnemyBullet b) {
		BoundingBox b1 = new BoundingBox(posX, posY, width, height);
		BoundingBox b2 = new BoundingBox(b.getPosX(), b.getPosY(), b.getWidth(), b.getHeight());
		return b1.intersects(b2);
	}
	
	
	

}
