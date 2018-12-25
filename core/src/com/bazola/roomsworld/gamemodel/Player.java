package com.bazola.roomsworld.gamemodel;

import com.bazola.roomsworld.AnimatedCharacter;
import com.bazola.roomsworld.AnimationType;

public class Player {

    private float xPos;
    private float yPos;
    
    private float xSpeed = 0;
    private float ySpeed = 0;
    
    private final float maxSpeed = 2.2f;
    private final float incrementSpeed = 0.12f;
    private final float drag = 0.06f;
    
    public AnimatedCharacter character = null;

    public Player(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    public void moveRight() {
        this.xSpeed = Math.min(this.xSpeed + this.incrementSpeed, this.maxSpeed);
        
        this.character.setAnimation(AnimationType.PLAYER_RIGHT);
    }
    
    public void moveLeft() {
        this.xSpeed = Math.max(this.xSpeed - this.incrementSpeed, -this.maxSpeed);
        
        this.character.setAnimation(AnimationType.PLAYER_LEFT);
    }
    
    public void moveUp() {
        this.ySpeed = Math.min(this.ySpeed + this.incrementSpeed, this.maxSpeed);
        
        this.character.setAnimation(AnimationType.PLAYER_UP);
    }
    
    public void moveDown() {
        this.ySpeed = Math.max(this.ySpeed - this.incrementSpeed, -this.maxSpeed);
        
        this.character.setAnimation(AnimationType.PLAYER_DOWN);
    }
    
    public void update(float delta) {
        this.xPos += this.xSpeed;
        this.yPos += this.ySpeed;
        
        if (this.xSpeed > 0) {
            this.xSpeed = Math.max(this.xSpeed - this.drag, 0);
        } else {
            this.xSpeed = Math.min(this.xSpeed + this.drag, 0);
        }
        
        if (this.ySpeed > 0) {
            this.ySpeed = Math.max(this.ySpeed - this.drag, 0);
        } else {
            this.ySpeed = Math.min(this.ySpeed + this.drag, 0);
        }
        
        if (this.character != null) {
            if (this.xSpeed == 0 &&
                this.ySpeed == 0) {
                this.character.stop();  
            } else {
                this.character.start();
            }
        }
    }
    
    public float getXPos() {
        return this.xPos;
    }
    
    public float getYPos() {
        return this.yPos;
    }
}
