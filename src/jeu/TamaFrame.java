package jeu;

import tamagoshis.Tamagoshi;
import visuel.TamaJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TamaFrame extends javax.swing.JFrame implements ActionListener {
    public TamaJPanel panel;
    public JTextArea dialogue;
    private JPanel buttons;
    private JButton nourrir;
    private JButton jouer;
    private TamaGame game;
    private Tamagoshi tamagoshi;
    private ArrayList<JButton> listButton;

    public Tamagoshi getTamagoshi() {
        return tamagoshi;
    }

    public void setTamagoshi(Tamagoshi tamagoshi) {
        this.tamagoshi = tamagoshi;
    }

    public TamaFrame(TamaGame game) {

        panel = new TamaJPanel();
        TamaGame tamagoshi = null;

        listButton = new ArrayList();


        dialogue = new JTextArea();

        buttons = new JPanel(new FlowLayout());
        JPanel textArea = new JPanel(new FlowLayout());

        nourrir = new JButton("nourrir");
        jouer = new JButton("jouer");

        nourrir.addActionListener(this);
        jouer.addActionListener(this);

        buttons.add(nourrir);
        buttons.add(jouer);

        listButton.add(nourrir);
        listButton.add(jouer);

        textArea.add(dialogue);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(BorderLayout.SOUTH,buttons);
        getContentPane().add(BorderLayout.CENTER,panel);
        getContentPane().add(BorderLayout.NORTH,textArea);

        this.game = game;

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == nourrir && tamagoshi.isAlive()) {

            tamagoshi.mange();
            nourrir.setEnabled(false);
            this.fin();

        }else if(e.getSource() == jouer && tamagoshi.isAlive()) {
            tamagoshi.joue();
            jouer.setEnabled(false);
            this.fin();
        }
        repaint();
    }

    public void fin() {
        if (!jouer.isEnabled() && !nourrir.isEnabled()) {
            game.finDeTour();
            game.Mort();
        }
    }

    public ArrayList<JButton> getButton(){
        return listButton;

    }


    public void initialisation() {
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("tama Game");
        setVisible(true);
        requestFocus();

    }
}
