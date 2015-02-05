package fr.uha.ensisa.abalone_game.View;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
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
public class View extends JFrame implements Observateur{
	int joueur1;
	int joueur2;
	private JLabel  winner1Label;
	private JLabel winner2Label;
	private JLabel lblJ1;
	private JLabel lblJ2;
	private JLabel label_1;
	private JLabel label;
	private ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource("/fr/uha/ensisa/abalone_game/Resources/joueur1.png")).getImage().getScaledInstance(103, 64, Image.SCALE_DEFAULT));
	public ImageIcon joueur = new ImageIcon(new ImageIcon(getClass().getResource("/fr/uha/ensisa/abalone_game/Resources/joueur2.png")).getImage().getScaledInstance(120, 60, Image.SCALE_DEFAULT));
	public ImageIcon iA = new ImageIcon(new ImageIcon(getClass().getResource("/fr/uha/ensisa/abalone_game/Resources/IA.png")).getImage().getScaledInstance(120, 60, Image.SCALE_DEFAULT));
	public ImageIcon play = new ImageIcon(new ImageIcon(getClass().getResource("/fr/uha/ensisa/abalone_game/Resources/play.png")).getImage().getScaledInstance(30, 20, Image.SCALE_DEFAULT));
	public ImageIcon pause = new ImageIcon(new ImageIcon(getClass().getResource("/fr/uha/ensisa/abalone_game/Resources/pause.png")).getImage().getScaledInstance(30,20, Image.SCALE_DEFAULT));
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
//	private JMenuItem mntmSauvegarde;
	private JMenu mnNouvellePartie;
	private JMenuItem mntmHumainVsHumain;
	private JMenu mnNewMenu_2;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem mntmNewMenuItem_4;
	private JMenuItem mntmNewMenuItem_5;
//	private JMenuItem mntmChargerPartie;
	private JMenuItem mntmQuitter;
	private JMenu mnAPropos;
	private JMenuItem mntmRgles;
	private JMenuItem mntmAPropos;
	private JPanel panel;
	private JMenuItem mntmInstruction;
	private	JPanel panel_1;
	private JPanel panel_5;
	private JPanel panel_2;
	private JPanel panel_6;
	
	private Echiquier echiquier = new Echiquier();
private RoundButton [] j1={new RoundButton("/fr/uha/ensisa/abalone_game/Resources/boule_n.png",12),new RoundButton("/fr/uha/ensisa/abalone_game/Resources/boule_n.png",12),new RoundButton("/fr/uha/ensisa/abalone_game/Resources/boule_b.png",12),new RoundButton("/fr/uha/ensisa/abalone_game/Resources/boule_n.png",12),new RoundButton("/fr/uha/ensisa/abalone_game/Resources/boule_n.png",12),new RoundButton("/fr/uha/ensisa/abalone_game/Resources/boule_n.png",12)};
private RoundButton [] j2={new RoundButton("/fr/uha/ensisa/abalone_game/Resources/boule_b.png",12),new RoundButton("/fr/uha/ensisa/abalone_game/Resources/boule_b.png",12),new RoundButton("/fr/uha/ensisa/abalone_game/Resources/boule_b.png",12),new RoundButton("/fr/uha/ensisa/abalone_game/Resources/boule_b.png",12),new RoundButton("/fr/uha/ensisa/abalone_game/Resources/boule_b.png",12),new RoundButton("/fr/uha/ensisa/abalone_game/Resources/boule_b.png",12)};
	public Echiquier getEchiquier() {
		return echiquier;
	}

	

	
	public View() {
		super();
		setFont(new Font("Comic Sans MS", Font.ITALIC, 12));
		initialize();
		
	}

	
	private void initialize() {
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(View.class.getResource("/fr/uha/ensisa/abalone_game/Resources/abalonearr.png")));
		this.getContentPane().setFont(new Font("Segoe Script", Font.BOLD | Font.ITALIC, 17));
		this.getContentPane().setBackground(new Color(245, 222, 179));
		this.setBounds(100, 100, 640, 502);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Abalone");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Jeu");
		menuBar.add(mnNewMenu);
		
