/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.FACTORY;

import BDPuntoVentaManuel.ABSTRACT.ICatEstatusPago;
import BDPuntoVentaManuel.CONCREAT.CatestatuspagoJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manuel
 */
public class FactoryCatestatuspago {
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
    
    public ICatEstatusPago getInstanceAbstract()
    {
        try{
            CatestatuspagoJpaController ctrlEstatusPago= new CatestatuspagoJpaController();
            return (ICatEstatusPago) ctrlEstatusPago;
        }catch(Exception ex)
        {
            System.out.println("Error\n"+ex.getMessage());
            return null;
        }
    }
}
