package fr.xebia.mowitnow.lawn;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isMowed ? 1231 : 1237);
		result = prime * result + (occupied ? 1231 : 1237);
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LawnCell))
			return false;
		LawnCell other = (LawnCell) obj;
		if (isMowed != other.isMowed)
			return false;
		if (occupied != other.occupied)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	@Override
	public String toString() {
		return position.toString() + " isMowed = " + isMowed +" isOccupied = "+ isOccupied();
	}
}
