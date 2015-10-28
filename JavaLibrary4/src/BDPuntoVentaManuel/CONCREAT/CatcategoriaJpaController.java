/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.ICatCategoria;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Catcategoria;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Provedor;
import java.util.ArrayList;
import java.util.Collection;
import BDPuntoVentaManuel.MODEL.Producto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class CatcategoriaJpaController implements Serializable, ICatCategoria {

    public CatcategoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public CatcategoriaJpaController()
    {
        this.emf=Persistence.createEntityManagerFactory("BDPuntoVentaManuelPU");
    }
    
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Catcategoria catcategoria) {
        if (catcategoria.getProvedorCollection() == null) {
            catcategoria.setProvedorCollection(new ArrayList<Provedor>());
        }
        if (catcategoria.getProductoCollection() == null) {
            catcategoria.setProductoCollection(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Provedor> attachedProvedorCollection = new ArrayList<Provedor>();
            for (Provedor provedorCollectionProvedorToAttach : catcategoria.getProvedorCollection()) {
                provedorCollectionProvedorToAttach = em.getReference(provedorCollectionProvedorToAttach.getClass(), provedorCollectionProvedorToAttach.getId());
                attachedProvedorCollection.add(provedorCollectionProvedorToAttach);
            }
            catcategoria.setProvedorCollection(attachedProvedorCollection);
            Collection<Producto> attachedProductoCollection = new ArrayList<Producto>();
            for (Producto productoCollectionProductoToAttach : catcategoria.getProductoCollection()) {
                productoCollectionProductoToAttach = em.getReference(productoCollectionProductoToAttach.getClass(), productoCollectionProductoToAttach.getId());
                attachedProductoCollection.add(productoCollectionProductoToAttach);
            }
            catcategoria.setProductoCollection(attachedProductoCollection);
            em.persist(catcategoria);
            for (Provedor provedorCollectionProvedor : catcategoria.getProvedorCollection()) {
                Catcategoria oldIdCategoriaOfProvedorCollectionProvedor = provedorCollectionProvedor.getIdCategoria();
                provedorCollectionProvedor.setIdCategoria(catcategoria);
                provedorCollectionProvedor = em.merge(provedorCollectionProvedor);
                if (oldIdCategoriaOfProvedorCollectionProvedor != null) {
                    oldIdCategoriaOfProvedorCollectionProvedor.getProvedorCollection().remove(provedorCollectionProvedor);
                    oldIdCategoriaOfProvedorCollectionProvedor = em.merge(oldIdCategoriaOfProvedorCollectionProvedor);
                }
            }
            for (Producto productoCollectionProducto : catcategoria.getProductoCollection()) {
                Catcategoria oldIdCategoriaOfProductoCollectionProducto = productoCollectionProducto.getIdCategoria();
                productoCollectionProducto.setIdCategoria(catcategoria);
                productoCollectionProducto = em.merge(productoCollectionProducto);
                if (oldIdCategoriaOfProductoCollectionProducto != null) {
                    oldIdCategoriaOfProductoCollectionProducto.getProductoCollection().remove(productoCollectionProducto);
                    oldIdCategoriaOfProductoCollectionProducto = em.merge(oldIdCategoriaOfProductoCollectionProducto);
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
    public void edit(Catcategoria catcategoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catcategoria persistentCatcategoria = em.find(Catcategoria.class, catcategoria.getId());
            Collection<Provedor> provedorCollectionOld = persistentCatcategoria.getProvedorCollection();
            Collection<Provedor> provedorCollectionNew = catcategoria.getProvedorCollection();
            Collection<Producto> productoCollectionOld = persistentCatcategoria.getProductoCollection();
            Collection<Producto> productoCollectionNew = catcategoria.getProductoCollection();
            Collection<Provedor> attachedProvedorCollectionNew = new ArrayList<Provedor>();
            for (Provedor provedorCollectionNewProvedorToAttach : provedorCollectionNew) {
                provedorCollectionNewProvedorToAttach = em.getReference(provedorCollectionNewProvedorToAttach.getClass(), provedorCollectionNewProvedorToAttach.getId());
                attachedProvedorCollectionNew.add(provedorCollectionNewProvedorToAttach);
            }
            provedorCollectionNew = attachedProvedorCollectionNew;
            catcategoria.setProvedorCollection(provedorCollectionNew);
            Collection<Producto> attachedProductoCollectionNew = new ArrayList<Producto>();
            for (Producto productoCollectionNewProductoToAttach : productoCollectionNew) {
                productoCollectionNewProductoToAttach = em.getReference(productoCollectionNewProductoToAttach.getClass(), productoCollectionNewProductoToAttach.getId());
                attachedProductoCollectionNew.add(productoCollectionNewProductoToAttach);
            }
            productoCollectionNew = attachedProductoCollectionNew;
            catcategoria.setProductoCollection(productoCollectionNew);
            catcategoria = em.merge(catcategoria);
            for (Provedor provedorCollectionOldProvedor : provedorCollectionOld) {
                if (!provedorCollectionNew.contains(provedorCollectionOldProvedor)) {
                    provedorCollectionOldProvedor.setIdCategoria(null);
                    provedorCollectionOldProvedor = em.merge(provedorCollectionOldProvedor);
                }
            }
            for (Provedor provedorCollectionNewProvedor : provedorCollectionNew) {
                if (!provedorCollectionOld.contains(provedorCollectionNewProvedor)) {
                    Catcategoria oldIdCategoriaOfProvedorCollectionNewProvedor = provedorCollectionNewProvedor.getIdCategoria();
                    provedorCollectionNewProvedor.setIdCategoria(catcategoria);
                    provedorCollectionNewProvedor = em.merge(provedorCollectionNewProvedor);
                    if (oldIdCategoriaOfProvedorCollectionNewProvedor != null && !oldIdCategoriaOfProvedorCollectionNewProvedor.equals(catcategoria)) {
                        oldIdCategoriaOfProvedorCollectionNewProvedor.getProvedorCollection().remove(provedorCollectionNewProvedor);
                        oldIdCategoriaOfProvedorCollectionNewProvedor = em.merge(oldIdCategoriaOfProvedorCollectionNewProvedor);
                    }
                }
            }
            for (Producto productoCollectionOldProducto : productoCollectionOld) {
                if (!productoCollectionNew.contains(productoCollectionOldProducto)) {
                    productoCollectionOldProducto.setIdCategoria(null);
                    productoCollectionOldProducto = em.merge(productoCollectionOldProducto);
                }
            }
            for (Producto productoCollectionNewProducto : productoCollectionNew) {
                if (!productoCollectionOld.contains(productoCollectionNewProducto)) {
                    Catcategoria oldIdCategoriaOfProductoCollectionNewProducto = productoCollectionNewProducto.getIdCategoria();
                    productoCollectionNewProducto.setIdCategoria(catcategoria);
                    productoCollectionNewProducto = em.merge(productoCollectionNewProducto);
                    if (oldIdCategoriaOfProductoCollectionNewProducto != null && !oldIdCategoriaOfProductoCollectionNewProducto.equals(catcategoria)) {
                        oldIdCategoriaOfProductoCollectionNewProducto.getProductoCollection().remove(productoCollectionNewProducto);
                        oldIdCategoriaOfProductoCollectionNewProducto = em.merge(oldIdCategoriaOfProductoCollectionNewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = catcategoria.getId();
                if (findCatcategoria(id) == null) {
                    throw new NonexistentEntityException("The catcategoria with id " + id + " no longer exists.");
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
            Catcategoria catcategoria;
            try {
                catcategoria = em.getReference(Catcategoria.class, id);
                catcategoria.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catcategoria with id " + id + " no longer exists.", enfe);
            }
            Collection<Provedor> provedorCollection = catcategoria.getProvedorCollection();
            for (Provedor provedorCollectionProvedor : provedorCollection) {
                provedorCollectionProvedor.setIdCategoria(null);
                provedorCollectionProvedor = em.merge(provedorCollectionProvedor);
            }
            Collection<Producto> productoCollection = catcategoria.getProductoCollection();
            for (Producto productoCollectionProducto : productoCollection) {
                productoCollectionProducto.setIdCategoria(null);
                productoCollectionProducto = em.merge(productoCollectionProducto);
            }
            em.remove(catcategoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Catcategoria> findCatcategoriaEntities() {
        return findCatcategoriaEntities(true, -1, -1);
    }

    @Override
    public List<Catcategoria> findCatcategoriaEntities(int maxResults, int firstResult) {
        return findCatcategoriaEntities(false, maxResults, firstResult);
    }

    private List<Catcategoria> findCatcategoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Catcategoria.class));
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
    public Catcategoria findCatcategoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Catcategoria.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getCatcategoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Catcategoria> rt = cq.from(Catcategoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
