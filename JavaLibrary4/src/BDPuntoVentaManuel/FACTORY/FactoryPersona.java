/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.IPersona;
import BDPuntoVentaManuel.CONCREAT.PersonaJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryPersona {
      private static FactoryPersona factory;
    
    static {
        try {
            factory=FactoryPersona.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryCatCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryPersona getInstance()
    {   
        return factory;
    }
    
    public IPersona getInstanceAbstract()
    {
        try{
            PersonaJpaController ctrlPersona= new PersonaJpaController();
            return (IPersona) ctrlPersona;
        }catch(Exception ex)
        {
            System.out.println("Error\n"+ex.getMessage());
            return null;
        }
    }
}
