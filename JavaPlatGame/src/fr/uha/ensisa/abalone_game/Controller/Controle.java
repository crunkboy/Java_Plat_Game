package fr.uha.ensisa.abalone_game.Controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fr.uha.ensisa.abalone_game.View.*;
import fr.uha.ensisa.abalone_game.Model.Boule;
import fr.uha.ensisa.abalone_game.Model.Joueur;
import fr.uha.ensisa.abalone_game.Model.Partie;
import fr.uha.ensisa.abalone_game.Model.Trou;

//the controller class with 3 types of listeners, for keystrokes, Mouseevents and button actions
public class Controle implements KeyListener, MouseListener, ActionListener {

	private static Partie a;
	private static View a1;
	private static int[] joueurs = new int[2];
	private int[][] j1 = new int[14][2]; //matrice des ids boule/trou
	private int[][] j2 = new int[14][2];

	// events associated with ball movements using keypress,
	@Override
	public void keyReleased(KeyEvent arg0) {

		switch (arg0.getKeyChar()) {
		// latérale
		case '4':// System.out.println("touche gauche");
			playCoups(4);
			break;

		case '6':// System.out.println("touche droite");
			playCoups(1);
			break;

		case '7':// System.out.println("touche haut gauche");
			playCoups(5);
			break;

		case '9':// System.out.println("touche haut droite");
			playCoups(0);
			break;

		case '1':// System.out.println("touche bas gauche");
			playCoups(3);
			break;

		case '3':// System.out.println("touche bas droite");
			playCoups(2);
			break;

		}

	}

	public void b1Selected(int direction) // lorsque 1e boule est selectionné
	{
		boolean depl = Joueur.deplacer(a.table.getBoulesSelectionees().get(0), direction);
		// déplacement a été effectué
		if (depl == true) {
			// rendre toute les bouleJoueur2 inactif et changer de joueur
			a.table.jouer();
			a.table.setNbSelect(0);
			System.out.println("deplacement effectué");
		}
		// sinon essayer une autre direction
	}

	public void b2Selected(int direction) // lorsque 2 boule sont selectionné
	{
		if (Joueur.testFlèche2(direction)) // deplacement latéral possible pour
											// ces 2 pion
		{
			Joueur.deplacer(a.table.getBoulesSelectionees().get(0), direction);
			Joueur.deplacer(a.table.getBoulesSelectionees().get(1), direction);
			a.table.jouer();
			a.table.setNbSelect(0);
			// System.out.println("deplacement latéral possible pour ces 2 pion");
		}
	}

	public void b3Selected(int direction) // lorsque 3 boule sont selectionné
	{
		if (Joueur.testFlèche2(direction)) // deplacement latéral possible pour  ces 3 pion
											
		{
			Joueur.deplacer(a.table.getBoulesSelectionees().get(0), direction);
			Joueur.deplacer(a.table.getBoulesSelectionees().get(1), direction);
			Joueur.deplacer(a.table.getBoulesSelectionees().get(2), direction);
			a.table.jouer();
			a.table.setNbSelect(0);
			// System.out.println("deplacement latéral possible pour ces 3 pion");
		}
	}

	public void playCoups(int direction) // cette fonction permet d'effectué le déplacement pour chaque coups/direction choisit par le joueur
	{
		if (a != null && a.table.getNbSelect() == 1) {
			b1Selected(direction);

		} else if (a != null && a.table.getNbSelect() == 2) {
			b2Selected(direction);
		} else if (a != null && a.table.getNbSelect() == 3) {
			b3Selected(direction);
		} else {
			System.out.println("pas de boule selectionné");
		}
	}


	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	public void keyPressed(KeyEvent e) {

	}

