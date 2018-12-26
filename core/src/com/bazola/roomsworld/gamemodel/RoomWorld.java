package com.bazola.roomsworld.gamemodel;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class RoomWorld {
    
    public Tile[][] level;
    
    public Player player;
    
    private final Pool<Intersector.MinimumTranslationVector> mtvPool = Pools.get(Intersector.MinimumTranslationVector.class, 200);
    
    public RoomWorld(int levelNumber) {
        try {
            this.createWorld(levelNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        this.player = new Player(250, 425);
    }
    
    public void update(float delta) {
        this.player.update(delta);
        
        this.fixPlayerPosForCollisions();
    }
    
    public void createWorld(int levelNumber) throws IOException {
        FileHandle levelData = null;
        String levelDataString = "rooms/cave" + String.valueOf(levelNumber);
        levelData = Gdx.files.internal(levelDataString);
        
        List<String> lines = new ArrayList<String>();
        BufferedReader br = new BufferedReader(levelData.reader());
        String line = null;
        while ((line = br.readLine()) != null) {
            lines.add(new String(line));
        }
        int size = lines.size();
        
        this.level = new Tile[size][size]; // TODO figure out the width and height beforehand so it does not have to be square
        
        int yPos = 0;
        for (int i = size - 1; i > 0; i--) {
            String levelLine = lines.get(i);
            levelLine = levelLine.replaceAll("\\s", "");
            String[] fragments = levelLine.split(",");
            
            int position = 0;
            for (String fragment : fragments) {
                int id = Integer.parseInt(fragment);
                TileType type = TileType.allTypesAndIds.get(id);
                Tile tile = new Tile(type, position, yPos); // TODO use a factory to create tiles
                this.level[yPos][position] = tile;
                
                position++;
            }
            
            yPos++;
        }
    }
    
    private void fixPlayerPosForCollisions() {
        Polygon playerBounds = this.player.getBounds();
            
        for (Tile[] row : this.level) {
            for (Tile tile : row) {
                if (tile == null ||
                    tile.type == TileType.EMPTY) {
                    continue;
                }
                
                Intersector.MinimumTranslationVector mtv = this.mtvPool.obtain();
                if (Intersector.overlapConvexPolygons(tile.bounds, playerBounds, mtv)) {
                    if (mtv.depth > 0) {
                        if (TileType.solid.contains(tile.type)) {
                            float xDiff = mtv.depth * mtv.normal.x;
                            float yDiff = mtv.depth * mtv.normal.y;
                            
                            this.player.xPos = this.player.xPos - xDiff;
                            this.player.yPos = this.player.yPos - yDiff;
                            
                            if (Math.abs(xDiff) > 0 ||
                                Math.abs(yDiff) > 0) {
                                this.player.hitWall();
                            }
                        }
                    }
                }
                this.mtvPool.free(mtv);       
            }
        }
    }
}
