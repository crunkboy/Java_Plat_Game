package fr.uha.ensisa.abalone_game.Observer;

import java.awt.Point;
import java.util.ArrayList;

public interface Observable {
	public ArrayList<Observateur> list=new ArrayList<Observateur>() ;
	public void addObservateur(Observateur obs);
	  public void delObserver();
	  public void NotifyObserver(boolean k,boolean a, Point b, int c);
	  public void NotifyObserver(boolean a,int id);
	  public void NotifyObserver(String a,String b,int a1,int b1);
	  
}
