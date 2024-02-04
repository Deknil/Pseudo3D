package ru.deknil.pseudo3d.core;

import ru.deknil.pseudo3d.Config;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @Author Deknil
 * @GitHub <a href=https://github.com/Deknil>https://github.com/Deknil</a>
 * @Date 04.02.2024
 * @Description Keystroke handler
 * <p></p>
 * Pseudo3D © 2024. All rights reserved.
 */
public class KeyHandler implements KeyListener {
    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        double nextPosX = GameLogic.player.posX;
        double nextPosY = GameLogic.player.posY;

        // Implementation of player movement
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> GameLogic.player.fovAngle -= Config.PLAYER_ANGLE_SPEED;
            case KeyEvent.VK_D ->  GameLogic.player.fovAngle += Config.PLAYER_ANGLE_SPEED;
            case KeyEvent.VK_W -> {
                nextPosX += Math.cos(GameLogic.player.fovAngle) * Config.PLAYER_MOVE_SPEED;
                nextPosY += Math.sin(GameLogic.player.fovAngle) * Config.PLAYER_MOVE_SPEED;
            }
            case KeyEvent.VK_S -> {
                nextPosX -= Math.cos(GameLogic.player.fovAngle) * Config.PLAYER_MOVE_SPEED;
                nextPosY -= Math.sin(GameLogic.player.fovAngle) * Config.PLAYER_MOVE_SPEED;
            }
        }

        // Checking for collision with a wall
        int mapX = (int)nextPosX;
        int mapY = (int)nextPosY;
        if (mapX >= 0 && mapX < Config.GAME_MAP_WIDTH && mapY >= 0 && mapY < Config.GAME_MAP_HEIGHT) {
            if (GameLogic.gameMap[mapY * Config.GAME_MAP_WIDTH + mapX] != '#') {
                GameLogic.player.posX = nextPosX;
                GameLogic.player.posY = nextPosY;
            }
        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }
}
