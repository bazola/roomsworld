package com.bazola.roomsworld.gamemodel;

public class Tile {
    
    public final TileType type;
    public final float x;
    public final float y;
    
    public final static int WIDTH = 16;
    public final static int HEIGHT = 16;
    
    public final MapPoint position;
    
    public Tile(TileType type, int x, int y) {
        
        this.position = new MapPoint(x, y);
        
        this.type = type;
        
        this.x = x * WIDTH;
        this.y = y * HEIGHT;
    }
}
