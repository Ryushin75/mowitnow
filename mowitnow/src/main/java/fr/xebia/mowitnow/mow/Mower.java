package fr.xebia.mowitnow.mow;

import java.util.Objects;
import java.util.Observable;
import java.util.Queue;

import org.apache.log4j.Logger;

import fr.xebia.lawn.Lawn;
import fr.xebia.lawn.Position;
import fr.xebia.mowitnow.enumerations.Command;
import fr.xebia.mowitnow.enumerations.Orientation;

public class Mower extends Observable {

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

	public void turnRight() {
		orientation = orientation.getRightOrientation();
		changeAndNotify();
	}

	public void turnLeft() {
		orientation = orientation.getLeftOrientation();
		changeAndNotify();
	}

	private void changeAndNotify() {
		setChanged();
		notifyObservers();
	}

	private void moveForward(Lawn lawn) {
		Position position = getNextPosition();
		if (lawn.contains(position) && !lawn.getCell(position).isOccupied()) {
			lawn.getCell(position).setOccupied(false);
			this.position = position;
			mow(lawn);
			changeAndNotify();
		}
	}

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

	private void mow(Lawn lawn) {
		lawn.getCell(this.position).setMowed(true);
		lawn.getCell(position).setOccupied(true);
	}

	public void startMowing(Lawn lawn, Queue<Command> right) {
		Objects.requireNonNull(right);
		Objects.requireNonNull(lawn);
		LOG.debug("Starting Mowing programmation...");
		mow(lawn);
		for (Command cmd : right) {
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
		LOG.debug("Mowing programmation ended, Mower with id "+id+" is now at position : "+ toString());
	}

	@Override
	public String toString() {
		return position.toString() + " " + orientation;
	}

	public Position getPosition() {
		return position;
	}
}
