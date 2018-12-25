package com.bazola.roomsworld;

public enum AnimationType {
    
    NONE(false),
    PLAYER_RIGHT(true),
    PLAYER_LEFT(true),
    PLAYER_UP(true),
    PLAYER_DOWN(true);
    
    public final boolean shouldLoop;
    
    private AnimationType(boolean shouldLoop) {
        this.shouldLoop = shouldLoop;
    }
}