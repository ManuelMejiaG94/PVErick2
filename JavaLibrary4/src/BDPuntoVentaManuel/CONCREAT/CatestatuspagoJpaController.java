/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.ICatEstatusPago;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Catestatuspago;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Pago;
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
public class CatestatuspagoJpaController implements Serializable, ICatEstatusPago {

    public CatestatuspagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public CatestatuspagoJpaController() {
        this.emf=Persistence.createEntityManagerFactory("BDPuntoVentaManuelPU");
    }

    
    
    
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Catestatuspago catestatuspago) {
        if (catestatuspago.getPagoCollection() == null) {
            catestatuspago.setPagoCollection(new ArrayList<Pago>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Pago> attachedPagoCollection = new ArrayList<Pago>();
            for (Pago pagoCollectionPagoToAttach : catestatuspago.getPagoCollection()) {
                pagoCollectionPagoToAttach = em.getReference(pagoCollectionPagoToAttach.getClass(), pagoCollectionPagoToAttach.getId());
                attachedPagoCollection.add(pagoCollectionPagoToAttach);
            }
            catestatuspago.setPagoCollection(attachedPagoCollection);
            em.persist(catestatuspago);
            for (Pago pagoCollectionPago : catestatuspago.getPagoCollection()) {
                Catestatuspago oldIdEstatusPagoOfPagoCollectionPago = pagoCollectionPago.getIdEstatusPago();
                pagoCollectionPago.setIdEstatusPago(catestatuspago);
                pagoCollectionPago = em.merge(pagoCollectionPago);
                if (oldIdEstatusPagoOfPagoCollectionPago != null) {
                    oldIdEstatusPagoOfPagoCollectionPago.getPagoCollection().remove(pagoCollectionPago);
                    oldIdEstatusPagoOfPagoCollectionPago = em.merge(oldIdEstatusPagoOfPagoCollectionPago);
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
    public void edit(Catestatuspago catestatuspago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catestatuspago persistentCatestatuspago = em.find(Catestatuspago.class, catestatuspago.getId());
            Collection<Pago> pagoCollectionOld = persistentCatestatuspago.getPagoCollection();
            Collection<Pago> pagoCollectionNew = catestatuspago.getPagoCollection();
            Collection<Pago> attachedPagoCollectionNew = new ArrayList<Pago>();
            for (Pago pagoCollectionNewPagoToAttach : pagoCollectionNew) {
                pagoCollectionNewPagoToAttach = em.getReference(pagoCollectionNewPagoToAttach.getClass(), pagoCollectionNewPagoToAttach.getId());
                attachedPagoCollectionNew.add(pagoCollectionNewPagoToAttach);
            }
            pagoCollectionNew = attachedPagoCollectionNew;
            catestatuspago.setPagoCollection(pagoCollectionNew);
            catestatuspago = em.merge(catestatuspago);
            for (Pago pagoCollectionOldPago : pagoCollectionOld) {
                if (!pagoCollectionNew.contains(pagoCollectionOldPago)) {
                    pagoCollectionOldPago.setIdEstatusPago(null);
                    pagoCollectionOldPago = em.merge(pagoCollectionOldPago);
                }
            }
            for (Pago pagoCollectionNewPago : pagoCollectionNew) {
                if (!pagoCollectionOld.contains(pagoCollectionNewPago)) {
                    Catestatuspago oldIdEstatusPagoOfPagoCollectionNewPago = pagoCollectionNewPago.getIdEstatusPago();
                    pagoCollectionNewPago.setIdEstatusPago(catestatuspago);
                    pagoCollectionNewPago = em.merge(pagoCollectionNewPago);
                    if (oldIdEstatusPagoOfPagoCollectionNewPago != null && !oldIdEstatusPagoOfPagoCollectionNewPago.equals(catestatuspago)) {
                        oldIdEstatusPagoOfPagoCollectionNewPago.getPagoCollection().remove(pagoCollectionNewPago);
                        oldIdEstatusPagoOfPagoCollectionNewPago = em.merge(oldIdEstatusPagoOfPagoCollectionNewPago);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = catestatuspago.getId();
                if (findCatestatuspago(id) == null) {
                    throw new NonexistentEntityException("The catestatuspago with id " + id + " no longer exists.");
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
            Catestatuspago catestatuspago;
            try {
                catestatuspago = em.getReference(Catestatuspago.class, id);
                catestatuspago.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catestatuspago with id " + id + " no longer exists.", enfe);
            }
            Collection<Pago> pagoCollection = catestatuspago.getPagoCollection();
            for (Pago pagoCollectionPago : pagoCollection) {
                pagoCollectionPago.setIdEstatusPago(null);
                pagoCollectionPago = em.merge(pagoCollectionPago);
            }
            em.remove(catestatuspago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Catestatuspago> findCatestatuspagoEntities() {
        return findCatestatuspagoEntities(true, -1, -1);
    }

    @Override
    public List<Catestatuspago> findCatestatuspagoEntities(int maxResults, int firstResult) {
        return findCatestatuspagoEntities(false, maxResults, firstResult);
    }

    private List<Catestatuspago> findCatestatuspagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Catestatuspago.class));
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
    public Catestatuspago findCatestatuspago(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Catestatuspago.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getCatestatuspagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Catestatuspago> rt = cq.from(Catestatuspago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
