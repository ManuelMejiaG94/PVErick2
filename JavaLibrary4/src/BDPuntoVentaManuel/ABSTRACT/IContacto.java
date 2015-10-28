/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Contacto;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IContacto {

    void create(Contacto contacto);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(Contacto contacto) throws NonexistentEntityException, Exception;

    Contacto findContacto(Integer id);

    List<Contacto> findContactoEntities();

    List<Contacto> findContactoEntities(int maxResults, int firstResult);

    int getContactoCount();

    EntityManager getEntityManager();
    
}
