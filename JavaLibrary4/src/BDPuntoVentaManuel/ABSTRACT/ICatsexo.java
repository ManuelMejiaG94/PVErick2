/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Catsexo;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface ICatsexo {

    void create(Catsexo catsexo);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(Catsexo catsexo) throws NonexistentEntityException, Exception;

    Catsexo findCatsexo(Integer id);

    List<Catsexo> findCatsexoEntities();

    List<Catsexo> findCatsexoEntities(int maxResults, int firstResult);

    int getCatsexoCount();

    EntityManager getEntityManager();
    
}
