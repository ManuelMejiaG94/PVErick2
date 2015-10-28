/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IPerfilModuloAccion;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Perfil;
import BDPuntoVentaManuel.MODEL.Modulo;
import BDPuntoVentaManuel.MODEL.PerfilModuloAccion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class PerfilModuloAccionJpaController implements Serializable, IPerfilModuloAccion {

    public PerfilModuloAccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public PerfilModuloAccionJpaController() {
        this.emf=Persistence.createEntityManagerFactory("BDPuntoVentaManuelPU");
    }

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(PerfilModuloAccion perfilModuloAccion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfil idPerfil = perfilModuloAccion.getIdPerfil();
            if (idPerfil != null) {
                idPerfil = em.getReference(idPerfil.getClass(), idPerfil.getId());
                perfilModuloAccion.setIdPerfil(idPerfil);
            }
            Modulo idModulo = perfilModuloAccion.getIdModulo();
            if (idModulo != null) {
                idModulo = em.getReference(idModulo.getClass(), idModulo.getId());
                perfilModuloAccion.setIdModulo(idModulo);
            }
            em.persist(perfilModuloAccion);
            if (idPerfil != null) {
                idPerfil.getPerfilModuloAccionCollection().add(perfilModuloAccion);
                idPerfil = em.merge(idPerfil);
            }
            if (idModulo != null) {
                idModulo.getPerfilModuloAccionCollection().add(perfilModuloAccion);
                idModulo = em.merge(idModulo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(PerfilModuloAccion perfilModuloAccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PerfilModuloAccion persistentPerfilModuloAccion = em.find(PerfilModuloAccion.class, perfilModuloAccion.getId());
            Perfil idPerfilOld = persistentPerfilModuloAccion.getIdPerfil();
            Perfil idPerfilNew = perfilModuloAccion.getIdPerfil();
            Modulo idModuloOld = persistentPerfilModuloAccion.getIdModulo();
            Modulo idModuloNew = perfilModuloAccion.getIdModulo();
            if (idPerfilNew != null) {
                idPerfilNew = em.getReference(idPerfilNew.getClass(), idPerfilNew.getId());
                perfilModuloAccion.setIdPerfil(idPerfilNew);
            }
            if (idModuloNew != null) {
                idModuloNew = em.getReference(idModuloNew.getClass(), idModuloNew.getId());
                perfilModuloAccion.setIdModulo(idModuloNew);
            }
            perfilModuloAccion = em.merge(perfilModuloAccion);
            if (idPerfilOld != null && !idPerfilOld.equals(idPerfilNew)) {
                idPerfilOld.getPerfilModuloAccionCollection().remove(perfilModuloAccion);
                idPerfilOld = em.merge(idPerfilOld);
            }
            if (idPerfilNew != null && !idPerfilNew.equals(idPerfilOld)) {
                idPerfilNew.getPerfilModuloAccionCollection().add(perfilModuloAccion);
                idPerfilNew = em.merge(idPerfilNew);
            }
            if (idModuloOld != null && !idModuloOld.equals(idModuloNew)) {
                idModuloOld.getPerfilModuloAccionCollection().remove(perfilModuloAccion);
                idModuloOld = em.merge(idModuloOld);
            }
            if (idModuloNew != null && !idModuloNew.equals(idModuloOld)) {
                idModuloNew.getPerfilModuloAccionCollection().add(perfilModuloAccion);
                idModuloNew = em.merge(idModuloNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = perfilModuloAccion.getId();
                if (findPerfilModuloAccion(id) == null) {
                    throw new NonexistentEntityException("The perfilModuloAccion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PerfilModuloAccion perfilModuloAccion;
            try {
                perfilModuloAccion = em.getReference(PerfilModuloAccion.class, id);
                perfilModuloAccion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perfilModuloAccion with id " + id + " no longer exists.", enfe);
            }
            Perfil idPerfil = perfilModuloAccion.getIdPerfil();
            if (idPerfil != null) {
                idPerfil.getPerfilModuloAccionCollection().remove(perfilModuloAccion);
                idPerfil = em.merge(idPerfil);
            }
            Modulo idModulo = perfilModuloAccion.getIdModulo();
            if (idModulo != null) {
                idModulo.getPerfilModuloAccionCollection().remove(perfilModuloAccion);
                idModulo = em.merge(idModulo);
            }
            em.remove(perfilModuloAccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<PerfilModuloAccion> findPerfilModuloAccionEntities() {
        return findPerfilModuloAccionEntities(true, -1, -1);
    }

    @Override
    public List<PerfilModuloAccion> findPerfilModuloAccionEntities(int maxResults, int firstResult) {
        return findPerfilModuloAccionEntities(false, maxResults, firstResult);
    }

    private List<PerfilModuloAccion> findPerfilModuloAccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PerfilModuloAccion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public PerfilModuloAccion findPerfilModuloAccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PerfilModuloAccion.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getPerfilModuloAccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PerfilModuloAccion> rt = cq.from(PerfilModuloAccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
