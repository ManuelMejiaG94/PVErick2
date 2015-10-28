/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Catcategoria;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface ICatCategoria {

    void create(Catcategoria catcategoria);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(Catcategoria catcategoria) throws NonexistentEntityException, Exception;

    Catcategoria findCatcategoria(Integer id);

    List<Catcategoria> findCatcategoriaEntities();

    List<Catcategoria> findCatcategoriaEntities(int maxResults, int firstResult);

    int getCatcategoriaCount();

    EntityManager getEntityManager();
    
}
