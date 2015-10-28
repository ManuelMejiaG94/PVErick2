/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.IPedido;
import BDPuntoVentaManuel.CONCREAT.PedidoJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryPedido {
     private static FactoryPedido factory;
    
    static {
        try {
            factory=FactoryPedido.class.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(FactoryCatCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FactoryPedido getInstance()
    {   
        return factory;
    }
    
    public IPedido getInstanceAbstract()
    {
        try{
            PedidoJpaController ctrlPedido= new PedidoJpaController();
            return (IPedido) ctrlPedido;
        }catch(Exception ex)
        {
            System.out.println("Error\n"+ex.getMessage());
            return null;
        }
    }



    
}
