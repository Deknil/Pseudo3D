package ru.deknil.pseudo3d.core;

import ru.deknil.pseudo3d.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Author Deknil
 * @GitHub <a href=https://github.com/Deknil>https://github.com/Deknil</a>
 * @Date 04.02.2024
 * @Description The canvas class on which game elements are drawn
 * <p></p>
 * Pseudo3D © 2024. All rights reserved.
 */
public class Canvas extends JPanel {
    private final BufferedImage image = new BufferedImage(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);

    /**
     * Game map constructor
     */
    public Canvas() {
        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        addKeyListener(new KeyHandler());
        setFocusable(true);
        requestFocus();
    }

    /**
     * Drawing a game map
     */
    private void render() {
        Graphics2D g = image.createGraphics();

        // Clear the canvas
        clear(g);

        // Map rendering
        renderEnvironment();

        // Drawing the minimap
        renderMinimap(g);

        // Rendering information about the player's position and camera angle
        g.setColor(new Color(255,255,255));
        g.drawString(String.format("x: %.1f | y: %.1f | angle: %.2f", GameLogic.player.posX, GameLogic.player.posY, GameLogic.player.fovAngle), 10, 20);

        // Drawing the player on the minimap
        g.setColor(Config.MINIMAP_PLAYER_COLOR);
        g.fillOval(Config.MINIMAP_MARGIN_LEFT + (int)(GameLogic.player.posX * Config.MINIMAP_WALL_SIZE), Config.MINIMAP_MARGIN_TOP + (int)(GameLogic.player.posY * Config.MINIMAP_WALL_SIZE), Config.MINIMAP_WALL_SIZE, Config.MINIMAP_WALL_SIZE);
    }

    /**
     * Rendering the game environment
     */
    private void renderEnvironment() {
        // Cast rays for each pixel column on the screen
        for (int pixelX = 0; pixelX < Config.WINDOW_WIDTH; pixelX++) {
            double distanceToWall = Ray.castRay(pixelX).distance;

            // Getting the height of the sky on the monitor
            int ceiling = (int)(Config.WINDOW_HEIGHT / 2.0 - Config.WINDOW_HEIGHT * Config.PLAYER_FOV / distanceToWall);

            // Getting the floor height on the monitor
            int floor = Config.WINDOW_HEIGHT - ceiling;

            for (int pixelY = 0; pixelY < Config.WINDOW_HEIGHT; pixelY++) {
                // Sky rendering
                if (pixelY < ceiling) {
                    image.setRGB(pixelX, pixelY, new Color(0,0,0).getRGB());
                }
                // Drawing walls
                else if (pixelY > ceiling && pixelY <= floor) {
                    int colorValue = 255 - (int)(distanceToWall * 255 / Config.RAY_DEPTH);
                    colorValue = Math.clamp(colorValue,0, 255);
                    Color wallColor = new Color(colorValue, colorValue, colorValue);
                    image.setRGB(pixelX, pixelY, wallColor.getRGB());
                }
                // Drawing the floor
                else {
                    double distance = (pixelY - Config.WINDOW_HEIGHT / 2.0) / (Config.WINDOW_HEIGHT / 2.0);
                    int brightness = (int)(distance * 255);
                    brightness = Math.clamp(brightness, 0, 255);
                    Color floorColor = new Color(brightness, brightness, brightness);
                    image.setRGB(pixelX, pixelY, floorColor.getRGB());
                }
            }
        }
    }

    /**
     * Drawing the minimap
     */
    private void renderMinimap(Graphics2D g) {
        // Drawing a mini map
        for (int y = 0; y < Config.GAME_MAP_HEIGHT; y++) {
            for (int x = 0; x < Config.GAME_MAP_WIDTH; x++) {
                if (GameLogic.gameMap[y * Config.GAME_MAP_WIDTH + x] == '#') {
                    g.setColor(Color.GRAY);
                    g.fillRect(Config.MINIMAP_MARGIN_LEFT + x * Config.MINIMAP_WALL_SIZE, Config.MINIMAP_MARGIN_TOP + y * Config.MINIMAP_WALL_SIZE, Config.MINIMAP_WALL_SIZE, Config.MINIMAP_WALL_SIZE);
                }
            }
        }

        // Drawing rays on the minimap
        for (int pixelX = 0; pixelX < Config.WINDOW_WIDTH; pixelX++) {
            Ray.RayHitInfo rayHitInfo = Ray.castRay(pixelX);

            // Ray starting point (player's position on the minimap)
            int startX = Config.MINIMAP_MARGIN_LEFT + (int)GameLogic.player.posX * Config.MINIMAP_WALL_SIZE;
            int startY = Config.MINIMAP_MARGIN_TOP + (int)GameLogic.player.posY * Config.MINIMAP_WALL_SIZE;

            // Ray end point (calculation based on angle and distance)
            int endX = (int) (GameLogic.player.posX + Math.cos(rayHitInfo.angle) * rayHitInfo.distance);
            int endY = (int) (GameLogic.player.posY + Math.sin(rayHitInfo.angle) * rayHitInfo.distance);

            // Limiting coordinates to prevent leaving the minimap
            endX = Math.max(0, Math.min(endX, Config.GAME_MAP_WIDTH - 1));
            endY = Math.max(0, Math.min(endY, Config.GAME_MAP_HEIGHT - 1));

            // Recalculation of endpoints taking into account the scale and offset of the minimap
            endX = Config.MINIMAP_MARGIN_LEFT + endX * Config.MINIMAP_WALL_SIZE;
            endY = Config.MINIMAP_MARGIN_TOP + endY * Config.MINIMAP_WALL_SIZE;

            // Setting the color for the beam and drawing the line
            g.setColor(Color.YELLOW);
            g.drawLine(startX, startY, endX, endY);

        }
    }

    /**
     * Clearing the Canvas
     */
    private void clear(Graphics2D g) {
        g.setColor(Config.BACKGROUND_COLOR);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * Canvas drawing logic
     */
    @Override
    public void paint(Graphics g) {
        render();
        ((Graphics2D)g).drawImage(image, null, 0, 0);
    }
}