package visuel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import dessin.*;

public class TamaJPanel extends JPanel implements MouseListener, ActionListener {

    private static List<Color> listeCouleurs;

    private List<ObjetGraphique> listeFigures;
    private Cercle oeilGauche, oeilDroit, tete;
    private Rectangle bouche;
    private JButton boutonOeilDroit, boutonOeilGauche, boutonBouche, boutonTete;

    public TamaJPanel() {

        // les objets graphiques
        oeilGauche = new Cercle(new Point(160, 150), 20);
        oeilDroit = new Cercle(new Point(240, 150), 20);
        tete = new Cercle(new Point(200, 200), 100);
        bouche = new Rectangle(new Point(150, 220), 100, 40);

        listeCouleurs = Arrays.asList(Color.BLACK, Color.BLUE, Color.GREEN, Color.RED, Color.ORANGE, Color.WHITE);

        listeFigures = new ArrayList<>(Arrays.asList(oeilDroit, oeilGauche, bouche, tete));

        // Ce jpanel est aussi son propre écouteur
        this.addMouseListener(this);

        // les boutons
        boutonBouche = new JButton("Bouche");
        boutonOeilGauche = new JButton("OeilGauche");
        boutonOeilDroit = new JButton("OeilDroit");
        boutonTete = new JButton("Tete");
        add(boutonBouche);
        add(boutonOeilGauche);
        add(boutonOeilDroit);
        add(boutonTete);

        // c'est aussi le jpanel qui écoute les boutons
        boutonBouche.addActionListener(this);
        boutonOeilGauche.addActionListener(this);
        boutonOeilDroit.addActionListener(this);
        boutonTete.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonBouche) {
            bouche.setVisible(!bouche.isVisible());
        } else if (e.getSource() == boutonOeilDroit) {
            oeilDroit.setVisible(!oeilDroit.isVisible());
        }

        else if (e.getSource() == boutonOeilGauche) {
            oeilGauche.setVisible(!oeilGauche.isVisible());
        }

        else {
            tete.setVisible(!tete.isVisible());
        }
        repaint();
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
