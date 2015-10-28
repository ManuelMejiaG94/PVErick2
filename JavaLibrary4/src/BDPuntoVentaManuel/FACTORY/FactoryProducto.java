/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.IProducto;
import BDPuntoVentaManuel.CONCREAT.ProductoJpaController;
import BDPuntoVentaManuel.CONCREATE.Extends.ProductoJpaControllerExtends;
import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IProductExtends;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryProducto {
      private static FactoryProducto factory;
    
    static {
        try {
            factory=FactoryProducto.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryCatCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryProducto getInstance()
    {   
        return factory;
    }
    
    public IProducto getInstanceAbstract()
    {
        try{
            ProductoJpaController ctrlProducto= new ProductoJpaController();
            return (IProducto) ctrlProducto;
        }catch(Exception ex)
        {
            System.out.println("Error\n"+ex.getMessage());
            return null;
        }
    }
    
     public IProductExtends getInstanceAbstractExtends()
    {
        try{
            ProductoJpaControllerExtends ctrlProducto= new ProductoJpaControllerExtends();
            return (IProductExtends) ctrlProducto;
        }catch(Exception ex)
        {
            System.out.println("Error\n"+ex.getMessage());
            return null;
        }
    }
}
