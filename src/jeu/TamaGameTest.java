package jeu;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import visuel.Tama;
import tamagoshis.Tamagoshi;


/**
 * un petit jeu avec des tamagoshis
 */
public class TamaGameTest extends JFrame{

	protected ArrayList<Tamagoshi> listeTotale = new ArrayList();
	protected ArrayList<Tamagoshi> alive = new ArrayList();
	protected ArrayList<Tama> frames = new ArrayList();
	protected JTextArea infos;
	protected int cycle = 0;
	protected int nbActions = 0;

	/** build the game */
	/*private TamaGameTest() {
		allTamagoshis = new ArrayList<Tamagoshi>();
		aliveTamagoshis = new ArrayList<Tamagoshi>();
		initialisation();
	}
	*/

	public TamaGameTest() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				TamaGameTest.this.close();
			}
		});
		this.initialisation();
	}



	private void initialisation() {
		this.infos = new JTextArea();
		this.add(new JScrollPane(this.infos));


	}

	/*private void initialisation() {
		System.out.println("Entrez le nombre de tamagoshis désiré !");

		Tama t=new Tama();
		t.setSize(400, 100);
		t.setLocationRelativeTo(null);
		t.setDefaultCloseOperation(EXIT_ON_CLOSE);
		t.setTitle("Initialisation");
		t.setVisible(true);

		int n = 0;
		while (n < 1) {
			System.out.println("Saisisez un nombre > 0 :");
			try {
				n = Integer.parseInt(saisieClavier());
			} catch (NumberFormatException e) {
				System.out.println("Il faut saisir un nombre !");
			}
		}
		for (int i = 0; i < n; i++) {
			System.out.println("Entrez le nom du tamagoshi numéro " + i + " : ");
			if (Math.random() < .5)
				allTamagoshis.add(new GrosJoueur(saisieClavier()));
			else
				allTamagoshis.add(new GrosMangeur(saisieClavier()));

		}

		 aliveTamagoshis = new ArrayList<Tamagoshi>(allTamagoshis);
	}

	/**
	 * returns the selected tamagoshi
	 * 
	 * @param question the question to ask to the user
	 * @return the selected instance
	 */
	/*
	private Tamagoshi tamaSelection(String question) {

		System.out.println(question);
		int index = 0;
		for (ListIterator<Tamagoshi> iterator = aliveTamagoshis.listIterator(); iterator.hasNext();) {
			System.out.print("\t(" + (iterator.nextIndex()) + ") " + iterator.next().getName() + "  ");
		}
		System.out.print("\n\tEntrez un choix : ");
		try {
			index = Integer.parseInt(saisieClavier());
		} catch (NumberFormatException e) {
			System.out.println("Il faut saisir un nombre !");
			return tamaSelection(question);
		}
		if (index < 0 || index >= aliveTamagoshis.size()) {
			System.out.println("il n'y a pas de tamagoshi portant le numéro " + index);
			return tamaSelection(question);
		}
		return aliveTamagoshis.get(index);
	}



	/**
	 * Starts the game
	 */


	public void play() {

		Iterator var4 = this.frames.iterator();

		while(var4.hasNext()) {
			Tama f = (Tama)var4.next();
			f.dispose();
		}

		this.listeTotale = new ArrayList();
		int n = 0;
		while(n>=8){
			n = Integer.parseInt(JOptionPane.showInputDialog("Veuillez saisair le nombre de Tamagoshi (entre 1 et 8)"));
		}

		/*while (! aliveTamagoshis.isEmpty()) {
			System.out.println("\n------------Cycle n°" + (cycle++) + "-------------");
			for (Tamagoshi t : aliveTamagoshis)
				t.parle();
			tamaSelection("\n\tNourrir quel tamagoshi ?").mange();
			tamaSelection("\n\tJouer avec quel tamagoshi ?").joue();
			
			for (Iterator<Tamagoshi> iterator = aliveTamagoshis.iterator(); iterator.hasNext();) {
				Tamagoshi t = iterator.next();
				if (!t.consommeEnergy() || !t.consommeFun() || t.vieillit())
					iterator.remove();
			}
		}
		System.out.println("\n\t--------- fin de partie !! ----------------\n\n");
		resultat();*/

	}

	/*
	private double score() {

		int score = 0;
		for (Tamagoshi t : allTamagoshis)
			score += t.getAge();
		return score * 100 / (Tamagoshi.getLifeTime() * allTamagoshis.size());
	}

	private void resultat() {
		System.out.println("-------------bilan------------");
		for (Tamagoshi t : allTamagoshis) {
			String classe = t.getClass().getSimpleName();
			System.out.println(t.getName() + " qui était un " + classe + " "
					+ (t.getAge() == Tamagoshi.getLifeTime() ? " a survécu et vous remercie :)"
							: " n'est pas arrivé au bout et ne vous félicite pas :("));
		}
		System.out.println("\nniveau de difficulté : " + allTamagoshis.size() + ", score obtenu : " + score() + "%");
	}
	*/


	/** Launch a new instance of the game */

	public static void main(String[] args) {

		TamaGameTest jeu = new TamaGameTest();
		jeu.setSize(600, 200);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension window = jeu.getSize();
		jeu.setLocationRelativeTo(null);
		jeu.setTitle("Tamagoshis");
		jeu.setDefaultCloseOperation(3);
		jeu.setVisible(true);
		jeu.play();
	}

	private void close() {
		Iterator var2 = this.frames.iterator();

		while(var2.hasNext()) {
			JFrame f = (JFrame)var2.next();
			f.dispose();
		}
	}
	
	@Override
	public String toString() {
		return "tamagame";
	}

}
