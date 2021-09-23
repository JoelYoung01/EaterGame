package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cat extends Enemy implements Commons, ActionListener
{
    private int counter;
    private final int moveSpeed = 2;
    private final int animationDelay = 100;
    private boolean move;
    private boolean ranMove;
    public Cat(int x, int y)
    {
        super(x,y);
        type = "cat";
        counter = 0;
        move = false;
        ranMove = false;
        frames = 8;
        stage = 1;
        damage = .01;
        visionRad = 300;
        size = 5;
        bonus = 5;
        setImage("images/enemy/" + type + stage + ".png");

        t = new Timer(animationDelay, this);
        t.start();
    }

    public Cat() {this(0,0); randomizeCoordinates();}
    public void act(Player p)
    {
        if (counter > 20 && (int)Math.random()*7 == 0)
        {move = !move; counter = 0; ranMove = true;}
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
                    case 0: subx = moveSpeed / 2; break;
                    case 1: addx = moveSpeed / 2; break;
                    case 2: suby = moveSpeed / 2; break;
                    case 3: addy = moveSpeed / 2; break;
                    case 4: move = false; break;
                }
            }
        } else {subx = 0; addx = 0; suby = 0; addy = 0;}

        int dy = addy - suby;
        int dx = addx - subx;
        if (p.getSize() >= getBonus()) {dy += -1; dx *= -1;}

        y += dy;
        x += dx;
        if (x < 0) x = 1;
        if (x > WINDOW_WIDTH-width) x = WINDOW_WIDTH - width;

        if (y < 0) y = 1;
        if (y > WINDOW_HEIGHT-height) y = WINDOW_HEIGHT - height;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (move) nextFrame();
        else setImage("images/enemy/" + type + "1.png");
        counter++;
    }
}
