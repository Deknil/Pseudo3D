package ru.deknil.pseudo3d;

import java.awt.*;

/**
 * @Author Deknil
 * @GitHub <a href=https://github.com/Deknil>https://github.com/Deknil</a>
 * @Date 04.02.2024
 * @Description Main application configuration file
 * <p></p>
 * Pseudo3D © 2024. All rights reserved.
 */
public class Config {
    public static final String WINDOW_TITLE = "Pseudo 3D"; // Window title
    public static final int WINDOW_WIDTH = 512; // Game map width
    public static final int WINDOW_HEIGHT = 512; // Game map height
    public static final int GAME_SPEED = 1; // Game speed in ms.
    public static final int GAME_MAP_WIDTH = 32; // Game map width
    public static final int GAME_MAP_HEIGHT = 32; // Game map height
    public static final int MINIMAP_MARGIN_TOP = 30; // Indentation of the minimap from the edges, TOP
    public static final int MINIMAP_MARGIN_LEFT = 10; // Indentation of the minimap from the edges, LEFT
    public static final int MINIMAP_WALL_SIZE = 3; // Wall thickness
    public static final double PLAYER_FOV = Math.PI / 3.5; // Player FOV
    public static final float PLAYER_MOVE_SPEED = 0.1f; // Player movement speed
    public static final float PLAYER_ANGLE_SPEED = 0.05f; // Player camera rotation speed
    public static final double RAY_DEPTH = 16; // Number of ray forwarding iterations
    public static final double RAY_STEP = 0.05d; // Ray step
    public static final String GAME_MAP = "################################" +
                                          "#..............................#" +
                                          "#..............................#" +
                                          "#..............................#" +
                                          "#..............................#" +
                                          "#........#.....................#" +
                                          "#........#.....................#" +
                                          "##########...........#####.....#" +
                                          "#..............................#" +
                                          "#..............................#" +
                                          "#..............................#" +
                                          "#..............................#" +
                                          "#..............................#" +
                                          "#...........########...........#" +
                                          "#..............................#" +
                                          "#..............................#" +
                                          "#..............................#" +
                                          "#..............................#" +
                                          "#...............#..............#" +
                                          "#..............#.#.............#" +
                                          "#...............#..............#" +
                                          "#..............................#" +
                                          "#............#####.............#" +
                                          "#..............................#" +
                                          "#..............#...............#" +
                                          "#..............#...............#" +
                                          "#..#####...............####....#" +
                                          "#..............................#" +
                                          "#..............................#" +
                                          "#..............###.............#" +
                                          "#..............................#" +
                                          "################################"; // Game world map (dimensions 32x32)

    public final static Color BACKGROUND_COLOR = new Color(10, 10, 10, 255); // Canvas background color
    public final static Color MINIMAP_PLAYER_COLOR = new Color(100, 100, 50, 255); // Player color on the minimap
}
