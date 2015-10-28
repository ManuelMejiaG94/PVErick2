/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.IPerfil;
import BDPuntoVentaManuel.CONCREAT.PerfilJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryPerfil {
      private static FactoryPerfil factory;
    
    static {
        try {
            factory=FactoryPerfil.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryCatCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryPerfil getInstance()
    {   
        return factory;
    }
    
    public IPerfil getInstanceAbstract()
    {
        try{
            PerfilJpaController ctrlPerfil= new PerfilJpaController();
            return (IPerfil) ctrlPerfil;
        }catch(Exception ex)
        {
            System.out.println("Error\n"+ex.getMessage());
            return null;
        }
    }

}
