package fr.xebia.mowitnow.lawn;

import java.util.HashMap;


/**
 * 
 * @author aymen
 * Lawn class represented by an height (yLimit) and a width (yLimit)
 * cellMap represents a grid of all the lawn's cell
 */
public class Lawn {
	private final int xLimit;
	private final int yLimit;
	private final HashMap<Position, LawnCell> cellMap;

	public Lawn(int x, int y) {
		this.xLimit = x;
		this.yLimit = y;

		cellMap = new HashMap<>();
		for (int i = 0; i <= xLimit; i++) {
			for (int j = 0; j <= yLimit; j++) {
				Position p = new Position(i, j);
				cellMap.put(p, new LawnCell(p));
			}
		}
	}

	public int getxLimit() {
		return xLimit;
	}

	public int getyLimit() {
		return yLimit;
	}
	
	public LawnCell getCell(Position position){
		return cellMap.get(position);
	}

	public boolean contains(Position position) {
		return 0 <= position.getX() && position.getX() <= xLimit
				&& 0 <= position.getY() && position.getY() <= yLimit;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cellMap == null) ? 0 : cellMap.hashCode());
		result = prime * result + xLimit;
		result = prime * result + yLimit;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Lawn))
			return false;
		Lawn other = (Lawn) obj;
		if (cellMap == null) {
			if (other.cellMap != null)
				return false;
		} else if (!cellMap.equals(other.cellMap))
			return false;
		if (xLimit != other.xLimit)
			return false;
		if (yLimit != other.yLimit)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lawn [xLimit=" + xLimit + ", yLimit=" + yLimit + ", cellMap="
				+ cellMap + "]";
	}

	public int getGridSize() {
		return cellMap.size();
	}
	
	public HashMap<Position, LawnCell> getGrid(){
		return cellMap;
	}
}
