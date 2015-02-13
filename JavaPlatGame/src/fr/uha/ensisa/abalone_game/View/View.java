package fr.uha.ensisa.abalone_game.View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import fr.uha.ensisa.abalone_game.Controller.Controle;
import fr.uha.ensisa.abalone_game.Model.Hexagone;
import fr.uha.ensisa.abalone_game.Observer.Observateur;

@SuppressWarnings("serial")
public class View extends JFrame implements Observateur {
	int player1BOut; // number of ball of player1 out of play
	int player2BOut;
	private JLabel player1WinnerLable; // a label to display the winner icon,
										// for player 1
	private JLabel player2WinnerLable;
	private JLabel lblP1; // player1 stats label
	private JLabel lblP2;
	private JLabel labelPlayerRed;
	private JLabel labelPlayerBlue;
	private ImageIcon iconPlayerRed = new ImageIcon(new ImageIcon(getClass()
			.getResource("/fr/uha/ensisa/abalone_game/Resources/joueur1.png"))
			.getImage().getScaledInstance(103, 64, Image.SCALE_DEFAULT));
	public ImageIcon iconPlayerBlue = new ImageIcon(new ImageIcon(getClass()
			.getResource("/fr/uha/ensisa/abalone_game/Resources/joueur2.png"))
			.getImage().getScaledInstance(120, 60, Image.SCALE_DEFAULT));
	// public ImageIcon iA = new ImageIcon(new
	// ImageIcon(getClass().getResource("/fr/uha/ensisa/abalone_game/Resources/IA.png")).getImage().getScaledInstance(120,
	// 60, Image.SCALE_DEFAULT));
	public ImageIcon iconPlay = new ImageIcon(new ImageIcon(getClass()
			.getResource("/fr/uha/ensisa/abalone_game/Resources/play.png"))
			.getImage().getScaledInstance(30, 20, Image.SCALE_DEFAULT));
	public ImageIcon iconPause = new ImageIcon(new ImageIcon(getClass()
			.getResource("/fr/uha/ensisa/abalone_game/Resources/pause.png"))
			.getImage().getScaledInstance(30, 20, Image.SCALE_DEFAULT));
	private JMenuBar menuBar; // the main menu bar
	private JMenu gameMenu;
	private JMenuItem saveMenu;
	private JMenuItem newMenu;

	private JMenuItem loadMenu;
	private JMenuItem quitMenu;
	private JMenu helpMenu;
	private JMenuItem rulesMenu;
	private JMenuItem aboutMenu;
	private JPanel panel;
	private JMenuItem mntmInstruction;
	private JPanel panel_1;
	private JPanel panel_5;
	private JPanel panel_2;
	private JPanel panel_6;

	private Echiquier echiquier = new Echiquier();
	private RoundButton[] j1 = {
			new RoundButton(
					"/fr/uha/ensisa/abalone_game/Resources/boule_n.png", 12),
			new RoundButton(
					"/fr/uha/ensisa/abalone_game/Resources/boule_n.png", 12),
			new RoundButton(
					"/fr/uha/ensisa/abalone_game/Resources/boule_n.png", 12),
			new RoundButton(
					"/fr/uha/ensisa/abalone_game/Resources/boule_n.png", 12),
			new RoundButton(
					"/fr/uha/ensisa/abalone_game/Resources/boule_n.png", 12),
			new RoundButton(
					"/fr/uha/ensisa/abalone_game/Resources/boule_n.png", 12) };
	private RoundButton[] j2 = {
			new RoundButton(
					"/fr/uha/ensisa/abalone_game/Resources/boule_b.png", 12),
			new RoundButton(
					"/fr/uha/ensisa/abalone_game/Resources/boule_b.png", 12),
			new RoundButton(
					"/fr/uha/ensisa/abalone_game/Resources/boule_b.png", 12),
			new RoundButton(
					"/fr/uha/ensisa/abalone_game/Resources/boule_b.png", 12),
			new RoundButton(
					"/fr/uha/ensisa/abalone_game/Resources/boule_b.png", 12),
			new RoundButton(
					"/fr/uha/ensisa/abalone_game/Resources/boule_b.png", 12) };

	public Echiquier getEchiquier() {
		return echiquier;
	}

