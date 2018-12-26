package com.bazola.roomsworld;

import com.bazola.roomsworld.gamemodel.RoomWorld;

public class PersonDrawer {
    
    private final LibGDXGame libGDXGame;

    private final RoomWorld world;
    
    private final int playerWidth = 16;
    private final int playerHeight = 16;
    
    public PersonDrawer(LibGDXGame libGDXGame, RoomWorld world) {
        this.libGDXGame = libGDXGame;
        this.world = world;
    }
    
    public void draw(float deltaTime) {
        this.drawPlayer(deltaTime);
    }
    
    private void drawPlayer(float delta) {
        // Do this here to prevent any possible exceptions
        if (this.world.player.character == null) {
            this.world.player.character = new AnimatedCharacter(this.libGDXGame.playerAnimations);
        }

        this.libGDXGame.batch.draw(this.world.player.character.getRegionForTime(delta),
                                   this.world.player.xPos,
                                   this.world.player.yPos,
                                   this.playerWidth,
                                   this.playerHeight);
                                   
    }
}
