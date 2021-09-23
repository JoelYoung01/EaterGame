package com.company;

public class bush extends Prey implements Commons
{
    private final int MODELS = 2;
    public bush(int x, int y)
    {
        this.x = x;
        this.y = y;
        bonus = 3;
        size = 2;
        type = "bush";
        setImage("images/prey/bush" + (int)(Math.random()*MODELS+1) + ".png");
    }
    public bush()
    {
        this(0,0);
        randomizeCoordinates();
    }
}
