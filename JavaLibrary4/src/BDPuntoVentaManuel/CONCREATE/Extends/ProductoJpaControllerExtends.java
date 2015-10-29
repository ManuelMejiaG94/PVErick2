/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREATE.Extends;

import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IProductExtends;
import BDPuntoVentaManuel.FACTORY.FactoryProducto;
import BDPuntoVentaManuel.MODEL.Producto;
import BDPuntoVentaManuel.MODEL.Catcategoria;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                    "select p from Producto p wHERE p.strNombre LIKE :letter")
                    .setParameter("letter","%"+letter+"%")
                    .getResultList();
            return listAlumno;
        } catch (Exception ex) {
            System.out.println("Error "+ex.getMessage());
            return null;
            }
    }
    
    @Override
    public List<Producto> FindDataByCategoriaId(Catcategoria categoria)
    {
        try{
            EntityManager em = getEntityManager();
            List<Producto>  products=em.createQuery("SELECT p FROM Producto p WHERE p.idCategoria= :categoriaId")
                    .setParameter("categoriaId",categoria).getResultList();
            
            em.close();
            return products;
        }catch(Exception e)
        {
            Logger.getLogger(FactoryProducto.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    @Override
    public Producto findProductByCode(String strClave) {
         try {
            EntityManager enm = getEntityManager();

            Producto product = (Producto) enm.createQuery("SELECT p FROM Producto p WHERE p.strClave = :strClave")
                    .setParameter("strClave", strClave).getSingleResult();
            return product;
        } catch (Exception ex) {
            System.out.println("Error "+ex.getMessage());
            return null;
            }
    }
}
