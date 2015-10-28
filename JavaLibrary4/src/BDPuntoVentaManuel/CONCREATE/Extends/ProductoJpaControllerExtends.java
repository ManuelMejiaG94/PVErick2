/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREATE.Extends;

import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IProductExtends;
import BDPuntoVentaManuel.MODEL.Producto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class ProductoJpaControllerExtends implements IProductExtends {
    private EntityManagerFactory emf=null;
    
    public ProductoJpaControllerExtends()
    {
        this.emf=Persistence.createEntityManagerFactory("BDPuntoVentaManuelPU");
    }
    
    @Override
    public EntityManager getEntityManager()
    {
        return this.emf.createEntityManager();
    }
    
    @Override
    public List<Producto> findDatadefault()
    {
         try {
            EntityManager enm = getEntityManager();

            List<Producto> listAlumno = (List<Producto>) enm.createQuery("select p from Producto p")
                    .getResultList();
            return listAlumno;
        } catch (Exception ex) {
            System.out.println("Error "+ex.getMessage());
            return null;
            }
    }
    
    @Override
    public List<Producto> FindDataByFirstLetter(String letter)
    {
         try {
            EntityManager enm = getEntityManager();

            List<Producto> listAlumno = (List<Producto>) enm.createQuery(
                    "select p from Producto p LIKE :letter")
                    .setParameter("letter",letter)
                    .getResultList();
            return listAlumno;
        } catch (Exception ex) {
            System.out.println("Error "+ex.getMessage());
            return null;
            }
    }
    
    
}
