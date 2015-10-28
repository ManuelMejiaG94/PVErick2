/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Pedido;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IPedido {

    void create(Pedido pedido);

    void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException;

    void edit(Pedido pedido) throws IllegalOrphanException, NonexistentEntityException, Exception;

    Pedido findPedido(Integer id);

    List<Pedido> findPedidoEntities();

    List<Pedido> findPedidoEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getPedidoCount();
    
}
