package fr.uha.ensisa.abalone_game.Model;

import java.awt.Color;
import java.awt.Point;


public class Boule {
	
	private Color couleur;				//couleur de la boule
	private boolean sortie=false;       //si la boule est toujours dans l'hexagone ou pas
	private Joueur joueur;				//joueur possedant la boule
	private Trou trou;					//Trou dans lequel se trouve la boule
	private static int cpteur=0;		//compteur d'attribution d'idBoule
	private int idBoule;				//identifiant de la boule (unique)
	private Point PositionBoule;		//emplacement de la boule (trou sur lequel elle se trouve)
	
	public Boule(Trou a)
	{
		idBoule=cpteur;
		cpteur++;
		trou=a;
		
		PositionBoule=a.getPosition();
		trou.setBoule(this);
	}
	
	public void modifyEtat()           //test si le trou dan lequel se trouve la boule est un bord
    {
		if(trou.getBord())  
			{
			setSortie(true);			//boule sortie
			}                            
		else 
			{
			setSortie(false);			//boule toujours en jeu
			this.setPosition(trou.getPosition());
			}
			joueur.getTable().modifyState();
			joueur.getTable().NotifyObserver(joueur.getTable().isPartieEnd(),getSortie(), getPosition(), getIdBoule());
	}
    
   
	
	// Getters et Setters
	
    public boolean getSortie()    		
    {
		return sortie;
	}
    
   
     public void setSortie(boolean j)    
    {
		sortie=j;
	}
    
   
    public void setTrou(Trou a)    //but:affecter un trou a la  boule
    {     
    			
    			trou.setOccupied(false);	//L'ancien trou n'est plus oocupé
    			//trou.setBoule(null);		
    			
	    		trou=a;
	    		trou.setBoule(this);
	    		modifyEtat(); //en principe on teste si le trou est un bord pour mettre à jour l'attribut sortie d la boul
     }
    
    
    public int getIdBoule() 
    {
		return idBoule;
	}
	public void setIdBoule(int idBoule) {
		this.idBoule = idBoule;
	} 
    public Trou getTrou()
    {
    	return trou;
    }
    
    public Joueur getJoueur()
    {
		return joueur;
    	
    }
    public void setJoueur(Joueur j)
    {
    	joueur=j;
    }
    
    public void setColor(Color c)
    {
    	couleur=c;
    }
    public Color getColor()
    {
    	return couleur;
    }
	public Point getPosition() {
		return PositionBoule;
	}
	
	public void setPosition(Point position) {
		PositionBoule = position;
	}
	public static int getCpteur() {
		return cpteur;
	}
	public static void setCpteur(int cpteur) {
		Boule.cpteur = cpteur;
	}

}
