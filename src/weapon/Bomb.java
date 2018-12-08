package weapon;

import application.GameEntity;
import application.SoundManager;
import character.Hero;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bomb extends GameObject {

	private Hero hero;
	private boolean isIgnited;
	private Image[] Bomb;
	private Image[] Smoke;
	private int bombFrame;
	private int smokeFrame;
	private boolean soundIsPlay;

	public Bomb(double width, double height, Hero hero) {
		super(0, 0, width, height);
		this.hero = hero;
		soundIsPlay = false;
		posX = hero.getPosX();
		posY = hero.getPosY();
		baseVeloX = 8;
		veloY = -10;
		isIgnited = false;
		Bomb = new Image[9];
		for (int i = 1; i <= 9; i++) {
			Bomb[i - 1] = new Image("file:res/images/bomb" + i + ".png");
		}
		bombFrame = 0;

		Smoke = new Image[21];
		for (int i = 1; i <= 21; i++) {
			Smoke[i - 1] = new Image("file:res/images/Layer " + i + ".png");
		}
		smokeFrame = 0;
	}

	@Override
	public void render(GraphicsContext gc) {
		if (isIgnited) {
			gc.drawImage(Smoke[smokeFrame], posX - 10, posY - 100);
			smokeFrame++;
			if (smokeFrame == 21) {
				isHit = true;
			}
			if (!soundIsPlay) {
				SoundManager.play("grenade", 1);
				soundIsPlay = true;
			}
		} else {
			gc.drawImage(Bomb[(bombFrame / 3) % 9], posX, posY);
			bombFrame++;
		}
	}

	public void update() {
		if (posY >= 800 || posX < -width) {
			isHit = true;
			setHeroCanShoot();
		}
		if (!hasVerticalCollition) {
			this.veloY += GRAVITY;
		} else if (hasVerticalCollition && veloY > 0) {
			isIgnited = true;
			this.veloY = 0;
			baseVeloX = 0;
			width = 100;
			height = 100;
			Thread thread = new Thread(() -> {
				try {
					Thread.sleep(200);
					setHeroCanShoot();
					hero.setThrowingBomb(false);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ArrayIndexOutOfBoundsException e) {

				}
			});
			thread.start();
		}
		veloX = baseVeloX - GameEntity.getCurrentFg().getVeloX() / 2;
		posX += veloX;
		posY += veloY;
		box = new BoundingBox(posX, posY, width, height);
	}

	public boolean isIgnited() {
		return isIgnited;
	}

	public void setIgnited(boolean isIgnited) {
		this.isIgnited = isIgnited;
	}

	public void setHeroCanShoot() {
		hero.setCanShoot(true);
	}
}
