package dessin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * The Class Cercle.
 */
public class Cercle extends ObjetGraphique {

    private Point centre;
    private int rayon;

    public Cercle() {
        this(new Point(), 0);
    }

    public Cercle(Point centre, int rayon) {
        this(centre, rayon, Color.BLACK);
    }

    public Cercle(Point centre, int rayon, Color couleur) {
        super(couleur);
        this.centre = centre;
        this.rayon = rayon;
    }

    public void setLocation(Point p) {
        centre = p;
    }

    public void setRayon(int r) {
        rayon = r;
    }

    public Point getLocation() {
        return centre;
    }

    public int getRayon() {
        return rayon;
    }

    public void dessineToi(Graphics g) {
        super.dessineToi(g);
        int diameter = rayon * 2;
        g.drawOval(centre.x - rayon, centre.y - rayon, diameter, diameter);
    }

    public boolean contient(int vx, int vy) {
        int dx, dy, d;
        dx = vx - centre.x;
        dy = vy - centre.y;
        d = dx * dx + dy * dy;
        return (d <= (rayon * rayon));
    }

}
