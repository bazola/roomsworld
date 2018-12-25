package com.bazola.roomsworld;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;

public class AnimationLoader {

    public static Animation<TextureRegion> playerMoveRight(ObjectMap<ImageType, TextureRegion> atlas) {
        TextureRegion[] regions = new TextureRegion[3];
        regions[0] = atlas.get(ImageType.PLAYER_RIGHT_01);
        regions[1] = atlas.get(ImageType.PLAYER_RIGHT_02);
        regions[2] = atlas.get(ImageType.PLAYER_RIGHT_03);
        return new Animation<TextureRegion>(1/10f, regions);
    }
    
    public static Animation<TextureRegion> playerMoveLeft(ObjectMap<ImageType, TextureRegion> atlas) {
        TextureRegion[] regions = new TextureRegion[3];
        regions[0] = atlas.get(ImageType.PLAYER_LEFT_01);
        regions[1] = atlas.get(ImageType.PLAYER_LEFT_02);
        regions[2] = atlas.get(ImageType.PLAYER_LEFT_03);
        return new Animation<TextureRegion>(1/10f, regions);
    }
    
    public static Animation<TextureRegion> playerMoveUp(ObjectMap<ImageType, TextureRegion> atlas) {
        TextureRegion[] regions = new TextureRegion[3];
        regions[0] = atlas.get(ImageType.PLAYER_UP_01);
        regions[1] = atlas.get(ImageType.PLAYER_UP_02);
        regions[2] = atlas.get(ImageType.PLAYER_UP_03);
        return new Animation<TextureRegion>(1/10f, regions);
    }
    
    public static Animation<TextureRegion> playerMoveDown(ObjectMap<ImageType, TextureRegion> atlas) {
        TextureRegion[] regions = new TextureRegion[3];
        regions[0] = atlas.get(ImageType.PLAYER_DOWN_01);
        regions[1] = atlas.get(ImageType.PLAYER_DOWN_02);
        regions[2] = atlas.get(ImageType.PLAYER_DOWN_03);
        return new Animation<TextureRegion>(1/10f, regions);
    }
    
    public static Animation<TextureRegion> none(LibGDXGame libGDXGame) {
        TextureRegion[] regions = new TextureRegion[1];
        regions[0] = new TextureRegion(libGDXGame.alpha);
        return new Animation<TextureRegion>(1/6f, regions);
    }
}
