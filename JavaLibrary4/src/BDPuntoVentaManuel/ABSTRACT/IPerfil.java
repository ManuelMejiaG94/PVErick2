/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Perfil;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IPerfil {

    void create(Perfil perfil);

    void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException;

    void edit(Perfil perfil) throws IllegalOrphanException, NonexistentEntityException, Exception;

    Perfil findPerfil(Integer id);

    List<Perfil> findPerfilEntities();

    List<Perfil> findPerfilEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getPerfilCount();
    
}
