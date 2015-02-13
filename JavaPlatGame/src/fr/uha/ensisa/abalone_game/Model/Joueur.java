package fr.uha.ensisa.abalone_game.Model;

import java.awt.Color;


public class Joueur implements Cloneable {
	private static int cpteur=0;
	private int idJoueur; 
	private Color couleur;
	private int nBout=0;                        //nombre de boule du joueur etant déja sorti
    private Boule [][] mesBoules=new Boule[3][];              
    private static Hexagone tableDjeu;
	private Joueur adversaire;
	

	public Boule getBoule(int id)
	{
		for(int i=0;i<mesBoules.length;i++)
			for(int j=0;j<mesBoules[i].length;j++)
			{
				if(mesBoules[i][j].getIdBoule()==id)//on ajoute le pion id dans la liste des pion selectionné s'il existe déja on l'a sort
				{ 
					return mesBoules[i][j];
				}
			}
		for(int i=0;i<mesBoules.length;i++)
			for(int j=0;j<mesBoules[i].length;j++)
			{
				if(mesBoules[i][j].getIdBoule()==id)//on ajoute le pion id dans la liste des pion selectionné s'il existe déja on l'a sort
				{ 
					return mesBoules[i][j];
				}
			}
		return null;
	}

	//on ajoute la boule selectionée à l aliste des boules selectionées sinon on l'a retire si elle y est deja 
	public boolean select(int id)
	{
		System.out.println("click sur boule "+id);
		if(getBoule(id)!=null && tableDjeu.nbSelect==0)
		{				
					tableDjeu.BoulesSelectionees.add(getBoule(id));
					System.out.println("ajouté!");
					tableDjeu.nbSelect++;
					tableDjeu.NotifyObserver(true, id);
					return true;
		}
			
		//s'il y'a déja une boule et qu'on veut en selectioné pour le deplacement latérale
		else if(getBoule(id)!=null && (tableDjeu.nbSelect>0 && tableDjeu.nbSelect<3) )
		{
			if(tableDjeu.BoulesSelectionees.contains(getBoule(id)))
			{	
						System.out.println("oté");
						tableDjeu.BoulesSelectionees.remove(getBoule(id));
						tableDjeu.nbSelect--;
						tableDjeu.NotifyObserver(false, id);
						return true;
			}
			else
			{
				//on ajoute et on vérifie l'alignement
				tableDjeu.BoulesSelectionees.add(getBoule(id));
				tableDjeu.nbSelect++;
				if(!alignement())
				{
					tableDjeu.BoulesSelectionees.remove(getBoule(id));
					tableDjeu.nbSelect--;
					//System.out.println("les boules ne sont pas aligné ou ne sont pas du meme joueur");
					return false;
				}
				else
				{
					tableDjeu.NotifyObserver(true, id);
					
					//System.out.println("les boules st alignés");
					return true;
				}
				
			}
			
				
		}
		else if(tableDjeu.nbSelect==3)
		{
			if(tableDjeu.BoulesSelectionees.contains(getBoule(id)))
			{	
						System.out.println("oté");
						tableDjeu.BoulesSelectionees.remove(getBoule(id));
						tableDjeu.nbSelect--;
						tableDjeu.NotifyObserver(false, id);
						return true;
			}
			else
			{
				System.out.println("nombre max de boule atteint");
				return false;
				
			}
		}
		return false;
			
	}
	
	public Joueur getAdversaire() {
		return adversaire;
	}



	public void setAdversaire(Joueur adversaire) {
		this.adversaire = adversaire;
	}

	
	public Joueur(Color couleur,boolean ia,Hexagone b,Boule [][] a)
    {
    	idJoueur=cpteur;
    	cpteur++;
    	setTableDjeu(b);
    	this.couleur=couleur;
    	mesBoules=a;
    	initBouleAttribute();
   }
   
    
    
    public int getIdJoueur() {
		return idJoueur;
	}


	public void initBouleAttribute()
    {
    	for(int i=0;i<mesBoules.length;i++)
    	{
    		for(int j=0;j<mesBoules[i].length;j++)
    		{
    			//System.out.println(mesBoules[i][j]);
    			mesBoules[i][j].setColor(this.couleur);
    			mesBoules[i][j].setJoueur(this);
    		}
    	}
    }
    
   
    
    public void setNBout(int n)
    {
    	nBout=n;
    }
    public int getNBout()
    {
    	modifyNBout();
    	return nBout;
    }
    
    
    //Met à jour le nombrede boule sorties
    public void modifyNBout()
    {
    	int nb=0;
    	for(int i=0;i<mesBoules.length;i++)
    	{
    		for(int j=0;j<mesBoules[i].length;j++)
    		{
    			if(mesBoules[i][j].getSortie()==true)
    			{
    				nb++;
    			}
    		}
    	}		
    	nBout=nb;
    }
    
  
   
    
    public Color getColor()
    {
    	return couleur;
    }
    
    public void setColor(Color a)
    {
    	couleur=a;
    }
    
    
    
    
    
