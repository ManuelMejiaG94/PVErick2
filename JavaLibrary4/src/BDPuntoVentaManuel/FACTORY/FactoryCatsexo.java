/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.ICatsexo;
import BDPuntoVentaManuel.CONCREAT.CatsexoJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryCatsexo {
     private static FactoryCatCategoria factory;
    
    static {
        try {
            factory=FactoryCatCategoria.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryCatCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryCatCategoria getInstance()
    {   
        return factory;
    }
    
    public ICatsexo getInstanceAbstract()
    {
        try{
            CatsexoJpaController ctrlSexo= new CatsexoJpaController();
            return (ICatsexo) ctrlSexo;
        }catch(Exception ex)
        {
            System.out.println("Error\n"+ex.getMessage());
            return null;
        }
    }
}
