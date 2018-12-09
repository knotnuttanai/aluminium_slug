package application;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class ExpBar extends ProgressBar {
	private static int killCount;
	private double maxKillCount;
	public static int level = 1;

	public ExpBar() {
		super(1);
		setPrefWidth(640);
		setPrefHeight(10);
		setTranslateX(0);
		setTranslateY(470);
		setProgress(0);
		Label lebel = new Label("hi");
		getChildren().add(lebel);
		maxKillCount = 3;
	}

	public void update() {
		checkLevelUp();
		setProgress((double) killCount / maxKillCount);
	}

	public void checkLevelUp() {
		if (killCount >= maxKillCount) {
			killCount = 0;
			maxKillCount = maxKillCount * 1.2;
			level++;
			HeroStatusPane.statusPoint++;
		}
	}

	public static void addKillCount(int kills) {
		killCount += kills;
	}
}
