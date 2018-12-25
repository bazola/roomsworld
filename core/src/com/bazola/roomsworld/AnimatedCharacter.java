package com.bazola.roomsworld;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;

public class AnimatedCharacter {
    
    private float stateTime = 0;
    
    private AnimationType currentAnimation;
    
    private final ObjectMap<AnimationType, Animation<TextureRegion>> animations;
    
    private boolean stopped = false;
    
    public AnimatedCharacter(ObjectMap<AnimationType, Animation<TextureRegion>> animations) {
        this.animations = animations;
        
        this.currentAnimation = AnimationType.PLAYER_DOWN;
    }

    public AnimationType getAnimation() {
        return this.currentAnimation;
    }
    
    public void setAnimation(AnimationType type) {
        if (this.currentAnimation == type) {
            return;
        }
        this.stateTime = 0;
        this.currentAnimation = type;
    }
    
    public void stop() {
        this.stopped = true;
    }
    
    public void start() {
        this.stopped = false;
    }
    
    public TextureRegion getRegionForTime(float delta) {
        
        if (this.stopped) {
            return this.animations.get(this.currentAnimation)
                    .getKeyFrame(0);
        }
        
        return this.animations.get(this.currentAnimation)
                              .getKeyFrame(this.stateTime += delta, this.currentAnimation.shouldLoop);
    }
}