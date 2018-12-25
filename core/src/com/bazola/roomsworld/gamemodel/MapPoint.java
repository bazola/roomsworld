package com.bazola.roomsworld.gamemodel;

public class MapPoint {
	
	public int x;
	public int y;
	
	public MapPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public MapPoint addPoint(MapPoint point) {
	    //Since these are added into an array, a new object is useful
		return new MapPoint(this.x + point.x, this.y + point.y);
	}
	
	public void flipPoint(int width, int height) {
		this.x = width - this.x;
		this.y = height - this.y;
	}
	
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
}
