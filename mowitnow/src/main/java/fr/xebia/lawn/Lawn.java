package fr.xebia.lawn;

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
	public boolean equals(Object obj) {
		if(!(obj instanceof Lawn ))
    		return false;
		Lawn lawn = (Lawn) obj;
		return xLimit == lawn.getxLimit() && yLimit == lawn.getyLimit();
	}

	public int getGridSize() {
		return cellMap.size();
	}
	
	public HashMap<Position, LawnCell> getGrid(){
		return cellMap;
	}
}
