package com.company;

import javax.swing.*;
import java.awt.*;

public abstract class Enemy extends Sprite implements Commons, Edible
{
    protected int frames,stage;
    protected double damage;
    protected String type;
    protected int visionRad;
    protected int size;
    protected int bonus;
    protected int killSize;

    public Enemy(int x, int y) {super(x,y);killSize = 1;}

    public abstract void act(Player p);

    public void drawExtras(JPanel j, Graphics g) {}
    public boolean tryShot() {return false;}
    public void shotOff() {}
    public int getAngle() { return 12; }
    public double getDamage() {return damage;}
    public String getType() {return type;}
    public int getSize() { return size; }
    public int getBonus() { return bonus; }
    public void increaseVision(int factor) { visionRad *= factor; }

    public void interactWith(Player p)
    {
        if (p.getSize() <= killSize) p.kill();
        else if (p.getSize() >= getBonus()) p.eat(this);
        else p.hurt(damage);
    }

    public void nextFrame()
    {
        stage++;
        if (stage > frames) stage = 1;
        setImage("images/enemy/" + type + stage + ".png");
    }

    public boolean canSee(Player p)
    {
        return Math.sqrt(Math.pow(this.x - p.getX(),2) + Math.pow(this.y - p.getY(),2)) <= visionRad;
    }
}
