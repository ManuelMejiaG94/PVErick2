/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.IPago;
import BDPuntoVentaManuel.CONCREAT.PagoJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryPago {
     private static FactoryPago factory;
    
    static {
        try {
            factory=FactoryPago.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryCatCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryPago getInstance()
    {   
        return factory;
    }
    
    public IPago getInstanceAbstract()
    {
        try{
            PagoJpaController ctrlModulo= new PagoJpaController();
            return (IPago) ctrlModulo;
        }catch(Exception ex)
        {
            System.out.println("Error\n"+ex.getMessage());
            return null;
        }
    }
}


