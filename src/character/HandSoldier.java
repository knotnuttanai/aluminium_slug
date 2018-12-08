package character;

import application.SoundManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class HandSoldier extends Enemy {
	private Image[] scare;
	private Image[] run;
	private int scareFrame;
	private int runFrame;
	private boolean normalCondition;
	private boolean scareCondition;
	private boolean runCondition;
	public boolean soundIsPlay;

	public HandSoldier(double posX, double posY, int health) {
		super(posX, posY, health);
		soundIsPlay = false;
		walk = new Image[12];
		for (int i = 1; i <= 12; i++) {
			walk[i - 1] = new Image(ClassLoader.getSystemResource("normalsoldier" + i + ".png").toString());
		}
		walkFrame = 0;
		normalCondition = true;

		scare = new Image[6];
		for (int i = 1; i <= 6; i++) {
			scare[i - 1] = new Image(ClassLoader.getSystemResource("soldierscare" + i + ".png").toString());
		}
		scareFrame = 0;
		scareCondition = false;

		run = new Image[11];
		for (int i = 3; i <= 13; i++) {
			run[i - 3] = new Image(ClassLoader.getSystemResource("soldierrun" + i + ".png").toString());
		}
		runFrame = 0;
		runCondition = false;

		dead = new Image[11];
		for (int i = 1; i <= 11; i++) {
			dead[i - 1] = new Image(ClassLoader.getSystemResource("soldierdead" + i + ".png").toString());
		}
		deadFrame = 0;
	}

	@Override
	public void update() {
		super.update();
		if (Math.random() < 0.02) {
			this.Jump();
		}

	}

	@Override
	public void render(GraphicsContext gc) {
		if (!isAlive) {
			gc.drawImage(dead[deadFrame / 3], posX, posY + deadFrame);
			deadFrame++;
			if (deadFrame == 33) {
				isAnimatedDead = true;
			}
		} else {
			if (normalCondition) {
				gc.drawImage(walk[walkFrame / 3], posX, posY);
				walkFrame++;
				if (walkFrame == 36) {
					walkFrame = 0;
				}
			}

			if (runCondition) {
				gc.drawImage(run[runFrame / 3], posX, posY);
				runFrame++;
				if (runFrame == 33) {
					runFrame = 0;
				}
			} else if (CheckScareCondition()) {
				if (!soundIsPlay) {
					SoundManager.play("scare", 0.2);
					soundIsPlay = true;
				}
				if (scareFrame >= 83) {
					gc.drawImage(scare[5], posX, posY);
				} else if (scareFrame >= 80) {
					gc.drawImage(scare[4], posX, posY);
				} else {
					gc.drawImage(scare[scareFrame / 20], posX, posY);
				}
				scareFrame++;
				if (scareFrame < 80) {
					veloX = 0;
					walkDirection = 0;
					baseVeloX = veloX;
				}
				if (scareFrame == 86) {
					runCondition = true;
					veloX = 2;
					walkDirection = 1;
					baseVeloX = veloX;
				}
			}
		}
	}

	private boolean CheckScareCondition() {
		if ((int) (Math.random() * 100) == 10 || scareCondition) {
			normalCondition = false;
			scareCondition = true;
			return true;
		}
		return false;
	}

}
