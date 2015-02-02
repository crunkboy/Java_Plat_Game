package fr.uha.ensisa.abalone_game.View;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
@SuppressWarnings("serial")
public class RoundButton extends JButton {


 
 
public RoundButton(String img,int taille) 
{
 super();
 ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource(img)).getImage().getScaledInstance(taille, taille, Image.SCALE_DEFAULT));
 this.setIcon(icon);
 setBorderPainted(false);
 setBorder(null);
 setContentAreaFilled(false);
 this.setFocusPainted(false);
 setVisible(false);
 }

}
