package com.company;

public class Rock extends Prey implements Commons
{
    private final int MODELS = 2;
    public Rock(int x, int y)
    {
        this.x = x;
        this.y = y;
        bonus = 1;
        size = 1;
        type = "rock";
        setImage("images/prey/rock" + (int)(Math.random()*MODELS+1) + ".png");
    }
    public Rock()
    {
        this(0,0);
        randomizeCoordinates();
    }
}
