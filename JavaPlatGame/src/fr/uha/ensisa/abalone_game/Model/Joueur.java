package fr.uha.ensisa.abalone_game.Model;

import java.awt.Color;


public class Joueur implements Cloneable {
	private static int cpteur=0;
	private int idJoueur; 
	private Color couleur;
	private int nBout=0;                        //nombre de boule du joueur etant d�ja sorti
    private Boule [][] mesBoules=new Boule[3][];              
    private static Hexagone tableDjeu;
	private Joueur adversaire;
	

	public Boule getBoule(int id)
	{
		for(int i=0;i<mesBoules.length;i++)
			for(int j=0;j<mesBoules[i].length;j++)
			{
				if(mesBoules[i][j].getIdBoule()==id)//on ajoute le pion id dans la liste des pion selectionn� s'il existe d�ja on l'a sort
				{ 
					return mesBoules[i][j];
				}
			}
		for(int i=0;i<mesBoules.length;i++)
			for(int j=0;j<mesBoules[i].length;j++)
			{
				if(mesBoules[i][j].getIdBoule()==id)//on ajoute le pion id dans la liste des pion selectionn� s'il existe d�ja on l'a sort
				{ 
					return mesBoules[i][j];
				}
			}
		return null;
	}

	//on ajoute la boule selection�e � l aliste des boules selection�es sinon on l'a retire si elle y est deja 
	public boolean select(int id)
	{
		System.out.println("click sur boule "+id);
		if(getBoule(id)!=null && tableDjeu.nbSelect==0)
		{				
					tableDjeu.BoulesSelectionees.add(getBoule(id));
					System.out.println("ajout�!");
					tableDjeu.nbSelect++;
					tableDjeu.NotifyObserver(true, id);
					return true;
		}
			
		//s'il y'a d�ja une boule et qu'on veut en selection� pour le deplacement lat�rale
		else if(getBoule(id)!=null && (tableDjeu.nbSelect>0 && tableDjeu.nbSelect<3) )
		{
			if(tableDjeu.BoulesSelectionees.contains(getBoule(id)))
			{	
						System.out.println("ot�");
						tableDjeu.BoulesSelectionees.remove(getBoule(id));
						tableDjeu.nbSelect--;
						tableDjeu.NotifyObserver(false, id);
						return true;
			}
			else
			{
				//on ajoute et on v�rifie l'alignement
				tableDjeu.BoulesSelectionees.add(getBoule(id));
				tableDjeu.nbSelect++;
				if(!alignement())
				{
					tableDjeu.BoulesSelectionees.remove(getBoule(id));
					tableDjeu.nbSelect--;
					//System.out.println("les boules ne sont pas align� ou ne sont pas du meme joueur");
					return false;
				}
				else
				{
					tableDjeu.NotifyObserver(true, id);
					
					//System.out.println("les boules st align�s");
					return true;
				}
				
			}
			
				
		}
		else if(tableDjeu.nbSelect==3)
		{
			if(tableDjeu.BoulesSelectionees.contains(getBoule(id)))
			{	
						System.out.println("ot�");
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
    
    
    //Met � jour le nombrede boule sorties
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
		for(int j=0;j<6;j++)//la 2�m boule doit �tr dan l'un des voisin,de mm propri�tair k la boule selection� et de mm couleur
			{
				if((tableDjeu.BoulesSelectionees.get(1).getTrou().equals(tableDjeu.BoulesSelectionees.get((0)).getTrou().voisinage[j])) && (tableDjeu.BoulesSelectionees.get(1).getColor()==tableDjeu.BoulesSelectionees.get((0)).getColor()))
					{
						result=true;
						direction=j;
					
					}
			}
		if(result==true && tableDjeu.nbSelect==3) //si le 1er test est vrai on v�rifie que la 3�me est voisin de la 2�m  dan la mm direction ou voisin de la 1�re boule dan la direction oppos�
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

public static  boolean testFl�che2(int dir) //teste si toutes les boules align�es peuvent se deplacer lteralement en deplacer_1
{
	for(Boule a:tableDjeu.BoulesSelectionees)
	{
		if(tableDjeu.r�gle.getDeplacement(a.getTrou(), dir)!=-1)
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
//qui r�alise le d�placement si celui ci est possible.
public static boolean  deplacer(Boule b,int direction)      
{
	boolean done=true;
	switch(tableDjeu.r�gle.getDeplacement(b.getTrou(),direction))                                
	{ 
	case -1:System.out.println("je joue la boule "+b.getIdBoule()+" se trouvant dans le trou "+b.getTrou().getIdTrou()+" en deplacer_1");tableDjeu.r�gle.deplacer_1(b,direction);break;
	case -2:System.out.println("je joue la boule "+b.getIdBoule()+" se trouvant dans le trou "+b.getTrou().getIdTrou()+" en deplacer_2");tableDjeu.r�gle.deplacer_2(b,direction);break;
	case -3:System.out.println("je joue la boule "+b.getIdBoule()+" se trouvant dans le trou "+b.getTrou().getIdTrou()+" en deplacer_3");tableDjeu.r�gle.deplacer_3(b,direction);break;
	case -4:System.out.println("je joue la boule "+b.getIdBoule()+" se trouvant dans le trou "+b.getTrou().getIdTrou()+" en pousser_2_1");tableDjeu.r�gle.pousser_2_1(b,direction);break;
	case -5:System.out.println("je joue la boule "+b.getIdBoule()+" se trouvant dans le trou "+b.getTrou().getIdTrou()+" en pousser_3_1");tableDjeu.r�gle.pousser_3_1(b,direction);break;
	case -6:System.out.println("je joue la boule "+b.getIdBoule()+" se trouvant dans le trou "+b.getTrou().getIdTrou()+" en pousser_3_2");tableDjeu.r�gle.pousser_3_2(b,direction);break;
	case -7:System.out.println("je joue la boule "+b.getIdBoule()+" se trouvant dans le trou "+b.getTrou().getIdTrou()+" en manger_2_1");tableDjeu.r�gle.manger_2_1(b,direction);break;
	case -8:System.out.println("je joue la boule "+b.getIdBoule()+" se trouvant dans le trou "+b.getTrou().getIdTrou()+" en manger_3_1");tableDjeu.r�gle.manger_3_1(b,direction);break;
	case -9:System.out.println("je joue la boule "+b.getIdBoule()+" se trouvant dans le trou "+b.getTrou().getIdTrou()+" en manger_3_2");tableDjeu.r�gle.manger_3_2(b,direction);break;
	case -20:tableDjeu.r�gle.Bloquer();System.out.println("bloquer");done=false;break;
	default: tableDjeu.r�gle.Bloquer();System.out.println("bloquer");done=false;break;
	}
			
	return done;
}

}
