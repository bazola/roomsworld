package com.bazola.roomsworld.gamemodel;

import com.badlogic.gdx.math.Polygon;

public class Tile {
    
    public final TileType type;
    public final float x;
    public final float y;
    
    public final static int WIDTH = 16;
    public final static int HEIGHT = 16;
    
    public final MapPoint position;
    
    public Polygon bounds;
    
    public Tile(TileType type, int x, int y) {
        
        this.position = new MapPoint(x, y);
        
        this.type = type;
        
        this.x = x * WIDTH;
        this.y = y * HEIGHT;
        
        this.bounds = this.getBounds();
    }
    
    protected Polygon getBounds() {
        //counter clockwise for the verts
        
        float[] verts = new float[8];
        verts[0] = this.x;
        verts[1] = this.y;
        
        verts[2] = this.x + WIDTH;
        verts[3] = this.y;
        
        verts[4] = this.x + WIDTH;
        verts[5] = this.y + HEIGHT;
        
        verts[6] = this.x;
        verts[7] = this.y + HEIGHT;
    
        /*
        if (this.lastPolygonCreated != null) {
            this.polygonPool.free(this.lastPolygonCreated);
        }
        Polygon newPolygon = this.polygonPool.obtain();
        newPolygon.setVertices(verts);
        this.lastPolygonCreated = newPolygon;
        */
        return new Polygon(verts);
    }
}
