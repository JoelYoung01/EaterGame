package com.company;

public class stump extends Prey implements Commons {
    private final int MODELS = 2;

    public stump(int x, int y) {
        this.x = x;
        this.y = y;
        bonus = 5;
        size = 3;
        type = "stump";
        setImage("images/prey/stump" + (int) (Math.random() * MODELS + 1) + ".png");
    }

    public stump() {
        this(0, 0);
        randomizeCoordinates();
    }
}
