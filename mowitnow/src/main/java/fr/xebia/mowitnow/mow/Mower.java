package fr.xebia.mowitnow.mow;

import java.util.Objects;
import java.util.Observable;
import java.util.Queue;

import org.apache.log4j.Logger;

import fr.xebia.mowitnow.enumerations.Command;
import fr.xebia.mowitnow.enumerations.Orientation;
import fr.xebia.mowitnow.lawn.Lawn;
import fr.xebia.mowitnow.lawn.Position;

/**
 * 
 * @author aymen
 * Mower class contains all the logic to move the mower and mow the lawn
 */
public class Mower extends Observable implements Mowable {

	private static final Logger LOG = Logger.getLogger(Mower.class);

	private final int id;
	private Position position;
	private Orientation orientation;

	public Mower(int id, Position position, Orientation orientation) {
		this.id = id;
		this.position = position;
		this.orientation = orientation;
	}

	public Mower(Position position, Orientation orientation) {
		this(0, position, orientation);
	}

	public int getId() {
		return id;
	}

	/**
	 * Change the mower's orientation to its right 
	 */
	public void turnRight() {
		orientation = orientation.getRightOrientation();
		changeAndNotify();
	}

	/**
	 * Change the mower's orientation to its left 
	 */
	public void turnLeft() {
		orientation = orientation.getLeftOrientation();
		changeAndNotify();
	}
	/**
	 * Notity all the observers
	 */
	private void changeAndNotify() {
		setChanged();
		notifyObservers();
	}

	/**
	 * Advance the mower one cell forward depending on its orientation
	 */
	public void moveForward(Lawn lawn) {
		Position position = getNextPosition();
		if (lawn.contains(position) && !lawn.getCell(position).isOccupied()) {
			lawn.getCell(this.position).setOccupied(false);
			this.position = position;
			mow(lawn);
			changeAndNotify();
		}
	}

	/**
	 * 
	 * @return Position : the next position to be reached if accessible
	 */
	private Position getNextPosition() {
		Position position = null;
		switch (orientation) {
		case East:
			position = this.position.moveEast();
			break;
		case North:
			position = this.position.moveNorth();
			break;
		case South:
			position = this.position.moveSouth();
			break;
		case West:
			position = this.position.moveWest();
			break;
		default:
			throw new IllegalArgumentException("Unvalid argument");
		}
		return position;
	}

	/**
	 * Mow the current position
	 * @param lawn : The lawn to mow
	 */
	private void mow(Lawn lawn) {
		lawn.getCell(this.position).setMowed(true);
		lawn.getCell(position).setOccupied(true);
	}

	/**
	 * Start executing the commands list
	 * @param lawn : Lawn to move on
	 * @param commands : List of commands to be executed
	 */
	
	public void startMowing(Lawn lawn, Queue<Command> commands) {
		Objects.requireNonNull(commands);
		Objects.requireNonNull(lawn);
		LOG.debug("Starting Mowing programmation...");
		mow(lawn);
		for (Command cmd : commands) {
			switch (cmd) {
			case Avance:
				moveForward(lawn);
				break;
			case Droite:
				turnRight();
				break;
			case Gauche:
				turnLeft();
				break;
			default:
				throw new IllegalArgumentException("Unvalid argument" + cmd);
			}
		}
		LOG.debug("Mowing programmation ended, Mower with id " + id
				+ " is now at position : " + toString());
	}

	@Override
	public String toString() {
		return position.toString() + " " + orientation;
	}

	public Position getPosition() {
		return position;
	}
	public Orientation getOrientation() {
		return orientation;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Mower))
			return false;
		Mower mow = (Mower) obj;
		return id == mow.getId() && position.equals(mow.getPosition())
				&& orientation.equals(mow.getOrientation());
	}
}
