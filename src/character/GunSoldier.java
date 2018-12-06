package character;

import application.GameEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import weapon.EnemyBullet;

public class GunSoldier extends Enemy implements Shootable{
	Thread thread;
	int count;
	private Image[] normal;
	private Image[] grabGun;
	private Image[] SoldierShoot;
	private Image[] dead;
	private int normalFrame;
	private int grabGunFrame;
	private int shootFrame;
	private int deadFrame;
	private boolean normalCondition;
	private boolean grabGunCondition;
	private boolean shootCondition;
	final private int walkCount = (int)(Math.random()*2);

	public GunSoldier(double posX, double posY, int health) {
		super(posX, posY, health);
		count = 0;
		normal = new Image[12];
		for(int i = 1; i <= 12; i++) {
			normal[i-1] = new Image("file:res/images/normalsoldier" + i + ".png");
		}
		normalFrame = 0;
		normalCondition = true;
		
		grabGun = new Image[8];
		for(int i = 1; i <= 8; i++) {
			grabGun[i-1] = new Image("file:res/images/grabgun" + i + ".png");
		}
		grabGunFrame = 0;
		grabGunCondition = false;
		
		SoldierShoot = new Image[5];
		for(int i = 0; i <= 4; i++) {
			SoldierShoot[i] = new Image("file:res/images/soldiershoot" + i + ".png");
		}
		shootFrame = 0;
		shootCondition = false;
		
		dead = new Image[6];
		for(int i = 1; i <= 6; i++) {
			dead[i-1] = new Image("file:res/images/gundead" + i + ".png");
		}
		deadFrame = 0;
		
	}

	@Override
	public void shoot() {
		isShoot = true;
		EnemyBullet bullet = new EnemyBullet(this);
		bullet.setDamage(dmg+bullet.getDamage());
		bullet.addBullet();
	}
	
	@Override
	public void update() {
		if(!isAlive) {
			baseVeloX = 0;
		}
		if(this.posY >=800||this.posX +this.width < -10) {
			this.setDead();
		}
		if(isJump || !isHasVerticalCollition()) {
			this.veloY += GRAVITY;
	
		}
		else if(hasVerticalCollition && veloY > 0) {
			this.veloY = 0;
		}
		veloX = baseVeloX+GameEntity.getCurrentFg().getVeloX();
		this.posY += this.veloY;
		
		this.posX += this.veloX;
		if(normalCondition) {
			
			if(normalFrame >= walkCount * 36) {
				normalCondition = false;
				grabGunCondition = true;
				
			}
		}
		if(grabGunCondition) {
			veloX = 0;
			walkDirection = 0;
			baseVeloX = veloX;
			if(grabGunFrame == 24) {
				grabGunCondition = false;
				shootCondition = true;
				//shoot();
			}
			
		}
		if (shootCondition) {
			if((int)(Math.random()*99)+1 < 2) {
				count = 0;
			}
		}
		//new condition while not shooting?
	}
	
	@Override
	public void render(GraphicsContext gc) {
		
		if(!isAlive) {
			gc.drawImage(dead[deadFrame/5], posX, posY + deadFrame*1.1);
			deadFrame++;
			if(deadFrame == 30) isAnimatedDead = true;
		}
		
		else {
		
		if(normalCondition) {
			gc.drawImage(normal[(normalFrame/3)%12], posX, posY);
			normalFrame++;

			/*if(normalFrame >= walkCount * 36) {
				normalCondition = false;
				grabGunCondition = true;
				
			}*/
		}
		if(grabGunCondition) {
			//veloX = 0;
			//walkDirection = 0;
			//baseVeloX = veloX;
			gc.drawImage(grabGun[grabGunFrame/3], posX, posY);
			grabGunFrame++;
			/*if(grabGunFrame == 24) {
				grabGunCondition = false;
				shootCondition = true;
				shoot();
			}*/
			
		}
		if(shootCondition) {
			if(count<=1) {
				gc.drawImage(SoldierShoot[(shootFrame/3)%5], posX - adjustShootPosX(), posY + adjustShootPosY());
				shootFrame++;
			}
			else {
				gc.drawImage(SoldierShoot[0], posX, posY);
			}
			thread = new Thread(()->{
			try {
			
					if(shootFrame >= 15) {
						shootFrame = 0;
						if(count <= 1) {
							shoot();
							count++;
							//หยุดยิงตอนcountเกิน
						}else {
							//change condition ?????
						}
						
					}
				
			}
			catch(ArrayIndexOutOfBoundsException e) {
				
			}
			
			});thread.start();
			if(!isAlive) {
				try {
					thread.interrupt();
					thread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
		}
		}
	}
	
	private int adjustShootPosX() {
		if(shootFrame/3 == 1) return 44;
		if(shootFrame/3 == 2 || shootFrame/3 == 3) return 40;
		if(shootFrame/3 == 4) return 42; 
		return 0;
	}
	
	private int adjustShootPosY() {
		if(shootFrame/3 == 2) return 2;
		if(shootFrame/3 == 3 || shootFrame/3 == 4) return 6;
		return 0;
	}
	

}
