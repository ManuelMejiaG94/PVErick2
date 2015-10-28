/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.IPedidoProducto;
import BDPuntoVentaManuel.CONCREAT.PedidoProductoJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryPedidoProducto {
      private static FactoryPedidoProducto factory;
    
    static {
        try {
            factory=FactoryPedidoProducto.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryCatCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryPedidoProducto getInstance()
    {   
        return factory;
    }
    
    public IPedidoProducto getInstanceAbstract()
    {
        try{
            PedidoProductoJpaController ctrlPedidoP= new PedidoProductoJpaController();
            return (IPedidoProducto) ctrlPedidoP;
        }catch(Exception ex)
        {
            System.out.println("Error\n"+ex.getMessage());
            return null;
        }
    }

}
