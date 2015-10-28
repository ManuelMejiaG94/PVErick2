/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREATE.Extends;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class CatCategoriaExtends {
    private EntityManagerFactory emf=null;
    
    public CatCategoriaExtends()
    {
        this.emf=Persistence.createEntityManagerFactory("BDPuntoVentaManuelPU");
    }
    
    public EntityManager getEntityManager()
    {
        return this.emf.createEntityManager();
    }
    
    
    
}
