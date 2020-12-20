package gameClient.util;

import Server.Game_Server_Ex2;
import gameClient.Arena;

import javax.swing.*;
import java.awt.*;

/**
 * this class represent the screen frame of the game
 */
public class Gframe extends JFrame {
    private panel pa;

    /**
     * this method is a constructor for the screen frame of the game
     *
     * @param str
     */
    public Gframe(String str) {
        super(str);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int height = dim.height;
        int width = dim.width;
        this.setSize(width, height - 20);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = this.getContentPane();
        c.setBackground(Color.pink);
        setVisible(true);
        this.setResizable(true);
        pa = new panel();
        this.add(pa);

    }

    /**
     * this method update this frame
     *
     * @param ar
     */
    public void update(Arena ar) {
        pa.update(ar);
    }

}
