package fr.uha.ensisa.abalone_game.Model;

import java.awt.Color;

public class Partie {
	public  Hexagone table;
	public  Joueur j1;
	public  Joueur j2;
	
	public Partie()
	{
		table=new Hexagone(new Coups());
		j1=new Joueur(Color.BLUE,false,table,table.BouleJoueur1());
		j2=new Joueur(Color.PINK,false,table,table.bouleJoueur2());
		j1.setAdversaire(j2);
		j2.setAdversaire(j1);
		Joueur [] j=new Joueur[2];
		j[0]=j1;
		j[1]=j2;
		table.setJoueurs(j);
	}

}
