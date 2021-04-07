package jeu;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import graphic.TamaFrame;
import tamagoshis.GrosJoueur;
import tamagoshis.GrosMangeur;
import tamagoshis.Tamagoshi;

public class AutoTamaGame extends TamaGameGraphic {
    public AutoTamaGame() {
        this.listeTotale = new ArrayList();
        this.alive = new ArrayList();
        this.frames = new ArrayList();
        Tamagoshi.setLifeTime(1000);
        this.initialisation();
    }

    private void initialisation() {
        JMenuBar menubar = new JMenuBar();
        JMenu partie = new JMenu("jeu");
        menubar.add(partie);
        JMenuItem nou = new JMenuItem("nouvelle partie", 78);
        nou.setAccelerator(KeyStroke.getKeyStroke(78, 8));
        nou.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AutoTamaGame.this.initPlay();
            }
        });
        partie.add(nou);
        this.setJMenuBar(menubar);
        this.infos = new JTextArea();
        this.add(new JScrollPane(this.infos));
    }

    private double score() {
        int score = 0;

        Tamagoshi t;
        for(Iterator var3 = this.listeTotale.iterator(); var3.hasNext(); score += t.getAge()) {
            t = (Tamagoshi)var3.next();
        }

        return (double)score;
    }

    private void resultat() {
        this.infos.append("==============bilan==============");
        Iterator var2 = this.listeTotale.iterator();

        while(var2.hasNext()) {
            Tamagoshi t = (Tamagoshi)var2.next();
            String classe = t.getClass().getName();
            classe = classe.substring(classe.lastIndexOf(".") + 1);
            this.infos.append("\n" + t.getName() + " qui était un " + classe + " a survécu jusqu'à l'âge de " + t.getAge());
        }

        this.afficheInfos("\nniveau de difficulté : " + this.listeTotale.size() + ", score obtenu :" + this.score());
    }

    private void afficheInfos(String informations) {
        System.out.println(informations);
        this.infos.append(informations);
        this.infos.setCaretPosition(this.infos.getDocument().getLength());
    }

    private void isFinished() {
        if (this.alive.isEmpty()) {
            this.afficheInfos("\n\t============== The END ==============\n\n");
            this.resultat();
        }

    }

    public final void initPlay() {
        Iterator var2 = this.frames.iterator();

        while(var2.hasNext()) {
            JFrame frame = (JFrame)var2.next();
            frame.dispose();
        }

        this.listeTotale = new ArrayList();
        this.alive = new ArrayList();
        this.frames = new ArrayList();
        List<String> temp = Arrays.asList("Pierre", "Paul", "Jacques", "Zizou", "Karl", "Neo", "Pikachu", "Goldorak");
        ArrayList<String> names = new ArrayList(temp);
        int n = 0;

        while(n <= 0 || n > 8) {
            try {
                n = Integer.parseInt(JOptionPane.showInputDialog("Entrez le nombre de tamagoshis désiré (Max : 8) :", "3"));
            } catch (NumberFormatException var11) {
                JOptionPane.showMessageDialog((Component)null, "Entrez un nombre svp (Max : 8)", "Erreur de saisie", 0);
            }
        }

        int i = 0;
        int x = 0;

        for(int y = 0; i < n; ++x) {
            String name = (String)names.remove((int)(Math.random() * (double)names.size()));
            Tamagoshi t;
            if (Math.random() < 0.5D) {
                t = new GrosJoueur(name);
            } else {
                t = new GrosMangeur(name);
            }

            this.listeTotale.add(t);
            ((Tamagoshi)t).setInitialPause(1000 * n);
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            TamaFrame tamaFrame = new TamaFrame((Tamagoshi)t, this);
            System.out.println(tamaFrame.getSize());
            if (x * tamaFrame.getSize().width + tamaFrame.getSize().width > screen.width) {
                x = 0;
                y += tamaFrame.getSize().height + 30;
            }

            tamaFrame.setLocation(x * (tamaFrame.getSize().width + 5), y);
            tamaFrame.setVisible(true);
            this.frames.add(tamaFrame);
            ++i;
        }

        this.alive = (ArrayList)this.listeTotale.clone();
        JOptionPane.showMessageDialog(this, "cliquez pour lancer la partie");
        this.launch();
    }

    public final void tamaDeath(Tamagoshi t) {
        this.alive.remove(t);
        Iterator var3 = this.alive.iterator();

        while(var3.hasNext()) {
            Tamagoshi tt = (Tamagoshi)var3.next();
            tt.decreasePause(500);
        }

        this.isFinished();
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        AutoTamaGame jeu = new AutoTamaGame();
        jeu.setSize(600, 200);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension window = jeu.getSize();
        jeu.setLocation((screen.width - window.width) / 2, (screen.height - window.height) / 2);
        jeu.setTitle("Les tamagoshis");
        jeu.setDefaultCloseOperation(3);
        jeu.setVisible(true);
        jeu.initPlay();
    }

    private void launch() {
        Iterator var2 = this.listeTotale.iterator();

        while(var2.hasNext()) {
            Tamagoshi t = (Tamagoshi)var2.next();
            Thread tamaThread = new Thread(t);
            tamaThread.start();
        }

    }
}
