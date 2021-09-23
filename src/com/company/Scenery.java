package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Scenery extends Sprite implements ActionListener, Commons
{
    private Timer timer;
    private String path;
    private int stage;
    private int MAX_STAGE;
    private int delayFactor;
    private int inc;

    public Scenery(Passive object, int x, int y)
    {
        this.x = x;
        this.y = y;
        stage = (int)(Math.random() * 6) + 1;

        if (object == Passive.Grass)
        {
            path = "images/scenery/grass";
            MAX_STAGE = 6;
            delayFactor = 10;
            inc = 1;
        }

        setImage(path+stage+".png");
        timer = new Timer(DELAY*delayFactor, this);
        timer.start();
    }
    public Scenery(Passive object)
    {
        this(object,
                (int)(Math.random() * (WINDOW_WIDTH-40))+10,
                (int)(Math.random() * (WINDOW_HEIGHT-40))+10
        );
    }

    public void kill()
    {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (inc == 1 && stage >= MAX_STAGE) inc = -1;
        if (inc == -1 && stage <= 1) inc = 1;

        stage+=inc;

        setImage(path+stage+".png");
    }
}
