package fr.uha.ensisa.abalone_game.Observer;

import java.awt.Point;

public interface Observateur {

	public void update(boolean k, boolean a, Point b, int c);
	public void update(boolean v,int id);
	public void update(String a,String b,int a1,int b1);
}
