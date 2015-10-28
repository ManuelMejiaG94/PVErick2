/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IPedidoProducto;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Producto;
import BDPuntoVentaManuel.MODEL.Pedido;
import BDPuntoVentaManuel.MODEL.PedidoProducto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class PedidoProductoJpaController implements Serializable, IPedidoProducto {

    public PedidoProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public PedidoProductoJpaController() {
        this.emf=Persistence.createEntityManagerFactory("BDPuntoVentaManuelPU");
    }
    
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(PedidoProducto pedidoProducto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto idProducto = pedidoProducto.getIdProducto();
            if (idProducto != null) {
                idProducto = em.getReference(idProducto.getClass(), idProducto.getId());
                pedidoProducto.setIdProducto(idProducto);
            }
            Pedido idPedido = pedidoProducto.getIdPedido();
            if (idPedido != null) {
                idPedido = em.getReference(idPedido.getClass(), idPedido.getId());
                pedidoProducto.setIdPedido(idPedido);
            }
            em.persist(pedidoProducto);
            if (idProducto != null) {
                idProducto.getPedidoProductoCollection().add(pedidoProducto);
                idProducto = em.merge(idProducto);
            }
            if (idPedido != null) {
                idPedido.getPedidoProductoCollection().add(pedidoProducto);
                idPedido = em.merge(idPedido);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(PedidoProducto pedidoProducto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PedidoProducto persistentPedidoProducto = em.find(PedidoProducto.class, pedidoProducto.getId());
            Producto idProductoOld = persistentPedidoProducto.getIdProducto();
            Producto idProductoNew = pedidoProducto.getIdProducto();
            Pedido idPedidoOld = persistentPedidoProducto.getIdPedido();
            Pedido idPedidoNew = pedidoProducto.getIdPedido();
            if (idProductoNew != null) {
                idProductoNew = em.getReference(idProductoNew.getClass(), idProductoNew.getId());
                pedidoProducto.setIdProducto(idProductoNew);
            }
            if (idPedidoNew != null) {
                idPedidoNew = em.getReference(idPedidoNew.getClass(), idPedidoNew.getId());
                pedidoProducto.setIdPedido(idPedidoNew);
            }
            pedidoProducto = em.merge(pedidoProducto);
            if (idProductoOld != null && !idProductoOld.equals(idProductoNew)) {
                idProductoOld.getPedidoProductoCollection().remove(pedidoProducto);
                idProductoOld = em.merge(idProductoOld);
            }
            if (idProductoNew != null && !idProductoNew.equals(idProductoOld)) {
                idProductoNew.getPedidoProductoCollection().add(pedidoProducto);
                idProductoNew = em.merge(idProductoNew);
            }
            if (idPedidoOld != null && !idPedidoOld.equals(idPedidoNew)) {
                idPedidoOld.getPedidoProductoCollection().remove(pedidoProducto);
                idPedidoOld = em.merge(idPedidoOld);
            }
            if (idPedidoNew != null && !idPedidoNew.equals(idPedidoOld)) {
                idPedidoNew.getPedidoProductoCollection().add(pedidoProducto);
                idPedidoNew = em.merge(idPedidoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedidoProducto.getId();
                if (findPedidoProducto(id) == null) {
                    throw new NonexistentEntityException("The pedidoProducto with id " + id + " no longer exists.");
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
            PedidoProducto pedidoProducto;
            try {
                pedidoProducto = em.getReference(PedidoProducto.class, id);
                pedidoProducto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedidoProducto with id " + id + " no longer exists.", enfe);
            }
            Producto idProducto = pedidoProducto.getIdProducto();
            if (idProducto != null) {
                idProducto.getPedidoProductoCollection().remove(pedidoProducto);
                idProducto = em.merge(idProducto);
            }
            Pedido idPedido = pedidoProducto.getIdPedido();
            if (idPedido != null) {
                idPedido.getPedidoProductoCollection().remove(pedidoProducto);
                idPedido = em.merge(idPedido);
            }
            em.remove(pedidoProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<PedidoProducto> findPedidoProductoEntities() {
        return findPedidoProductoEntities(true, -1, -1);
    }

    @Override
    public List<PedidoProducto> findPedidoProductoEntities(int maxResults, int firstResult) {
        return findPedidoProductoEntities(false, maxResults, firstResult);
    }

    private List<PedidoProducto> findPedidoProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PedidoProducto.class));
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
    public PedidoProducto findPedidoProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PedidoProducto.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getPedidoProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PedidoProducto> rt = cq.from(PedidoProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
