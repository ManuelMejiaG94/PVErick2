/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Provedor;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IProvedor {

    void create(Provedor provedor);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(Provedor provedor) throws NonexistentEntityException, Exception;

    Provedor findProvedor(Integer id);

    List<Provedor> findProvedorEntities();

    List<Provedor> findProvedorEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getProvedorCount();
    
}
