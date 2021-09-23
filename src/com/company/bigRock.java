package com.company;

public class bigRock extends Prey implements Commons
{
    private final int MODELS = 2;
    public bigRock(int x, int y)
    {
        this.x = x;
        this.y = y;
        bonus = 2;
        size = 2;
        type = "bigRock";
        setImage("images/prey/bigRock" + (int)(Math.random()*MODELS+1) + ".png");
    }
    public bigRock()
    {
        this(0,0);
        randomizeCoordinates();
    }
}
