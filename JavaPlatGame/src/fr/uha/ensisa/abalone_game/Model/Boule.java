package fr.uha.ensisa.abalone_game.Model;

import java.awt.Color;
import java.awt.Point;


public class Boule {
	
	private Color couleur;
	private boolean sortie=false;       //si la boule est toujours dans l'hexagone ou pas
	private Joueur joueur;
	private Trou trou;
	private static int cpteur=0;
	private int idBoule;
	private Point PositionBoule;
	
	public Boule(Trou a)
	{
		idBoule=cpteur;
		cpteur++;
		trou=a;
		
		PositionBoule=a.getPosition();
		trou.setBoule(this);
	}
    public boolean getEtat()    
    {
		return sortie;
	}
    
   
     public void setEtat(boolean j)    
    {
		sortie=j;
	}
    
    public void modifyEtat()           //test si le trou dan lekel on è è un bord
    {
		if(trou.getBord())  
			{
			   setEtat(true);
			}                            
		else 
			{
			setEtat(false);
			this.setPosition(trou.getPosition());
			}
			//joueur.getTable().modifyState();
			//joueur.getTable().NotifyObserver(joueur.getTable().getState(),getEtat(), getPosition(), getIdBoule());
	}
    
   
    
   
    public void setTrou(Trou a)    //but:affecter un trou a la  boule
    {     
    			
    			trou.setOccupied(false);
    			//trou.setBoule(null);		//on prévient aux trou dans lequel il était qu'il n'est plus occupé
    			
	    		trou=a;
	    		trou.setBoule(this);
	    		modifyEtat(); //en principe on teste si le trou est un bord alors on change l'attribt sortie d la boul
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
