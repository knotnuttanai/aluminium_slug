package character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class HandSoldier extends Enemy {
	private Image[] normal;
	private Image[] scare;
	private Image[] run;
	private int normalFrame;
	private int scareFrame;
	private int runFrame;
	private boolean normalCondition;
	private boolean scareCondition;
	private boolean runCondition;

	public HandSoldier(double posX, double posY, int health) {
		super(posX, posY, health);
		normal = new Image[12];
		for(int i = 1; i <= 12; i++) {
			normal[i-1] = new Image("file:res/images/normalsoldier" + i + ".png");
		}
		normalFrame = 0;
		normalCondition = true;
		
		scare = new Image[6];
		for(int i = 1; i <= 6; i++) {
			scare[i-1] = new Image("file:res/images/soldierscare" + i + ".png");
		}
		scareFrame = 0;
		scareCondition = false;
		
		run  = new Image[11];
		for(int i = 3; i <= 13; i++) {
			run[i-3] = new Image("file:res/images/soldierrun" + i + ".png");
		}
		runFrame = 0;
		runCondition = false;
		
	}
	
	@Override
	public void render(GraphicsContext gc) {
		if(normalCondition) {
			gc.drawImage(normal[normalFrame/3], posX, posY);
			normalFrame++;
			if(normalFrame == 36) normalFrame = 0;
		}
		
		if(runCondition) {
			gc.drawImage(run[runFrame/3], posX, posY);
			runFrame++;
			if(runFrame == 33) runFrame = 0;
		}
	
		else if(CheckScareCondition()) {
			if(scareFrame >= 83)  gc.drawImage(scare[5], posX, posY);
			else if(scareFrame >= 80) gc.drawImage(scare[4], posX, posY);
			else gc.drawImage(scare[scareFrame/20], posX, posY);
			scareFrame++;
			if(scareFrame < 80) {
				veloX = 0;
				walkDirection = 0;
				baseVeloX = veloX;
			}
			if(scareFrame == 86) {
				runCondition = true;
				veloX = 2;
				walkDirection = 1;
				baseVeloX = veloX;
				
			}
		}
		
	}
	
	private boolean CheckScareCondition() {
		if((int)(Math.random()*100) == 10 || scareCondition) {
			normalCondition = false;
			scareCondition = true;
			return true;
		}
		return false;
	}
	

}