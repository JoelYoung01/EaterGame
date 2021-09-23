package com.company;
import java.awt.*;

import java.awt.Image;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public interface Commons
{
    public static final int WINDOW_WIDTH = 1080;
    public static final int WINDOW_HEIGHT = 720;
    public static final int DELAY = 10;

    public enum Passive {Grass}
    public static final int GRASS_AMOUNT = 20;
    public static final int PREY_AMOUNT = 10;
    public static final int ENEMY_AMOUNT = 2;

    public static final Font SMALL = new Font("Helvetica", Font.BOLD, 14);
}