package gameClient.util;

import Server.Game_Server_Ex2;
import api.directed_weighted_graph;
import api.edge_data;
import api.geo_location;
import api.node_data;
import gameClient.Arena;
import gameClient.CL_Agent;
import gameClient.CL_Pokemon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * this class represent the JPanel that the game being show on
 */
public class panel extends JPanel {
    private Arena _ar;
    private gameClient.util.Range2Range _w2f;

    /**
     * this method is constructor for a new panel
     */
    public panel() {
        super();
        _ar = new Arena();
        this.setBackground(Color.white);
        setVisible(true);
    }

    /**
     * this method get an Arena and up data the frame by the updateFrame method
     * according the Arena
     *
     * @param ar
     */
    public void update(Arena ar) {
        this._ar = ar;
        updateFrame();
    }

    /**
     * this method up data the game panel according to the changes in the Arena
     */

    private void updateFrame() {
        Range rx = new Range(20, this.getWidth() - 20);
        Range ry = new Range(this.getHeight() - 10, 150);
        Range2D frame = new Range2D(rx, ry);
        directed_weighted_graph g = _ar.getGraph();
        _w2f = Arena.w2f(g, frame);
    }

    /**
     * this method clear the old panel and repaint it again
     * its draw:Pokemon's,agents,graph,info of the agents,timer of the game
     *
     * @param g
     */

    public void paint(Graphics g) {
        int w = this.getWidth();
        int h = this.getHeight();
        g.clearRect(0, 0, w, h);
        updateFrame();
        drawPokemons(g);
        drawGraph(g);
        drawAgants(g);
       // drawInfo(g);
        painTimer(g);


    }

    /**
     * this method draw timer on the panel game
     *
     * @param g
     */
    private void painTimer(Graphics g) {
        int time = (int) _ar.getTime() / 1000;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int height = dim.height;
        int width = dim.width;
        g.setColor(Color.pink);
        g.setFont(new Font("Monaco", Font.PLAIN, 23));
        g.drawString("Time left: " + time + ":00", width - 250, 30);
    }

    /**
     * this method draw the info of the agents on the panel game
     *
     * @param g
     */

    private void drawInfo(Graphics g) {
        java.util.List<String> str = _ar.get_info();
        String dt = "none";
        for (int i = 0; i < str.size(); i++) {
            g.drawString(str.get(i) + " dt: " + dt, 100, 60 + i * 20);
        }

    }

    /**
     * this method draw the graph on the panel game by using drawNode and drawEdge method
     *
     * @param g
     */

    private void drawGraph(Graphics g) {
        directed_weighted_graph gg = _ar.getGraph();
        Iterator<node_data> iter = gg.getV().iterator();
        while (iter.hasNext()) {
            node_data n = iter.next();
            g.setColor(Color.blue);
            drawNode(n, 5, g);
            Iterator<edge_data> itr = gg.getE(n.getKey()).iterator();
            while (itr.hasNext()) {
                edge_data e = itr.next();
                g.setColor(Color.gray);
                drawEdge(e, g);
            }
        }
    }

    /**
     * this method draw a Pokemon's on the panel game
     *
     * @param g
     */
    private void drawPokemons(Graphics g) {
        java.util.List<CL_Pokemon> fs = _ar.getPokemons();
        if (fs != null) {
            Iterator<CL_Pokemon> itr = fs.iterator();
            while (itr.hasNext()) {
                CL_Pokemon f = itr.next();
                Point3D c = f.getLocation();
                int r = 10;
                g.setColor(Color.green);
                if (f.getType() < 0) {
                    g.setColor(Color.orange);
                }
                if (c != null) {

                    geo_location fp = this._w2f.world2frame(c);
                    g.fillOval((int) fp.x() - r, (int) fp.y() - r, 2 * r, 2 * r);
                    g.drawString(" " + f.getValue(), (int) fp.x() - r, (int) fp.y() - r);


                }
            }
        }
    }

    /**
     * this method draw a agents on the panel game
     *
     * @param g
     */
    private void drawAgants(Graphics g) {
        List<CL_Agent> rs = _ar.getAgents();
        Iterator<CL_Agent> itr = rs.iterator();
        int loc = 40;
        g.setColor(Color.red);
        while (itr.hasNext()) {
            CL_Agent temp = itr.next();
            geo_location c = temp.getLocation();
            int r = 8;
            if (c != null) {
                geo_location fp = this._w2f.world2frame(c);

                g.setColor(Color.red);
                g.fillOval((int) fp.x() - r, (int) fp.y() - r, 2 * r, 2 * r);
                g.drawString(" " + temp.getID(), (int) fp.x() - r, (int) fp.y() - r);
                g.setColor(Color.black);
                g.drawString("agent number: " + temp.getID() + " value: " + temp.getValue() + " dest: " + temp.getNextNode(), (int) 20, (int) loc);
                loc += 20;

            }
        }
    }

    /**
     * this method draw single node on the panel game
     * @param g
     * */
    private void drawNode(node_data n, int r, Graphics g) {
        geo_location pos = n.getLocation();
        geo_location fp = this._w2f.world2frame(pos);
        g.fillOval((int) fp.x() - r, (int) fp.y() - r, 2 * r, 2 * r);
        g.drawString("" + n.getKey(), (int) fp.x(), (int) fp.y() - 4 * r);
    }
    /**
     * this method draw single edge on the panel game
     * @param g
     * */
    private void drawEdge(edge_data e, Graphics g) {
        directed_weighted_graph gg = _ar.getGraph();
        geo_location s = gg.getNode(e.getSrc()).getLocation();
        geo_location d = gg.getNode(e.getDest()).getLocation();
        geo_location s0 = this._w2f.world2frame(s);
        geo_location d0 = this._w2f.world2frame(d);
        g.drawLine((int) s0.x(), (int) s0.y(), (int) d0.x(), (int) d0.y());
    }


}
