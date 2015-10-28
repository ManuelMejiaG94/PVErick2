/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IPedido;
import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Provedor;
import BDPuntoVentaManuel.MODEL.Pago;
import BDPuntoVentaManuel.MODEL.Pedido;
import java.util.ArrayList;
import java.util.Collection;
import BDPuntoVentaManuel.MODEL.PedidoProducto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class PedidoJpaController implements Serializable, IPedido {

    public PedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public PedidoJpaController() {
        this.emf=Persistence.createEntityManagerFactory("BDPuntoVentaManuelPU");
    }

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Pedido pedido) {
        if (pedido.getPagoCollection() == null) {
            pedido.setPagoCollection(new ArrayList<Pago>());
        }
        if (pedido.getPedidoProductoCollection() == null) {
            pedido.setPedidoProductoCollection(new ArrayList<PedidoProducto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provedor idProvedor = pedido.getIdProvedor();
            if (idProvedor != null) {
                idProvedor = em.getReference(idProvedor.getClass(), idProvedor.getId());
                pedido.setIdProvedor(idProvedor);
            }
            Collection<Pago> attachedPagoCollection = new ArrayList<Pago>();
            for (Pago pagoCollectionPagoToAttach : pedido.getPagoCollection()) {
                pagoCollectionPagoToAttach = em.getReference(pagoCollectionPagoToAttach.getClass(), pagoCollectionPagoToAttach.getId());
                attachedPagoCollection.add(pagoCollectionPagoToAttach);
            }
            pedido.setPagoCollection(attachedPagoCollection);
            Collection<PedidoProducto> attachedPedidoProductoCollection = new ArrayList<PedidoProducto>();
            for (PedidoProducto pedidoProductoCollectionPedidoProductoToAttach : pedido.getPedidoProductoCollection()) {
                pedidoProductoCollectionPedidoProductoToAttach = em.getReference(pedidoProductoCollectionPedidoProductoToAttach.getClass(), pedidoProductoCollectionPedidoProductoToAttach.getId());
                attachedPedidoProductoCollection.add(pedidoProductoCollectionPedidoProductoToAttach);
            }
            pedido.setPedidoProductoCollection(attachedPedidoProductoCollection);
            em.persist(pedido);
            if (idProvedor != null) {
                idProvedor.getPedidoCollection().add(pedido);
                idProvedor = em.merge(idProvedor);
            }
            for (Pago pagoCollectionPago : pedido.getPagoCollection()) {
                Pedido oldIdPedidoOfPagoCollectionPago = pagoCollectionPago.getIdPedido();
                pagoCollectionPago.setIdPedido(pedido);
                pagoCollectionPago = em.merge(pagoCollectionPago);
                if (oldIdPedidoOfPagoCollectionPago != null) {
                    oldIdPedidoOfPagoCollectionPago.getPagoCollection().remove(pagoCollectionPago);
                    oldIdPedidoOfPagoCollectionPago = em.merge(oldIdPedidoOfPagoCollectionPago);
                }
            }
            for (PedidoProducto pedidoProductoCollectionPedidoProducto : pedido.getPedidoProductoCollection()) {
                Pedido oldIdPedidoOfPedidoProductoCollectionPedidoProducto = pedidoProductoCollectionPedidoProducto.getIdPedido();
                pedidoProductoCollectionPedidoProducto.setIdPedido(pedido);
                pedidoProductoCollectionPedidoProducto = em.merge(pedidoProductoCollectionPedidoProducto);
                if (oldIdPedidoOfPedidoProductoCollectionPedidoProducto != null) {
                    oldIdPedidoOfPedidoProductoCollectionPedidoProducto.getPedidoProductoCollection().remove(pedidoProductoCollectionPedidoProducto);
                    oldIdPedidoOfPedidoProductoCollectionPedidoProducto = em.merge(oldIdPedidoOfPedidoProductoCollectionPedidoProducto);
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
    public void edit(Pedido pedido) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido persistentPedido = em.find(Pedido.class, pedido.getId());
            Provedor idProvedorOld = persistentPedido.getIdProvedor();
            Provedor idProvedorNew = pedido.getIdProvedor();
            Collection<Pago> pagoCollectionOld = persistentPedido.getPagoCollection();
            Collection<Pago> pagoCollectionNew = pedido.getPagoCollection();
            Collection<PedidoProducto> pedidoProductoCollectionOld = persistentPedido.getPedidoProductoCollection();
            Collection<PedidoProducto> pedidoProductoCollectionNew = pedido.getPedidoProductoCollection();
            List<String> illegalOrphanMessages = null;
            for (PedidoProducto pedidoProductoCollectionOldPedidoProducto : pedidoProductoCollectionOld) {
                if (!pedidoProductoCollectionNew.contains(pedidoProductoCollectionOldPedidoProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PedidoProducto " + pedidoProductoCollectionOldPedidoProducto + " since its idPedido field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idProvedorNew != null) {
                idProvedorNew = em.getReference(idProvedorNew.getClass(), idProvedorNew.getId());
                pedido.setIdProvedor(idProvedorNew);
            }
            Collection<Pago> attachedPagoCollectionNew = new ArrayList<Pago>();
            for (Pago pagoCollectionNewPagoToAttach : pagoCollectionNew) {
                pagoCollectionNewPagoToAttach = em.getReference(pagoCollectionNewPagoToAttach.getClass(), pagoCollectionNewPagoToAttach.getId());
                attachedPagoCollectionNew.add(pagoCollectionNewPagoToAttach);
            }
            pagoCollectionNew = attachedPagoCollectionNew;
            pedido.setPagoCollection(pagoCollectionNew);
            Collection<PedidoProducto> attachedPedidoProductoCollectionNew = new ArrayList<PedidoProducto>();
            for (PedidoProducto pedidoProductoCollectionNewPedidoProductoToAttach : pedidoProductoCollectionNew) {
                pedidoProductoCollectionNewPedidoProductoToAttach = em.getReference(pedidoProductoCollectionNewPedidoProductoToAttach.getClass(), pedidoProductoCollectionNewPedidoProductoToAttach.getId());
                attachedPedidoProductoCollectionNew.add(pedidoProductoCollectionNewPedidoProductoToAttach);
            }
            pedidoProductoCollectionNew = attachedPedidoProductoCollectionNew;
            pedido.setPedidoProductoCollection(pedidoProductoCollectionNew);
            pedido = em.merge(pedido);
            if (idProvedorOld != null && !idProvedorOld.equals(idProvedorNew)) {
                idProvedorOld.getPedidoCollection().remove(pedido);
                idProvedorOld = em.merge(idProvedorOld);
            }
            if (idProvedorNew != null && !idProvedorNew.equals(idProvedorOld)) {
                idProvedorNew.getPedidoCollection().add(pedido);
                idProvedorNew = em.merge(idProvedorNew);
            }
            for (Pago pagoCollectionOldPago : pagoCollectionOld) {
                if (!pagoCollectionNew.contains(pagoCollectionOldPago)) {
                    pagoCollectionOldPago.setIdPedido(null);
                    pagoCollectionOldPago = em.merge(pagoCollectionOldPago);
                }
            }
            for (Pago pagoCollectionNewPago : pagoCollectionNew) {
                if (!pagoCollectionOld.contains(pagoCollectionNewPago)) {
                    Pedido oldIdPedidoOfPagoCollectionNewPago = pagoCollectionNewPago.getIdPedido();
                    pagoCollectionNewPago.setIdPedido(pedido);
                    pagoCollectionNewPago = em.merge(pagoCollectionNewPago);
                    if (oldIdPedidoOfPagoCollectionNewPago != null && !oldIdPedidoOfPagoCollectionNewPago.equals(pedido)) {
                        oldIdPedidoOfPagoCollectionNewPago.getPagoCollection().remove(pagoCollectionNewPago);
                        oldIdPedidoOfPagoCollectionNewPago = em.merge(oldIdPedidoOfPagoCollectionNewPago);
                    }
                }
            }
            for (PedidoProducto pedidoProductoCollectionNewPedidoProducto : pedidoProductoCollectionNew) {
                if (!pedidoProductoCollectionOld.contains(pedidoProductoCollectionNewPedidoProducto)) {
                    Pedido oldIdPedidoOfPedidoProductoCollectionNewPedidoProducto = pedidoProductoCollectionNewPedidoProducto.getIdPedido();
                    pedidoProductoCollectionNewPedidoProducto.setIdPedido(pedido);
                    pedidoProductoCollectionNewPedidoProducto = em.merge(pedidoProductoCollectionNewPedidoProducto);
                    if (oldIdPedidoOfPedidoProductoCollectionNewPedidoProducto != null && !oldIdPedidoOfPedidoProductoCollectionNewPedidoProducto.equals(pedido)) {
                        oldIdPedidoOfPedidoProductoCollectionNewPedidoProducto.getPedidoProductoCollection().remove(pedidoProductoCollectionNewPedidoProducto);
                        oldIdPedidoOfPedidoProductoCollectionNewPedidoProducto = em.merge(oldIdPedidoOfPedidoProductoCollectionNewPedidoProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedido.getId();
                if (findPedido(id) == null) {
                    throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.");
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
            Pedido pedido;
            try {
                pedido = em.getReference(Pedido.class, id);
                pedido.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<PedidoProducto> pedidoProductoCollectionOrphanCheck = pedido.getPedidoProductoCollection();
            for (PedidoProducto pedidoProductoCollectionOrphanCheckPedidoProducto : pedidoProductoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pedido (" + pedido + ") cannot be destroyed since the PedidoProducto " + pedidoProductoCollectionOrphanCheckPedidoProducto + " in its pedidoProductoCollection field has a non-nullable idPedido field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Provedor idProvedor = pedido.getIdProvedor();
            if (idProvedor != null) {
                idProvedor.getPedidoCollection().remove(pedido);
                idProvedor = em.merge(idProvedor);
            }
            Collection<Pago> pagoCollection = pedido.getPagoCollection();
            for (Pago pagoCollectionPago : pagoCollection) {
                pagoCollectionPago.setIdPedido(null);
                pagoCollectionPago = em.merge(pagoCollectionPago);
            }
            em.remove(pedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Pedido> findPedidoEntities() {
        return findPedidoEntities(true, -1, -1);
    }

    @Override
    public List<Pedido> findPedidoEntities(int maxResults, int firstResult) {
        return findPedidoEntities(false, maxResults, firstResult);
    }

    private List<Pedido> findPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedido.class));
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
    public Pedido findPedido(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedido.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedido> rt = cq.from(Pedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
