package com.bazola.roomsworld;

import java.util.HashMap;
import java.util.Map;

public enum ImageType {
    
    PLAYER_RIGHT_01(0, "playerRight01"),
    PLAYER_RIGHT_02(1, "playerRight02"),
    PLAYER_RIGHT_03(2, "playerRight03"),
    
    PLAYER_LEFT_01(3, "playerLeft01"),
    PLAYER_LEFT_02(4, "playerLeft02"),
    PLAYER_LEFT_03(5, "playerLeft03"),
    
    PLAYER_UP_01(6, "playerUp01"),
    PLAYER_UP_02(7, "playerUp02"),
    PLAYER_UP_03(8, "playerUp03"),
    
    PLAYER_DOWN_01(9, "playerDown01"),
    PLAYER_DOWN_02(10, "playerDown02"),
    PLAYER_DOWN_03(11, "playerDown03");
    
    public final int id;
    public final String path;
    
    public static Map<Integer, ImageType> allTypesAndIds = ImageType.getAllTypesAndIds();

    private ImageType(int id, String path) {
        this.id = id;
        this.path = path;
    }
    
    private static Map<Integer, ImageType> getAllTypesAndIds() {
        Map<Integer, ImageType> types = new HashMap<Integer, ImageType>();
        for (ImageType type : ImageType.values()) {
            types.put(type.id, type);
        }
        return types;
    }
}
