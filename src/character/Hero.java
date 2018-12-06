package character;
import javafx.geometry.BoundingBox;
import application.GameEntity;
import environment.Foreground;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import weapon.Bomb;
import weapon.Bullet;
import weapon.EnemyBullet;
import weapon.HeroBullet;
import weapon.MachineGunBullet;
import weapon.PistolBullet;

public class Hero extends Person implements Shootable{
	private boolean isJump, isInTheTank, requestToEnterTank, isThrowingBomb;
	private int gun;
	//gun0 is pistol
	//gun1 is machine gun
	//gun3 is tank
	private int moveSpeed;
	private int maxGun1Bullet;
	private int useGunBullet;
	
	private Image marcoTop;
	private Image marcoBottom;
	private Image marcoMachine;
	private Image marcoLookUp;
	private Image marcoLookDown;
	private Image marcoMachUp;
	private Image marcoMachDown;
	private Image[] machShoot;
	private Image[] machShootUp;
	private Image[] machShootDown;
	private Image[] shoot;
	private Image[] shootUp;
	private Image[] shootDown;
	private Image[] walk;
	private Image[] ThrowingBomb;
	private int walkFrame,shootFrame,shootUpFrame,shootDownFrame,machFrame,machUpFrame,machDownFrame,throwingBombFrame;
	private int firerate;

	public Hero(double posX, double posY, int health) {
		super(posX, posY, health);
		// TODO Auto-generated constructor stub
		dmg = 0;
		gun = 0;
		this.firerate = 200;
		moveSpeed = -5;
		maxGun1Bullet = 256;
		useGunBullet = 256;
		veloX = 0;
		veloY = 0;
		height = 75;
		width = 45;
		canShoot = true;
		isInTheTank = false;
		requestToEnterTank = false;
		isThrowingBomb = false;
		marcoTop = new Image("file:res/images/top_marco1.png");
		marcoMachine = new Image("file:res/images/machgun.png");
		marcoBottom = new Image("file:res/images/bottom_marco.png");
		marcoLookUp = new Image("file:res/images/marcoUp.png");
		marcoLookDown = new Image("file:res/images/marcodown.png");
		marcoMachUp = new Image("file:res/images/machup.png");
		marcoMachDown = new Image("file:res/images/machdown.png");
		shoot = new Image[10];
		shootUp = new Image[10];
		shootDown = new Image[6];
		machShootUp = new Image[4];
		machShootDown = new Image[4];
		walk = new Image[10];
		machShoot = new Image[4];
		ThrowingBomb = new Image[5];
		walkFrame = 0;
		shootFrame = 0;
		shootUpFrame = 0;
		shootDownFrame = 0;
		machFrame = 0;
		machUpFrame = 0;
		machDownFrame = 0;
		throwingBombFrame = 0;
		
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
		
		for(int i = 1; i <= 4; i++) {
			machShootUp[i-1] = new Image("file:res/images/machshootup" + i + ".png");
		}
		
		for(int i = 1; i <= 4; i++) {
			machShootDown[i-1] = new Image("file:res/images/machshootdown" + i + ".png");
		}
		
		for(int i = 1; i <= 5; i++) {
			ThrowingBomb[i-1] = new Image("file:res/images/throwbomb" + i + ".png");
		}
		
		GameEntity.createHero(this);
	}
	
	public int getFirerate() {
		return firerate;
	}

	public void setFirerate(int firerate) {
		this.firerate = firerate;
	}
	
	private int WalkAdjustPos() {
		if(walkFrame/5==0) return 2;
		else if(walkFrame/5==1) return 6;
		else if(walkFrame/5==2) return 7;
		else if(walkFrame/5==3) return 0;
		else if(walkFrame/5==4) return -4;
		else if(walkFrame/5==5) return -3;
		else if(walkFrame/5==6) return -1;
	    else if(walkFrame/5==7) return 4;
	    else if(walkFrame/5==8) return 5;
	    else if(walkFrame/5==9) return 0;
		return 0;
	}
	
	private int ShootAdjustPos() {
		if(shootUpFrame/2==0||shootUpFrame/2==1) return 82;
		else if(shootUpFrame/2==2) return 86;
		else if(shootUpFrame/2==3) return 32;
		else if(shootUpFrame/2==4||shootUpFrame/2==5||shootUpFrame/2==7) return 34;
		else if(shootUpFrame/2==6) return 36;
		else if(shootUpFrame/2==8) return 28;
		else if(shootUpFrame/2==9) return 8;
		else return 0;
	}
	
	private int MachAdjustPos() {
		if(machUpFrame/2==0) return 92;
		else if(machUpFrame/2==1) return 96;
		else if(machUpFrame/2==2) return 94;
		else if(machUpFrame/2==3) return 98;
		else return 0;
	}
	
	private void finishShoot(String frame) {
		isShoot = false;
		if(frame.equals("shootUpFrame")) shootUpFrame = 0;
		else if(frame.equals("shootDownFrame")) shootDownFrame = 0;
		else if(frame.equals("shootFrame")) shootFrame = 0;
		else if(frame.equals("machUpFrame")) machUpFrame = 0;
		else if(frame.equals("machDownFrame")) machDownFrame = 0;
		else if(frame.equals("machFrame")) machFrame = 0;
	}
	

