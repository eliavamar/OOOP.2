package gameClient.util;

import gameClient.Ex2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

/**
 * this class represent the first opening screen of the game
 */
public class PanelOpening extends JFrame implements Runnable, ActionListener {
    private static long id;
    private static int scenario_num;
    private JTextField txtID;
    private JTextField txtScenario;
    private JLabel labelID;
    private JLabel labelScenario;
    private JButton button;

    /**
     * this method return this id number
     *
     * @return id number of the user
     */
    public long getID() {
        return this.id;

    }

    /**
     * this method return this scenario number
     *
     * @return scenario (level) number of the game
     */
    public int getScenario_num() {
        return scenario_num;
    }

    /**
     * this method is constructor for the new screen panel
     */
    public PanelOpening(String[] args) {
        super("welcome to the best pokemon game!");
        if (args.length > 0) {
            try {
                id = Long.parseLong(args[0]);
                scenario_num = Integer.parseInt(args[1]);
                Thread client = new Thread(new Ex2());
                client.start();
                return;
            } catch (Exception e) {
                return;
            }
        }else {
            this.setSize(400, 300);
            this.setLayout(null);
            this.setResizable(true);
            setLocationRelativeTo(null);
            txtID = new JTextField();
            txtID.setBounds(110, 50, 130, 30);
            labelID = new JLabel();
            labelID.setText("your ID:");
            labelID.setBounds(20, 13, 100, 100);
            labelScenario = new JLabel();
            labelScenario.setText("choose level:");
            labelScenario.setBounds(20, 63, 100, 100);
            txtScenario = new JTextField();
            txtScenario.setBounds(110, 100, 130, 30);
            button = new JButton("log in");
            button.addActionListener(this);
            button.setBounds(120, 170, 100, 50);
            this.add(labelID);
            this.add(txtID);
            this.add(labelScenario);
            this.add(txtScenario);
            this.add(button);
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

    }

    @Override
    public void run() {


    }

    /**
     * this method open an error window
     * if the user entered an incorrect input
     */
    public void errorWindow() {

        JFrame frame = new JFrame("ERROR!");
        frame.setSize(400, 300);
        frame.setLayout(null);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        JLabel lab = new JLabel();
        lab.setText("Your id number or scenario lever is incorrect,please try again :-)");
        lab.setBounds(10, 80, 600, 100);
        frame.add(lab);
        frame.setVisible(true);


    }

    /**
     * this method receive the id number of the user and game level
     * and start the game with this input
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;
        try {
            id = Long.parseLong(txtID.getText());
        } catch (NumberFormatException x) {
            flag = false;
        }
        try {
            scenario_num = Integer.parseInt(txtScenario.getText());
        } catch (NumberFormatException x) {
            flag = false;
        }


        if (flag) {
            this.setVisible(false);
            this.dispose();
            Thread client = new Thread(new Ex2());
            client.start();
        } else {
            this.errorWindow();
        }


    }


}

