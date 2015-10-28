/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.IPerfilModuloAccion;
import BDPuntoVentaManuel.CONCREAT.PerfilModuloAccionJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryPerfilModuloAccion {
      private static FactoryPerfilModuloAccion factory;
    
    static {
        try {
            factory=FactoryPerfilModuloAccion.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryCatCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryPerfilModuloAccion getInstance()
    {   
        return factory;
    }
    
    public IPerfilModuloAccion getInstanceAbstract()
    {
        try{
            PerfilModuloAccionJpaController ctrlPerfilMA= new PerfilModuloAccionJpaController();
            return (IPerfilModuloAccion) ctrlPerfilMA;
        }catch(Exception ex)
        {
            System.out.println("Error\n"+ex.getMessage());
            return null;
        }
    }
}
