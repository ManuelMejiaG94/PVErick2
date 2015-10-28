/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.DireccionProvedor;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IDireccionProvedor {

    void create(DireccionProvedor direccionProvedor);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(DireccionProvedor direccionProvedor) throws NonexistentEntityException, Exception;

    DireccionProvedor findDireccionProvedor(Integer id);

    List<DireccionProvedor> findDireccionProvedorEntities();

    List<DireccionProvedor> findDireccionProvedorEntities(int maxResults, int firstResult);

    int getDireccionProvedorCount();

    EntityManager getEntityManager();
    
}
