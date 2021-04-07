package jeu;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import graphic.TamaFrame;
import tamagoshis.GrosJoueur;
import tamagoshis.GrosMangeur;
import tamagoshis.Tamagoshi;

public class TamaGameGraphic extends JFrame {
    protected ArrayList<Tamagoshi> listeTotale = new ArrayList();
    protected ArrayList<Tamagoshi> alive = new ArrayList();
    protected ArrayList<TamaFrame> frames = new ArrayList();
    protected JTextArea infos;
    protected int cycle = 0;
    protected int nbActions = 0;

    public TamaGameGraphic() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                TamaGameGraphic.this.close();
            }
        });
        this.initialisation();
    }

    private void initialisation() {
        this.infos = new JTextArea();
        this.add(new JScrollPane(this.infos));
    }

    private double score() {
        int score = 0;

        Tamagoshi t;
        for(Iterator var3 = this.listeTotale.iterator(); var3.hasNext(); score += t.getAge()) {
            t = (Tamagoshi)var3.next();
        }

        return (double)(score * 100 / (Tamagoshi.getLifeTime() * this.listeTotale.size()));
    }

    private void resultat() {
        this.infos.append("==============bilan de la partie==============");
        Iterator var2 = this.listeTotale.iterator();

        while(var2.hasNext()) {
            Tamagoshi t = (Tamagoshi)var2.next();
            String classe = t.getClass().getName();
            classe = classe.substring(classe.lastIndexOf(".") + 1);
            this.infos.append("\n" + t.getName() + " qui était un " + classe + " " + (t.getAge() == Tamagoshi.getLifeTime() ? " a survécu et vous remercie :)" : " n'est pas arrivé au bout et ne vous félicite pas :("));
        }

        this.displayInfos("\nniveau de difficulté : " + this.listeTotale.size() + ", score obtenu :" + this.score() + "%");
    }

    private void newCycle() {
        this.enableButtons("nourrir", true);
        this.enableButtons("jouer", true);
        this.displayInfos("\n==============Cycle n°" +this.cycle + "==============");
        this.nbActions = 0;
        Iterator var2 = this.alive.iterator();

        while(var2.hasNext()) {
            Tamagoshi t = (Tamagoshi)var2.next();
            t.parle();
        }
    }

    private void displayInfos(String informations) {
        System.out.println(informations);
        this.infos.append(informations);
        this.infos.setCaretPosition(this.infos.getDocument().getLength());
    }

    private void isFinished() {
        if (this.nbActions == 2) {
            Iterator i = this.alive.iterator();

            while(true) {
                Tamagoshi t;
                do {
                    if (!i.hasNext()) {
                        if (this.alive.isEmpty()) {
                            this.displayInfos("\n\t============== The END ==============\n\n");
                            this.resultat();
                        } else {
                            this.newCycle();
                        }

                        return;
                    }

                    t = (Tamagoshi)i.next();
                } while(t.consommeEnergie() && t.consommeFun() && !t.vieillit());

                i.remove();
            }
        }
    }

    private void enableButtons(String buttonName, boolean enable) {
        Iterator var4 = this.frames.iterator();

        while(var4.hasNext()) {
            TamaFrame frame = (TamaFrame)var4.next();
            frame.enableButtons(buttonName, enable);
        }

    }

    public void play() {
        List<String> temp = Arrays.asList("Pierre", "Paul", "Jacques", "Zizou", "Karl", "Neo", "Ubufox", "Hal");
        ArrayList<String> names = new ArrayList(temp);
        Iterator var4 = this.frames.iterator();

        while(var4.hasNext()) {
            TamaFrame f = (TamaFrame)var4.next();
            f.dispose();
        }

        this.listeTotale = new ArrayList();
        this.displayInfos("\n============== Nouvelle Partie ==============");
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
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            TamaFrame tamaFrame = new TamaFrame((Tamagoshi)t, this);
            this.frames.add(tamaFrame);
            System.out.println(tamaFrame.getSize());
            if (x * tamaFrame.getSize().width + tamaFrame.getSize().width > screen.width) {
                x = 0;
                y += tamaFrame.getSize().height + 30;
            }

            tamaFrame.setLocation(x * (tamaFrame.getSize().width + 5), y);
            tamaFrame.setVisible(true);
            ++i;
        }

        this.alive = (ArrayList)this.listeTotale.clone();
        this.cycle = 0;
        this.newCycle();
    }

    public void userAction(String action) {
        this.enableButtons(action, false);
        ++this.nbActions;
        this.isFinished();
    }

    private void close() {
        Iterator var2 = this.frames.iterator();

        while(var2.hasNext()) {
            JFrame f = (JFrame)var2.next();
            f.dispose();
        }

    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        TamaGameGraphic jeu = new TamaGameGraphic();
        jeu.setSize(600, 200);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension window = jeu.getSize();
        jeu.setLocationRelativeTo((Component)null);
        jeu.setTitle("Les tamagoshis");
        jeu.setDefaultCloseOperation(3);
        jeu.setVisible(true);
        jeu.play();
    }
}
