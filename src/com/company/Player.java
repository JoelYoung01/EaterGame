package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Sprite implements Commons
{
    private final int MAX_SIZE = 6;
    private final int MoveSpeed = 4;
    private int size;
    private double stomach;
    private double score;
    private double fullScore;
    private int full;
    private String imagePath;

    //TODO: correctly scale player image based on size, also animate player image while it moves
    public Player()
    {
        x = WINDOW_WIDTH/2;
        y = WINDOW_HEIGHT/2;
        size = 1;
        stomach = 0;
        full = 5;
        score = 0;
        fullScore = 0;
        imagePath = "images/player/player0.png";
        setImage(imagePath);
    }

    @Override
    public void setImage(String path) {
        ImageIcon ii = new ImageIcon(path);

        Image scaledImage = ii.getImage().getScaledInstance(size * 50, -1, Image.SCALE_AREA_AVERAGING);
        while (scaledImage.getHeight(null) < 0 && scaledImage.getWidth(null) < 0) {}    // Wait for the image to be loaded, pretty garbage solution though

        setImage(scaledImage);
    }

    public void act()
    {
        if (stomach >= full) grow();
        if (size < 1) kill();
        if (score < 0) kill();

        y = y + addy - suby;
        x = x + addx - subx;
        if (x < 0) x = 1;
        if (x > WINDOW_WIDTH-width) x = WINDOW_WIDTH - width;

        if (y < 0) y = 1;
        if (y > WINDOW_HEIGHT-height) y = WINDOW_HEIGHT - height;
    }

    public void eat(Edible s)
    {
        stomach += s.getBonus();
        score += s.getBonus();
        fullScore += s.getBonus();
        s.kill();
    }

    public void hurt(double damage)
    {
        stomach -= damage;
        score -= damage;
        if (stomach < 0) shrink();
    }

    private void grow()
    {
        size++;
        setImage(imagePath);
        full *= size;
        stomach = 0;
    }
    private void shrink()
    {
        full /= size;
        size--;
        setImage(imagePath);
        stomach = full-1;
    }

    public int getSize() { return size;}
    public int getScore() { return (int)score;}
    public int getFullScore() { return (int)fullScore;}
    public int getStomach() { return (int)stomach;}
    public double getProgress() { return stomach / full;}
    public int getMaxSize() {return MAX_SIZE;}

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) addx = MoveSpeed;
        if (key == KeyEvent.VK_LEFT) subx = MoveSpeed;

        if (key == KeyEvent.VK_UP) suby = MoveSpeed;
        if (key == KeyEvent.VK_DOWN) addy = MoveSpeed;
    }
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) addx = 0;
        if (key == KeyEvent.VK_LEFT) subx = 0;

        if (key == KeyEvent.VK_UP) suby = 0;
        if (key == KeyEvent.VK_DOWN) addy = 0;
    }
}
