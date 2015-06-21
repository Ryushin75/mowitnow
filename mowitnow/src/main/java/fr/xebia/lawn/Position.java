package fr.xebia.lawn;



/**
 * 
 * @author aymen
 *
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
		return x + " " + y;
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
	public boolean equals(Object obj) {
		if (!(obj instanceof Position))
			return false;
		Position pos = (Position) obj;
		return x == pos.getX() && y == pos.getY();
	}
}