	public View() {
		super();
		setFont(new Font("Comic Sans MS", Font.ITALIC, 12));
		initialize();

	}

	private void initialize() {

		this.getContentPane().setFont(
				new Font("Segoe Script", Font.BOLD | Font.ITALIC, 17));
		this.getContentPane().setBackground(new Color(245, 222, 179));
		this.setBounds(100, 100, 640, 502);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Abalone");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		gameMenu = new JMenu("Game");
		menuBar.add(gameMenu);

		saveMenu = new JMenuItem("Save Game");
		saveMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_DOWN_MASK));
		saveMenu.setIcon(new ImageIcon(getClass().getResource(
				"/fr/uha/ensisa/abalone_game/Resources/disc.png")));
		saveMenu.addActionListener(new Controle());

		newMenu = new JMenuItem("New Game");
		newMenu.setIcon(new ImageIcon(
				View.class
						.getResource("/fr/uha/ensisa/abalone_game/Resources/manette-jeu-jeux-forfait-icone-6110-16.png")));
		gameMenu.add(newMenu);
		newMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_DOWN_MASK));

		newMenu.addActionListener(new Controle());
		gameMenu.add(saveMenu);

		loadMenu = new JMenuItem("Load Game");
		loadMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_DOWN_MASK));
		loadMenu.setIcon(new ImageIcon(
				getClass()
						.getResource(
								"/fr/uha/ensisa/abalone_game/Resources/loading_throbber_icon.png")));
		loadMenu.addActionListener(new Controle());
		gameMenu.add(loadMenu);

		quitMenu = new JMenuItem("Quit");
		quitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
				InputEvent.ALT_DOWN_MASK));

		quitMenu.setIcon(new ImageIcon(View.class
				.getResource("/fr/uha/ensisa/abalone_game/Resources/k.png")));
		quitMenu.addActionListener(new Controle());

		gameMenu.add(quitMenu);

		helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

		rulesMenu = new JMenuItem("Rules");
		rulesMenu.setIcon(new ImageIcon(getClass().getResource(
				"/fr/uha/ensisa/abalone_game/Resources/info.png")));
		rulesMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String rules;
				rules = "\nLe jeu Consiste a  faire sortir six boules adverses hors du plateau de jeu\n"
						+ "\t\tLe joueur qui a les boules rouges commence.\n"
						+ "A chaque tour de jeu, il faut deplacer une, deux ou trois boules.\n"
						+ "Les boules adverses sont repoussées ou sortis (hors du plateau) dans les cas qui suivent :\n"
						+ "\t\t\t - 3 boules contre 2 boules ,\n\t\t\t - 3 boules contre 1 boule,\n\t\t\t - 2 boules contre 1 boule.\n                      Bonne Partie !";
				JOptionPane.showMessageDialog(null, rules);
			}
		});
		helpMenu.add(rulesMenu);

		aboutMenu = new JMenuItem("About");
		aboutMenu
				.setIcon(new ImageIcon(
						getClass()
								.getResource(
										"/fr/uha/ensisa/abalone_game/Resources/business_users_search.png")));
		aboutMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pfe;
				pfe = "\t\t\t Platform Java Project - ENSISA IR 3A\n"
						+ "\n\t By- Pandey and Sahi \n";
				JOptionPane.showMessageDialog(null, pfe);

			}
		});

		mntmInstruction = new JMenuItem("Instructions");
		mntmInstruction.setIcon(new ImageIcon(getClass().getResource(
				"/fr/uha/ensisa/abalone_game/Resources/help.png")));
		mntmInstruction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pfe;
				pfe = "\t\t\t Instruction du Jeu Abalone\n\n\t"
						+ "1-Lancer une nouvelle Partie dans le menu jeu\n\t"
						+ "2-Le joueur qui possède les boules rouges commmence\n\t"
						+

						"\n\n\t\t\t\t\t\t\t\t\t\t LES TYPES DE DEPLACEMENT"
						+ "\n\n\t Déplacement latérale: Selectionner a la suite les billes concernées par le déplacement (3 au maximums)"
						+ "\n\t Déplacement en flèche: Selectionner seulement la bille qui poussera les autres\n"
						+

						"\n\n\t\t\t\t\t\t\t\t\t\t TOUCHE DE LA SOURIS"
						+ "\n\n\t\t Clic Gauche:Selectionne une bille  \n"
						+ "\t\t Clic Droit:Déselectionne toutes les billes selectionnées \n"
						+

						"\n\n\t\t\t\t\t\t\t\t\t\t TOUCHE DE DEPLACEMENT"
						+ "\n\n\t\t déplacer a droite: touche (6) du clavier numérique  \n"
						+ "\t\t déplacer a gauche: touche  (4) du clavier numérique\n"
						+ "\t\t déplacer en haut a gauche: touche (7) du clavier numérique \n"
						+ "\t\t déplacer en haut a droite: touche (9) du clavier numérique \n"
						+ "\t\t déplacer en bas a gauche: touche (1) du clavier numérique \n"
						+ "\t\t déplacer en haut a gauche: touche (3) du clavier numérique \n";
				JOptionPane.showMessageDialog(null, pfe);

			}
		});
		helpMenu.add(mntmInstruction);
		helpMenu.add(aboutMenu);
		this.getContentPane().setLayout(null);
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		panel.setSize(new Dimension(550, 450));

		panel.setBorder(UIManager.getBorder("CheckBox.border"));
		panel.setBackground(new Color(188, 143, 143));
		panel.setBounds(10, 11, 404, 421);
		panel.setLayout(null);
		echiquier.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		echiquier.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		echiquier.setForeground(new Color(0, 0, 0));

		echiquier.setBackground(new Color(119, 136, 153));
		echiquier.setBounds(10, 0, 384, 410);
		// echiquier.updateUI();
		panel.add(echiquier);
		panel.updateUI();
		this.getContentPane().add(panel);

		panel_1 = new JPanel();
		// panel_1.updateUI();
		panel_1.setBorder(UIManager.getBorder("CheckBox.border"));
		panel_1.setBackground(new Color(72, 61, 139));
		panel_1.setBounds(449, 45, 162, 150);
		this.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		panel_5 = new JPanel();
		panel_5.setOpaque(false);
		panel_5.setBounds(10, 113, 142, 26);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		for (int i = 0; i < j2.length; i++) {

			// j2[i].setLocation(0,(30*i));
			panel_5.add(j2[i]);

		}

		panel_1.add(panel_5);

		lblP2 = new JLabel(iconPause);
		lblP2.setHorizontalAlignment(SwingConstants.CENTER);
		lblP2.setFont(new Font("Franklin Gothic Medium", Font.BOLD
				| Font.ITALIC, 16));
		lblP2.setBounds(48, 76, 66, 26);
		panel_1.add(lblP2);

		labelPlayerBlue = new JLabel(iconPlayerBlue);
		labelPlayerBlue.setBounds(27, 11, 104, 65);
		panel_1.add(labelPlayerBlue);

		player2WinnerLable = new JLabel(new ImageIcon(new ImageIcon(getClass()
				.getResource("/fr/uha/ensisa/abalone_game/Resources/ok.png"))
				.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		player2WinnerLable.setVisible(false);
		player2WinnerLable.setBounds(10, 76, 46, 26);
		panel_1.add(player2WinnerLable);

		panel_2 = new JPanel();
		panel_2.setBorder(UIManager.getBorder("CheckBox.border"));
		panel_2.setBackground(new Color(165, 42, 42));
		panel_2.setBounds(449, 244, 162, 150);
		this.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_6.setBounds(10, 111, 142, 26);
		// panel_6.add(j1[0]);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		for (int i = 0; i < j1.length; i++) {
			// j1[i].setLocation(0,(30*i));
			panel_6.add(j1[i]);

		}
		panel_2.add(panel_6);

		lblP1 = new JLabel(iconPlay);
		lblP1.setFont(new Font("Franklin Gothic Medium", Font.BOLD
				| Font.ITALIC, 16));
		lblP1.setHorizontalAlignment(SwingConstants.CENTER);
		lblP1.setBounds(49, 74, 68, 26);
		panel_2.add(lblP1);

		labelPlayerRed = new JLabel(iconPlayerRed);
		labelPlayerRed.setBounds(29, 11, 104, 65);
		panel_2.add(labelPlayerRed);

		player1WinnerLable = new JLabel(new ImageIcon(new ImageIcon(getClass()
				.getResource("/fr/uha/ensisa/abalone_game/Resources/ok.png"))
				.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		player1WinnerLable.setVisible(false);
		player1WinnerLable.setBounds(10, 76, 46, 26);
		panel_2.add(player1WinnerLable);

	}

	public JFrame getFrame() {
		return this;
	}

	public void initGame() {

	}

	public void isVisibleJ1(int nbSortie) {
		for (int i = 0; i < nbSortie; i++) {
			if (!j1[i].isVisible())
				j1[i].setVisible(true);
		}
		for (int k = nbSortie; k < j1.length; k++) {
			if (j1[k].isVisible())
				j1[k].setVisible(false);
		}
	}

	public void isVisibleJ2(int nbSortie) {
		for (int i = 0; i < nbSortie; i++) {
			if (!j2[i].isVisible())
				j2[i].setVisible(true);
		}
		for (int j = nbSortie; j < j2.length; j++) {
			if (j1[j].isVisible())
				j2[j].setVisible(false);
		}
	}

	public int test() // testing the no of ball exited by player 2
	{
		int i = 0;
		for (i = 0; i < 6; i++) {
			if (j2[i].isVisible() == false)
				return i;
		}
		return i;
	}

	@Override
	public void update(boolean k, boolean a, Point b, int c) {
		echiquier.update(a, b, c);

		// rendre visible l'icone ok du gagnant!

		if (k == true) {

			if (test() == 5)
				player2WinnerLable.setVisible(true);
			else
				player1WinnerLable.setVisible(true);

			winner a1 = new winner();
			a1.setVisible(true);

		}

	}

	@Override
	public void update(boolean v, int id) {
		echiquier.update(v, id);

	}

	public void setTable(Hexagone table) {
		table.addObservateur(this);
	}

	public RoundButton[] getJ1() {
		return j1;
	}

	public RoundButton[] getJ2() {
		return j2;
	}

	public JLabel getlabelP1() {
		return lblP1;
	}

	public JLabel getlabelP2() {
		return lblP2;
	}

	public JLabel getlabelPlayerBlue() {
		return labelPlayerBlue;
	}

	public JLabel getlabelPlayerRed() {
		return labelPlayerRed;
	}

	@Override
	public void update(String a, String b, int a1, int b1) {
		if (a.matches("play")) {
			lblP1.setIcon(iconPlay);
			lblP1.repaint();
			lblP2.setIcon(iconPause);
			lblP2.repaint();
		} else {
			lblP1.setIcon(iconPause);
			lblP1.repaint();
			lblP2.setIcon(iconPlay);
			lblP2.repaint();
		}
		player1BOut = a1;
		player2BOut = b1;
		isVisibleJ1(b1);
		isVisibleJ2(a1);

	}

	public JLabel getplayer1WinnerLable() {
		return player1WinnerLable;
	}

	public JLabel getplayer2WinnerLable() {
		return player2WinnerLable;
	}

	public void newGame() {
		getlabelP1().setIcon(iconPlay);
		getlabelP2().setIcon(iconPause);
		getlabelPlayerBlue().setIcon(iconPlayerBlue);
		getplayer1WinnerLable().setVisible(false);
		getplayer2WinnerLable().setVisible(false);
		for (RoundButton c : getJ1())
			c.setVisible(false);
		for (RoundButton c : getJ2())
			c.setVisible(false);
	}

	// public void iaPlayer()
	// {
	// getlabel().setIcon(iA);
	// getlabel().repaint();
	//
	// }
	public class winner extends JDialog {
		private JPanel jPanel1;
		private JLabel jLabel1;

		public winner() {

			super();
			initComponents();
		}

		private void initComponents() {
			jPanel1 = new JPanel();

			jLabel1 = new JLabel();

			jPanel1.setLayout(new java.awt.BorderLayout());

			jPanel1.setBackground(new java.awt.Color(204, 255, 255));
			jLabel1.setBackground(new java.awt.Color(255, 255, 255));
			jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource(
					"/fr/uha/ensisa/abalone_game/Resources/winner-cup.gif")));
			jLabel1.setSize(new java.awt.Dimension(290, 268));
			jPanel1.add(jLabel1, java.awt.BorderLayout.CENTER);
			add(jPanel1, java.awt.BorderLayout.CENTER);

			this.setSize(300, 300);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			// this.setVisible(true);

		}
	}
}