	// Selection des Boules
	@Override
	public void mouseClicked(MouseEvent arg0) {
		int c = a1.getEchiquier().getIDB(arg0.getSource()); // on récupère l'iD de la boule selectionnée
		if ((a != null && a.table.isPartieEnd() == false)&& (a.table.getTour().equals(a.j1) && c >= 14) && arg0.getButton() == 1) 
			{
			a.j1.select(c);
			} 
		else if ((a != null && a.table.isPartieEnd() == false)&& (a.table.getTour().equals(a.j2) && c < 14)&& arg0.getButton() == 1)
		{
			a.j2.select(c);
		} else if (arg0.getButton() == 3 && a != null
				&& a.table.getNbSelect() >= 1) // deselectionne la totalité des boules selectionnées
		{
			a.table.setNbSelect(0);

		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Stub de la méthode généré automatiquement

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Stub de la méthode généré automatiquement

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Stub de la méthode généré automatiquement

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Stub de la méthode généré automatiquement

	}

	public static void nouvellePartie() {
		if (a == null) {
			a = new Partie();
			a1.setTable(a.table);
			a.table.setTour(a.j1);
			a1.newGame();
		} else {
			Trou.setNum(0);
			Boule.setCpteur(0);
			Joueur.setCpteur(0);
			a = null;
			a = new Partie();
			a1.setTable(a.table);
			a.table.setTour(a.j1);
			a.table.nouvellePartie();
			a1.newGame();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		switch (arg0.getActionCommand()) {

		case "New Game":
			nouvellePartie();

			break;

		case "Save Game":

			if (a != null) {
				save();
			}

			break;

		case "Load Game":
			nouvellePartie();
			load();
			break;

		case "Quit":
			if (a != null)
				save();
			System.exit(0);

			break;
		}

	}

	public void save() {
		int i = 0;
		if (a.table.getTour().equals(a.j1)) {
			joueurs[0] = -2;
			joueurs[1] = -1;
		} else {
			joueurs[1] = -2;
			joueurs[0] = -1;
		}

		for (Boule[] c : a.table.BouleJoueur1()) {
			for (Boule d : c) {
				j1[i][0] = d.getIdBoule();
				j1[i][1] = d.getTrou().getIdTrou();
				i++;
			}
		}
		i = 0;
		for (Boule[] c : a.table.bouleJoueur2()) {
			for (Boule d : c) {
				j2[i][0] = d.getIdBoule();
				j2[i][1] = d.getTrou().getIdTrou();
				i++;
			}
		}
		write_Fichier(j1, j2, joueurs, "save.txt");
	}

	public void load() {

		read_Fichier(j1, j2, joueurs, "save.txt");

		for (Boule[] v : a.table.BouleJoueur1()) {
			for (Boule d : v) {
				for (int i1 = 0; i1 < 14; i1++)
					if (d.getIdBoule() == j1[i1][0]) {
						d.setTrou(a.table.getTrouByID(j1[i1][1]));
					}
			}
		}

		for (Boule[] v : a.table.bouleJoueur2()) {
			for (Boule d : v) {
				for (int i1 = 0; i1 < 14; i1++)
					if (d.getIdBoule() == j2[i1][0]) {
						d.setTrou(a.table.getTrouByID(j2[i1][1]));
					}
			}
		}

		if (joueurs[1] == -1 && joueurs[0] == -2) // tour du joueur1(rouge)
		{
			a.table.setTour(a.j1);
			a.table.jouer();
			a.table.jouer();
			System.out.println("\nle joueur rouge commence");

		} 
		else if (joueurs[1] == -2 && joueurs[0] == -1) //tour du joueur2(bleu)
		{
			a.table.setTour(a.j2);
			a.table.jouer();
			a.table.jouer();
			System.out.println("\nle joueur bleu commence");
		}
		else
			System.out.println("\nProblème de chargement");
			
	}

	public void write_Fichier(int[][] joueur_1, int[][] joueur_2, int[] j_1, String nomFichier) {
		File f = null; 
		// Pour la premiere ecriture si le fichier n'existe pas cette ligne permet de le créer sinon elle écrit directement dans le fichier
		try {
			f = new File(nomFichier);
			PrintWriter ecrire = new PrintWriter(new BufferedWriter(
					new FileWriter(f)));
			ecrire.println(j_1[0]);
			for (int i = 0; i < joueur_1.length; i++) {
				ecrire.println(joueur_1[i][0]);
				ecrire.println(joueur_1[i][1]);
			}
			ecrire.println(j_1[1]);
			for (int i = 0; i < joueur_2.length; i++) {
				ecrire.println(joueur_2[i][0]);
				ecrire.println(joueur_2[i][1]);
			}
			ecrire.close();
		} catch (IOException exception) {
			System.out.println("Erreur lors de la lecture : "
					+ exception.getMessage());
		}
	}

	public void read_Fichier(int[][] joueur_1, int[][] joueur_2, int[] j_1,
			String nomFichier) {
		// lecture du fichier texte
		InputStream fich = null;
		try {
			fich = new FileInputStream(nomFichier);
			InputStreamReader fichiLu = new InputStreamReader(fich);
			BufferedReader br = new BufferedReader(fichiLu);

			j_1[0] = Integer.parseInt(br.readLine());

			for (int i1 = 0; i1 < joueur_1.length; i1++) {
				joueur_1[i1][0] = Integer.parseInt(br.readLine());
				joueur_1[i1][1] = Integer.parseInt(br.readLine());
			}

			j_1[1] = Integer.parseInt(br.readLine());

			for (int i1 = 0; i1 < joueur_2.length; i1++) {
				joueur_2[i1][0] = Integer.parseInt(br.readLine());
				joueur_2[i1][1] = Integer.parseInt(br.readLine());
			}
			br.close();
		} catch (IOException exception) {
			System.out.println("Erreur lors de la lecture : "
					+ exception.getMessage());
		}

	}

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		;

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				View window = new View();
				a1 = window;
				window.getFrame().setVisible(true);

			}
		});

	}
}
