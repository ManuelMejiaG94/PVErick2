/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IProvedor;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.DireccionProvedor;
import BDPuntoVentaManuel.MODEL.Contacto;
import BDPuntoVentaManuel.MODEL.Catcategoria;
import BDPuntoVentaManuel.MODEL.Pedido;
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
public class ProvedorJpaController implements Serializable, IProvedor {

    public ProvedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ProvedorJpaController() {
        this.emf=Persistence.createEntityManagerFactory("BDPuntoVentaManuelPU");
    }

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Provedor provedor) {
        if (provedor.getPedidoCollection() == null) {
            provedor.setPedidoCollection(new ArrayList<Pedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DireccionProvedor idDireccion = provedor.getIdDireccion();
            if (idDireccion != null) {
                idDireccion = em.getReference(idDireccion.getClass(), idDireccion.getId());
                provedor.setIdDireccion(idDireccion);
            }
            Contacto idContacto = provedor.getIdContacto();
            if (idContacto != null) {
                idContacto = em.getReference(idContacto.getClass(), idContacto.getId());
                provedor.setIdContacto(idContacto);
            }
            Catcategoria idCategoria = provedor.getIdCategoria();
            if (idCategoria != null) {
                idCategoria = em.getReference(idCategoria.getClass(), idCategoria.getId());
                provedor.setIdCategoria(idCategoria);
            }
            Collection<Pedido> attachedPedidoCollection = new ArrayList<Pedido>();
            for (Pedido pedidoCollectionPedidoToAttach : provedor.getPedidoCollection()) {
                pedidoCollectionPedidoToAttach = em.getReference(pedidoCollectionPedidoToAttach.getClass(), pedidoCollectionPedidoToAttach.getId());
                attachedPedidoCollection.add(pedidoCollectionPedidoToAttach);
            }
            provedor.setPedidoCollection(attachedPedidoCollection);
            em.persist(provedor);
            if (idDireccion != null) {
                idDireccion.getProvedorCollection().add(provedor);
                idDireccion = em.merge(idDireccion);
            }
            if (idContacto != null) {
                idContacto.getProvedorCollection().add(provedor);
                idContacto = em.merge(idContacto);
            }
            if (idCategoria != null) {
                idCategoria.getProvedorCollection().add(provedor);
                idCategoria = em.merge(idCategoria);
            }
            for (Pedido pedidoCollectionPedido : provedor.getPedidoCollection()) {
                Provedor oldIdProvedorOfPedidoCollectionPedido = pedidoCollectionPedido.getIdProvedor();
                pedidoCollectionPedido.setIdProvedor(provedor);
                pedidoCollectionPedido = em.merge(pedidoCollectionPedido);
                if (oldIdProvedorOfPedidoCollectionPedido != null) {
                    oldIdProvedorOfPedidoCollectionPedido.getPedidoCollection().remove(pedidoCollectionPedido);
                    oldIdProvedorOfPedidoCollectionPedido = em.merge(oldIdProvedorOfPedidoCollectionPedido);
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
    public void edit(Provedor provedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Provedor persistentProvedor = em.find(Provedor.class, provedor.getId());
            DireccionProvedor idDireccionOld = persistentProvedor.getIdDireccion();
            DireccionProvedor idDireccionNew = provedor.getIdDireccion();
            Contacto idContactoOld = persistentProvedor.getIdContacto();
            Contacto idContactoNew = provedor.getIdContacto();
            Catcategoria idCategoriaOld = persistentProvedor.getIdCategoria();
            Catcategoria idCategoriaNew = provedor.getIdCategoria();
            Collection<Pedido> pedidoCollectionOld = persistentProvedor.getPedidoCollection();
            Collection<Pedido> pedidoCollectionNew = provedor.getPedidoCollection();
            if (idDireccionNew != null) {
                idDireccionNew = em.getReference(idDireccionNew.getClass(), idDireccionNew.getId());
                provedor.setIdDireccion(idDireccionNew);
            }
            if (idContactoNew != null) {
                idContactoNew = em.getReference(idContactoNew.getClass(), idContactoNew.getId());
                provedor.setIdContacto(idContactoNew);
            }
            if (idCategoriaNew != null) {
                idCategoriaNew = em.getReference(idCategoriaNew.getClass(), idCategoriaNew.getId());
                provedor.setIdCategoria(idCategoriaNew);
            }
            Collection<Pedido> attachedPedidoCollectionNew = new ArrayList<Pedido>();
            for (Pedido pedidoCollectionNewPedidoToAttach : pedidoCollectionNew) {
                pedidoCollectionNewPedidoToAttach = em.getReference(pedidoCollectionNewPedidoToAttach.getClass(), pedidoCollectionNewPedidoToAttach.getId());
                attachedPedidoCollectionNew.add(pedidoCollectionNewPedidoToAttach);
            }
            pedidoCollectionNew = attachedPedidoCollectionNew;
            provedor.setPedidoCollection(pedidoCollectionNew);
            provedor = em.merge(provedor);
            if (idDireccionOld != null && !idDireccionOld.equals(idDireccionNew)) {
                idDireccionOld.getProvedorCollection().remove(provedor);
                idDireccionOld = em.merge(idDireccionOld);
            }
            if (idDireccionNew != null && !idDireccionNew.equals(idDireccionOld)) {
                idDireccionNew.getProvedorCollection().add(provedor);
                idDireccionNew = em.merge(idDireccionNew);
            }
            if (idContactoOld != null && !idContactoOld.equals(idContactoNew)) {
                idContactoOld.getProvedorCollection().remove(provedor);
                idContactoOld = em.merge(idContactoOld);
            }
            if (idContactoNew != null && !idContactoNew.equals(idContactoOld)) {
                idContactoNew.getProvedorCollection().add(provedor);
                idContactoNew = em.merge(idContactoNew);
            }
            if (idCategoriaOld != null && !idCategoriaOld.equals(idCategoriaNew)) {
                idCategoriaOld.getProvedorCollection().remove(provedor);
                idCategoriaOld = em.merge(idCategoriaOld);
            }
            if (idCategoriaNew != null && !idCategoriaNew.equals(idCategoriaOld)) {
                idCategoriaNew.getProvedorCollection().add(provedor);
                idCategoriaNew = em.merge(idCategoriaNew);
            }
            for (Pedido pedidoCollectionOldPedido : pedidoCollectionOld) {
                if (!pedidoCollectionNew.contains(pedidoCollectionOldPedido)) {
                    pedidoCollectionOldPedido.setIdProvedor(null);
                    pedidoCollectionOldPedido = em.merge(pedidoCollectionOldPedido);
                }
            }
            for (Pedido pedidoCollectionNewPedido : pedidoCollectionNew) {
                if (!pedidoCollectionOld.contains(pedidoCollectionNewPedido)) {
                    Provedor oldIdProvedorOfPedidoCollectionNewPedido = pedidoCollectionNewPedido.getIdProvedor();
                    pedidoCollectionNewPedido.setIdProvedor(provedor);
                    pedidoCollectionNewPedido = em.merge(pedidoCollectionNewPedido);
                    if (oldIdProvedorOfPedidoCollectionNewPedido != null && !oldIdProvedorOfPedidoCollectionNewPedido.equals(provedor)) {
                        oldIdProvedorOfPedidoCollectionNewPedido.getPedidoCollection().remove(pedidoCollectionNewPedido);
                        oldIdProvedorOfPedidoCollectionNewPedido = em.merge(oldIdProvedorOfPedidoCollectionNewPedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = provedor.getId();
                if (findProvedor(id) == null) {
                    throw new NonexistentEntityException("The provedor with id " + id + " no longer exists.");
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
            Provedor provedor;
            try {
                provedor = em.getReference(Provedor.class, id);
                provedor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The provedor with id " + id + " no longer exists.", enfe);
            }
            DireccionProvedor idDireccion = provedor.getIdDireccion();
            if (idDireccion != null) {
                idDireccion.getProvedorCollection().remove(provedor);
                idDireccion = em.merge(idDireccion);
            }
            Contacto idContacto = provedor.getIdContacto();
            if (idContacto != null) {
                idContacto.getProvedorCollection().remove(provedor);
                idContacto = em.merge(idContacto);
            }
            Catcategoria idCategoria = provedor.getIdCategoria();
            if (idCategoria != null) {
                idCategoria.getProvedorCollection().remove(provedor);
                idCategoria = em.merge(idCategoria);
            }
            Collection<Pedido> pedidoCollection = provedor.getPedidoCollection();
            for (Pedido pedidoCollectionPedido : pedidoCollection) {
                pedidoCollectionPedido.setIdProvedor(null);
                pedidoCollectionPedido = em.merge(pedidoCollectionPedido);
            }
            em.remove(provedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Provedor> findProvedorEntities() {
        return findProvedorEntities(true, -1, -1);
    }

    @Override
    public List<Provedor> findProvedorEntities(int maxResults, int firstResult) {
        return findProvedorEntities(false, maxResults, firstResult);
    }

    private List<Provedor> findProvedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Provedor.class));
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
    public Provedor findProvedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Provedor.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getProvedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Provedor> rt = cq.from(Provedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
