package com.bazola.roomsworld.gamemodel;

import com.badlogic.gdx.math.Polygon;
import com.bazola.roomsworld.AnimatedCharacter;
import com.bazola.roomsworld.AnimationType;

public class Player {

    public float xPos;
    public float yPos;
    
    private float xSpeed = 0;
    private float ySpeed = 0;
    
    private final float maxSpeed = 2.2f;
    private final float incrementSpeed = 0.12f;
    private final float drag = 0.06f;
    
    public AnimatedCharacter character = null;
    
    public final int width = 16;
    public final int height = 16;
    
    private final int boundingOffset = 3;

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
    
    public void hitWall() {
        this.xSpeed = 0;
        this.ySpeed = 0;
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
    
    //counter clockwise for the vertexes
    public Polygon getBounds() {
        float[] verts = new float[8];
        verts[0] = this.xPos + this.boundingOffset;
        verts[1] = this.yPos + this.boundingOffset;
        
        verts[2] = (this.xPos + this.boundingOffset) + (this.width - this.boundingOffset * 2);
        verts[3] = this.yPos + this.boundingOffset;
        
        verts[4] = (this.xPos + this.boundingOffset) + (this.width - this.boundingOffset * 2);
        verts[5] = (this.yPos + this.boundingOffset) + (this.height - this.boundingOffset * 2);
        
        verts[6] = this.xPos + this.boundingOffset;
        verts[7] = (this.yPos + this.boundingOffset) + (this.height - this.boundingOffset * 2);
    
        return new Polygon(verts);
    }
}
