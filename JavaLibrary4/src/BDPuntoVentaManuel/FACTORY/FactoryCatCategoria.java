/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.ICatCategoria;
import BDPuntoVentaManuel.CONCREAT.CatcategoriaJpaController;
import BDPuntoVentaManuel.MODEL.Catcategoria;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryCatCategoria {
    
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
    
    public ICatCategoria getInstanceAbstract()
    {
        try{
            CatcategoriaJpaController ctrlCategoria= new CatcategoriaJpaController();
            return (ICatCategoria) ctrlCategoria;
        }catch(Exception ex)
        {
            System.out.println("Error\n"+ex.getMessage());
            return null;
        }
    }
}
