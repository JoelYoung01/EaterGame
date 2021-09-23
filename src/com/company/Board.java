package com.company;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener, Commons
{
    private Timer timer;
    private Player player;
    private Scenery[] grass;
    private List<Prey> prey;
    private List<Enemy> enemies;
    private int[] enemyCounter;
    private String endMessage;
    private boolean ingame;

    public Board()
    {
        addKeyListener(new TAdapter());
        timer = new Timer(DELAY, this);
        setFocusable(true);
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        timer.start();
        initGame();
    }

    public void initGame()
    {
        // set up the background color for the grass
        setBackground(new Color(18,173,42)); //grass green
        ingame = true;

        // initialize the player and the grass (as an array)
        player = new Player();
        grass = new Scenery[GRASS_AMOUNT];
        for (int i = 0; i < GRASS_AMOUNT; i++)
        { grass[i] = new Scenery(Passive.Grass); }

        // init rocks
        prey = new ArrayList<>();
        for (int i = 0; i < PREY_AMOUNT; i++) {
            // make a new rock and set intersects value
            Rock rock = new Rock();
            boolean intersects = false;
            // while the intersects value is still true, reset the rock coordinates
            do {
                for (Prey prey : prey) intersects = intersects || prey.touches(rock);
                if (intersects) rock.randomizeCoordinates();
            } while (intersects);

            // since the rock is unique, add it to the preylist
            System.out.println("adding rock at (" + rock.getX() + ", " + rock.getY() + ")");
            prey.add(rock);
        }

        // init the enemies (just puddles
        enemies = new ArrayList<>();
        /* the enemyCounter is an int array that stores how many of each enemy there is at any given time */
        enemyCounter = new int[5]; // 0:puddle, 1:cat, 2:guncat
        for (int i = 0; i < ENEMY_AMOUNT; i++)
        {
            // make the new puddle object and set intersects value
            Puddle puddle = new Puddle();
            boolean intersects = false;

            // while the puddle intersects something on the board, randomize the coordinates
            do {
                for (Prey prey : prey) intersects = intersects || prey.touches(puddle);
                for (Enemy enemy : enemies) intersects = intersects || enemy.touches(puddle);
                if (intersects) puddle.randomizeCoordinates();
            } while (intersects);

            // add the puddle to the enemies list, and increment the puddle counter
            enemies.add(puddle);
            enemyCounter[0]++;
        }

        setDoubleBuffered(true);
    }

    public void updateCollisions() {
        // eating prey
        Prey temp = null;
        for (Prey preyItem : prey) {
            // for each prey, check if they intersect
            // if the player overlaps the prey more than the required threshold, go in
            if (player.touches(preyItem) && player.overlap(preyItem) >= preyItem.requiredOverlap(player) && player.getSize() >= preyItem.getSize())
            {
                // block to choose what new object to spawn in
                /*  If the player's score is greater than a threshold and the prey being eaten is too small,
                    create a new prey item that is higher level. Else, create the same type */
                // TODO: add more variation in the enemy spawning
                if      (player.getScore() >= 15 && preyItem.getType().equals("rock")) temp = new bigRock();
                else if (player.getScore() >= 30 && preyItem.getType().equals("bigRock")) temp = new bush();
                else if (player.getScore() >= 50 && preyItem.getType().equals("bush")) temp = new stump();
                else if (player.getScore() >= 75 && preyItem.getType().equals("stump")) temp = new smallTree();
                else if (player.getScore() >= 105 && preyItem.getType().equals("smallTree")) temp = new tree();
                else if (player.getScore() >= 145 && preyItem.getType().equals("bigTree")) temp = new empty();
                else { temp = preyItem.duplicate(); }
                player.eat(preyItem);
            }
        }
        if (temp != null) {
            prey.add(temp);
        }

        //remove eaten prey from the list
        for (int i = 0; i < prey.size(); i++){
            if (!prey.get(i).isVisible()) { prey.remove(i); i--; }
        }

        //check enemy collisions
        for (Enemy enemy : enemies) {
            if (player.overlap(enemy) >= player.requiredOverlap(enemy)) {
                enemy.interactWith(player);
            }
        }
        for (int i = 0; i < enemies.size(); i++){
            if (!enemies.get(i).isVisible()) { enemies.remove(i); i--; }
        }
    }

    public void updateScoreBoard(Graphics g)
    {
        Font small = new Font("Helvetica", Font.BOLD, 14);

        //score
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString("Score: "+player.getScore(), WINDOW_WIDTH - 200,20);

        //level
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString("Level: " + player.getSize(), WINDOW_WIDTH - 100,20);

        //progress bar
        g.setColor(new Color(18,173,42));
        g.fillRect(WINDOW_WIDTH-200, 30, 190,7);
        g.setColor(Color.WHITE);
        g.drawRect(WINDOW_WIDTH-200, 30, 190,7);
        g.fillRect(WINDOW_WIDTH-200, 30, (int)(player.getProgress()*190),7);
    }

    public void updateEnemy(Graphics g)
    {

        if (player.getSize() > 2 && enemyCounter[1] < 2){
            while (enemyCounter[1] < 2) {enemies.add(new Cat()); enemyCounter[1]++;}
        }

        if (player.getSize() > 3 && enemyCounter[1] < 3){
            while (enemyCounter[1] < 3) {enemies.add(new Cat()); enemyCounter[1]++;}
            updateEnemyVision(2);
        }

        if (player.getSize() > 4 && enemyCounter[2] < 1){
            while (enemyCounter[2] < 1) {enemies.add(new GunCat()); enemyCounter[2]++;}
        }

        if (player.getSize() > 5 && enemyCounter[2] < 2){
            while (enemyCounter[2] < 2) {enemies.add(new GunCat()); enemyCounter[2]++;}
        }

        Enemy temp = null;
        for (Enemy enemy : enemies){
            if (enemy.tryShot()) {temp = new Bullet(enemy); enemy.shotOff();}
            enemy.drawExtras(this, g);
        }
        if (temp != null) {enemies.add(temp);}
    }
    public void updateEnemyVision(int factor) {
        for (Enemy enemy : enemies) enemy.increaseVision(factor);
    }

    public void update(Graphics g)
    {
        //check for collisions
        updateCollisions();

        //paint objects
        for (Scenery grass : grass) paint(grass,g);
        for (Enemy enemy : enemies) paint(enemy, g);
        for (Prey prey : prey) paint(prey, g);
        updateEnemy(g);

        //must be last
        if (prey.size() <= 0 || player.getSize() > player.getMaxSize()) ingame = false;
        if (player.isVisible()) paint(player,g);
        else ingame = false;
        updateScoreBoard(g);
    }

    public void killAll() {
        for (Prey prey: prey) prey.kill();
        for (Enemy enemy: enemies) enemy.kill();
        for (Scenery item : grass) item.kill();
    }

    public void gameOver(Graphics g)
    {
        timer.stop();
        killAll();
        if (!player.isVisible()) endMessage = "Game Over";
        else endMessage = "You Win!";

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);
        String scoreMessage = "Your Score: " + player.getFullScore();

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(endMessage,
                (WINDOW_WIDTH - metr.stringWidth(endMessage)) / 2,
                WINDOW_HEIGHT / 2 - 20);

        g.drawString(scoreMessage,
                (WINDOW_WIDTH - metr.stringWidth(scoreMessage)) / 2,
                WINDOW_HEIGHT / 2);

        g.dispose();
    }

    public void paint(Sprite s, Graphics g) {
        g.drawImage(s.getImage(), s.getX(), s.getY(), this);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (ingame) update(g);
        else gameOver(g);

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        player.act();
        for (Enemy enemy : enemies) enemy.act(player);

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

    }
}