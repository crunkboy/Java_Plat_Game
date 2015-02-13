package fr.uha.ensisa.abalone_game.Model;

import java.awt.Point;
import java.util.ArrayList;

import fr.uha.ensisa.abalone_game.Observer.Observable;
import fr.uha.ensisa.abalone_game.Observer.Observateur;


public class Hexagone implements Observable {
	private Joueur tour;
	protected int nbSelect=0;
	protected ArrayList<Boule> BoulesSelectionees=new ArrayList<Boule>();

	public Joueur [] joueurs=new Joueur[2];            //les 2 joueurs
	private Boule [][] bouleJoueur1=new Boule[3][];
	private Boule [][] bouleJoueur2=new Boule[3][];
	private boolean partieEnd=false;                  //si la partie est fini ou pas
	private Trou[][] toute=new Trou[11][];            //11 lignes
	public Coups règle;                               //les règle du jeu
	private Point [][] T = 							  //35 de diff entre les deux points
		{
			{null,null,null,null,null,null},
			{null,new Point(95, 54),new Point(130, 54),new Point(165, 54),new Point(200, 54),new Point(235, 54),null},
			{null,new Point(77, 88),new Point(112, 88),new Point(147, 88), new Point(182, 88),new Point(217, 88), new Point(252, 88),null},
			{null,new Point(59, 122),new Point(95, 122),new Point(130, 122),new Point(165, 122),new Point(200, 122),new Point(235, 122), new Point(270, 122),null},
			{null,new Point(41, 156),new Point(77, 156), new Point(112, 156),new Point(147, 156), new Point(182, 156),new Point(217, 156),new Point(252, 156),new Point(287, 156),null},
			{null,new Point(25, 190),new Point(60, 190),new Point(95, 190),new Point(130, 190),new Point(165, 190),new Point(200, 190), new Point(235, 190),new Point(270, 190),new Point(305, 190),null},
			{null,new Point(41, 224),new Point(77, 224), new Point(112, 224),new Point(147, 224),new Point(182, 224),new Point(217, 224),new Point(252, 224),new Point(287, 224),null},
			{null,new Point(60, 258),new Point(95, 258),new Point(130, 258),new Point(165, 258),new Point(200, 258),new Point(235, 258),new Point(270, 258),null},
			{null,new Point(77, 290),new Point(112, 290),new Point(147, 290),new Point(182, 290),new Point(217, 290),new Point(252, 290),null},
			{null,new Point(95, 325),new Point(130, 325),new Point(165, 325),new Point(200, 325),new Point(235, 325),null},
			{null,null,null,null,null,null}
		};
	
	

	public Hexagone(Coups r)
	{
		
		règle=r;
		init();
		
	}
	
	public void init()
	{
		initTrou();
		initVoisinage();
		initBoulej1j2();
		initBoule();
	}
	
	public void nouvellePartie()
	{
		for(Boule [] a:bouleJoueur1)
			for(Boule d:a)
				d.setTrou(d.getTrou());
		for(Boule [] a:bouleJoueur2)
			for(Boule d:a)
				d.setTrou(d.getTrou());
	}
	
	public int getNbSelect() 
	{
		return nbSelect;
	}

	
	public void setNbSelect(int nbSelect) {
		this.nbSelect = nbSelect;
		if(nbSelect==0)
			{
				for(Boule v:BoulesSelectionees)
				{
					this.NotifyObserver(false, v.getIdBoule());		//déselectionne les boules
				}
				BoulesSelectionees.removeAll(BoulesSelectionees);
			}
	}
	public Joueur getJoueurAt(int i)
	{
		return joueurs[i];
	}
	public Trou getTrouAt(int i,int j)
    {
    	return toute[i][j];
    }
	public void initBoulej1j2()
	{
		bouleJoueur1[0]=new Boule[5];
    	bouleJoueur1[1]=new Boule[6];
    	bouleJoueur1[2]=new Boule[3];
    	bouleJoueur2[0]=new Boule[5];
    	bouleJoueur2[1]=new Boule[6];
    	bouleJoueur2[2]=new Boule[3];
	}
	
	//créer les trou
	public void initTrou()
	{
		toute[0]=new Trou[6];
		toute[1]=new Trou[7];
		toute[2]=new Trou[8];
		toute[3]=new Trou[9];
		toute[4]=new Trou[10];
		toute[5]=new Trou[11];
		toute[6]=new Trou[10];
		toute[7]=new Trou[9];
		toute[8]=new Trou[8];
		toute[9]=new Trou[7];
		toute[10]=new Trou[6];
		for(int i=0;i<toute.length;i++)
		{
			for(int j=0;j<toute[i].length;j++)
			{
				toute[i][j]=new Trou();
				toute[i][j].setPosition(T[i][j]);
				if(i==0 || i==10 || j==0 || j==toute[i].length-1)
					toute[i][j].setBord(true);

			}
		}
		
		
		
	}
	

	public void initBoule()
	{
		int a,b;
		for(int i=0;i<bouleJoueur2.length;i++)
		{
			for(int j=0;j<bouleJoueur2[i].length;j++)
			{
				if(i==2)
				{
					a=i+1;
					b=j+3;  //to make sure that only 3 balls are placed in the third row
					bouleJoueur2[i][j]=new Boule(toute[a][b]);
				}
				
				else
				{ 
					a=i+1;
					b=j+1;
					bouleJoueur2[i][j]=new Boule(toute[a][b]);
					

				}
			}
		}
		
		//les boules inférieur
		for(int i=0;i<bouleJoueur1.length;i++)
		{
			for(int j=0;j<bouleJoueur1[i].length;j++)
			{
				if(i==2)
				{
					a=9-i;
					b=j+3; // same logic as above, just the calculations are different 
					bouleJoueur1[i][j]=new Boule(toute[a][b]);
				}
				
				else
				{
					a=9-i;
					b=j+1;
					bouleJoueur1[i][j]=new Boule(toute[a][b]);	
				}
			}
		}
		
	}

