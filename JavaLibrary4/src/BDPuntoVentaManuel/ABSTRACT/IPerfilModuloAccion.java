/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.PerfilModuloAccion;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IPerfilModuloAccion {

    void create(PerfilModuloAccion perfilModuloAccion);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(PerfilModuloAccion perfilModuloAccion) throws NonexistentEntityException, Exception;

    PerfilModuloAccion findPerfilModuloAccion(Integer id);

    List<PerfilModuloAccion> findPerfilModuloAccionEntities();

    List<PerfilModuloAccion> findPerfilModuloAccionEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getPerfilModuloAccionCount();
    
}
