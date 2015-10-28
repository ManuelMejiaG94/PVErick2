/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IProducto;
import BDPuntoVentaManuel.CONCREAT.exceptions.IllegalOrphanException;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Catcategoria;
import BDPuntoVentaManuel.MODEL.PedidoProducto;
import BDPuntoVentaManuel.MODEL.Producto;
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
public class ProductoJpaController implements Serializable, IProducto {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ProductoJpaController() {
        this.emf=Persistence.createEntityManagerFactory("BDPuntoVentaManuelPU");
    }

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Producto producto) {
        if (producto.getPedidoProductoCollection() == null) {
            producto.setPedidoProductoCollection(new ArrayList<PedidoProducto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catcategoria idCategoria = producto.getIdCategoria();
            if (idCategoria != null) {
                idCategoria = em.getReference(idCategoria.getClass(), idCategoria.getId());
                producto.setIdCategoria(idCategoria);
            }
            Collection<PedidoProducto> attachedPedidoProductoCollection = new ArrayList<PedidoProducto>();
            for (PedidoProducto pedidoProductoCollectionPedidoProductoToAttach : producto.getPedidoProductoCollection()) {
                pedidoProductoCollectionPedidoProductoToAttach = em.getReference(pedidoProductoCollectionPedidoProductoToAttach.getClass(), pedidoProductoCollectionPedidoProductoToAttach.getId());
                attachedPedidoProductoCollection.add(pedidoProductoCollectionPedidoProductoToAttach);
            }
            producto.setPedidoProductoCollection(attachedPedidoProductoCollection);
            em.persist(producto);
            if (idCategoria != null) {
                idCategoria.getProductoCollection().add(producto);
                idCategoria = em.merge(idCategoria);
            }
            for (PedidoProducto pedidoProductoCollectionPedidoProducto : producto.getPedidoProductoCollection()) {
                Producto oldIdProductoOfPedidoProductoCollectionPedidoProducto = pedidoProductoCollectionPedidoProducto.getIdProducto();
                pedidoProductoCollectionPedidoProducto.setIdProducto(producto);
                pedidoProductoCollectionPedidoProducto = em.merge(pedidoProductoCollectionPedidoProducto);
                if (oldIdProductoOfPedidoProductoCollectionPedidoProducto != null) {
                    oldIdProductoOfPedidoProductoCollectionPedidoProducto.getPedidoProductoCollection().remove(pedidoProductoCollectionPedidoProducto);
                    oldIdProductoOfPedidoProductoCollectionPedidoProducto = em.merge(oldIdProductoOfPedidoProductoCollectionPedidoProducto);
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
    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getId());
            Catcategoria idCategoriaOld = persistentProducto.getIdCategoria();
            Catcategoria idCategoriaNew = producto.getIdCategoria();
            Collection<PedidoProducto> pedidoProductoCollectionOld = persistentProducto.getPedidoProductoCollection();
            Collection<PedidoProducto> pedidoProductoCollectionNew = producto.getPedidoProductoCollection();
            List<String> illegalOrphanMessages = null;
            for (PedidoProducto pedidoProductoCollectionOldPedidoProducto : pedidoProductoCollectionOld) {
                if (!pedidoProductoCollectionNew.contains(pedidoProductoCollectionOldPedidoProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PedidoProducto " + pedidoProductoCollectionOldPedidoProducto + " since its idProducto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCategoriaNew != null) {
                idCategoriaNew = em.getReference(idCategoriaNew.getClass(), idCategoriaNew.getId());
                producto.setIdCategoria(idCategoriaNew);
            }
            Collection<PedidoProducto> attachedPedidoProductoCollectionNew = new ArrayList<PedidoProducto>();
            for (PedidoProducto pedidoProductoCollectionNewPedidoProductoToAttach : pedidoProductoCollectionNew) {
                pedidoProductoCollectionNewPedidoProductoToAttach = em.getReference(pedidoProductoCollectionNewPedidoProductoToAttach.getClass(), pedidoProductoCollectionNewPedidoProductoToAttach.getId());
                attachedPedidoProductoCollectionNew.add(pedidoProductoCollectionNewPedidoProductoToAttach);
            }
            pedidoProductoCollectionNew = attachedPedidoProductoCollectionNew;
            producto.setPedidoProductoCollection(pedidoProductoCollectionNew);
            producto = em.merge(producto);
            if (idCategoriaOld != null && !idCategoriaOld.equals(idCategoriaNew)) {
                idCategoriaOld.getProductoCollection().remove(producto);
                idCategoriaOld = em.merge(idCategoriaOld);
            }
            if (idCategoriaNew != null && !idCategoriaNew.equals(idCategoriaOld)) {
                idCategoriaNew.getProductoCollection().add(producto);
                idCategoriaNew = em.merge(idCategoriaNew);
            }
            for (PedidoProducto pedidoProductoCollectionNewPedidoProducto : pedidoProductoCollectionNew) {
                if (!pedidoProductoCollectionOld.contains(pedidoProductoCollectionNewPedidoProducto)) {
                    Producto oldIdProductoOfPedidoProductoCollectionNewPedidoProducto = pedidoProductoCollectionNewPedidoProducto.getIdProducto();
                    pedidoProductoCollectionNewPedidoProducto.setIdProducto(producto);
                    pedidoProductoCollectionNewPedidoProducto = em.merge(pedidoProductoCollectionNewPedidoProducto);
                    if (oldIdProductoOfPedidoProductoCollectionNewPedidoProducto != null && !oldIdProductoOfPedidoProductoCollectionNewPedidoProducto.equals(producto)) {
                        oldIdProductoOfPedidoProductoCollectionNewPedidoProducto.getPedidoProductoCollection().remove(pedidoProductoCollectionNewPedidoProducto);
                        oldIdProductoOfPedidoProductoCollectionNewPedidoProducto = em.merge(oldIdProductoOfPedidoProductoCollectionNewPedidoProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getId();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<PedidoProducto> pedidoProductoCollectionOrphanCheck = producto.getPedidoProductoCollection();
            for (PedidoProducto pedidoProductoCollectionOrphanCheckPedidoProducto : pedidoProductoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the PedidoProducto " + pedidoProductoCollectionOrphanCheckPedidoProducto + " in its pedidoProductoCollection field has a non-nullable idProducto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Catcategoria idCategoria = producto.getIdCategoria();
            if (idCategoria != null) {
                idCategoria.getProductoCollection().remove(producto);
                idCategoria = em.merge(idCategoria);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    @Override
    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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
    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