//		mntmSauvegarde = new JMenuItem("Sauvegarder la Partie");
//		mntmSauvegarde.setAccelerator(KeyStroke.getKeyStroke
//				(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
//		mntmSauvegarde.setIcon(new ImageIcon(getClass().getResource("/fr/uha/ensisa/abalone_game/Resources/disc.png")));
//		 mntmSauvegarde.addActionListener(new Controle());
		 
		
		mnNouvellePartie = new JMenu("Nouvelle Partie");
		mnNouvellePartie.setIcon(new ImageIcon(View.class.getResource("/fr/uha/ensisa/abalone_game/Resources/manette-jeu-jeux-forfait-icone-6110-16.png")));
		mnNewMenu.add(mnNouvellePartie);
		
		mntmHumainVsHumain = new JMenuItem("Humain vs Humain");
		mnNouvellePartie.add(mntmHumainVsHumain);
		mntmHumainVsHumain.setIcon(new ImageIcon(getClass().getResource("/fr/uha/ensisa/abalone_game/Resources/users.png")));
		
		mntmHumainVsHumain.addActionListener(new Controle());
		
//		mnNewMenu_2 = new JMenu("Humain vs Machine");
//		mnNouvellePartie.add(mnNewMenu_2);
//		mnNewMenu_2.setIcon(new ImageIcon(getClass().getResource("/fr/uha/ensisa/abalone_game/Resources/user.png")));
//		
//		mntmNewMenuItem = new JMenuItem("Niveau 1");
//		mntmNewMenuItem.addActionListener(new Controle());
//		mnNewMenu_2.add(mntmNewMenuItem);
//		
//		mntmNewMenuItem_1 = new JMenuItem("Niveau 2");
//		mntmNewMenuItem_1.addActionListener(new Controle());
//		mnNewMenu_2.add(mntmNewMenuItem_1);
//		
//		mntmNewMenuItem_2=new JMenuItem("Niveau 3");
//		mntmNewMenuItem_2.addActionListener(new Controle());
//		mnNewMenu_2.add(mntmNewMenuItem_2);
//		
//		mntmNewMenuItem_3=new JMenuItem("Niveau 4");
//		mntmNewMenuItem_3.addActionListener(new Controle());
//		mnNewMenu_2.add(mntmNewMenuItem_3);
//		
//		mntmNewMenuItem_4=new JMenuItem("Niveau 5");
//		mntmNewMenuItem_4.addActionListener(new Controle());
//		mnNewMenu_2.add(mntmNewMenuItem_4);
//		
//		mntmNewMenuItem_5=new JMenuItem("Niveau 6");
//		mntmNewMenuItem_5.addActionListener(new Controle());
//		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		
		
