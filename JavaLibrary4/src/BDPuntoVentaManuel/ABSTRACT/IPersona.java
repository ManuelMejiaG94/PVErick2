/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Persona;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IPersona {

    void create(Persona persona);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(Persona persona) throws NonexistentEntityException, Exception;

    Persona findPersona(Integer id);

    List<Persona> findPersonaEntities();

    List<Persona> findPersonaEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getPersonaCount();
    
}
