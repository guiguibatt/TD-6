package visuel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import javax.swing.JPanel;

import dessin.*;

public class TamaJPanel extends JPanel {

    private static List<Color> listeCouleurs;

    private List<ObjetGraphique> listeFigures;
    private Cercle oeilGauche, oeilDroit, tete;
    private Rectangle bouche;


    public TamaJPanel() {


        oeilGauche = new Cercle(new Point(160, 150), 20);
        oeilDroit = new Cercle(new Point(240, 150), 20);
        tete = new Cercle(new Point(200, 200), 100);
        bouche = new Rectangle(new Point(150, 220), 100, 40);

        listeCouleurs = Arrays.asList(Color.BLACK, Color.BLUE, Color.GREEN, Color.RED, Color.ORANGE, Color.WHITE);

        listeFigures = new ArrayList<>(Arrays.asList(oeilDroit, oeilGauche, bouche, tete));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // À ne pas oublier !!
        for (ObjetGraphique og : listeFigures) {
            if (og.isVisible()) {
                og.dessineToi(g);
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        for (ObjetGraphique figure : listeFigures) {
            if (figure.isVisible() && figure.contient(e.getX(), e.getY())) {
                // si la figure est active et contient le clique
                Color couleurFigureCliquee = figure.getColor();
                int indexCouleurSuivante = listeCouleurs.indexOf(couleurFigureCliquee) + 1;
                if (indexCouleurSuivante == listeCouleurs.size())
                    figure.setColor(listeCouleurs.get(0)); // il faut repartir du début
                else
                    figure.setColor(listeCouleurs.get(indexCouleurSuivante));
                // rafraichissement du composant
                repaint();
                // On a trouvé la figure cliquée, donc on sort de la méthode
                return;
            }
        }
    }



    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
