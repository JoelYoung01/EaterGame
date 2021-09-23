package com.company;

public class empty extends Prey implements Commons
{
    public empty()
    {
        this.x = 0;
        this.y = 0;
        type = "empty";
        kill();
    }

    @Override
    public int getBonus() throws IllegalCallerException {
        throw new IllegalCallerException("cannot call getBonus() on 'empty' object");
    }

    @Override
    public int getSize() throws IllegalCallerException {
        throw new IllegalCallerException("cannot call getSize() on 'empty' object");
    }
}