	public static void setCpteur(int cpteur) {
		Joueur.cpteur = cpteur;
	}



public Boule getBouleAt(int i,int j)
{
	return mesBoules[i][j];
}


public Hexagone getTable()
{
	return tableDjeu;
}

public void setTableDjeu(Hexagone table) {
	tableDjeu = table;
}


public boolean  alignement()          
{
	boolean result=false;
	int direction=-1;
		for(int j=0;j<6;j++)//la 2èm boule doit ètr dan l'un des voisin,de mm propriétair k la boule selectioné et de mm couleur
			{
				if((tableDjeu.BoulesSelectionees.get(1).getTrou().equals(tableDjeu.BoulesSelectionees.get((0)).getTrou().voisinage[j])) && (tableDjeu.BoulesSelectionees.get(1).getColor()==tableDjeu.BoulesSelectionees.get((0)).getColor()))
					{
						result=true;
						direction=j;
					
					}
			}
		if(result==true && tableDjeu.nbSelect==3) //si le 1er test est vrai on vérifie que la 3ème est voisin de la 2èm  dan la mm direction ou voisin de la 1ère boule dan la direction opposé
		{								
			result=false;
			if(direction==0 || direction==1 || direction==2)
				{
					if((tableDjeu.BoulesSelectionees.get(2).getTrou().equals(tableDjeu.BoulesSelectionees.get((1)).getTrou().voisinage[direction])) && (tableDjeu.BoulesSelectionees.get(2).getColor()==tableDjeu.BoulesSelectionees.get((1)).getColor())
					|| (tableDjeu.BoulesSelectionees.get(2).getTrou().equals(tableDjeu.BoulesSelectionees.get((0)).getTrou().voisinage[(direction+3)])) && (tableDjeu.BoulesSelectionees.get(2).getColor()==tableDjeu.BoulesSelectionees.get((0)).getColor()))
					result=true;
				}
			else
			{
				if((tableDjeu.BoulesSelectionees.get(2).getTrou().equals(tableDjeu.BoulesSelectionees.get((1)).getTrou().voisinage[direction])) && (tableDjeu.BoulesSelectionees.get(2).getColor()==tableDjeu.BoulesSelectionees.get((1)).getColor())
						|| (tableDjeu.BoulesSelectionees.get(2).getTrou().equals(tableDjeu.BoulesSelectionees.get((0)).getTrou().voisinage[abs(direction-3)])) && (tableDjeu.BoulesSelectionees.get(2).getColor()==tableDjeu.BoulesSelectionees.get((0)).getColor()))
					result=true;
			}
				
		}
		return result;
		
	}

public static  boolean testFlèche2(int dir) //teste si toutes les boules alignées peuvent se deplacer lteralement en deplacer_1
{
	for(Boule a:tableDjeu.BoulesSelectionees)
	{
		if(tableDjeu.règle.getDeplacement(a.getTrou(), dir)!=-1)
			return false;
	}
	return true;
}



public int abs(int i) {
	if(i<0)
	return -i;
	else
		return i;
}
//qui réalise le déplacement si celui ci est possible.
public static boolean  deplacer(Boule b,int direction)      
{
	boolean done=true;
	switch(tableDjeu.règle.getDeplacement(b.getTrou(),direction))                                
	{ 
	case -1:System.out.println("je joue la boule "+b.getIdBoule()+" se trouvant dans le trou "+b.getTrou().getIdTrou()+" en deplacer_1");tableDjeu.règle.deplacer_1(b,direction);break;
	case -2:System.out.println("je joue la boule "+b.getIdBoule()+" se trouvant dans le trou "+b.getTrou().getIdTrou()+" en deplacer_2");tableDjeu.règle.deplacer_2(b,direction);break;
	case -3:System.out.println("je joue la boule "+b.getIdBoule()+" se trouvant dans le trou "+b.getTrou().getIdTrou()+" en deplacer_3");tableDjeu.règle.deplacer_3(b,direction);break;
	case -4:System.out.println("je joue la boule "+b.getIdBoule()+" se trouvant dans le trou "+b.getTrou().getIdTrou()+" en pousser_2_1");tableDjeu.règle.pousser_2_1(b,direction);break;
	case -5:System.out.println("je joue la boule "+b.getIdBoule()+" se trouvant dans le trou "+b.getTrou().getIdTrou()+" en pousser_3_1");tableDjeu.règle.pousser_3_1(b,direction);break;
	case -6:System.out.println("je joue la boule "+b.getIdBoule()+" se trouvant dans le trou "+b.getTrou().getIdTrou()+" en pousser_3_2");tableDjeu.règle.pousser_3_2(b,direction);break;
	case -7:System.out.println("je joue la boule "+b.getIdBoule()+" se trouvant dans le trou "+b.getTrou().getIdTrou()+" en manger_2_1");tableDjeu.règle.manger_2_1(b,direction);break;
	case -8:System.out.println("je joue la boule "+b.getIdBoule()+" se trouvant dans le trou "+b.getTrou().getIdTrou()+" en manger_3_1");tableDjeu.règle.manger_3_1(b,direction);break;
	case -9:System.out.println("je joue la boule "+b.getIdBoule()+" se trouvant dans le trou "+b.getTrou().getIdTrou()+" en manger_3_2");tableDjeu.règle.manger_3_2(b,direction);break;
	case -20:tableDjeu.règle.Bloquer();System.out.println("bloquer");done=false;break;
	default: tableDjeu.règle.Bloquer();System.out.println("bloquer");done=false;break;
	}
			
	return done;
}

}
