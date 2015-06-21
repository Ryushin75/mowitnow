package fr.xebia.mowitnow.mow;

import fr.xebia.mowitnow.lawn.Lawn;

public interface Mowable {
	public void turnRight();
	public void turnLeft();
	public void moveForward(Lawn lawn);
}
