package com.company;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class RunGame extends JFrame {

    public RunGame() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setResizable(true);
        pack();

        setTitle("Eater Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            RunGame eat = new RunGame();
            eat.setVisible(true);
        });
    }
}