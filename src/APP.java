import com.ly.snake.GamePanel;

import javax.swing.*;
import java.awt.*;

public class APP {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("snake");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 25*30;
        int height = 25*30;
        jFrame.setBounds((screenSize.width-height)/2,(screenSize.height-height)/2,width,height);

        jFrame.add(new GamePanel());

        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
