package com.company;

public class smallTree extends Prey implements Commons
{
    private final int MODELS = 3;
    public smallTree(int x, int y)
    {
        this.x = x;
        this.y = y;
        bonus = 10;
        size = 3;
        type = "smallTree";
        setImage("images/prey/smallTree" + (int)(Math.random()*MODELS+1) + ".png");
    }
    public smallTree()
    {
        this(0,0);
        randomizeCoordinates();
    }
}
