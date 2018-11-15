package weapon;

public class Gun {
	protected int damage;
	protected int amount;

	public Gun(int damage, int amount) {
		this.damage = damage;
		this.amount = amount;
	}
	
	public void shoot() {
		this.amount--;
	}

}
