package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// enemies
public class Puddle extends Enemy implements Commons, ActionListener
{
    public Puddle(int x, int y)
    {
        super(x,y);
        type = "puddle";
        frames = 2;
        stage = 1;
        damage = .1;
        setImage("images/enemy/" + type + stage + ".png");

        t = new Timer(500, this);
        t.start();
    }

    public Puddle() {this(0,0); randomizeCoordinates();}
    public int getBonus() {return 3;}
    public void act(Player p) {}

    @Override
    public void actionPerformed(ActionEvent e)
    {
        nextFrame();
    }
}
