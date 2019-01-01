package com.bazola.roomsworld.gamemodel;

public class ExitEvent {
    
    public String targetRoom;
    public int xPos;
    public int yPos;

    public ExitEvent(String targetRoom, int xPos, int yPos) {
        this.targetRoom = targetRoom;
        this.xPos = xPos;
        this.yPos = yPos;
    }
}
