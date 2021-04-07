package dessin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Rectangle extends ObjetGraphique {

    private java.awt.Rectangle monRectangle;

    public Rectangle(int x, int y, int largeur, int hauteur, Color c) {
        monRectangle = new java.awt.Rectangle(x, y, largeur, hauteur);
        setColor(c);
    }

    public Rectangle(Point p, int largeur, int hauteur, Color c) {
        this(p.x, p.y, largeur, hauteur, c);
    }


    public Rectangle(Point p, int largeur, int hauteur) {
        this(p.x, p.y, largeur, hauteur, Color.BLACK);
    }

    @Override
    public void dessineToi(Graphics g) {
        super.dessineToi(g);
        g.drawRect(monRectangle.x, monRectangle.y, monRectangle.width, monRectangle.height);
    }

    @Override
    public boolean contient(int x, int y) {
        return monRectangle.contains(x, y);
    }

}
