package graphic;

import dessin.Cercle;
import dessin.Ligne;
import dessin.ObjetGraphique;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;

public class TamaJPanel extends JPanel {
    private List<ObjetGraphique> figures = new ArrayList();
    private String phraseEtat;
    private String phraseReponse;
    private String age;

    public TamaJPanel() {
        this.figures.add(new Cercle(new Point(80, 100), 20));
        this.figures.add(new Cercle(new Point(160, 100), 20));
        this.figures.add(new Cercle(new Point(110, 150), 100));
        this.figures.add(new Ligne(130, 120, 130, 150));
        this.figures.add(new Ligne(130, 150, 100, 150));
        this.phraseEtat = "";
        this.phraseReponse = "";
    }

    public void phraseEtat(String s, String age) {
        this.phraseEtat = s;
        this.age = age;
        this.repaint();
    }

    public void phraseReponse(String s) {
        this.phraseReponse = s;
        this.repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Iterator var3 = this.figures.iterator();

        while(var3.hasNext()) {
            ObjetGraphique og = (ObjetGraphique)var3.next();
            og.dessineToi(g);
            g.setColor(Color.WHITE);
            g.fillArc(10, 30, 50, 100, 50, 25);
            g.fillRoundRect(5, 5, 250, 40, 55, 100);
            g.setColor(Color.BLACK);
            g.drawString(this.phraseEtat, 15, 20);
            g.drawString(this.phraseReponse, 15, 38);
            g.drawString(this.age, 220, 150);
            g.fillArc(50, 120, 130, 120, 180, 180);
            g.setColor(this.getBackground());
            g.fillArc(50, 120, 130, 90, 180, 180);
        }

    }
}
