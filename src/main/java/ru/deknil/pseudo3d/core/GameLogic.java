package ru.deknil.pseudo3d.core;

import ru.deknil.pseudo3d.Config;

/**
 * @Author Deknil
 * @GitHub <a href=https://github.com/Deknil>https://github.com/Deknil</a>
 * @Date 04.02.2024
 * @Description Set of game elements
 * <p></p>
 * Pseudo3D © 2024. All rights reserved.
 */
public class GameLogic {
    // Map symbol array - map cells
    public static final char[] gameMap = Config.GAME_MAP.toCharArray();

    // Player instance
    public static final Player player = new Player(2, 2, 0);
}
