/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Modulo;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IModulo {

    void create(Modulo modulo);

    void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException;

    void edit(Modulo modulo) throws IllegalOrphanException, NonexistentEntityException, Exception;

    Modulo findModulo(Integer id);

    List<Modulo> findModuloEntities();

    List<Modulo> findModuloEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getModuloCount();
    
}