//		mnNewMenu.add(mntmSauvegarde);
//		
//		mntmChargerPartie = new JMenuItem("Charger une Partie");
//		mntmChargerPartie.setAccelerator(KeyStroke.getKeyStroke
//				(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
//		mntmChargerPartie.setIcon(new ImageIcon(getClass().getResource("/fr/uha/ensisa/abalone_game/Resources/loading_throbber_icon.png")));
//		mntmChargerPartie.addActionListener(new Controle());
//		mnNewMenu.add(mntmChargerPartie);
		
		mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.setAccelerator(KeyStroke.getKeyStroke
				(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		
		mntmQuitter.setIcon(new ImageIcon(View.class.getResource("/fr/uha/ensisa/abalone_game/Resources/k.png")));
		mntmQuitter.addActionListener(new Controle());
		mnNewMenu.add(mntmQuitter);
		
		 
		mnAPropos = new JMenu("Aide");
		menuBar.add(mnAPropos);
		
		mntmRgles = new JMenuItem("R\u00E8gles");
		mntmRgles.setIcon(new ImageIcon(getClass().getResource("/fr/uha/ensisa/abalone_game/Resources/info.png")));
		mntmRgles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String loi;
        		loi = "\nLe jeu Consiste a  faire sortir six boules adverses hors du plateau de jeu\n" +
		        "\t\tLe joueur qui a les boules bleues commencent.\n" +
		         "A chaque tour de jeu, il faut deplacer une ranger d'une, deux ou trois boules.\n" +
		          "Les boules adverses sont repoussées ou avalées (hors du plateau) dans les cas qui suivent :\n" +
		           "\t\t\t - 3 contre 2,\n\t\t\t - 3 contre 1,\n\t\t\t - 2 contre 1.\n                      Bonne Partie !" ;
        		JOptionPane.showMessageDialog(null, loi);
			}
		});
		mnAPropos.add(mntmRgles);
		
		mntmAPropos = new JMenuItem("A propos");
		mntmAPropos.setIcon(new ImageIcon(getClass().getResource("/fr/uha/ensisa/abalone_game/Resources/business_users_search.png")));
		mntmAPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pfe;
        		pfe = "\t\t\tEcole Nationale Sup\u00e9rieure d'Ing\u00e9nieurs Sud Alsace (ENSISA)\n" +
		               "             \tProjet Platforme Java  -    Version 1.0\n\n" +
		                "             Contact  -   " +"Mail: etudiant397@gmail.fr";
        		JOptionPane.showMessageDialog(null, pfe);
				
			}
		});
		
		mntmInstruction = new JMenuItem("Instruction");
		mntmInstruction.setIcon(new ImageIcon(getClass().getResource("/fr/uha/ensisa/abalone_game/Resources/help.png")));
		mntmInstruction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pfe;
        		pfe = "\t\t\t Instruction du Jeu Abalone\n\n\t" +
		               "1-Lancer une nouvelle Partie dans le menu jeu\n\t" +
		                "2-Le qui possède les boules du bas commmence\n\t" +
		                "3-Pour jouer contre l'IA(la machine) choisissez son niveau dans le menu Humain vs Machine\n\t"+
		                  "4-Vous etes désormais pret a jouer"+
		                  "\n\n\t\t\t\t\t\t\t\t\t\t LES TYPES DE DEPLACEMENT"+
		                   "\n\n\t Déplacement latérale: Selectionner a la suite les billes consernées par le déplacement"+
		                     "\n\t Déplacement en flèche: Selectionner seulement la bille qui poussera les autres\n"+
		                     
		                     "\n\n\t\t\t\t\t\t\t\t\t\t TOUCHE DE DEPLACEMENT"+
		                     "\n\n\t\t déplacer a droite: touche (6) du clavier numérique  \n"+
		  
		                     "\t\t déplacer a gauche: touche  (4) du clavier numérique\n"+
        						"\t\t déplacer en haut a gauche: touche (7) du clavier numérique \n"+
        						"\t\t déplacer en haut a droite: touche (9) du clavier numérique \n"+
        						"\t\t déplacer en bas a gauche: touche (1) du clavier numérique \n"+
        						"\t\t déplacer en haut a gauche: touche (3) du clavier numérique \n"+
        				
        						  "\n\n\t\t\t\t\t\t\t\t\t\t TOUCHE DE LA SOURIS"+
        						  "\n\n\t\t Clic Gauche:Selectionne une bille  \n"+
        						  "\t\t Clic Droit:Déselectionne toute les billes selectionné \n"+
        						  "\n\n\n \t\t\t NB:selectionner la bille avant de tenter le déplacement";
        		JOptionPane.showMessageDialog(null, pfe);
				
			}
		});
		mnAPropos.add(mntmInstruction);
		mnAPropos.add(mntmAPropos);
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
		//echiquier.updateUI();
		panel.add(echiquier);
		panel.updateUI();
		this.getContentPane().add(panel);
		
		
		panel_1 = new JPanel();
		//panel_1.updateUI();
		panel_1.setBorder(UIManager.getBorder("CheckBox.border"));
		panel_1.setBackground(new Color(72, 61, 139));
		panel_1.setBounds(449, 45, 162, 150);
		this.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		panel_5 = new JPanel();
		panel_5.setOpaque(false);
		panel_5.setBounds(10, 113, 142, 26);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		 for(int i=0;i<j2.length;i++)
		    {
		
		    	//j2[i].setLocation(0,(30*i));
		    	panel_5.add(j2[i]);
		    
		    }
		
		panel_1.add(panel_5);
		
		
		lblJ2 = new JLabel(pause);
		lblJ2.setHorizontalAlignment(SwingConstants.CENTER);
		lblJ2.setFont(new Font("Franklin Gothic Medium", Font.BOLD | Font.ITALIC, 16));
		lblJ2.setBounds(48, 76, 66, 26);
		panel_1.add(lblJ2);
		
		label = new JLabel(joueur);
		label.setBounds(27, 11, 104, 65);
		panel_1.add(label);
		
		winner2Label = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/fr/uha/ensisa/abalone_game/Resources/ok.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		winner2Label.setVisible(false);
		winner2Label.setBounds(10, 76, 46, 26);
		panel_1.add(winner2Label);
		
		panel_2 = new JPanel();
		panel_2.setBorder(UIManager.getBorder("CheckBox.border"));
		panel_2.setBackground(new Color(165, 42, 42));
		panel_2.setBounds(449, 244, 162, 150);
		this.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_6.setBounds(10, 111, 142, 26);
		//panel_6.add(j1[0]);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		 for(int i=0;i<j1.length;i++)
		    { 
		    	//j1[i].setLocation(0,(30*i));
		    	panel_6.add(j1[i]);
		    
		    }
		panel_2.add(panel_6);
		
		
		lblJ1 = new JLabel(play);
		lblJ1.setFont(new Font("Franklin Gothic Medium", Font.BOLD | Font.ITALIC, 16));
		lblJ1.setHorizontalAlignment(SwingConstants.CENTER);
		lblJ1.setBounds(49, 74, 68, 26);
		panel_2.add(lblJ1);
		
		label_1 = new JLabel(icon);
		label_1.setBounds(29, 11, 104, 65);
		panel_2.add(label_1);
		
		winner1Label = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/fr/uha/ensisa/abalone_game/Resources/ok.png")).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		winner1Label.setVisible(false);
		winner1Label.setBounds(10, 76, 46, 26);
		panel_2.add(winner1Label);
		
	}
	public JFrame getFrame() {
		return this;
	}
	
	public void initGame()
	{
		
	}
	
	public void isVisibleJ1(int nbSortie)
	{
		for(int i=0;i<nbSortie;i++)
		{
			if(!j1[i].isVisible())
			j1[i].setVisible(true);
		}
		for(int k=nbSortie;k<j1.length;k++)
		{
			if(j1[k].isVisible())
			j1[k].setVisible(false);
		}
	}
	public void isVisibleJ2(int nbSortie)
	{
		for(int i=0;i<nbSortie;i++)
		{
			if(!j2[i].isVisible())
			j2[i].setVisible(true);
		}
		for(int j=nbSortie;j<j2.length;j++)
		{
			if(j1[j].isVisible())
			j2[j].setVisible(false);
		}
	}

