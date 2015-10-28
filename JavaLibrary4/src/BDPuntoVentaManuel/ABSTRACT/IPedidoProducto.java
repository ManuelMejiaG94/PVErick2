/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.PedidoProducto;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IPedidoProducto {

    void create(PedidoProducto pedidoProducto);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(PedidoProducto pedidoProducto) throws NonexistentEntityException, Exception;

    PedidoProducto findPedidoProducto(Integer id);

    List<PedidoProducto> findPedidoProductoEntities();

    List<PedidoProducto> findPedidoProductoEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getPedidoProductoCount();
    
}
