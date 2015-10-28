/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Pago;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IPago {

    void create(Pago pago);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(Pago pago) throws NonexistentEntityException, Exception;

    Pago findPago(Integer id);

    List<Pago> findPagoEntities();

    List<Pago> findPagoEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getPagoCount();
    
}