	public void render(GraphicsContext gc) {

		int k = 0;
		int d = 0;
		
		  if(isWalk) {
		   gc.drawImage(walk[(walkFrame/5)%10], posX, posY + 38);
		   k = WalkAdjustPos();
		   walkFrame++;
		   
		   if(walkFrame==50) walkFrame = 0;
		  }
		  else gc.drawImage(marcoBottom, posX, posY + 38);
		  
		  if(isShoot) {
	
			  if(gun == 0) {
					  if(isLookUp) {
						  d = ShootAdjustPos();
						  gc.drawImage(shootUp[(shootUpFrame/2)%10], posX + k , posY - d);
						  shootUpFrame++;
						  if(shootUpFrame==20) finishShoot("shootUpFrame");
						  
					  }
					  else if(isLookDown) {
						  gc.drawImage(shootDown[(shootDownFrame/2)%6], posX + k , posY);
						  shootDownFrame++;
						  if(shootDownFrame==12) finishShoot("shootDownFrame");
					  }
					  
					  else {
						  gc.drawImage(shoot[(shootFrame/2)%10], posX + k , posY);
						  shootFrame++;
						  if(shootFrame==20) finishShoot("shootFrame");
						}
						  
					  }
				  else {
	
					  if(isLookUp) {
						  d = MachAdjustPos();
						  gc.drawImage(machShootUp[(machUpFrame/2)%4], posX + k , posY - d);
						  machUpFrame++;
						  if(machUpFrame==8) finishShoot("machUpFrame");
					  }
					  
					  else if(isLookDown) {
						  gc.drawImage(machShootDown[(machDownFrame/2)%4], posX - 8 + k , posY);
						  machDownFrame++;
						  if(machDownFrame==8) finishShoot("machDownFrame");
					  }
					  else {
						  gc.drawImage(machShoot[(machFrame/2)%4], posX + k , posY);
						  machFrame++;
						  if(machFrame==8) finishShoot("machFrame");
					  }
			  }
		  }
		  
		  else if(isThrowingBomb) {
			  if(gun == 0) {
				  gc.drawImage(ThrowingBomb[throwingBombFrame], posX + k, posY);
				  throwingBombFrame++;
				  if(throwingBombFrame == 5) {
					  throwingBombFrame = 0;
					  isThrowingBomb = false;
				  }
			  }
		  }
			  
		  else {
			  if(gun == 0) {
				  if(isLookUp) gc.drawImage(marcoLookUp, posX + k, posY - 6);
				  else if(isLookDown) gc.drawImage(marcoLookDown, posX + k, posY);
				  else gc.drawImage(marcoTop, posX + k, posY);
			  }
			  else {
				  if(isLookUp) gc.drawImage(marcoMachUp, posX + k, posY - 20);
				  else if(isLookDown) gc.drawImage(marcoMachDown, posX - 8 + k, posY);
				  else gc.drawImage(marcoMachine, posX + k, posY);
			  }
		  }
		  	
		}
	
	@Override	
	public void Walk(int direction) {
		posX += direction*2;
		
	}
	
	public void shoot() {
		
		if(canShoot) {
			isShoot = true;
					if(isInTheTank) {
						gun = 0;
						firerate = 300;
						canShoot = false;
						Bomb bomb = new Bomb(15, 15, this);
						bomb.setVeloY(bomb.getVeloY()-5 );
						bomb.addObject();
						return;
						
					}
					if(gun == 1) {
						if(useGunBullet == 0) {
							gun = 0;
							firerate = 200;
							PistolBullet bullet = new PistolBullet(this);
							bullet.setDamage(dmg+bullet.getDamage());
							bullet.addBullet();
							return;
						}
						firerate = 80;
						MachineGunBullet bullet = new MachineGunBullet(this);
						bullet.setDamage(dmg+bullet.getDamage());
						bullet.addBullet();
						useGunBullet--;
					}
					if(gun == 0) {
						firerate = 200;
						PistolBullet bullet = new PistolBullet(this);
						bullet.setDamage(dmg+bullet.getDamage());
						bullet.addBullet();/*
						Bomb bomb = new Bomb(10, 10, this);
						bomb.addGun();*/
					}
		}
		
	}
	
	public void throwBomb() {
		if(isThrowingBomb) {
			Bomb bomb = new Bomb(10,10,this);
			bomb.addObject();
		}
	}
	
	public boolean isHitByBullet(EnemyBullet b) {
		BoundingBox b1 = new BoundingBox(posX, posY, width, height);
		BoundingBox b2 = new BoundingBox(b.getPosX(), b.getPosY(), b.getWidth(), b.getHeight());
		return b1.intersects(b2);
	}

	public int getGun() {
		return gun;
	}

	public void setGun(int gun) {
		this.gun = gun;
	}

	public int getMaxGun1Bullet() {
		return maxGun1Bullet;
	}

	public void setMaxGun1Bullet(int maxGun1Bullet) {
		this.maxGun1Bullet = maxGun1Bullet;
	}

	public int getUsedGun1Bullet() {
		return useGunBullet;
	}

	public void setUsedGun1Bullet(int usedGun1Bullet) {
		this.useGunBullet = usedGun1Bullet;
	}

	public boolean isInTheTank() {
		return isInTheTank;
	}

	public void setInTheTank(boolean isInTheTank) {
		this.isInTheTank = isInTheTank;
	}

	public boolean isRequestToEnterTank() {
		return requestToEnterTank;
	}

	public void setRequestToEnterTank(boolean requestToEnterTank) {
		this.requestToEnterTank = requestToEnterTank;
	}
	
	public void increaseHp(int heal) {
		int hp = health + heal;
		if(hp >= maxHealth) {
			hp = maxHealth;
		}
		health = hp;
	}

	public int getUseGunBullet() {
		return useGunBullet;
	}

	public void setUseGunBullet(int useGunBullet) {
		this.useGunBullet = useGunBullet;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	
	public void setThrowingBomb(boolean throwingBomb) {
		isThrowingBomb = throwingBomb;
	}
	
	
	

}
