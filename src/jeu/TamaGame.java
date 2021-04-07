package jeu;

import java.util.*;

import tamagoshis.GrosJoueur;
import tamagoshis.GrosMangeur;
import tamagoshis.Lunatique;
import tamagoshis.Tamagoshi;
import tamagoshi.util.Utilisateur;

import javax.swing.*;

/**
 * un petit jeu avec des tamagoshis
 */
public class TamaGame {

	private ArrayList<Tamagoshi> listeTotale, alive;
	private ArrayList<TamaFrame> frameTamagoshi;
	private List<String> nameList;

	/** build the game */
	public TamaGame() {
		listeTotale = new ArrayList<Tamagoshi>();
		alive = new ArrayList<Tamagoshi>();
		nameList = new ArrayList<>();
		frameTamagoshi = new ArrayList<>();
		initialisation();
	}

	private void initialisation(){
		nameList.addAll( Arrays.asList("Hurricane","Sanoma","Daddario","Rudolph","the Red","Copacabana","Copa","Little Bear","Diva","Southside","Arizona","Sunrise","Yum Yum","Buffalo","Doctor Funk"));
		String value = JOptionPane.showInputDialog("Combien de tamagoshi joue ?");
		int n = Integer.parseInt(value);
		for (int i = 0; i < n; i++) {
			TamaFrame tf = new TamaFrame(this);
			if(Math.random() > 0.5) {
				double r = Math.random();
				if(r < 0.33) {
					GrosJoueur gj = new GrosJoueur(nameList.remove((int)(Math.random() * nameList.size())));
					listeTotale.add(gj);
					tf.setTamagoshi(gj);
					frameTamagoshi.add(tf);
				}else if(r > 0.33 && r < 0.66) {
					GrosMangeur gm = new GrosMangeur(nameList.remove((int)(Math.random() * nameList.size())));
					listeTotale.add(gm);
					tf.setTamagoshi(gm);
					frameTamagoshi.add(tf);
				}else {
					Lunatique l = new Lunatique(nameList.remove((int)(Math.random() * nameList.size())));
					listeTotale.add(l);
					tf.setTamagoshi(l);
					frameTamagoshi.add(tf);
				}
			}else {
				Tamagoshi tg = new Tamagoshi(nameList.remove((int)(Math.random() * nameList.size())));
				listeTotale.add(tg);
				tf.setTamagoshi(tg);
				frameTamagoshi.add(tf);
			}
		}
		for (TamaFrame tf :frameTamagoshi) {
			tf.initialisation();
		}
		alive.addAll(listeTotale);
	}



	/**
	 * returns the calculated score
	 *
	 *
	 * @return the score
	 */
	private double score() {
		int score = 0;
		for (Tamagoshi t : listeTotale)
			score += t.getAge();
		return score * 100 / (Tamagoshi.getLifeTime() * listeTotale.size());
	}

	private void resultat() {
		System.out.println("-------------bilan------------");
		for (Tamagoshi t : listeTotale) {
			String classe = t.getClass().getSimpleName();
			System.out.println(t.getName() + " qui était un " + classe + " "
					+ (t.getAge() == Tamagoshi.getLifeTime() ? " a survécu et vous remercie :)"
							: " n'est pas arrivé au bout et ne vous félicite pas :("));
		}
		System.out.println("\nniveau de difficulté : " + listeTotale.size() + ", score obtenu : " + score() + "%");
	}


	public void finDeTour() {
		for (TamaFrame tf: frameTamagoshi) {
			for (JButton fdt: tf.getButton()) {
				fdt.setEnabled(true);
			}
		}
	}

	/** Launch a new instance of the game */
	public static void main(String[] args) {
		TamaGame TMG = new TamaGame();
	}

	public void Mort() {
		for (Iterator<TamaFrame> i = frameTamagoshi.iterator(); i.hasNext();) {
			TamaFrame tf = i.next();
			if (!tf.getTamagoshi().consommeEnergy() || !tf.getTamagoshi().consommeFun() || tf.getTamagoshi().vieillit()) {


				System.out.println(tf.getTamagoshi().getName());
				alive.remove(tf.getTamagoshi());
				for (JButton fdt: tf.getButton()) {
					fdt.setEnabled(false);}
				tf.dispose();

			}else {
				tf.getTamagoshi().parle();
			}



		}

	}
	
	@Override
	public String toString() {
		return "tamagame";
	}

}
