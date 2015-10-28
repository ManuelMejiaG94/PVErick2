/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IPago;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Pedido;
import BDPuntoVentaManuel.MODEL.Catestatuspago;
import BDPuntoVentaManuel.MODEL.Pago;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class PagoJpaController implements Serializable, IPago {

    public PagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public PagoJpaController() {
        this.emf=Persistence.createEntityManagerFactory("BDPuntoVentaManuelPU");
    }
    
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Pago pago) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido idPedido = pago.getIdPedido();
            if (idPedido != null) {
                idPedido = em.getReference(idPedido.getClass(), idPedido.getId());
                pago.setIdPedido(idPedido);
            }
            Catestatuspago idEstatusPago = pago.getIdEstatusPago();
            if (idEstatusPago != null) {
                idEstatusPago = em.getReference(idEstatusPago.getClass(), idEstatusPago.getId());
                pago.setIdEstatusPago(idEstatusPago);
            }
            em.persist(pago);
            if (idPedido != null) {
                idPedido.getPagoCollection().add(pago);
                idPedido = em.merge(idPedido);
            }
            if (idEstatusPago != null) {
                idEstatusPago.getPagoCollection().add(pago);
                idEstatusPago = em.merge(idEstatusPago);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Pago pago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pago persistentPago = em.find(Pago.class, pago.getId());
            Pedido idPedidoOld = persistentPago.getIdPedido();
            Pedido idPedidoNew = pago.getIdPedido();
            Catestatuspago idEstatusPagoOld = persistentPago.getIdEstatusPago();
            Catestatuspago idEstatusPagoNew = pago.getIdEstatusPago();
            if (idPedidoNew != null) {
                idPedidoNew = em.getReference(idPedidoNew.getClass(), idPedidoNew.getId());
                pago.setIdPedido(idPedidoNew);
            }
            if (idEstatusPagoNew != null) {
                idEstatusPagoNew = em.getReference(idEstatusPagoNew.getClass(), idEstatusPagoNew.getId());
                pago.setIdEstatusPago(idEstatusPagoNew);
            }
            pago = em.merge(pago);
            if (idPedidoOld != null && !idPedidoOld.equals(idPedidoNew)) {
                idPedidoOld.getPagoCollection().remove(pago);
                idPedidoOld = em.merge(idPedidoOld);
            }
            if (idPedidoNew != null && !idPedidoNew.equals(idPedidoOld)) {
                idPedidoNew.getPagoCollection().add(pago);
                idPedidoNew = em.merge(idPedidoNew);
            }
            if (idEstatusPagoOld != null && !idEstatusPagoOld.equals(idEstatusPagoNew)) {
                idEstatusPagoOld.getPagoCollection().remove(pago);
                idEstatusPagoOld = em.merge(idEstatusPagoOld);
            }
            if (idEstatusPagoNew != null && !idEstatusPagoNew.equals(idEstatusPagoOld)) {
                idEstatusPagoNew.getPagoCollection().add(pago);
                idEstatusPagoNew = em.merge(idEstatusPagoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pago.getId();
                if (findPago(id) == null) {
                    throw new NonexistentEntityException("The pago with id " + id + " no longer exists.");
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
            Pago pago;
            try {
                pago = em.getReference(Pago.class, id);
                pago.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pago with id " + id + " no longer exists.", enfe);
            }
            Pedido idPedido = pago.getIdPedido();
            if (idPedido != null) {
                idPedido.getPagoCollection().remove(pago);
                idPedido = em.merge(idPedido);
            }
            Catestatuspago idEstatusPago = pago.getIdEstatusPago();
            if (idEstatusPago != null) {
                idEstatusPago.getPagoCollection().remove(pago);
                idEstatusPago = em.merge(idEstatusPago);
            }
            em.remove(pago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Pago> findPagoEntities() {
        return findPagoEntities(true, -1, -1);
    }

    @Override
    public List<Pago> findPagoEntities(int maxResults, int firstResult) {
        return findPagoEntities(false, maxResults, firstResult);
    }

    private List<Pago> findPagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pago.class));
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
    public Pago findPago(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pago.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getPagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pago> rt = cq.from(Pago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
