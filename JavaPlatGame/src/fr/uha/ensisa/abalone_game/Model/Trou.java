package fr.uha.ensisa.abalone_game.Model;


import java.awt.Point;

public class Trou{
	private static int num=0;						//compteur d'attribution d'idBoule (ie Boule.cpteur)
	private int idTrou; 							//id du trou
	private boolean occupied;						//Occupation du trou par une boule
	private Boule boule;							//boule occupant le trou (si la boule n'est pas)
	public Trou[] voisinage=new Trou[6];			//6 directions permises pour le déplacement d'une boule
	private boolean bord;							//pour savoir s'il s'agit d'une cellule bord de l'hexagone
	private Point PositionTrou;						//coordonnée du trou

	
	public Trou(boolean a)
	{
		super();
		bord=a;
		
	}
	
	public Trou()
	{
		idTrou=num;
		num++;										//unicité de l'id
		occupied=false;								//initialement non occupé
	}
	
	
	// Getters and Setters
	public Boule getBoule()
	{
		return boule;
	}
	
	public void setBoule(Boule b)
	{
		if(b!=null)
			{
				this.setOccupied(true);
			}
		if(bord==false)
		boule=b;

	}
	
	public boolean getOccupied()                //but:savoir si le trou est occupé ou pas
	{
		return occupied;
	}
	
	public void setOccupied(boolean a)			//changer l'etat d'ocupation  du trou avec une valeur
	{
		occupied=a;
	}
	
	
	public void setVoisinage(Trou[] voisinage) 
	{
		this.voisinage = voisinage;
	}
	
	public boolean getBord() {					//on veut savoir si le trou est un bord ou pas
		return bord;
	}
	
	public void setBord(boolean bord) {
		this.bord = bord;
	}
	
	public static int getNum() {
		return num;
	}
	
	public static void setNum(int num) {
		Trou.num = num;
	}
	
	public int getIdTrou() {
		return idTrou;
	}
	
	public void setIdTrou(int idTrou) {
		this.idTrou = idTrou;
	}
	
	
	public Point getPositionTrou() {
		return PositionTrou;
	}
	
	
	public void setPositionTrou(Point positionTrou) {
		PositionTrou = positionTrou;
	}
	public Trou[] getVoisinage() {
		return voisinage;
	}
	
	
	public Point getPosition() {
		return PositionTrou;
	}
	
	public void setPosition(Point position) {
		PositionTrou = position;
	}	
}
