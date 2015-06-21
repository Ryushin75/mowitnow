package fr.xebia.lawn;


public class Lawn {
	private final int xLimit;
	private final int yLimit;

	private final LawnCell[][] grid;

	public Lawn(int x, int y) {
		this.xLimit = x;
		this.yLimit = y;

		grid = new LawnCell[xLimit][yLimit];
		for (int i = 0; i < xLimit; i++) {
			for (int j = 0; j < yLimit; j++) {
				grid[i][j] = new LawnCell(i, j);
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
		return grid[position.getX()][position.getY()];
	}

	public boolean contains(Position position) {
		return 0 <= position.getX() && position.getX() <= xLimit
				&& 0 <= position.getY() && position.getY() <= yLimit;
	}
}
