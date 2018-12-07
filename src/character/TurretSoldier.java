package character;

import application.SoundManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import weapon.EnemyBullet;
import weapon.TurretBullet;

public class TurretSoldier extends Enemy implements Shootable {
	
	Thread thread;
	int count;
	private Image Turret;
	private Image[] TurretShoot;
	private Image[] dead;
	private int deadFrame,turretFrame;
	private boolean shootCondition;
	private boolean soundisplay;
	private boolean needRandomShoot;

	public TurretSoldier(double posX, double posY, int health) {
		super(posX, posY - 10, health);
		count = 0;
		veloX = 0;
		baseVeloX = 0;
		dmg = 0;
		walkDirection = 0;
		Turret = new Image("file:res/images/turret.png");
		TurretShoot = new Image[3];
		for(int i = 1; i <=3; i++) {
			TurretShoot[i-1] = new Image("file:res/images/turretfiring" + i + ".png");
		}
		turretFrame = 0;
		dead = new Image[21];
		for(int i = 1; i <= 21; i++) {
			dead[i-1] = new Image("file:res/images/Layer " + i + ".png");
		}
		deadFrame = 0;
		
		shootCondition = false;
		needRandomShoot = true;
	}

	@Override
	public void shoot() {
		TurretBullet bullet = new TurretBullet(this);
		bullet.setDamage(dmg+bullet.getDamage());
		bullet.addBullet();

	}
	
	public boolean randomShoot() {
		if((int)(Math.random()*20) + 1 == 5 ) return true;
		return false;
	}
	
	public int adjustShootPosX() {
		if(turretFrame/3==0) return 100;
		if(turretFrame/3==1) return 82;
		if(turretFrame/3==2) return 94;
		return 0;
	}
	
	@Override
	public void render(GraphicsContext gc) {
		
		if(health == 0) {
			gc.drawImage(dead[deadFrame], posX , posY - 100);
			deadFrame++;
			if(deadFrame == 21) isAnimatedDead = true;
			if(!soundisplay) {
				SoundManager.play("grenade", 1);
				soundisplay = true;
			}
			if(deadFrame == 30) isAnimatedDead = true;
		}
		
		else {
			if(needRandomShoot){
				shootCondition = randomShoot();
			}
			if(shootCondition) {
				needRandomShoot = false;
				gc.drawImage(TurretShoot[(turretFrame/3)%3], posX - adjustShootPosX(), posY /*+ adjustShootPosY()*/);
				turretFrame++;
				Thread thread = new Thread(()->{
					if(turretFrame == 3) shoot();
					if(turretFrame >= 9) {
						turretFrame = 0;
						needRandomShoot = true;
					}
				});thread.start();
				
			}
			else gc.drawImage(Turret, posX - 50, posY);
			/*thread = new Thread(()->{
				try {
					if(turretFrame >= 9) {
						turretFrame = 0;
						if(count <= 1) {
							shoot();
							count++;	//เธซเธขเธธเธ”เธขเธดเธ�เธ•เธญเธ�countเน€เธ�เธดเธ�
						}
						else {
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
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}*/
	//	if(true) {
		//	if(count<=1) {
		//		gc.drawImage(TurretShoot[(turretFrame/3)%5], posX /*- adjustShootPosX()*/, posY /*+ adjustShootPosY()*/);
		//		turretFrame++;
		//	}
		//	else {
		//		gc.drawImage(Turret, posX, posY);
			//}
			/*thread = new Thread(()->{
			try {
			
					if(turretFrame >= 15) {
						turretFrame = 0;
						if(count <= 1) {
							shoot();
							count++;
							//เธซเธขเธธเธ”เธขเธดเธ�เธ•เธญเธ�countเน€เธ�เธดเธ�
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
			}*/
			
			
			
		//}
		}
	
	}

}
