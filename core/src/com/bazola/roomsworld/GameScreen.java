package com.bazola.roomsworld;

import com.badlogic.gdx.ScreenAdapter;
import com.bazola.roomsworld.gamemodel.RoomWorld;

public class GameScreen extends ScreenAdapter {
    
    private final LibGDXGame libGDXGame;
    
    private RoomWorld world;
    
    public GameScreen(LibGDXGame libGDXGame) {
        this.libGDXGame = libGDXGame;
        
        this.createWorld();
    }
    
    private void createWorld() {
        this.world = new RoomWorld(1);
        this.libGDXGame.drawer = new RoomDrawer(this.libGDXGame, this.world);
    }
}
