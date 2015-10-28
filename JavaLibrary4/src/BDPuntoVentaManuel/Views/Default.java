/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author manuel
 */
public class Default extends JPanel{
    
      public Image ImageBackground;
    public URL img;
    
    public Default()
    {
        img=this.getClass().getResource("../img/Fondo_View_Start.jpg");
        ImageBackground=new ImageIcon(img).getImage();
    }
    
    
    public void paintComponent(Graphics g)
    {        
        g.drawImage(ImageBackground,0,0,getWidth(),getHeight(),null);
    }
    
}