	//initialise le voisinage des trou
	public void initVoisinage()
	{
		for(int k=1;k<toute.length-1;k++)
		{
			if(k<5)
			{
				for(int o=1;o<toute[k].length-1;o++)
				{
					toute[k][o].voisinage[0]=toute[(k-1)][o];//NE
					toute[k][o].voisinage[1]=toute[k][(o+1)];//E
					toute[k][o].voisinage[2]=toute[(k+1)][(o+1)];//SE
					toute[k][o].voisinage[3]=toute[(k+1)][o];//SO
					toute[k][o].voisinage[4]=toute[k][o-1];//O
					toute[k][o].voisinage[5]=toute[(k-1)][(o-1)];//NO
					
				}
			}
			else if(k==5)
			{
				for(int o=1;o<toute[k].length-1;o++)
				{
					toute[k][o].voisinage[0]=toute[(k-1)][o];//NE
					toute[k][o].voisinage[1]=toute[k][(o+1)];//E
					toute[k][o].voisinage[2]=toute[(k+1)][o];//SE
					toute[k][o].voisinage[3]=toute[(k+1)][(o-1)];//SO
					toute[k][o].voisinage[4]=toute[k][o-1];//O
					toute[k][o].voisinage[5]=toute[(k-1)][(o-1)];//NO
					
				}
			}
			else
			{
				for(int o=1;o<toute[k].length-1;o++)
				{
					toute[k][o].voisinage[0]=toute[(k-1)][o+1];//NE
					toute[k][o].voisinage[1]=toute[k][(o+1)];//E
					toute[k][o].voisinage[2]=toute[(k+1)][o];//SE
					toute[k][o].voisinage[3]=toute[(k+1)][(o-1)];//SO
					toute[k][o].voisinage[4]=toute[k][o-1];//O
					toute[k][o].voisinage[5]=toute[(k-1)][(o)];//NO
					
				}
			}
			
		}
	}
	
	
	public void setpartieEnd(boolean v)             
	{
		partieEnd=v;
	}
	public boolean isPartieEnd()
	{
		return partieEnd;
	}
	public void modifyState()
	{
		joueurs[0].modifyNBout();
		joueurs[1].modifyNBout();
		
		if(joueurs[0].getNBout()==6 || joueurs[1].getNBout()==6) //if the number of balls displaced from the board is equal to 6, the game has ended
		{
			partieEnd=true;
		}
		else
			partieEnd=false;
	}
	
	
	
	
	public Joueur getTour() {
		return tour;
	}
	public void setTour(Joueur j)
	{
		tour=j;
	}

	public void setJoueurs(Joueur[] joueurs) {
		this.joueurs = joueurs;
	}

	@Override
	public void addObservateur(Observateur obs) {
		listObserver.add(obs);
		
	}


	@Override
	public void delObserver() {
		
		listObserver.removeAll(listObserver);	
	}
	
	public void jouer()
	{
		if(tour.equals(joueurs[0]))
			{
				tour=joueurs[1];
				NotifyObserver("pause","play",joueurs[0].getNBout(),joueurs[1].getNBout());
			}
		else
			{
				tour=joueurs[0];
				NotifyObserver("play","pause",joueurs[0].getNBout(),joueurs[1].getNBout());
			}
	}
	
	public Boule [][] BouleJoueur1() {
		return bouleJoueur1;
	}
	public void setBouleJoueur1(Boule [][] bouleJoueur1) {
		this.bouleJoueur1 = bouleJoueur1;
	}
	public Boule [][] bouleJoueur2() {
		return bouleJoueur2;
	}
	public void setBouleJoueur2(Boule [][] bouleJoueur2) {
		this.bouleJoueur2 = bouleJoueur2;
	}
	
	public Boule getBouleByID(int id)
	{
		for(Boule [] t:bouleJoueur1)
		{
			for(Boule a:t)
				if(a.getIdBoule()==id)
					return a;
				
		}
		for(Boule [] t:bouleJoueur2)
		{
			for(Boule a:t)
				if(a.getIdBoule()==id)
					return a;
				
		}
		
		
		return null;
			
	}
	
	public Trou getTrouByID(int id)
	{
		for(Trou [] t:toute)
		{
			for(Trou a:t)
				if(a.getIdTrou()==id)
					return a;
				
		}
		return null;
	}
	
	public ArrayList<Boule> getBoulesSelectionees() {
		return BoulesSelectionees;
	}
	
	@Override
	public void NotifyObserver(boolean l, boolean a, Point b, int c) 
	{
		 for(Observateur a1:listObserver)
		{
			a1.update(l,a,b,c);
		}
		
	}
	@Override
	public void NotifyObserver(boolean a,int id) {
		
		for(Observateur a1:listObserver)
		{
			a1.update(a,id);
		}
	}
	

	@Override
	public void NotifyObserver(String a,String b,int a1,int b1) {
		for(Observateur a11:listObserver)
		{
			a11.update(a,b,a1,b1);
		}
		
	}

}
	


