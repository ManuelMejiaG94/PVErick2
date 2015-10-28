/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IDireccionProvedor;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.DireccionProvedor;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Provedor;
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
public class DireccionProvedorJpaController implements Serializable, IDireccionProvedor {

    public DireccionProvedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public DireccionProvedorJpaController() {
        this.emf=Persistence.createEntityManagerFactory("BDPuntoVentaManuelPU");
    }

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(DireccionProvedor direccionProvedor) {
        if (direccionProvedor.getProvedorCollection() == null) {
            direccionProvedor.setProvedorCollection(new ArrayList<Provedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Provedor> attachedProvedorCollection = new ArrayList<Provedor>();
            for (Provedor provedorCollectionProvedorToAttach : direccionProvedor.getProvedorCollection()) {
                provedorCollectionProvedorToAttach = em.getReference(provedorCollectionProvedorToAttach.getClass(), provedorCollectionProvedorToAttach.getId());
                attachedProvedorCollection.add(provedorCollectionProvedorToAttach);
            }
            direccionProvedor.setProvedorCollection(attachedProvedorCollection);
            em.persist(direccionProvedor);
            for (Provedor provedorCollectionProvedor : direccionProvedor.getProvedorCollection()) {
                DireccionProvedor oldIdDireccionOfProvedorCollectionProvedor = provedorCollectionProvedor.getIdDireccion();
                provedorCollectionProvedor.setIdDireccion(direccionProvedor);
                provedorCollectionProvedor = em.merge(provedorCollectionProvedor);
                if (oldIdDireccionOfProvedorCollectionProvedor != null) {
                    oldIdDireccionOfProvedorCollectionProvedor.getProvedorCollection().remove(provedorCollectionProvedor);
                    oldIdDireccionOfProvedorCollectionProvedor = em.merge(oldIdDireccionOfProvedorCollectionProvedor);
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
    public void edit(DireccionProvedor direccionProvedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DireccionProvedor persistentDireccionProvedor = em.find(DireccionProvedor.class, direccionProvedor.getId());
            Collection<Provedor> provedorCollectionOld = persistentDireccionProvedor.getProvedorCollection();
            Collection<Provedor> provedorCollectionNew = direccionProvedor.getProvedorCollection();
            Collection<Provedor> attachedProvedorCollectionNew = new ArrayList<Provedor>();
            for (Provedor provedorCollectionNewProvedorToAttach : provedorCollectionNew) {
                provedorCollectionNewProvedorToAttach = em.getReference(provedorCollectionNewProvedorToAttach.getClass(), provedorCollectionNewProvedorToAttach.getId());
                attachedProvedorCollectionNew.add(provedorCollectionNewProvedorToAttach);
            }
            provedorCollectionNew = attachedProvedorCollectionNew;
            direccionProvedor.setProvedorCollection(provedorCollectionNew);
            direccionProvedor = em.merge(direccionProvedor);
            for (Provedor provedorCollectionOldProvedor : provedorCollectionOld) {
                if (!provedorCollectionNew.contains(provedorCollectionOldProvedor)) {
                    provedorCollectionOldProvedor.setIdDireccion(null);
                    provedorCollectionOldProvedor = em.merge(provedorCollectionOldProvedor);
                }
            }
            for (Provedor provedorCollectionNewProvedor : provedorCollectionNew) {
                if (!provedorCollectionOld.contains(provedorCollectionNewProvedor)) {
                    DireccionProvedor oldIdDireccionOfProvedorCollectionNewProvedor = provedorCollectionNewProvedor.getIdDireccion();
                    provedorCollectionNewProvedor.setIdDireccion(direccionProvedor);
                    provedorCollectionNewProvedor = em.merge(provedorCollectionNewProvedor);
                    if (oldIdDireccionOfProvedorCollectionNewProvedor != null && !oldIdDireccionOfProvedorCollectionNewProvedor.equals(direccionProvedor)) {
                        oldIdDireccionOfProvedorCollectionNewProvedor.getProvedorCollection().remove(provedorCollectionNewProvedor);
                        oldIdDireccionOfProvedorCollectionNewProvedor = em.merge(oldIdDireccionOfProvedorCollectionNewProvedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = direccionProvedor.getId();
                if (findDireccionProvedor(id) == null) {
                    throw new NonexistentEntityException("The direccionProvedor with id " + id + " no longer exists.");
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
            DireccionProvedor direccionProvedor;
            try {
                direccionProvedor = em.getReference(DireccionProvedor.class, id);
                direccionProvedor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The direccionProvedor with id " + id + " no longer exists.", enfe);
            }
            Collection<Provedor> provedorCollection = direccionProvedor.getProvedorCollection();
            for (Provedor provedorCollectionProvedor : provedorCollection) {
                provedorCollectionProvedor.setIdDireccion(null);
                provedorCollectionProvedor = em.merge(provedorCollectionProvedor);
            }
            em.remove(direccionProvedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<DireccionProvedor> findDireccionProvedorEntities() {
        return findDireccionProvedorEntities(true, -1, -1);
    }

    @Override
    public List<DireccionProvedor> findDireccionProvedorEntities(int maxResults, int firstResult) {
        return findDireccionProvedorEntities(false, maxResults, firstResult);
    }

    private List<DireccionProvedor> findDireccionProvedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DireccionProvedor.class));
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
    public DireccionProvedor findDireccionProvedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DireccionProvedor.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getDireccionProvedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DireccionProvedor> rt = cq.from(DireccionProvedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
