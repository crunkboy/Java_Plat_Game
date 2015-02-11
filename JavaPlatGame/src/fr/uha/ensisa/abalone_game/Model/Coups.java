package fr.uha.ensisa.abalone_game.Model;


public class Coups {
	int [][] tableTransition={
			{1,7,-20,-20},//0
			{2,-20,-1,-20},//1
			{4,3,-2,-20},//2
			{-20,-20,-4,-7},//3
			{-20,5,-3,-20},//4
			{-20,6,-5,-8},//5
			{-20,-20,-6,-9},//6
			{-20,-8,-1,-20},//7
			{9,10,-2,-20},//8
			{-20,-20,-4,-7},//9
			{11,-20,-3,-20},//10
			{12,-20,-5,-8},//11
			{-20,-20,-6,-9}//12
			};
    
    
	
	
	
	public int getDeplacement(Trou trou,int direction) // retourne le deplacement possible pour un trou et une direction
	{

		int etat=0,suivant=0;
		Trou vois=trou.voisinage[direction];
		while(etat>=0)
		{
			etat=tableTransition[etat][suivant];
			
			if(vois.getBord()==true)
			{
				suivant=3;
			}
			else if(vois.getOccupied()==false)
			{
				suivant=2;
			}
			else if(vois.getOccupied()==true)
			{
				if(vois.getBoule().getColor()==trou.getBoule().getColor())
					{
						suivant=0;
						vois=vois.voisinage[direction];
					}
				else
					{
						suivant=1;
						vois=vois.voisinage[direction];
					}
			}
			
		}
		return etat;
		
	}
   
	
   
    
    //retourne le voisin d'un trou selon la direction indiqué
    public Trou getVoisin(Trou a,int direction)       
    {
		return a.voisinage[direction];
	}
	
  
	

	
	
	//Les fonction de déplacement
	public void deplacer_1(Boule b,int direction)
	{
		Trou temp1=getVoisin(b.getTrou(),direction);
		b.setTrou(temp1);
	}
	
	
	public void deplacer_2(Boule b,int direction)
	{
		Trou temp=getVoisin(b.getTrou(),direction);
		deplacer_1(temp.getBoule(),direction);
		deplacer_1(b,direction);
	}
	
	public void deplacer_3(Boule b,int direction)
	{
		Trou temp=getVoisin(b.getTrou(),direction);
		deplacer_2(temp.getBoule(),direction);
		deplacer_1(b,direction);
	}
	
	public void pousser_2_1(Boule b,int direction)
	{
		Trou temp=getVoisin(b.getTrou(),direction);
		deplacer_2(temp.getBoule(),direction);
		deplacer_1(b,direction);
		
	}
	public void pousser_3_1(Boule b,int direction)
	{
		Trou temp=getVoisin(b.getTrou(),direction);
		deplacer_3(temp.getBoule(),direction);
		deplacer_1(b,direction);
		
		
	}
	
	public void pousser_3_2(Boule b,int direction)
	{
		Trou temp=getVoisin(b.getTrou(),direction);
		pousser_3_1(temp.getBoule(),direction);
		deplacer_1(b,direction);
	}
	
	//précise a la boule manger qu'elle est sortie
    //on previent la table de jeu que il y'a une boule qui est sortie ainsi la table vérifie ou en est la partie
	public void manger_2_1(Boule b,int direction)
	{
		pousser_2_1(b,direction);
	}
	public void manger_3_1(Boule b,int direction)
	{
		pousser_3_1(b,direction);                                        
		    
	}
	
	public void manger_3_2(Boule b,int direction)
	{
		pousser_3_2(b,direction);
	}
	
	public void Bloquer()
	{
		//ne rien faire comme traitement
	}
	
	

}
