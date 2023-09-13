package com.bazola.roomsworld;

public class attack {
    public float xPos;
    public float yPos;

    public int rotation;

    public float DMG;

    public AnimatedCharacter character = null;

    public attack(float xPos, float yPos, int rotation, float DMG) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rotation = rotation;
        this.DMG = DMG;
    }

    public void update(float delta) {
        
    }
}
