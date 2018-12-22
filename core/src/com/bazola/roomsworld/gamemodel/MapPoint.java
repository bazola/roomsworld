package com.bazola.roomsworld.gamemodel;

public class MapPoint {
	
	public int x;
	public int y;
	
	//private Array<MapPoint> neighborPoints;
	//private Array<MapPoint> adjacentPoints;
	
	//private final boolean isBigWorldPoint;
	
	public MapPoint(int x, int y) {
		//this.isBigWorldPoint = isBigWorldPoint;
		
		this.x = x;
		this.y = y;
		/*
		if (precalculateNeighbors) {
			this.neighborPoints = this.getNeighborPoints();
			this.adjacentPoints = this.getAdjacentPoints();
		} else {
			this.neighborPoints = null;
			this.adjacentPoints = null;
		}
		*/
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
		//this.neighborPoints = null;
		//this.adjacentPoints = null;
	}
	
	/*
	public void move(MapDirection direction, int bounds) {
	    this.x += direction.getX();
	    this.y += direction.getY();
	    
	    this.neighborPoints = null;
		this.adjacentPoints = null;
		
	    if (this.x < 0) {
	    	this.x = 0;
	    } else if (this.x > bounds) {
	    	this.x = bounds;
	    }
	    if (this.y < 0) {
	    	this.y = 0;
 	    } else if (this.y > bounds) {
 	    	this.y = bounds;
 	    }
	}
	*/
	
	/*
	public void randomMoveTowards(MapDirection direction, int bounds, Random random) {
		boolean randomBool = random.nextBoolean();
		switch (direction) {
		case LEFT:
		case RIGHT:
			if (randomBool) {
				this.move(MapDirection.UP, bounds);
			} else {
				this.move(MapDirection.DOWN, bounds);
			}
			break;
		case UP:
		case DOWN:
			if (randomBool) {
				this.move(MapDirection.LEFT, bounds);
			} else {
				this.move(MapDirection.RIGHT, bounds);
			}
			break;
		}
	}
	*/
	
	/*
	private Array<MapPoint> calculateAdjacentPoints() {
		Array<MapPoint> adjacentPoints = new Array<MapPoint>();
		for (int i = this.x - 1; i <= this.x + 1; i++) {
			for (int j = this.y - 1; j <= this.y + 1; j++) {
				MapPoint newPoint = new MapPoint(i, j, false, this.isBigWorldPoint);
				
				//need to do this because each world has a different size
				if (this.isBigWorldPoint) {
					if (this.isInsideGridBigWorld(i, j) &&
							!newPoint.equals(this)) {
							adjacentPoints.add(newPoint);
						}
				} else {
					if (this.isInsideGrid(i, j) &&
							!newPoint.equals(this)) {
							adjacentPoints.add(newPoint);
						}
				}
			}
		}
		return adjacentPoints;
	}
	*/
	
	/*
	private Array<MapPoint> calculateNeighborPoints() {
		Array<MapPoint> neighborPoints = new Array<MapPoint>();
		for (MapDirection direction : MapDirection.values()) {
			MapPoint newPoint = new MapPoint(this.x + direction.getX(), this.y + direction.getY(), false, this.isBigWorldPoint);
			
			//need to do this because each world has a different size
			if (this.isBigWorldPoint) {
				if (this.isInsideGridBigWorld(newPoint.x, newPoint.y)) {
					neighborPoints.add(newPoint);
				}
			} else {
				if (this.isInsideGrid(newPoint.x, newPoint.y)) {
					neighborPoints.add(newPoint);
				}
			}
		}
		return neighborPoints;
	}
	*/
	
	/*
	//ONLY used for precalculating things
	//to increase performance of flood fill algorithm
    private boolean isInsideGrid (int x, int y) {
    	return !(x < 0 || 
    			 y < 0 || 
    			 x >= TileWorld.WIDTH || 
    			 y >= TileWorld.HEIGHT);
    }
    
	//ONLY used for precalculating things
	//to increase performance of flood fill algorithm
    private boolean isInsideGridBigWorld (int x, int y) {
    	return !(x < 0 || 
    			 y < 0 || 
    			 x >= BigWorldTileWorld.WIDTH || 
    			 y >= BigWorldTileWorld.HEIGHT);
    }
	*/
	
	//Since these are added into an array, a new object is useful
	public MapPoint addPoint(MapPoint point) {
		//this.x += point.x;
		//this.y += point.y;
		//this.adjacentPoints = null;
		//this.neighborPoints = null;
		return new MapPoint(this.x + point.x, this.y + point.y);
	}
	
	public void flipPoint(int width, int height) {
		this.x = width - this.x;
		this.y = height - this.y;
		//return new MapPoint(width - this.x, height - this.y);
	}
	
	/*
	public Array<MapPoint> getNeighborPoints() {
		if (this.neighborPoints == null) {
			//System.out.println("creating neighbor points");
			//System.out.println(this.toString());
			this.neighborPoints = this.calculateNeighborPoints();
		}
		return this.neighborPoints;
	}
	*/
	
	/*
	public Array<MapPoint> getAdjacentPoints() {
		if (this.adjacentPoints == null) {
			//System.out.println("creating adjacent points");
			//System.out.println(this.toString());
			this.adjacentPoints = this.calculateAdjacentPoints();
		}
		return this.adjacentPoints;
	}
	*/
	
	@Override
	public String toString() {
		return "x = " + String.valueOf(this.x) + " y = " + String.valueOf(this.y);
	}
	
	//hashcode and equals from libgdx GridPoint2
	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || o.getClass() != this.getClass()) return false;
		MapPoint g = (MapPoint)o;
		return this.x == g.x && this.y == g.y;
	}
	@Override
	public int hashCode () {
		final int prime = 53;
		int result = 1;
		result = prime * result + this.x;
		result = prime * result + this.y;
		return result;
	}
	
	/*
	@Override
	public int hashCode() {
	    int hash = 7;
	    hash = 71 * hash + this.x;
	    hash = 71 * hash + this.y;
	    return hash;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (o == null) {
	        return false;
	    }
	    if (!(o instanceof MapPoint)) {
	        return false;
	    }
	    return (x == ((MapPoint) o).x && y == ((MapPoint) o).y);
	}
	*/
}
