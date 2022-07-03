package com.ly.snake;

import javax.swing.*;
import java.net.URL;

public class Images {
    private static URL body = Images.class.getResource("/images/body.png");
    public static ImageIcon bodyImage = new ImageIcon(body);

    private static URL up = Images.class.getResource("/images/up.png");
    public static ImageIcon upImage = new ImageIcon(up);

    private static URL down = Images.class.getResource("/images/down.png");
    public static ImageIcon downImage = new ImageIcon(down);

    private static URL left = Images.class.getResource("/images/left.png");
    public static ImageIcon leftImage = new ImageIcon(left);

    private static URL right = Images.class.getResource("/images/right.png");
    public static ImageIcon rightImage = new ImageIcon(right);

    private static URL food = Images.class.getResource("/images/food.png");
    public static ImageIcon foodImage = new ImageIcon(food);
}
