package com.bazola.roomsworld.gamemodel;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class RoomWorld {
    
    public Tile[][] level;
    
    public RoomWorld(int levelNumber) {
        try {
            this.createWorld(levelNumber);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void update(float delta) {
        //update player here
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
}
