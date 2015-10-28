/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IPerfil;
import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Perfil;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.PerfilModuloAccion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class PerfilJpaController implements Serializable, IPerfil {

    public PerfilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public PerfilJpaController() {
        this.emf=Persistence.createEntityManagerFactory("BDPuntoVentaManuelPU");
    }

    
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Perfil perfil) {
        if (perfil.getPerfilModuloAccionCollection() == null) {
            perfil.setPerfilModuloAccionCollection(new ArrayList<PerfilModuloAccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<PerfilModuloAccion> attachedPerfilModuloAccionCollection = new ArrayList<PerfilModuloAccion>();
            for (PerfilModuloAccion perfilModuloAccionCollectionPerfilModuloAccionToAttach : perfil.getPerfilModuloAccionCollection()) {
                perfilModuloAccionCollectionPerfilModuloAccionToAttach = em.getReference(perfilModuloAccionCollectionPerfilModuloAccionToAttach.getClass(), perfilModuloAccionCollectionPerfilModuloAccionToAttach.getId());
                attachedPerfilModuloAccionCollection.add(perfilModuloAccionCollectionPerfilModuloAccionToAttach);
            }
            perfil.setPerfilModuloAccionCollection(attachedPerfilModuloAccionCollection);
            em.persist(perfil);
            for (PerfilModuloAccion perfilModuloAccionCollectionPerfilModuloAccion : perfil.getPerfilModuloAccionCollection()) {
                Perfil oldIdPerfilOfPerfilModuloAccionCollectionPerfilModuloAccion = perfilModuloAccionCollectionPerfilModuloAccion.getIdPerfil();
                perfilModuloAccionCollectionPerfilModuloAccion.setIdPerfil(perfil);
                perfilModuloAccionCollectionPerfilModuloAccion = em.merge(perfilModuloAccionCollectionPerfilModuloAccion);
                if (oldIdPerfilOfPerfilModuloAccionCollectionPerfilModuloAccion != null) {
                    oldIdPerfilOfPerfilModuloAccionCollectionPerfilModuloAccion.getPerfilModuloAccionCollection().remove(perfilModuloAccionCollectionPerfilModuloAccion);
                    oldIdPerfilOfPerfilModuloAccionCollectionPerfilModuloAccion = em.merge(oldIdPerfilOfPerfilModuloAccionCollectionPerfilModuloAccion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Perfil perfil) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfil persistentPerfil = em.find(Perfil.class, perfil.getId());
            Collection<PerfilModuloAccion> perfilModuloAccionCollectionOld = persistentPerfil.getPerfilModuloAccionCollection();
            Collection<PerfilModuloAccion> perfilModuloAccionCollectionNew = perfil.getPerfilModuloAccionCollection();
            List<String> illegalOrphanMessages = null;
            for (PerfilModuloAccion perfilModuloAccionCollectionOldPerfilModuloAccion : perfilModuloAccionCollectionOld) {
                if (!perfilModuloAccionCollectionNew.contains(perfilModuloAccionCollectionOldPerfilModuloAccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PerfilModuloAccion " + perfilModuloAccionCollectionOldPerfilModuloAccion + " since its idPerfil field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<PerfilModuloAccion> attachedPerfilModuloAccionCollectionNew = new ArrayList<PerfilModuloAccion>();
            for (PerfilModuloAccion perfilModuloAccionCollectionNewPerfilModuloAccionToAttach : perfilModuloAccionCollectionNew) {
                perfilModuloAccionCollectionNewPerfilModuloAccionToAttach = em.getReference(perfilModuloAccionCollectionNewPerfilModuloAccionToAttach.getClass(), perfilModuloAccionCollectionNewPerfilModuloAccionToAttach.getId());
                attachedPerfilModuloAccionCollectionNew.add(perfilModuloAccionCollectionNewPerfilModuloAccionToAttach);
            }
            perfilModuloAccionCollectionNew = attachedPerfilModuloAccionCollectionNew;
            perfil.setPerfilModuloAccionCollection(perfilModuloAccionCollectionNew);
            perfil = em.merge(perfil);
            for (PerfilModuloAccion perfilModuloAccionCollectionNewPerfilModuloAccion : perfilModuloAccionCollectionNew) {
                if (!perfilModuloAccionCollectionOld.contains(perfilModuloAccionCollectionNewPerfilModuloAccion)) {
                    Perfil oldIdPerfilOfPerfilModuloAccionCollectionNewPerfilModuloAccion = perfilModuloAccionCollectionNewPerfilModuloAccion.getIdPerfil();
                    perfilModuloAccionCollectionNewPerfilModuloAccion.setIdPerfil(perfil);
                    perfilModuloAccionCollectionNewPerfilModuloAccion = em.merge(perfilModuloAccionCollectionNewPerfilModuloAccion);
                    if (oldIdPerfilOfPerfilModuloAccionCollectionNewPerfilModuloAccion != null && !oldIdPerfilOfPerfilModuloAccionCollectionNewPerfilModuloAccion.equals(perfil)) {
                        oldIdPerfilOfPerfilModuloAccionCollectionNewPerfilModuloAccion.getPerfilModuloAccionCollection().remove(perfilModuloAccionCollectionNewPerfilModuloAccion);
                        oldIdPerfilOfPerfilModuloAccionCollectionNewPerfilModuloAccion = em.merge(oldIdPerfilOfPerfilModuloAccionCollectionNewPerfilModuloAccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = perfil.getId();
                if (findPerfil(id) == null) {
                    throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.");
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
    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfil perfil;
            try {
                perfil = em.getReference(Perfil.class, id);
                perfil.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<PerfilModuloAccion> perfilModuloAccionCollectionOrphanCheck = perfil.getPerfilModuloAccionCollection();
            for (PerfilModuloAccion perfilModuloAccionCollectionOrphanCheckPerfilModuloAccion : perfilModuloAccionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Perfil (" + perfil + ") cannot be destroyed since the PerfilModuloAccion " + perfilModuloAccionCollectionOrphanCheckPerfilModuloAccion + " in its perfilModuloAccionCollection field has a non-nullable idPerfil field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(perfil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Perfil> findPerfilEntities() {
        return findPerfilEntities(true, -1, -1);
    }

    @Override
    public List<Perfil> findPerfilEntities(int maxResults, int firstResult) {
        return findPerfilEntities(false, maxResults, firstResult);
    }

    private List<Perfil> findPerfilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Perfil.class));
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
    public Perfil findPerfil(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Perfil.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getPerfilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Perfil> rt = cq.from(Perfil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
