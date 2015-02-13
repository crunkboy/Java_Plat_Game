package fr.uha.ensisa.abalone_game.View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DebugGraphics;
import javax.swing.JPanel;

import fr.uha.ensisa.abalone_game.Controller.Controle;

@SuppressWarnings("serial")
public class Echiquier extends JPanel {
	// set of points for holding the current positions of each of the marbles of
	// the two colours
	Point point_b[] = new Point[14];
	Point point_n[] = new Point[14];
	// set of Boule for holding the boules of individual players
	public BouleJoueur boule_n[] = new BouleJoueur[14];
	public BouleJoueur boule_b[] = new BouleJoueur[14];

	public Echiquier() {
		setOpaque(false);
		setBounds(10, 0, 384, 410);
		setInheritsPopupMenu(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
		setForeground(new Color(0, 0, 0));
		setBackground(new Color(250, 250, 210));
		initCoordinates();
		place();
		this.addMouseListener(new Controle());

	}

	public void initCoordinates() {
		// initialising the blue boules positions
		point_b[0] = new Point(95, 325);
		point_b[1] = new Point(130, 325);
		point_b[2] = new Point(165, 325);
		point_b[3] = new Point(200, 325);
		point_b[4] = new Point(235, 325);
		point_b[5] = new Point(77, 290);
		point_b[6] = new Point(112, 290);
		point_b[7] = new Point(147, 290);
		point_b[8] = new Point(182, 290);
		point_b[9] = new Point(217, 290);
		point_b[10] = new Point(252, 290);
		point_b[11] = new Point(130, 258);
		point_b[12] = new Point(165, 258);
		point_b[13] = new Point(200, 258);

		// initialising the black boules positions
		point_n[0] = new Point(95, 54);
		point_n[1] = new Point(130, 54);
		point_n[2] = new Point(165, 54);
		point_n[3] = new Point(200, 54);
		point_n[4] = new Point(235, 54);
		point_n[5] = new Point(77, 88);
		point_n[6] = new Point(112, 88);
		point_n[7] = new Point(147, 88);
		point_n[8] = new Point(182, 88);
		point_n[9] = new Point(217, 88);
		point_n[10] = new Point(252, 88);
		point_n[11] = new Point(130, 122);
		point_n[12] = new Point(165, 122);
		point_n[13] = new Point(200, 122);
	}

	public void place() {
		for (int i = 0; i < 14; i++) {
			// taking the position from the point array and constructing the
			// boules and placing them for both red and blue
			boule_n[i] = new BouleJoueur(point_n[i],
					"/fr/uha/ensisa/abalone_game/Resources/boule_n.png");
			boule_n[i].setLocation(point_n[i]);
			add(boule_n[i]);

		}
		for (int i = 0; i < 14; i++) {

			boule_b[i] = new BouleJoueur(point_b[i],
					"/fr/uha/ensisa/abalone_game/Resources/boule_b.png");
			boule_b[i].setLocation(point_b[i]);
			add(boule_b[i]);

		}
	}

	public void paintBorder(Graphics forme) {
		super.repaint();

		Image img = null;
		try {
			img = ImageIO.read(getClass().getResource(
					"/fr/uha/ensisa/abalone_game/Resources/Trou2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		forme.drawImage(img, 24, 54, null);
	}

	public int getIDB(Object a) // bouleId from its instance
	{
		for (BouleJoueur a1 : boule_n)
			if (a1.equals(a)) {
				return a1.getId();
			}
		for (BouleJoueur a1 : boule_b)
			if (a1.equals(a)) {
				return a1.getId();
			}
		return -1;

	}

	public void setBSelect(int id, boolean a) {
		if (id < 14) {
			for (BouleJoueur v : boule_n)
				if (v.getId() == id) {
					if (a == true) {
						// setS boolean is used to select the marble
						v.setS(true);
						v.repaint();
					} else {
						v.setS(false);
						v.repaint();
					}

				}
		} else {
			for (BouleJoueur v1 : boule_b)
				if (v1.getId() == id) {
					if (a == true) {
						v1.setS(true);
						v1.repaint();
					} else {

						v1.setS(false);
						v1.repaint();
					}
				}
		}
	}

	public void setBState(int id, boolean etat, Point position) // show the
																// boule at its
																// current
																// position and
																// make it
																// disappear
																// when pushed
																// out of play
	{
		if (id < 14) {
			for (BouleJoueur v : boule_n)
				if (v.getId() == id) {
					if (etat != true) {
						v.point = position;
						v.setVisible(true);
						v.repaint();
						return;
					} else if (etat == true) {
						v.setVisible(false);
						v.repaint();
						return;
					}
				}
		} else {
			for (BouleJoueur v : boule_b)
				if (v.getId() == id) {
					if (etat != true) {
						v.point = position;
						v.setVisible(true);
						v.repaint();
						return;
					} else if (etat == true) {
						v.setVisible(false);
						v.repaint();
						return;
					}
				}
		}
	}

	public void update(boolean a, Point b, int c) {
		setBState(c, a, b);

	}

	public void update(boolean v, int id) {
		setBSelect(id, v);

	}
}
