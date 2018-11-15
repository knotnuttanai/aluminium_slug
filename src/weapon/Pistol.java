package weapon;

public class Pistol extends Gun {
	private final static int damage = 10;

	public Pistol() {
		super(damage, 20);
	}
	
	@Override
	public void shoot() {
		amount--;
		if(amount == 0) reload();
	}
	
	public void reload() {
		// delay
		amount = 20;
	}

}
