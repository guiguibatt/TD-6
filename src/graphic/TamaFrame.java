package graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import jeu.AutoTamaGame;
import jeu.TamaGameGraphic;
import tamagoshis.Tamagoshi;

public class TamaFrame extends JFrame implements ActionListener {
    private TamaJPanel tamaPanel;
    private Tamagoshi monTamagoshi;
    private JButton nourrir;
    private JButton jouer;
    TamaGameGraphic game;

    public TamaFrame(Tamagoshi t, TamaGameGraphic game) {
        this.monTamagoshi = t;
        t.setMaframe(this);
        this.game = game;
        this.nourrir = new JButton("nourrir");
        this.nourrir.setName("nourrir");
        this.nourrir.addActionListener(this);
        this.jouer = new JButton("jouer");
        this.jouer.setName("jouer");
        this.jouer.addActionListener(this);
        JPanel p2 = new JPanel();
        p2.add(this.nourrir);
        p2.add(this.jouer);
        this.add("South", p2);
        this.tamaPanel = new TamaJPanel();
        this.monTamagoshi.setPanel(this.tamaPanel);
        this.add(this.tamaPanel);
        this.setSize(260, 300);
        this.setTitle(this.monTamagoshi.getName());
    }

    public void actionPerformed(ActionEvent e) {
        this.game.userAction(e.getActionCommand());
        System.out.println(e.getActionCommand());
        if (e.getSource() == this.nourrir) {
            this.monTamagoshi.mange();
            this.monTamagoshi.parle();
        } else {
            this.monTamagoshi.joue();
            this.monTamagoshi.parle();
        }

    }

    public void enableButtons(String buttonName, boolean enable) {
        if (this.monTamagoshi.isAlive() || !enable) {
            if (buttonName.equals(this.nourrir.getName())) {
                this.nourrir.setEnabled(enable);
            } else {
                this.jouer.setEnabled(enable);
            }
        }

    }

    public Tamagoshi getMonTamagoshi() {
        return this.monTamagoshi;
    }

    public void tamaDeath() {
        this.enableButtons("jouer", false);
        this.enableButtons("nourrir", false);
        ((AutoTamaGame)this.game).tamaDeath(this.getMonTamagoshi());
    }
}
