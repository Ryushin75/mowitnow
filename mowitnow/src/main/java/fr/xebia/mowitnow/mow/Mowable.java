package fr.xebia.mowitnow.mow;

/**
 * 
 * @author aymen
 * Interface Mowable define 3 methods that allow an object to move on a surface T
 *
 * @param <T> The surface to move on
 */

public interface Mowable<T> {
	public void turnRight();
	public void turnLeft();
	public void moveForward(T lawn);
}
