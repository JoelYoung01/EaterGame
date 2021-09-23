package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GunCatHat extends Enemy implements ActionListener
{
    private int stage;
    private final GunCat host;
    private double targetX,targetY;
    public GunCatHat(GunCat host)
    {
        super(host.getX(),host.getY());
        t = new Timer(100,this);
        this.host = host;

        type = "guncathat";
        visionRad = 500;
        size = host.getSize();
        bonus = 0;
        stage = 1;

        setImage("images/enemy/" + type + stage + ".png");
        t.start();
    }

    public void act(Player p)
    {
        x = host.getX()-1;
        y = host.getY()-12;
        targetX = p.getX();
        targetY = p.getY();

        setImage("images/enemy/" + type + stage + ".png");
    }

    public int getAngle() { return stage; }

    public void updateAngle()
    {
        //get angle
        double angle = Math.atan(Math.abs(targetY - y) / Math.abs(targetX - x))*(180/Math.PI);
        if (targetX > x && targetY < y) {
            angle = 90 - angle;
        } else if (targetX > x && targetY > y) {
            angle = 90 + angle;
        } else if (targetX < x && targetY > y) {
            angle = 270 - angle;
        } else if (targetX < x && targetY < y) {
            angle = 270 + angle;
        } else if (targetX == x) {
            if (targetY >= y) angle = 180;
            else angle = 360;
        } else if (targetY == y) {
            if (targetX >= x) angle = 90;
            else angle = 270;
        } else angle = 360;

        //convert angle
        if (angle < 15 || angle > 345) stage = 12;
        else stage = (int)((angle+15) / 30);
    }

    public void actionPerformed(ActionEvent e)
    {
        updateAngle();
    }
}
