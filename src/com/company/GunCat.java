package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GunCat extends Enemy implements Commons, ActionListener
{
    private GunCatHat hat;
    private boolean shotQ;
    private int counter;
    private final int moveSpeed = 3;
    private int shotChance;
    private final int animationDelay = 100;
    private boolean move;
    private boolean ranMove;
    public GunCat(int x, int y)
    {
        super(x,y);
        t = new Timer(animationDelay, this);

        type = "cat";
        counter = 0;
        move = false;
        ranMove = false;
        shotQ = false;
        frames = 8;
        stage = 1;
        damage = 2;
        visionRad = 50;
        size = 7;
        bonus = 20;
        shotChance = 500;

        setImage("images/enemy/" + type + stage + ".png");
        hat = new GunCatHat(this);
        t.start();
    }

    public GunCat() {this(0,0); randomizeCoordinates();}
    public void drawExtras(JPanel j, Graphics g) {
        g.drawImage(hat.getImage(), hat.getX(), hat.getY(), j);
    }
    public int getAngle() { return hat.getAngle(); }
    public boolean tryShot() { return shotQ; }
    public void shotOff() {shotQ = false;}

    public void act(Player p)
    {
        hat.act(p);

        if (counter > 20 && (int)Math.random()*7 == 0) {
            move = !move;
            if (move) counter = 0;
            else counter = 10;
            ranMove = true;
        }
        if (move)
        {
            if (canSee(p)) {
                if (p.getX() < x) {subx = moveSpeed; addx = 0;}
                else if (p.getX() > x) {addx = moveSpeed; subx = 0;}
                else {subx = 0; addx = 0;}

                if (p.getY() < y) {suby = moveSpeed; addy = 0;}
                else if (p.getY() > y) {addy = moveSpeed; suby = 0;}
                else {suby = 0; addy = 0;}
            } else if (ranMove) {
                ranMove = false;
                subx = 0; addx = 0; suby = 0; addy = 0;
                int i = (int)(Math.random() * 5);
                switch (i) {
                    case 0: subx = 1; break;
                    case 1: addx = 1; break;
                    case 2: suby = 1; break;
                    case 3: addy = 1; break;
                    case 4: move = false; break;
                }
            }
        } else {subx = 0; addx = 0; suby = 0; addy = 0;}

        int dy = addy - suby;
        int dx = addx - subx;
        if (p.getSize() >= 2) {dy += -1; dx *= -1;}

        y += dy;
        x += dx;
        if (x < 2) x = 2;
        if (x > WINDOW_WIDTH-width-2) x = WINDOW_WIDTH - width;

        if (y < 2) y = 2;
        if (y > WINDOW_HEIGHT-height-2) y = WINDOW_HEIGHT - height;

        if ((int)(Math.random()*shotChance) == 0) shotQ = true;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (move) nextFrame();
        else setImage("images/enemy/" + type + "1.png");
        counter++;
    }
}
