package com.bazola.roomsworld.gamemodel;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.ObjectSet;

public enum TileType {
    
    EMPTY(0, null),
    
    TOP_LEFT_CORNER_GREY_WALL(18, "tile017"),
    GREY_WALL(19, "tile018"),
    TOP_RIGHT_CORNER_GREY_WALL(20, "tile019"),
    
    BOTTOM_LEFT_CORNER_GREY_WALL(76, "tile075"),
    
    BOTTOM_RIGHT_CORNER_GREY_WALL(78, "tile077"),
    
    CRACKED_GREY_WALL(134, "tile133"),
    
    BLUE_WATER_TOP_LEFT_CORNER(291, "tile290"),
    BLUE_WATER_TOP(292, "tile291"),
    BLUE_WATER_TOP_RIGHT_CORNER(293, "tile292"),
    
    DIRT_WITH_ORE(303, "tile302"),
    
    BLUE_WATER_LEFT(320, "tile319"),
    BLUE_WATER(321, "tile320"),
    BLUE_WATER_RIGHT(322, "tile321"),
    BLUE_WATER_CORNER_BOTTOM_LEFT(323, "tile322"),
    BLUE_WATER_CORNER_BOTTOM_RIGHT(324, "tile323"),
    
    BLUE_WATER_BOTTOM_RIGHT_CORNER(351, "tile350"),
    
    BLUE_WATER_BOTTOM_LEFT_CORNER(378, "tile377"),
    BLUE_WATER_BOTTOM(379, "tile378"),
    
    YELLOW_FLOOR_01(423, "tile422"),
    
    YELLOW_FLOOR_02(425, "tile424"),
    
    YELLOW_CHECKERED_FLOOR(453, "tile452"),
    YELLOW_CHECKERED_FLOOR_BIG(454, "tile453"),
    
    YELLOW_CHECKERED_FLOOR_FANCY(456, "tile455");
    
    public final int id;
    public final String path;
    
    public static Map<Integer, TileType> allTypesAndIds = TileType.getAllTypesAndIds();
    //public static ObjectSet<TileType> solid = TileType.getSolidTiles();
    public static ObjectSet<TileType> passable = TileType.getPassableTiles();
    
    private TileType(int id, String path) {
        this.id = id;
        this.path = path;
    }
    
    private static Map<Integer, TileType> getAllTypesAndIds() {
        Map<Integer, TileType> types = new HashMap<Integer, TileType>();
        for (TileType type : TileType.values()) {
            types.put(type.id, type);
        }
        return types;
    }
    
    private static ObjectSet<TileType> getPassableTiles() {
        ObjectSet<TileType> tiles = new ObjectSet<TileType>();
        tiles.add(YELLOW_FLOOR_01);
        tiles.add(YELLOW_FLOOR_02);
        tiles.add(YELLOW_CHECKERED_FLOOR);
        tiles.add(YELLOW_CHECKERED_FLOOR_BIG);
        tiles.add(YELLOW_CHECKERED_FLOOR_FANCY);
        return tiles;
    }
    
    /*
    private static ObjectSet<TileType> getSolidTiles() {
        ObjectSet<TileType> tiles = new ObjectSet<TileType>();
        tiles.add(CRACKED_GREY_WALL);
        return tiles;
    }
    */
}
