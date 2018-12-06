package character;

import weapon.EnemyBullet;

public class GunSoldier extends Enemy implements Shootable{

	public GunSoldier(double posX, double posY, int health) {
		super(posX, posY, health);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void shoot() {
		isShoot = true;
		EnemyBullet bullet = new EnemyBullet(this);
		bullet.setDamage(dmg+bullet.getDamage());
		bullet.addBullet();
	}
	

}
