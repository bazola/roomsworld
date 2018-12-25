package com.bazola.roomsworld;

import com.badlogic.gdx.utils.Array;
import com.bazola.roomsworld.gamemodel.RoomWorld;
import com.bazola.roomsworld.gamemodel.Tile;
import com.bazola.roomsworld.gamemodel.TileType;

public class RoomDrawer {

    private final LibGDXGame libGDXGame;
    
    private final RoomWorld world;
    
    private final float tileWidth = 16;
    private final float tileHeight = 16;
    
    private Array<Tile> tiles = new Array<Tile>();
    
    public RoomDrawer(LibGDXGame libGDXGame, RoomWorld world) {
        this.libGDXGame = libGDXGame;
        this.world = world;
    }

    public void draw(float deltaTime) {
        this.sortTiles(); 
        this.drawTiles();      
    }

    private void sortTiles() {
     
        this.tiles.clear();
        
        for (Tile[] row : this.world.level) {
            for (Tile tile : row) {
                if (tile == null ||
                    tile.type == null ||
                    tile.type == TileType.EMPTY) {
                    continue;
                }
                
                this.tiles.add(tile);
            }
        }
    }
    
    private void drawTiles() {
        for (Tile tile : this.tiles) {
            this.drawTile(tile);
        }
    }
    
    private void drawTile(Tile tile) {
        this.libGDXGame.batch.draw(this.libGDXGame.atlasTextures.get(tile.type), 
                tile.x,
                tile.y,
                this.tileWidth,
                this.tileHeight);
    }
}
