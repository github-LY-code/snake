package com.ly.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel {
    private int[] snakeX;
    private int[] snakeY;
    private int length;
    private String direction;
    private boolean isStart = false;
    private Timer timer;
    private int foodX;
    private int foodY;
    private int score;
    private boolean isDie;

    public void init() {
        snakeX = new int[200];
        snakeY = new int[200];
        snakeX[0] = 2*25;
        snakeY[0] = 10*25;

        snakeX[1] = 1*25;
        snakeY[1] = 10*25;

        snakeX[2] = 0*25;
        snakeY[2] = 10*25;

        length = 3;

        score = 0;

        isDie = false;

        direction = "R";

        generateFood();
    }

    public GamePanel() {
        init();
        timer = new Timer((int) 10000/60, e-> {
            if (isStart && !isDie) {
                for (int i = length-1;i > 0;i--) {
                    snakeX[i] = snakeX[i-1];
                    snakeY[i] = snakeY[i-1];
                }
                if ("R".equals(direction)) {
                    snakeX[0] += 25;
                } else if ("L".equals(direction)) {
                    snakeX[0] -= 25;
                } else if ("U".equals(direction)) {
                    snakeY[0] -= 25;
                } else if ("D".equals(direction)) {
                    snakeY[0] += 25;
                }
                if (snakeX[0] > 25*29) {
                    snakeX[0] = 0;
                }
                if (snakeX[0] < 0) {
                    snakeX[0] = 25*29;
                }
                if (snakeY[0] < 50) {
                    snakeY[0] = 25*29;
                }
                if (snakeY[0] > 25*29) {
                    snakeY[0] = 50;
                }

                if (foodX == snakeX[0] && foodY == snakeY[0]) {
                    length += 1;
                    snakeX[length-1] = snakeX[length-2];
                    snakeY[length-1] = snakeY[length-2];
                    score += 10;
                    generateFood();
                }

                for (int i = 1; i < length; i++) {
                    if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                        isDie = true;
                    }
                }
                repaint();
            }
        });
        timer.start();
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (isDie) {
                        init();
                    } else {
                        isStart = !isStart;
                    }
                } else if (isStart && !isDie && e.getKeyCode() == KeyEvent.VK_UP) {
                    direction = direction.equals("D") ? "D" : "U";
                } else if (isStart && !isDie  && e.getKeyCode() == KeyEvent.VK_DOWN) {
                    direction = direction.equals("U") ? "U" : "D";
                } else if (isStart && !isDie  &&  e.getKeyCode() == KeyEvent.VK_LEFT) {
                    direction = direction.equals("R") ? "R" : "L";
                } else if (isStart && !isDie  && e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    direction = direction.equals("L") ? "L" : "R";
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(139, 200, 139));
        g.fillRect(0,0,25*30,50);

        g.setColor(new Color(255,255,255));
        g.setFont(new Font("微软雅黑", Font.BOLD, 20));
        String s1 = "贪吃蛇小游戏          作者：yang";
        g.drawString(s1,20,33);

        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("微软雅黑", Font.BOLD, 20));
        String s2 = "得分："+score;
        // 25*30-20 - s2.length()*20
        g.drawString(s2, 25*30-20 - s2.length()*20, 33);

        drawSnake(g);
        Images.foodImage.paintIcon(this, g,foodX, foodY);
    }

    private void drawSnake(Graphics g) {
        if ("R".equals(this.direction)) {
            Images.rightImage.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if ("L".equals(this.direction)) {
            Images.leftImage.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if ("U".equals(this.direction)) {
            Images.upImage.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if ("D".equals(this.direction)) {
            Images.downImage.paintIcon(this, g, snakeX[0], snakeY[0]);
        }

        for (int i = 1; i < length; i++) {
            Images.bodyImage.paintIcon(this,g,snakeX[i], snakeY[i]);
        }

        if (!isStart) {
            g.setColor(new Color(184, 70, 216));
            g.setFont(new Font("微软雅黑", Font.BOLD, 20));
            String str = "按下空格键开始游戏";
            g.drawString(str, (25*30 - str.length()*20)/2, (25*30-20)/2);
        }

        if (isDie) {
            g.setColor(new Color(178, 32, 42));
            g.setFont(new Font("微软雅黑", Font.BOLD, 20));
            String str = "小蛇死亡，按下空格键重新开始游戏";
            g.drawString(str, (25*30 - str.length()*20)/2, (25*30-20)/2);
        }
    }

    private void generateFood() {
        Random random = new Random();
        foodX = random.nextInt(29)*25;
        foodY = (random.nextInt(27)+2)*25;
        for (int i = 0; i < length; i++) {
            if (snakeX[i] == foodX && snakeY[i] == foodY) {
                generateFood();
            }
        }
    }
}
