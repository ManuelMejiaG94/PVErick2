/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Producto;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IProducto {

    void create(Producto producto);

    void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException;

    void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, Exception;

    Producto findProducto(Integer id);

    List<Producto> findProductoEntities();

    List<Producto> findProductoEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getProductoCount();
    
}
