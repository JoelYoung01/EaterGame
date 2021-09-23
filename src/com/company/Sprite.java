package com.company;

import javax.swing.*;
import java.awt.*;

public abstract class Sprite implements Commons
{
    private int stage, frames;
    private boolean visible;
    private Image image;
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected int addx,subx;
    protected int addy,suby;
    protected Timer t;

    // constructor, puts Sprite in the middle of the screen.
    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
        visible = true;
        addx = 0;
        subx = 0;
        addy = 0;
        suby = 0;
    }
    public Sprite() {this(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2);}

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void kill() { visible = false; }
    public void setImage(Image image) {
        this.image = image;
        width = getImage().getWidth(null);
        height = getImage().getHeight(null);
    }
    public void setImage(String path) {
        ImageIcon ii = new ImageIcon(path);
        setImage(ii.getImage());
    }

    public boolean isVisible() { return visible; }
    public Image getImage() { return image; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Rectangle getBox() { return new Rectangle(x,y,width,height);}
    public int requiredOverlap(Sprite s) {
        // return half of the area of the smallest sprite of the two sprites (this and s)
        return Math.min(width*height - width*height/2, s.getWidth() * s.getHeight() - s.getWidth() * s.getHeight() / 2);
    }
    public void randomizeCoordinates()
    {
        x = (int)(Math.random() * (WINDOW_WIDTH-width-10)+10);
        y = (int)(Math.random() * (WINDOW_HEIGHT-height-10)+10);
    }
    public boolean touches(Sprite s) {
        return getBox().intersects(s.getBox());
    }

    public int overlap(Sprite s)
    {
        Rectangle r = getBox().intersection(s.getBox());
        if (r.getWidth() > 0 && r.getHeight() > 0) return (int)(r.getWidth() * r.getHeight());
        return 0;
    }
}
