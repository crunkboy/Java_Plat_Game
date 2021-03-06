package fr.uha.ensisa.abalone_game.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import fr.uha.ensisa.abalone_game.Controller.Controle;

@SuppressWarnings("serial")
public class BouleJoueur extends JButton {
	// cpteur is used for assigning the id of each of the boules placed on the
	// board and also a counter to the number of boules on the board
	private static int cpteur = 0;
	public int id;
	public Point point;
	private boolean s = false;
	ImageIcon icon;

	public void setId(int id) {
		this.id = id;
	}

	public boolean isS() {
		return s;
	}

	public void setS(boolean s) {
		this.s = s;
	}

	// called at the time of populating the board
	public BouleJoueur(Point point, String img) {
		icon = new ImageIcon(new ImageIcon(getClass().getResource(img))
				.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
		id = cpteur;
		cpteur++;
		this.point = point;
		this.setIcon(icon);
		setBorderPainted(false);
		setBorder(null);
		setContentAreaFilled(false);
		this.addMouseListener(new Controle());
		this.addKeyListener(new Controle());
		this.setFocusPainted(false);
		setVisible(true);

	}

	public int getId() {
		return id;
	}

	public Point getPoint() {
		return point;
	}

	@Override
	protected void paintBorder(Graphics g) {
		super.repaint();
		setLocation(this.point);
		if (getModel().isEnabled() && s == true) {
			g.setColor(Color.GREEN);

		} else {
			g.setColor(getBackground());
		}
		g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
	}

}
