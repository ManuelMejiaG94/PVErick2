/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.IProvedor;
import BDPuntoVentaManuel.CONCREAT.ProvedorJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryProvedor {
      private static FactoryProvedor factory;
    
    static {
        try {
            factory=FactoryProvedor.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryCatCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryProvedor getInstance()
    {   
        return factory;
    }
    
    public IProvedor getInstanceAbstract()
    {
        try{
            ProvedorJpaController ctrlProvedor= new ProvedorJpaController();
            return (IProvedor) ctrlProvedor;
        }catch(Exception ex)
        {
            System.out.println("Error\n"+ex.getMessage());
            return null;
        }
    }
}
