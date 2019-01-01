package com.bazola.roomsworld.gamemodel;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.bazola.roomsworld.GameScreen;

public class RoomWorld {
    
    public Tile[][] level;
    
    public final int tileWidth = 16;
    public final int tileHeight = 16;
    
    public Player player;
    
    private final Pool<Intersector.MinimumTranslationVector> mtvPool = Pools.get(Intersector.MinimumTranslationVector.class, 200);
    
    private ObjectMap<Integer, ExitEvent> exitEvents = new ObjectMap<Integer, ExitEvent>();
    
    private GameScreen screen;
    
    public RoomWorld(GameScreen screen) {
        this.screen = screen;
        
        try {
            this.createWorld("cave1");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        this.player = new Player(250, 425);
    }
    
    public void update(float delta) {
        this.player.update(delta);
        
        this.fixPlayerPosForCollisions();
    }
    
    public void changeRooms(ExitEvent event) {
        this.exitEvents.clear();
        
        try {
            this.createWorld(event.targetRoom);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        float newX = this.tileWidth * event.xPos;
        float newY = this.tileHeight * event.yPos;
        
        this.player.xPos = newX;
        this.player.yPos = newY;
        this.player.hitWall();
        
        this.screen.changedRoom(newX, newY);
    }
    
    public void createWorld(String level) throws IOException {
        int[][] specialTiles = this.createSpecialTiles(level);
        
        String levelString = "rooms/" + level;
        List<String> lines = this.mapLines(levelString);
        int size = lines.size();
        
        this.level = new Tile[size][size];
        
        int yPos = 0;
        for (int i = size - 1; i > 0; i--) {
            String levelLine = lines.get(i);
            levelLine = levelLine.replaceAll("\\s", "");
            String[] fragments = levelLine.split(",");
            
            int position = 0;
            for (String fragment : fragments) {
                int id = Integer.parseInt(fragment);
                TileType type = TileType.allTypesAndIds.get(id);
                Tile tile = new Tile(type, position, yPos, specialTiles[yPos][position]); // TODO use a factory to create tiles
                this.level[yPos][position] = tile;
                
                position++;
            }
            
            yPos++;
        }
        
        this.createEvents(level);
    }
    
    public int[][] createSpecialTiles(String level) throws IOException {
        String specialString = "rooms/" + level + "special";
        List<String> lines = this.mapLines(specialString);
        int size = lines.size();
        
        int[][] specialMap = new int[size][size];
        
        int yPos = 0;
        for (int i = size - 1; i > 0; i--) {
            String levelLine = lines.get(i);
            levelLine = levelLine.replaceAll("\\s", "");
            String[] fragments = levelLine.split(",");
            
            int position = 0;
            for (String fragment : fragments) {
                specialMap[yPos][position] = Integer.parseInt(fragment);
                position++;
            }
            
            yPos++;
        }
        
        return specialMap;
    }
    
    private List<String> mapLines(String levelDataString) throws IOException {
        FileHandle levelData = null;
        levelData = Gdx.files.internal(levelDataString);
        
        List<String> lines = new ArrayList<String>();
        BufferedReader br = new BufferedReader(levelData.reader());
        String line = null;
        while ((line = br.readLine()) != null) {
            lines.add(new String(line));
        }
        
        return lines;
    }
    
    private void createEvents(String level) throws IOException {
        String eventString = "rooms/" + level + "event";
        List<String> lines = this.mapLines(eventString);
        for (String line : lines) {
            line = line.replaceAll("\\s", "");
            String[] fragments = line.split(",");
            if (fragments[0].equals("exit")) {
                this.exitEvents.put(
                        Integer.parseInt(fragments[1]), 
                        new ExitEvent(
                                fragments[2], 
                                Integer.parseInt(fragments[3]), 
                                Integer.parseInt(fragments[4])
                            )
                        );
            }
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
                        
                        if (tile.linkedSpecialTile != 0) {
                            ExitEvent event = this.exitEvents.get(tile.linkedSpecialTile);
                            if (event != null) {
                                this.changeRooms(event);
                            }
                        }
                        
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
