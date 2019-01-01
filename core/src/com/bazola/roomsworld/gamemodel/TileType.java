package com.bazola.roomsworld.gamemodel;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.ObjectSet;

public enum TileType {
    
    EMPTY(0, null),
    
    STONE_WALL_END_LEFT(10, "tile009"),
    STONE_WALL_LEFT_RIGHT(11, "tile010"),
    STONE_WALL_END_RIGHT(12, "tile011"),
    TOP_LEFT_CORNER_STONE_WALL(13, "tile012"),
    TOP_RIGHT_CORNER_STONE_WALL(14, "tile013"),
    
    STONE_WALL_LEFT_RIGHT_UP(16, "tile015"),
    
    TOP_LEFT_CORNER_GREY_WALL(18, "tile017"),
    GREY_WALL(19, "tile018"),
    TOP_RIGHT_CORNER_GREY_WALL(20, "tile019"),
    
    STONE_WALL_BOTTOM_END(38, "tile037"),
    STONE_WALL_SINGLE_TOP(39, "tile038"),
    
    STONE_WALL_UP_DOWN(41, "tile040"),
    BOTTOM_LEFT_CORNER_STONE_WALL(42, "tile041"),
    
    STONE_WALL_LEFT_EDGE(67, "tile066"),
    STONE_FLOOR(68, "tile067"),
    STONE_WALL_RIGHT_EDGE(69, "tile068"),
    STONE_WALL_SINGLE(70, "tile069"),
    WOOD_SUPPORT_LEFT(71, "tile070"),
    WOOD_SUPPORT_MIDDLE(72, "tile071"),
    WOOD_SUPPORT_LEFT_RIGHT(73, "tile072"),
    WOOD_SUPPORT_RIGHT(74, "tile073"),
    
    BOTTOM_LEFT_CORNER_GREY_WALL(76, "tile075"),
    
    BOTTOM_RIGHT_CORNER_GREY_WALL(78, "tile077"),
    
    SMALL_CRACK_WALL(98, "tile097"),
    BIG_CRACK_WALL(99, "tile098"),
    
    STONE_WALL_BOTTOM_LEFT(125, "tile124"),
    STONE_WALL_BOTTOM_LEFT_RIGHT(126, "tile125"),
    STONE_WALL_BOTTOM_RIGHT(127, "tile126"),
    
    STONE_WALL_BOTTOM_MIDDLE(130, "tile129"),
    
    CRACKED_GREY_WALL(134, "tile133"),
    
    PILLAR_TOP(146, "tile145"),
    
    DIRT_WALL_TOP_END(154, "tile153"),
    DIRT_WALL_LEFT_END(155, "tile154"),
    DIRT_WALL_LEFT_RIGHT(156, "tile155"),
    
    DIRT_WALL_TOP_RIGHT_CORNER(159, "tile158"),
    
    PILLAR_BOTTOM(177, "tile176"),
    
    DIRT_WALL_UP_DOWN(186, "tile185"),
    DIRT_WALL_BOTTOM_LEFT_CORNER(187, "tile186"),
    DIRT_WALL_BOTTOM_RIGHT_CORNER(188, "tile187"),
    
    DIRT_WALL_WOOD_LEFT_CORNER(216, "tile215"),
    DIRT_WALL_WOOD_MIDDLE(217, "tile216"),
    DIRT_WALL_WOOD_RIGHT_CORNER(219, "tile218"),
    
    CAGE_FULL(262, "tile261"),
    
    DIRT_WALL_WOOD_LEFT(274, "tile273"),
    
    DIRT_WALL_WOOD_RIGHT(277, "tile276"),
    
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
    
    YELLOW_CHECKERED_FLOOR_FOUR(452, "tile451"),
    YELLOW_CHECKERED_FLOOR(453, "tile452"),
    YELLOW_CHECKERED_FLOOR_BIG(454, "tile453"),
    
    YELLOW_CHECKERED_FLOOR_FANCY(456, "tile455");
    
    public final int id;
    public final String path;
    
