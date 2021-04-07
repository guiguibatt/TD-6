package dessin;

import java.awt.Color;
import java.awt.Graphics;

public abstract class ObjetGraphique {

    private Color couleur;
    private boolean visible;

    protected ObjetGraphique() {
        this(Color.black);
    }

    protected ObjetGraphique(Color c) {
        couleur = c;
        visible = true;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setColor(Color c) {
        couleur = c;
    }

    public Color getColor() {
        return (couleur);
    }

    public void dessineToi(Graphics g) {
        g.setColor(getColor());
    }

    public abstract boolean contient(int x, int y);
}
