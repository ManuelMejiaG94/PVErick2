/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;
import BDPuntoVentaManuel.ABSTRACT.IModulo;
import BDPuntoVentaManuel.CONCREAT.ModuloJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryModulo {
        private static FactoryModulo factory;
    
    static {
        try {
            factory=FactoryModulo.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryCatCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryModulo getInstance()
    {   
        return factory;
    }
    
    public IModulo getInstanceAbstract()
    {
        try{
            ModuloJpaController ctrlModulo= new ModuloJpaController();
            return (IModulo) ctrlModulo;
        }catch(Exception ex)
        {
            System.out.println("Error\n"+ex.getMessage());
            return null;
        }
    }
}

