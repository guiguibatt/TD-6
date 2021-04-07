package visuel;

public class Tama extends javax.swing.JFrame {

    public Tama() {
        add(new TamaJPanel());
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Tama t = new Tama();
        t.setSize(400, 400);
        t.setLocationRelativeTo(null);
        t.setDefaultCloseOperation(EXIT_ON_CLOSE);
        t.setTitle("Toto Swing");
        t.setVisible(true);
    }

}
