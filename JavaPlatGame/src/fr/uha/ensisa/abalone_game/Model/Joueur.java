package fr.uha.ensisa.abalone_game.Model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Joueur implements Cloneable {
	private static int cpteur=0;
	private int idJoueur; 
	private Color couleur;
	private int nBout=0;                        //nombre de boule du joueur etant déja sorti
    private Boule [][] mesBoules=new Boule[3][];              
    private boolean iaJoueur;                //savoir si c'est l'ordi ou pas
    private int niveau=1;                        //si c'est l'ordi,quel est son niveau de difficulté
    private static Hexagone tableDjeu;
	private Joueur adversaire;
	private int poidJoueur;                   //somme ds poidsds trou ou st placé boule,permet d savoir kel joueur a l'avantag
	public static Boule bP=null;					//best pion a déplacer
	public static  int bD=-20;						//best déplacement
	private  int bTD=-20;	//best type de déplacement
	private int fP=-20;		//profondeur coups en flèche
	private int dF=-20; 	//direction ds voisin pr le deplacement en flèche
  
	//public static ArrayList<Point> boulePos=new ArrayList<Point>();
	
	            //le numéro du joueur associ au pion déposé dans celle-ci.cet attribu est utile pr le trou
	

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

	//on ajoute le pion id dans la liste des pion selectionné si existe déja on l'a sort
	//on ajoute la boule selectionée au boules selectionées sinon on l'a sort si elle y est deja 
	public boolean select(int id)
	{
		System.out.println("click sur boule "+id);
		if(getBoule(id)!=null && tableDjeu.nbSelect==0)
		{
			if(tableDjeu.BoulesEnJeu.contains(getBoule(id))) //si elle existe déja on l'a sort
				{	
							System.out.println("oté");
							tableDjeu.BoulesEnJeu.remove(getBoule(id));
							tableDjeu.nbSelect--;
							tableDjeu.NotifyObserver(false, id);
							return true;
				}
			else		//sinon on l'ajoute
				{
					System.out.println("ajouté");
					tableDjeu.BoulesEnJeu.add(getBoule(id));
					tableDjeu.nbSelect++;
					tableDjeu.NotifyObserver(true, id);
					return true;
				}
		}
			
		//s'il y'a déja une boule et qu'on veut en selectioné pour le deplacement latérale
		else if(getBoule(id)!=null && (tableDjeu.nbSelect>0 && tableDjeu.nbSelect<3) )
		{
			if(tableDjeu.BoulesEnJeu.contains(getBoule(id)))
			{	
						//System.out.println("oté");
						tableDjeu.BoulesEnJeu.remove(getBoule(id));
						tableDjeu.nbSelect--;
						tableDjeu.NotifyObserver(false, id);
						return true;
			}
			else
			{
				//on ajoute et on vérifie l'alignement
				tableDjeu.BoulesEnJeu.add(getBoule(id));
				tableDjeu.nbSelect++;
				if(!alignement())
				{
					tableDjeu.BoulesEnJeu.remove(getBoule(id));
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
			if(tableDjeu.BoulesEnJeu.contains(getBoule(id)))
			{	
						//System.out.println("oté");
						tableDjeu.BoulesEnJeu.remove(getBoule(id));
						tableDjeu.nbSelect--;
						tableDjeu.NotifyObserver(false, id);
						return true;
			}
			else
			{
				//System.out.println("nombre max de boule atteint");
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
		iaJoueur=ia;
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
    
//	public void getDirectionCLIC(int source)
//	{
//		Boule a=locate
//	}
   
    
   
    
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



	public int calculPoidJoueurs()
    {
    		poidJoueur=0;
	    	for(int i=0;i<mesBoules.length;i++)
	    	{
	    		for(int j=0;j<mesBoules[i].length;j++)
	    		{
	    			if(mesBoules[i][j].getSortie()==false)
	    			{
	    				 poidJoueur+=mesBoules[i][j].getTrou().getPoid();
	    			}
	    			
	    		}
	    	}
			return poidJoueur;
    	
    }
    public int getPoid()
    {
    	return poidJoueur;
    }
    
    		public final Boule save(final Boule pion,final int TypeDeplacement,final int direction)
    		{
    			Boule saved=pion;
    			
    				for(int i=0;i<abs(TypeDeplacement)-5;i++)
        				saved=saved.getTrou().voisinage[direction].getBoule();
    			
				return saved;
    		}
    		
    		
    		public void Dejoue(Boule pion,Trou retour)
    		{
    			pion.setTrou(retour);
    		}
    		
    		
   public void meilleurCoups(int level)
   {
	   int ALPHA = -(Integer.MAX_VALUE);
	    int BETA = Integer.MAX_VALUE;

	   int meilleurEval=ALPHA;
	   int evalCoups;
	   ArrayList<Boule> coups=new ArrayList<Boule>();
	   ArrayList<Point> depCordonée=new ArrayList<Point>(); 
	   for(int i=0;i<this.mesBoules.length;i++)
		   for(int j=0;j<this.mesBoules[i].length;j++)
			   if(this.mesBoules[i][j].getSortie()==false)
				   for(int direction=0;direction<6;direction++)
					   if(tableDjeu.règle.getDeplacement(this.mesBoules[i][j].getTrou(), direction)!=-20)
					   {
						   int tD=tableDjeu.règle.getDeplacement(this.mesBoules[i][j].getTrou(), direction);
						   if(tD==-7 || tD==-8 || tD==-9 || tD==-4 || tD==-5 || tD==-6)
						   {
							   bD=direction;
							   bTD=tD;
							   bP=this.mesBoules[i][j];
							   return;
						   }

						 
						   
							   Trou depart=this.mesBoules[i][j].getTrou();
							   tableDjeu.joue(this.mesBoules[i][j], direction, tD);
							   evalCoups=minValue(this.getAdversaire(),ALPHA,BETA,(level-1));
							   tableDjeu.Dejoue(depart, direction, tD);
			
							   if(evalCoups>meilleurEval)
							   {
								   meilleurEval=evalCoups;
								   coups=new ArrayList<Boule>();
								   depCordonée=new ArrayList<Point>();
								   coups.add(depart.getBoule());
								   depCordonée.add(new Point(direction,tD));
								  

							   }
							   else if(evalCoups==meilleurEval)
							   {
								   coups.add(depart.getBoule());
								   depCordonée.add(new Point(direction,tD));
		                        
							   }

							   
						   
					   }
	   Random rnd =  new Random();
	   int n=rnd.nextInt(coups.size());
	   System.out.println(n);
       bP= coups.get(n);
       bTD=depCordonée.get(n).y;
       bD=depCordonée.get(n).x;
       if(bTD==-1)
	   {
		   int pF=-20;
		  
		for(int df=0;df<6 && df!=bD;df++)
		   {
				pF=tableDjeu.testFlèche(bP.getTrou(), bD, df);
				if(pF!=-20)
				{
					if(pF==2)
					{
							fP=2;
							dF=df;
							return;
				
					}
					else
					{
						
							fP=3;
							dF=df;
							return;
					
					}
				}		   
			}
		
	   }
   }
   
   public int minValue(Joueur j1,int alpha,int beta,int profondeur)
   {
	  if(profondeur==0)
		  return tableDjeu.fctEval(j1);
	  
	   for(int i=0;i<j1.mesBoules.length;i++)
		   for(int j=0;j<j1.mesBoules[i].length;j++)
			   if(j1.mesBoules[i][j].getSortie()==false)
				   for(int direction=0;direction<6;direction++)
					   {
					   		int tD=tableDjeu.règle.getDeplacement(j1.mesBoules[i][j].getTrou(), direction);
					   		if(tD!=-20 && tD!=-7 && tD!=-8 && tD!=-9)
						   {	

								   Trou depart=j1.mesBoules[i][j].getTrou();
								   tableDjeu.joue(j1.mesBoules[i][j], direction, tD);
								  
								   beta=Math.min(beta,maxValue(j1.getAdversaire(),alpha,beta,(profondeur-1)));
								   tableDjeu.Dejoue(depart, direction, tD);
								   if(alpha>=beta)
								   {
									   return alpha;
								   }
								  
								  
							   }
					   }
						   
					  
	   return beta;
   }		
           
 
   
   public int maxValue(Joueur j1,int alpha,int beta,int profondeur)
   {
	  if(profondeur==0)
		  return tableDjeu.fctEval(j1);
	  
	   for(int i=0;i<j1.mesBoules.length;i++)
		   for(int j=0;j<j1.mesBoules[i].length;j++)
			   if(j1.mesBoules[i][j].getSortie()==false)
				   for(int direction=0;direction<6;direction++)
				   {
					   int tD=tableDjeu.règle.getDeplacement(j1.mesBoules[i][j].getTrou(), direction);
					   if(tD!=-20 && tD!=-7 && tD!=-8 && tD!=-9)
					   {

						  
							   Trou depart=j1.mesBoules[i][j].getTrou();
							   tableDjeu.joue(j1.mesBoules[i][j], direction, tD);
		
							   alpha=Math.max(alpha,minValue(j1.getAdversaire(),alpha,beta,(profondeur-1)));
							   tableDjeu.Dejoue(depart, direction, tD);
							   if(alpha>=beta)
							   {
								   return beta;
							   }
							  
							  
						  
					  }
				   }
					   
	   return alpha;
   }		



public void calculCoup()
{
	int n=(this.niveau);
	meilleurCoups(n);
	if(fP!=-20 && bTD==-1)
	{
		Trou s=bP.getTrou().voisinage[dF];
		if(fP==2)
		{
			tableDjeu.joue(s.getBoule(),bD,bTD);
			fP=-20;
		}
		else if(fP==3)
		{
			
			tableDjeu.joue(s.getBoule(),bD,bTD);
			tableDjeu.joue(s.voisinage[dF].getBoule(),bD,bTD);
			fP=-20;
		}
			
	}


}

public Boule getBouleAt(int i,int j)
{
	return mesBoules[i][j];
}

public boolean isIaJoueur() {
	return iaJoueur;
}



public int getNiveau() {
	return niveau;
}



public void setNiveau(int niveau) {
	this.niveau = niveau;
}



public void setIaJoueur(boolean iaJoueur) {
	this.iaJoueur = iaJoueur;
}

public Hexagone getTable()
{
	return tableDjeu;
}




public boolean  alignement()          
{
	boolean result=false;
	int direction=-1;
		for(int j=0;j<6;j++)//la 2èm boule doit ètr dan l'un des voisin,de mm propriétair k la boule selectioné et de mm couleur
			{
				if((tableDjeu.BoulesEnJeu.get(1).getTrou().equals(tableDjeu.BoulesEnJeu.get((0)).getTrou().voisinage[j])) && (tableDjeu.BoulesEnJeu.get(1).getColor()==tableDjeu.BoulesEnJeu.get((0)).getColor()))
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
					if((tableDjeu.BoulesEnJeu.get(2).getTrou().equals(tableDjeu.BoulesEnJeu.get((1)).getTrou().voisinage[direction])) && (tableDjeu.BoulesEnJeu.get(2).getColor()==tableDjeu.BoulesEnJeu.get((1)).getColor())
						|| (tableDjeu.BoulesEnJeu.get(2).getTrou().equals(tableDjeu.BoulesEnJeu.get((0)).getTrou().voisinage[(direction+3)])) && (tableDjeu.BoulesEnJeu.get(2).getColor()==tableDjeu.BoulesEnJeu.get((0)).getColor()))
					result=true;
				}
			else
			{
				if((tableDjeu.BoulesEnJeu.get(2).getTrou().equals(tableDjeu.BoulesEnJeu.get((1)).getTrou().voisinage[direction])) && (tableDjeu.BoulesEnJeu.get(2).getColor()==tableDjeu.BoulesEnJeu.get((1)).getColor())
						|| (tableDjeu.BoulesEnJeu.get(2).getTrou().equals(tableDjeu.BoulesEnJeu.get((0)).getTrou().voisinage[abs(direction-3)])) && (tableDjeu.BoulesEnJeu.get(2).getColor()==tableDjeu.BoulesEnJeu.get((0)).getColor()))
					result=true;
			}
				
		}
		return result;
		
	}

public static  boolean testFlèche2(int dir)
{
	for(Boule a:tableDjeu.BoulesEnJeu)
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




public void setTableDjeu(Hexagone table) {
	tableDjeu = table;
}	
	
}

 //gerer cas special du coups en flèche
// if(tD==-1)
// {
//	   for(int dj=0;dj<6 && dj!=di;dj++)
//	   {
//		  pf=tableDjeu.testFlèche(saved, di, dj);
//		   if(pf!=-20)
//		   {
//			   if(pf==2)
//			   {
//				  Trou ctmp=saved.voisinage[dj];
//				  tableDjeu.joue(saved.getBoule(), di, tD);
//				  tableDjeu.joue(ctmp.getBoule(), di, tD);
//				  evalCoups=minValue(this.getAdversaire(),ALPHA,BETA,level);
//                 tableDjeu.Dejoue(saved, di, dj);
//				  tableDjeu.Dejoue(ctmp, di, tD);
//                   // Si le coup est meilleur on ecrase le coups
//                   if (evalCoups>meilleurEval)
//                   {
//                   	System.out.println("\n\n\t\t SAUVEGARDE");
//                           meilleurEval=evalCoups;
//                           bP=maboule;
//                           bD=di;
//                           bTD=-30;
//                           pF=2;
//                   }
//			   }
//			   else
//			   {
//				   
//				 Trou ctmp=saved.voisinage[dj];
//				 Trou ctmp_1=ctmp.voisinage[dj];
//				  tableDjeu.joue(saved.getBoule(), di, tD);
//				  tableDjeu.joue(ctmp.getBoule(), di, tD);
//				  tableDjeu.joue(ctmp_1.getBoule(), di, tD);
//				  
//				  evalCoups=minValue(this.getAdversaire(),ALPHA,BETA,level);
//                 tableDjeu.Dejoue(saved, di, dj);
//				  tableDjeu.Dejoue(ctmp, di, tD);
//				  tableDjeu.Dejoue(ctmp_1, di, tD);
//				  
//                   // Si le coup est meilleur on ecrase le coups
//                   if (evalCoups>meilleurEval)
//                   {
//                   	System.out.println("\n\n\t\t SAUVEGARDE");
//                           meilleurEval=evalCoups;
//                           bP=maboule;
//                           bD=di;
//                           bTD=-30;
//                           pF=3;
//                   }
//			   }
//			   
//			  }
//		   }
//	   
// }

//max
//if(TD==-1)
//	   {
//		   for(int dj=0; dj<6 && dj!=di;dj++)
//		   {
//			   pf=tableDjeu.testFlèche(saved, di, dj);
//			   if(pf!=-20)
//			   {
//				   if(pf==2)
//				   {
//					 Trou ctmp=saved.voisinage[dj];
//						 
//						  tableDjeu.joue(saved.getBoule(), di, TD);
//						  tableDjeu.joue(ctmp.getBoule(), di, TD);
//       			   alpha= Math.max(alpha, minValue(courant.getAdversaire(),alpha, beta, (profondeur - 1)));
//       			  tableDjeu.Dejoue(saved, di, dj);
//						  tableDjeu.Dejoue(ctmp, di, TD);
//       			   if (alpha >= beta)
//                       {
//                               return beta;
//                       }
//				   }
//				   else
//				   {
//					  Trou ctmp=saved.voisinage[dj];
//						  Trou ctmp_1=ctmp.voisinage[dj];
//						  
//						  tableDjeu.joue(saved.getBoule(), di, TD);
//						  tableDjeu.joue(ctmp.getBoule(), di, TD);
//						  tableDjeu.joue(ctmp_1.getBoule(), di, TD);
//						 alpha= Math.max(alpha, minValue(courant.getAdversaire(),alpha, beta, (profondeur - 1)));
//						  
//                       tableDjeu.Dejoue(saved, di, dj);
//						  tableDjeu.Dejoue(ctmp, di, TD);
//						  tableDjeu.Dejoue(ctmp_1, di, TD);
//					 if (alpha >= beta)
//                {
//                        return beta;
//                }
//						 
//				   }
//			   }
//		   }
//	   }


//if(TD==-1)
//  {
//	   for(int dj=0; dj<6 && dj!=di;dj++)
//	   {
//		   pf=tableDjeu.testFlèche(saved, di, dj);
//		   if(pf!=-20)
//		   {
//			   if(pf==2)
//			   {
//				 Trou ctmp=saved.voisinage[dj];
//					 
//					  tableDjeu.joue(saved.getBoule(), di, TD);
//					  tableDjeu.joue(ctmp.getBoule(), di, TD);
//					beta = Math.min(beta, maxValue(courant.getAdversaire(),alpha, beta, (profondeur - 1)));	
//     			  tableDjeu.Dejoue(saved, di, dj);
//					  tableDjeu.Dejoue(ctmp, di, TD);
//					if (alpha >= beta)
//                 {
//                         return alpha;
//                 }
//			   }
//			   else
//			   {
//				  Trou ctmp=saved.voisinage[dj];
//					  Trou ctmp_1=ctmp.voisinage[dj];
//					  
//					  tableDjeu.joue(saved.getBoule(), di, TD);
//					  tableDjeu.joue(ctmp.getBoule(), di, TD);
//					  tableDjeu.joue(ctmp_1.getBoule(), di, TD);
//					beta = Math.min(beta, maxValue(courant.getAdversaire(),alpha, beta, (profondeur - 1)));	
//					  
//                     tableDjeu.Dejoue(saved, di, dj);
//					  tableDjeu.Dejoue(ctmp, di, TD);
//					  tableDjeu.Dejoue(ctmp_1, di, TD);
//					if (alpha >= beta)
//                 {
//                         return alpha;
//                 }
//						 
//			   }
//		   }
//	   }
//  }


//else if(tD==-1)
//{
//	   for(int df=0;df<6;df++)
//	   {
//		  int pF=tableDjeu.testFlèche(mesBoules[i][j].getTrou(),direction, df);
//		  if(pF!=-20)
//		  {
//			  if(pF==2)
//			  {
//				  Trou departF=mesBoules[i][j].getTrou();
//				  Trou departF2=departF.voisinage[df];
//				  tableDjeu.joue(departF.getBoule(), direction, tD);
//				  tableDjeu.joue(departF2.getBoule(), direction, tD);
//				   evalCoups=minValue(this,ALPHA,BETA,(level-1));
//				   tableDjeu.Dejoue(departF, direction, tD);
//				   tableDjeu.Dejoue(departF2, direction, tD);
//				   if(evalCoups>meilleurEval)
//				   {
//					   meilleurEval=evalCoups;
//					   bD=direction;
//					   bTD=-30;
//					   bP=departF.getBoule();
//					   fD=df;
//					   pF=2;
//					   
//				   }
//				  
//			  }
//			  else
//			  {
//				  Trou departF=mesBoules[i][j].getTrou();
//				  Trou departF2=departF.voisinage[df];
//				  Trou departF3=departF2.voisinage[df];
//				  tableDjeu.joue(departF.getBoule(), direction, tD);
//				  tableDjeu.joue(departF2.getBoule(), direction, tD);
//				  tableDjeu.joue(departF3.getBoule(), direction, tD);
//				   evalCoups=minValue(this,ALPHA,BETA,(level-1));
//				   tableDjeu.Dejoue(departF, direction, tD);
//				   tableDjeu.Dejoue(departF2, direction, tD);
//				   tableDjeu.Dejoue(departF3, direction, tD);
//				   
//				   if(evalCoups>meilleurEval)
//				   {
//					   meilleurEval=evalCoups;
//					   bD=direction;
//					   bTD=-30;
//					   bP=departF.getBoule();
//					   fD=df;
//					   pF=3;
//					   
//				   }
//			  }
//		  }
//			  
//	   }
//	   
//}

//if(tD==-1)
//	 {
//		 for(int df=0;df<6;df++)
//	   {
//		  int pF=tableDjeu.testFlèche(mesBoules[i][j].getTrou(),direction, df);
//		  if(pF!=-20)
//		  {
//			  if(pF==2)
//			  {
//				  Trou departF=mesBoules[i][j].getTrou();
//				  Trou departF2=departF.voisinage[df];
//				  tableDjeu.joue(departF.getBoule(), direction, tD);
//				  tableDjeu.joue(departF2.getBoule(), direction, tD);
//				  alpha=Math.max(alpha,minValue(j1,alpha1,beta1,(profondeur-1)));
//				   tableDjeu.Dejoue(departF, direction, tD);
//				   tableDjeu.Dejoue(departF2, direction, tD);
//				   if(alpha1>=beta1)
//				   {
//					   return beta1;
//				   }
//			  }
//			  else
//			  {
//				  Trou departF=mesBoules[i][j].getTrou();
//				  Trou departF2=departF.voisinage[df];
//				  Trou departF3=departF2.voisinage[df];
//				  tableDjeu.joue(departF.getBoule(), direction, tD);
//				  tableDjeu.joue(departF2.getBoule(), direction, tD);
//				  tableDjeu.joue(departF3.getBoule(), direction, tD);
//				  alpha=Math.max(alpha,minValue(j1,alpha1,beta1,(profondeur-1)));
//				   tableDjeu.Dejoue(departF, direction, tD);
//				   tableDjeu.Dejoue(departF2, direction, tD);
//				   tableDjeu.Dejoue(departF3, direction, tD);
//				   if(alpha1>=beta1)
//				   {
//					   return beta1;
//				   }
//				  
//			  }
//	 }
// }
//	 }

//	 if(tD==-1)
//	 {
//		 for(int df=0;df<6;df++)
//	   {
//		  int pF=tableDjeu.testFlèche(mesBoules[i][j].getTrou(),direction, df);
//		  if(pF!=-20)
//		  {
//			  if(pF==2)
//			  {
//				  Trou departF=mesBoules[i][j].getTrou();
//				  Trou departF2=departF.voisinage[df];
//				  tableDjeu.joue(departF.getBoule(), direction, tD);
//				  tableDjeu.joue(departF2.getBoule(), direction, tD);
//				  beta=Math.min(beta,maxValue(j1,alpha1,beta1,(profondeur-1)));
//				   tableDjeu.Dejoue(departF, direction, tD);
//				   tableDjeu.Dejoue(departF2, direction, tD);
//				   if(alpha>=beta)
//				   {
//					   return alpha1;
//				   }
//			  }
//			  else
//			  {
//				  Trou departF=mesBoules[i][j].getTrou();
//				  Trou departF2=departF.voisinage[df];
//				  Trou departF3=departF2.voisinage[df];
//				  tableDjeu.joue(departF.getBoule(), direction, tD);
//				  tableDjeu.joue(departF2.getBoule(), direction, tD);
//				  tableDjeu.joue(departF3.getBoule(), direction, tD);
//				  beta=Math.min(beta,maxValue(j1,alpha1,beta1,(profondeur-1)));
//				   tableDjeu.Dejoue(departF, direction, tD);
//				   tableDjeu.Dejoue(departF2, direction, tD);
//				   tableDjeu.Dejoue(departF3, direction, tD);
//				   if(alpha>=beta)
//				   {
//					   return alpha1;
//				   } 
//				  
//			  }
//	 }
// }
//	 }
