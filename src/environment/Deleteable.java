package environment;

public interface Deleteable {
	void checkSelfDelete();
	boolean isDead();
	void setDead();
}