    public static Map<Integer, TileType> allTypesAndIds = TileType.getAllTypesAndIds();
    public static ObjectSet<TileType> solid = TileType.getSolidTiles();
    
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
    
    private static ObjectSet<TileType> getSolidTiles() {
        ObjectSet<TileType> tiles = new ObjectSet<TileType>();
        tiles.add(TOP_LEFT_CORNER_GREY_WALL);
        tiles.add(GREY_WALL);
        tiles.add(TOP_RIGHT_CORNER_GREY_WALL);
        tiles.add(BOTTOM_LEFT_CORNER_GREY_WALL);
        tiles.add(BOTTOM_RIGHT_CORNER_GREY_WALL);
        tiles.add(CRACKED_GREY_WALL);
        tiles.add(BLUE_WATER_TOP_LEFT_CORNER);
        tiles.add(BLUE_WATER_TOP);
        tiles.add(BLUE_WATER_TOP_RIGHT_CORNER);
        tiles.add(BLUE_WATER_LEFT);
        tiles.add(BLUE_WATER);
        tiles.add(BLUE_WATER_RIGHT);
        tiles.add(BLUE_WATER_CORNER_BOTTOM_LEFT);
        tiles.add(BLUE_WATER_CORNER_BOTTOM_RIGHT);
        tiles.add(BLUE_WATER_BOTTOM_RIGHT_CORNER);
        tiles.add(BLUE_WATER_BOTTOM_LEFT_CORNER);
        tiles.add(BLUE_WATER_BOTTOM);
        tiles.add(STONE_WALL_LEFT_RIGHT);
        tiles.add(TOP_LEFT_CORNER_STONE_WALL);
        tiles.add(TOP_RIGHT_CORNER_STONE_WALL);
        tiles.add(STONE_WALL_UP_DOWN);
        tiles.add(PILLAR_TOP);
        tiles.add(PILLAR_BOTTOM);
        tiles.add(STONE_WALL_LEFT_RIGHT_UP);
        tiles.add(STONE_WALL_END_LEFT);
        tiles.add(STONE_WALL_END_RIGHT);
        tiles.add(WOOD_SUPPORT_LEFT);
        tiles.add(WOOD_SUPPORT_MIDDLE);
        tiles.add(WOOD_SUPPORT_LEFT_RIGHT);
        tiles.add(WOOD_SUPPORT_RIGHT);
        tiles.add(STONE_WALL_BOTTOM_LEFT);
        tiles.add(STONE_WALL_BOTTOM_LEFT_RIGHT);
        tiles.add(STONE_WALL_BOTTOM_RIGHT);
        tiles.add(BOTTOM_LEFT_CORNER_STONE_WALL);
        tiles.add(STONE_WALL_SINGLE);
        tiles.add(STONE_WALL_SINGLE_TOP);
        tiles.add(STONE_WALL_LEFT_EDGE);
        tiles.add(SMALL_CRACK_WALL);
        tiles.add(BIG_CRACK_WALL);
        tiles.add(STONE_WALL_RIGHT_EDGE);
        tiles.add(STONE_WALL_BOTTOM_MIDDLE);
        tiles.add(STONE_WALL_BOTTOM_END);
        tiles.add(CAGE_FULL);
        tiles.add(DIRT_WALL_TOP_END);
        tiles.add(DIRT_WALL_UP_DOWN);
        tiles.add(DIRT_WALL_BOTTOM_LEFT_CORNER);
        tiles.add(DIRT_WALL_BOTTOM_RIGHT_CORNER);
        tiles.add(DIRT_WALL_LEFT_RIGHT);
        tiles.add(DIRT_WALL_LEFT_END);
        tiles.add(DIRT_WALL_WOOD_LEFT);
        tiles.add(DIRT_WALL_WOOD_RIGHT);
        tiles.add(DIRT_WALL_WOOD_LEFT_CORNER);
        tiles.add(DIRT_WALL_WOOD_MIDDLE);
        tiles.add(DIRT_WALL_WOOD_RIGHT_CORNER);
        tiles.add(DIRT_WALL_TOP_RIGHT_CORNER);
        return tiles;
    }
}
