package visuel;

public class TamaSwing extends javax.swing.JFrame {

    public TamaSwing() {
        add(new TamaJPanel());
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TamaSwing t = new TamaSwing();
        t.setSize(400, 400);
        t.setLocationRelativeTo(null);
        t.setDefaultCloseOperation(EXIT_ON_CLOSE);
        t.setTitle("Toto Swing");
        t.setVisible(true);
    }

}
