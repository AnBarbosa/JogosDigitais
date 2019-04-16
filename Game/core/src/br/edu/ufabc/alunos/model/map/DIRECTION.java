package br.edu.ufabc.alunos.model.map;

public enum DIRECTION {
	NORTH(0,1),
	SOUTH(0,-1),
	EAST(1,0),
	WEST(-1,0);
	
	private int x, y;
	
	private DIRECTION(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public static DIRECTION getOpposite(DIRECTION dir) {
		switch(dir) {
		case NORTH:
			return SOUTH;
		case SOUTH:
			return NORTH;
		case EAST:
			return WEST;
		case WEST:
			return EAST;
		}
		return null;
	}
	
	
}
