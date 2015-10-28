/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;


import BDPuntoVentaManuel.ABSTRACT.IDireccionProvedor;
import BDPuntoVentaManuel.CONCREAT.DireccionPersonaJpaController;
import BDPuntoVentaManuel.MODEL.DireccionProvedor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryDireccionProvedor {
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
    
    public IDireccionProvedor getInstanceAbstract()
    {
        try{
            DireccionPersonaJpaController ctrlDirecPersona= new DireccionPersonaJpaController();
            return (IDireccionProvedor) ctrlDirecPersona;
        }catch(Exception ex)
        {
            System.out.println("Error\n"+ex.getMessage());
            return null;
        }
    }
}
