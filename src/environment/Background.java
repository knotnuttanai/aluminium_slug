package environment;

public class Background {
	private int bgX;
	private int bgY;
	private int SpeedX;
	private boolean move;

	public Background (int x, int y){
		bgX = x;
		bgY = y;
		SpeedX = 0;
		move = true;
	}
	
	public void update() {
		bgX += SpeedX;
		if (bgX <= -2160){
			SpeedX = 0;
			move = false;
		}
	}

	public int getBgX() {
		return bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public int getSpeedX() {
		return SpeedX;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public void setSpeedX(int speedX) {
		this.SpeedX = speedX;
	}
	
	public boolean getMove() {
		return move;
	}

}