public int test()
{
	int i=0;
	for(i=0;i<6;i++)
	{
		if(j2[i].isVisible()==false)
			return i;
	}
	return i;
}

	@Override
	public void update(boolean k, boolean a, Point b, int c) {
		echiquier.update(a, b, c);
		
		//rendre visible l'icone ok du gagnant!
		
		
		if(k==true)
		{
			
			if(test()==5)
				winner2Label.setVisible(true);
			else
				winner1Label.setVisible(true);			
	            
			winner a1=new winner();
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
	
	public JLabel getlabelJ1()
	{
		return lblJ1;
	}
	
	public JLabel getlabelJ2()
	{
		return lblJ2;
	}
	public JLabel getlabel()
	{
		return label;
	}
	
	public JLabel getlabel_1()
	{
		return label_1;
	}

	
	


	@Override
	public void update(String a,String b,int a1,int b1) {
		if(a.matches("play"))
		{
			lblJ1.setIcon(play);
			lblJ1.repaint();
			lblJ2.setIcon(pause);
			lblJ2.repaint();
		}
		else
			{
				lblJ1.setIcon(pause);
				lblJ1.repaint();
				lblJ2.setIcon(play);
				lblJ2.repaint();
			}
		joueur1=a1;
		joueur2=b1;
		isVisibleJ1(b1);
		isVisibleJ2(a1);
		
		
		
	}
	
	public JLabel getWinner1Label() {
		return winner1Label;
	}




	
	public JLabel getWinner2Label() {
		return winner2Label;
	}
	
	
	public void newGame()
	{
		getlabelJ1().setIcon(play);
		getlabelJ2().setIcon(pause);
		getlabel().setIcon(joueur);
		getWinner1Label().setVisible(false);
		getWinner2Label().setVisible(false);
		for(RoundButton c:getJ1())
			c.setVisible(false);
		for(RoundButton c:getJ2())
			c.setVisible(false);
	}
	
	public void iaPlayer()
	{
		getlabel().setIcon(iA);
		getlabel().repaint();
		
	}
	public class winner extends JDialog
	{
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
		        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fr/uha/ensisa/abalone_game/Resources/winner-cup.gif")));
		        jLabel1.setSize(new java.awt.Dimension(290, 268));
		        jPanel1.add(jLabel1, java.awt.BorderLayout.CENTER);
		        add(jPanel1, java.awt.BorderLayout.CENTER);
		        
		        
		        this.setSize(300, 300);
		        this.setLocationRelativeTo(null);
		        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		        //this.setVisible(true);

		
	}
	}
}

