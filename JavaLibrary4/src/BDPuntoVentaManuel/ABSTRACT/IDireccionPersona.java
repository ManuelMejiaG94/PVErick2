/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.DireccionPersona;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IDireccionPersona {

    void create(DireccionPersona direccionPersona);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(DireccionPersona direccionPersona) throws NonexistentEntityException, Exception;

    DireccionPersona findDireccionPersona(Integer id);

    List<DireccionPersona> findDireccionPersonaEntities();

    List<DireccionPersona> findDireccionPersonaEntities(int maxResults, int firstResult);

    int getDireccionPersonaCount();

    EntityManager getEntityManager();
    
}
