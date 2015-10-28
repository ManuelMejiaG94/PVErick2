/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ABSTRACT;

import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Usuario;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public interface IUsuario {

    void create(Usuario usuario);

    void destroy(Integer id) throws NonexistentEntityException;

    void edit(Usuario usuario) throws NonexistentEntityException, Exception;

    Usuario findUsuario(Integer id);

    List<Usuario> findUsuarioEntities();

    List<Usuario> findUsuarioEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getUsuarioCount();
    
}
