/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.IUsuario;
import BDPuntoVentaManuel.CONCREAT.UsuarioJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryUsuario {
     private static FactoryUsuario factory;
    
    static {
        try {
            factory=FactoryUsuario.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryCatCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryUsuario getInstance()
    {   
        return factory;
    }
    
    public IUsuario getInstanceAbstract()
    {
        try{
            UsuarioJpaController ctrlUsuarior= new UsuarioJpaController();
            return (IUsuario) ctrlUsuarior;
        }catch(Exception ex)
        {
            System.out.println("Error\n"+ex.getMessage());
            return null;
        }
    }
}
