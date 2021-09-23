package com.company;

public class tree extends Prey implements Commons
{
    private final int MODELS = 3;
    public tree(int x, int y)
    {
        this.x = x;
        this.y = y;
        bonus = 15;
        size = 4;
        type = "tree";
        setImage("images/prey/tree" + (int)(Math.random()*MODELS+1) + ".png");
    }
    public tree()
    {
        this(0,0);
        randomizeCoordinates();
    }
}
