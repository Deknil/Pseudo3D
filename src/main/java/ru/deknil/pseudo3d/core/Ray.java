package ru.deknil.pseudo3d.core;

import ru.deknil.pseudo3d.Config;

/**
 * @Author Deknil
 * @GitHub <a href=https://github.com/Deknil>https://github.com/Deknil</a>
 * @Date 04.02.2024
 * @Description Ray Casting Tools
 * <p></p>
 * Pseudo3D © 2024. All rights reserved.
 */
public class Ray {
    /**
     * Calculation of the ray and detection of collision with an object
     */
    public static RayHitInfo castRay(int pixelX) {
        // Ray Angle Shaping
        double rayAngle = GameLogic.player.fovAngle - (Config.PLAYER_FOV / 2.0) + ((double)pixelX / Config.WINDOW_WIDTH) * Config.PLAYER_FOV;
        double rayX = Math.cos(rayAngle);
        double rayY = Math.sin(rayAngle);

        double distanceToWall = 0.0;
        boolean isWallHit = false;
        int testX;
        int testY;

        // Ray casting, checking for collision with a wall
        while (!isWallHit && distanceToWall < Config.RAY_DEPTH) {
            distanceToWall += Config.RAY_STEP;
            testX = (int)(GameLogic.player.posX + rayX * distanceToWall);
            testY = (int)(GameLogic.player.posY + rayY * distanceToWall);

            // Checking whether the ray goes beyond the map
            if (testX < 0 || testX >= Config.GAME_MAP_WIDTH || testY < 0 || testY >= Config.GAME_MAP_HEIGHT) {
                isWallHit = true; // The ray went beyond the map
                distanceToWall = Config.RAY_DEPTH;
            } else if (GameLogic.gameMap[testY * Config.GAME_MAP_WIDTH + testX] == '#') {
                isWallHit = true; // The ray collided with the wall
            }
        }

        return new RayHitInfo(distanceToWall, rayAngle, isWallHit);
    }

    /**
     * Information about the ray, its collision with the wall, distance.
     */
    static class RayHitInfo {
        double distance; // Distance between ray and player
        double angle; // Ray angle
        boolean isWallHit; // Checking for collision with a wall

        /**
         * Ray Information Constructor
         * @param distance Distance between ray and player
         * @param angle Ray angle
         * @param isWallHit Checking for collision with a wall
         */
        public RayHitInfo(double distance, double angle, boolean isWallHit) {
            this.distance = distance;
            this.angle = angle;
            this.isWallHit = isWallHit;
        }
    }
}
