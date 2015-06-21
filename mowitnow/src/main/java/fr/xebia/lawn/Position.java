package fr.xebia.lawn;



/**
 * 
 * @author aymen
 *	Position class with two coordinates x and y
 */

public class Position {
	private int x;
	private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "["+x + " " + y+"]";
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Position moveEast(){
		return new Position(x+1, y);
	}
	public Position moveWest(){
		return new Position(x-1, y);
	}
	
	public Position moveNorth(){
		return new Position(x,y+1);
	}
	public Position moveSouth(){
		return new Position(x,y-1);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Position))
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
