package application;

import character.Enemy;
import character.Hero;
import environment.Foreground;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class EventManager {
	private Scene scene;
	private boolean doneMovingLeft;
	private Hero hero;
	private boolean heroWalkOverBase;
	private boolean canWalk;
	public boolean dIsPressed, aIsPressed, isPaused;
	private Thread thread;

	public EventManager(Scene scene, Hero hero) {
		this.scene = scene;
		this.hero = hero;
		doneMovingLeft = true;
		heroWalkOverBase = true;
		canWalk = false;
		dIsPressed = false;
		aIsPressed = false;
		isPaused = false;
	}

	public boolean isCanWalk() {
		return canWalk;
	}

	public void setCanWalk(boolean canWalk) {
		this.canWalk = canWalk;
	}

	public void setPlayerControl() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode() == KeyCode.ENTER) {
		    		MenuPane.running = true;
		    		MenuPane.imageView1.setVisible(false);
		    		MenuPane.imageView1.setDisable(true);
		    		MenuPane.imageView0.setVisible(false);
		    		MenuPane.imageView0.setDisable(true);
		    		
		    	}
			}
		});
		
		
	}

	public boolean isAtTheEndOfScreen() {
		if (hero.getPosX() > 10 && hero.getPosX() < 680) {
			return false;
		}
		return true;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public boolean isHeroWalkOverBase() {
		return heroWalkOverBase;
	}

	public void setHeroWalkOverBase(boolean heroWalkOverBase) {
		this.heroWalkOverBase = heroWalkOverBase;
	}

	public boolean doneMovingLeft() {
		return doneMovingLeft;
	}

	public void keyHandle() {
		if(MenuPane.running) {
			
			scene.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if(!isPaused) {
					thread = new Thread(() -> {
						try {
							while (true) {
								hero.shoot();
								Thread.sleep(hero.getFirerate());
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					});
					thread.start();
					}
				}
			});
			scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					try {
						thread.interrupt();
						thread.join();

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					if (event.getCode() == KeyCode.ESCAPE) {
						if(!isPaused) {
							GameLoop.an.stop();
							MenuPane.imageView2.setVisible(true);
							isPaused = true;
						}
					}
					if (event.getCode() == KeyCode.ENTER) {
						if(isPaused) {
							GameLoop.an.start();
							MenuPane.imageView2.setVisible(false);
							isPaused = false;
						}
					}
					if (event.getCode() == KeyCode.W) {
						hero.setIsLookUp(true);
					}
					else if (event.getCode() == KeyCode.D) {
						dIsPressed = true;
					} else if (event.getCode() == KeyCode.A) {
						aIsPressed = true;
					} else if (event.getCode() == KeyCode.S) {
						hero.setIsLookDown(true);
					} else if (event.getCode() == KeyCode.E) {
						if (!hero.isRequestToEnterTank() && !hero.isInTheTank()) {
							hero.setRequestToEnterTank(true);
							System.out.println("hihi");
						}
					} else if (event.getCode() == KeyCode.B) {
						hero.setThrowingBomb(true);
						hero.throwBomb();
					} else if (event.getCode() == KeyCode.SPACE) {
						hero.Jump();
					}
					
				}
			});
			scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					if (event.getCode() == KeyCode.W) {
						hero.setIsLookUp(false);
					}
					if (event.getCode() == KeyCode.D) {
						dIsPressed = false;
						for (Enemy e : GameEntity.enemies) {
							e.stop();
						}
						for (Foreground fg : GameEntity.fgs) {
							fg.moveScreen(0);
						}

					} else if (event.getCode() == KeyCode.A) {
						aIsPressed = false;
						for (Enemy x : GameEntity.enemies) {
							x.stop();
						}
					} else if (event.getCode() == KeyCode.E) {
						if (hero.isRequestToEnterTank() && !hero.isInTheTank()) {
							hero.setRequestToEnterTank(false);
						}

					} else if (event.getCode() == KeyCode.S) {
						hero.setIsLookDown(false);
					}
				}
			});
		}
		if (!hero.isAlive())
			return;
		if (dIsPressed) {
			hero.setIsWalk(true);
			hero.setWalkDirection(1);
			if (hero.isHasHorizontalCollision()) {
				return;
			}
			if (canWalk) {
				hero.Walk(2);
			} else {
				for (Foreground fg : GameEntity.fgs) {
					fg.moveScreen(hero.getMoveSpeed());
				}
			}
		} else {
			hero.setIsWalk(false);
		}

		if (aIsPressed) {
			hero.setIsWalk(true);
			hero.setWalkDirection(-1);
			if (!isAtTheEndOfScreen()) {
				hero.Walk(-2);
			} else {
				hero.Walk(0);
			}
			doneMovingLeft = false;
		} else {
			hero.Walk(0);
		}
	}

}
