package com.company;

public abstract class Prey extends Sprite implements Commons, Edible
{
    protected int bonus;
    protected int size;
    protected String type;
    public int getBonus() { return bonus; }
    public int getSize() { return size; }
    public String getType() { return type; }
    public Prey duplicate() { return this; }
}