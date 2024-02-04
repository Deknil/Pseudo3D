package ru.deknil.pseudo3d.core;

/**
 * @Author Deknil
 * @GitHub <a href=https://github.com/Deknil>https://github.com/Deknil</a>
 * @Date 04.02.2024
 * @Description Player implementation class
 * <p></p>
 * Pseudo3D © 2024. All rights reserved.
 */
public class Player {
    public double posX; // Horizontal position
    public double posY; // Vertical position
    public double fovAngle; // Player Viewing Angle

    /**
     * Player object constructor
     * @param x horizontal position
     * @param y vertical position
     * @param fovAngle viewing angle
     */
    public Player(double x, double y, double fovAngle) {
        this.posX = x;
        this.posY = y;
        this.fovAngle = fovAngle;
    }
}
