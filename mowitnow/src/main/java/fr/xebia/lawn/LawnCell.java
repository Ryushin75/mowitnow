package fr.xebia.lawn;

/**
 * 
 * @author aymen
 * A lawn cell represented by three attributes
 * it's position in the lawn
 * a boolean if this cell was lawned
 * a boolean if this cell is occupied by a mower 
 */

public class LawnCell {
	private final Position position;
	private boolean isMowed;
	private boolean occupied;
	
	public LawnCell(int x, int y) {
		this.position = new Position(x, y);
		this.isMowed = false;
		this.setOccupied(false);
	}

	public LawnCell(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public void setMowed(boolean mowed) {
		this.isMowed = mowed;
	}
	
	public boolean isMowed(){
		return this.isMowed;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	@Override
	public String toString() {
		return position.toString() + " isMowed = " + isMowed +" isOccupied = "+ isOccupied();
	}
}
