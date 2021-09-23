package com.company;

public class Bullet extends Enemy
{
    private int dx,dy;
    private int angle;
    private final int SLOW = 3;
    private final int MED = (int)(SLOW * Math.sqrt(3));
    private final int FAST = SLOW * 2;

    public Bullet(Enemy c)
    {
        super(c.getX() + 12, c.getY()+ 3);
        type = "shot";
        setImage("images/enemy/" + type + "1.png");
        angle = c.getAngle();
        killSize = 5;
        bonus = 5;
        damage = 100;

        switch (angle) {
            case 1: dx = SLOW; dy = -MED; break;
            case 2: dx = MED; dy = -SLOW; break;
            case 3: dx = FAST; dy = 0; break;
            case 4: dx = MED; dy = SLOW; break;
            case 5: dx = SLOW; dy = MED; break;
            case 6: dx = 0; dy = FAST; break;
            case 7: dx = -SLOW; dy = MED; break;
            case 8: dx = -MED; dy = SLOW; break;
            case 9: dx = -FAST; dy = 0; break;
            case 10:dx = -MED; dy = -SLOW; break;
            case 11:dx = -SLOW; dy = -MED; break;
            case 12:dx = 0; dy = -FAST; break;
        }

    }

    public void act(Player p)
    {
        x += dx;
        y += dy;

        if (x < 0 || x > WINDOW_WIDTH-width || y < 0 || y > WINDOW_HEIGHT-height) kill();
    }
}
