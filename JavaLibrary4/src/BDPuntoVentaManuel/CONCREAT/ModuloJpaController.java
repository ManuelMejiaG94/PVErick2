/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IModulo;
import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Modulo;
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
public class ModuloJpaController implements Serializable, IModulo {

    public ModuloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ModuloJpaController() {
        this.emf=Persistence.createEntityManagerFactory("BDPuntoVentaManuelPU");
    }

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Modulo modulo) {
        if (modulo.getPerfilModuloAccionCollection() == null) {
            modulo.setPerfilModuloAccionCollection(new ArrayList<PerfilModuloAccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<PerfilModuloAccion> attachedPerfilModuloAccionCollection = new ArrayList<PerfilModuloAccion>();
            for (PerfilModuloAccion perfilModuloAccionCollectionPerfilModuloAccionToAttach : modulo.getPerfilModuloAccionCollection()) {
                perfilModuloAccionCollectionPerfilModuloAccionToAttach = em.getReference(perfilModuloAccionCollectionPerfilModuloAccionToAttach.getClass(), perfilModuloAccionCollectionPerfilModuloAccionToAttach.getId());
                attachedPerfilModuloAccionCollection.add(perfilModuloAccionCollectionPerfilModuloAccionToAttach);
            }
            modulo.setPerfilModuloAccionCollection(attachedPerfilModuloAccionCollection);
            em.persist(modulo);
            for (PerfilModuloAccion perfilModuloAccionCollectionPerfilModuloAccion : modulo.getPerfilModuloAccionCollection()) {
                Modulo oldIdModuloOfPerfilModuloAccionCollectionPerfilModuloAccion = perfilModuloAccionCollectionPerfilModuloAccion.getIdModulo();
                perfilModuloAccionCollectionPerfilModuloAccion.setIdModulo(modulo);
                perfilModuloAccionCollectionPerfilModuloAccion = em.merge(perfilModuloAccionCollectionPerfilModuloAccion);
                if (oldIdModuloOfPerfilModuloAccionCollectionPerfilModuloAccion != null) {
                    oldIdModuloOfPerfilModuloAccionCollectionPerfilModuloAccion.getPerfilModuloAccionCollection().remove(perfilModuloAccionCollectionPerfilModuloAccion);
                    oldIdModuloOfPerfilModuloAccionCollectionPerfilModuloAccion = em.merge(oldIdModuloOfPerfilModuloAccionCollectionPerfilModuloAccion);
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
    public void edit(Modulo modulo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Modulo persistentModulo = em.find(Modulo.class, modulo.getId());
            Collection<PerfilModuloAccion> perfilModuloAccionCollectionOld = persistentModulo.getPerfilModuloAccionCollection();
            Collection<PerfilModuloAccion> perfilModuloAccionCollectionNew = modulo.getPerfilModuloAccionCollection();
            List<String> illegalOrphanMessages = null;
            for (PerfilModuloAccion perfilModuloAccionCollectionOldPerfilModuloAccion : perfilModuloAccionCollectionOld) {
                if (!perfilModuloAccionCollectionNew.contains(perfilModuloAccionCollectionOldPerfilModuloAccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PerfilModuloAccion " + perfilModuloAccionCollectionOldPerfilModuloAccion + " since its idModulo field is not nullable.");
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
            modulo.setPerfilModuloAccionCollection(perfilModuloAccionCollectionNew);
            modulo = em.merge(modulo);
            for (PerfilModuloAccion perfilModuloAccionCollectionNewPerfilModuloAccion : perfilModuloAccionCollectionNew) {
                if (!perfilModuloAccionCollectionOld.contains(perfilModuloAccionCollectionNewPerfilModuloAccion)) {
                    Modulo oldIdModuloOfPerfilModuloAccionCollectionNewPerfilModuloAccion = perfilModuloAccionCollectionNewPerfilModuloAccion.getIdModulo();
                    perfilModuloAccionCollectionNewPerfilModuloAccion.setIdModulo(modulo);
                    perfilModuloAccionCollectionNewPerfilModuloAccion = em.merge(perfilModuloAccionCollectionNewPerfilModuloAccion);
                    if (oldIdModuloOfPerfilModuloAccionCollectionNewPerfilModuloAccion != null && !oldIdModuloOfPerfilModuloAccionCollectionNewPerfilModuloAccion.equals(modulo)) {
                        oldIdModuloOfPerfilModuloAccionCollectionNewPerfilModuloAccion.getPerfilModuloAccionCollection().remove(perfilModuloAccionCollectionNewPerfilModuloAccion);
                        oldIdModuloOfPerfilModuloAccionCollectionNewPerfilModuloAccion = em.merge(oldIdModuloOfPerfilModuloAccionCollectionNewPerfilModuloAccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = modulo.getId();
                if (findModulo(id) == null) {
                    throw new NonexistentEntityException("The modulo with id " + id + " no longer exists.");
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
            Modulo modulo;
            try {
                modulo = em.getReference(Modulo.class, id);
                modulo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The modulo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<PerfilModuloAccion> perfilModuloAccionCollectionOrphanCheck = modulo.getPerfilModuloAccionCollection();
            for (PerfilModuloAccion perfilModuloAccionCollectionOrphanCheckPerfilModuloAccion : perfilModuloAccionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Modulo (" + modulo + ") cannot be destroyed since the PerfilModuloAccion " + perfilModuloAccionCollectionOrphanCheckPerfilModuloAccion + " in its perfilModuloAccionCollection field has a non-nullable idModulo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(modulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Modulo> findModuloEntities() {
        return findModuloEntities(true, -1, -1);
    }

    @Override
    public List<Modulo> findModuloEntities(int maxResults, int firstResult) {
        return findModuloEntities(false, maxResults, firstResult);
    }

    private List<Modulo> findModuloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Modulo.class));
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
    public Modulo findModulo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Modulo.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getModuloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Modulo> rt = cq.from(Modulo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
