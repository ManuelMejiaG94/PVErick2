/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Catestatuspago;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface ICatEstatusPago {

    void create(Catestatuspago catestatuspago);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(Catestatuspago catestatuspago) throws NonexistentEntityException, Exception;

    Catestatuspago findCatestatuspago(Integer id);

    List<Catestatuspago> findCatestatuspagoEntities();

    List<Catestatuspago> findCatestatuspagoEntities(int maxResults, int firstResult);

    int getCatestatuspagoCount();

    EntityManager getEntityManager();
    
}
