package com.bazola.roomsworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
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
        this.libGDXGame.personDrawer = new PersonDrawer(this.libGDXGame, this.world);
    }
    
    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Keys.W)) {
            this.world.player.moveUp();
        }
        if (Gdx.input.isKeyPressed(Keys.A)) {
            this.world.player.moveLeft();
        }
        if (Gdx.input.isKeyPressed(Keys.S)) {
            this.world.player.moveDown();
        }
        if (Gdx.input.isKeyPressed(Keys.D)) {
            this.world.player.moveRight();
        }
        
        this.world.update(delta);
        
        this.libGDXGame.camera.updatePosition(delta);
        this.libGDXGame.camera.moveToPosition(this.world.player.getXPos(), this.world.player.getYPos());
    }
}
