/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IContacto;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Contacto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Persona;
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
public class ContactoJpaController implements Serializable, IContacto {

    public ContactoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ContactoJpaController() {
        this.emf=Persistence.createEntityManagerFactory("BDPuntoVentaManuelPU");
    }

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Contacto contacto) {
        if (contacto.getProvedorCollection() == null) {
            contacto.setProvedorCollection(new ArrayList<Provedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona idPersona = contacto.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getId());
                contacto.setIdPersona(idPersona);
            }
            Collection<Provedor> attachedProvedorCollection = new ArrayList<Provedor>();
            for (Provedor provedorCollectionProvedorToAttach : contacto.getProvedorCollection()) {
                provedorCollectionProvedorToAttach = em.getReference(provedorCollectionProvedorToAttach.getClass(), provedorCollectionProvedorToAttach.getId());
                attachedProvedorCollection.add(provedorCollectionProvedorToAttach);
            }
            contacto.setProvedorCollection(attachedProvedorCollection);
            em.persist(contacto);
            if (idPersona != null) {
                idPersona.getContactoCollection().add(contacto);
                idPersona = em.merge(idPersona);
            }
            for (Provedor provedorCollectionProvedor : contacto.getProvedorCollection()) {
                Contacto oldIdContactoOfProvedorCollectionProvedor = provedorCollectionProvedor.getIdContacto();
                provedorCollectionProvedor.setIdContacto(contacto);
                provedorCollectionProvedor = em.merge(provedorCollectionProvedor);
                if (oldIdContactoOfProvedorCollectionProvedor != null) {
                    oldIdContactoOfProvedorCollectionProvedor.getProvedorCollection().remove(provedorCollectionProvedor);
                    oldIdContactoOfProvedorCollectionProvedor = em.merge(oldIdContactoOfProvedorCollectionProvedor);
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
    public void edit(Contacto contacto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contacto persistentContacto = em.find(Contacto.class, contacto.getId());
            Persona idPersonaOld = persistentContacto.getIdPersona();
            Persona idPersonaNew = contacto.getIdPersona();
            Collection<Provedor> provedorCollectionOld = persistentContacto.getProvedorCollection();
            Collection<Provedor> provedorCollectionNew = contacto.getProvedorCollection();
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getId());
                contacto.setIdPersona(idPersonaNew);
            }
            Collection<Provedor> attachedProvedorCollectionNew = new ArrayList<Provedor>();
            for (Provedor provedorCollectionNewProvedorToAttach : provedorCollectionNew) {
                provedorCollectionNewProvedorToAttach = em.getReference(provedorCollectionNewProvedorToAttach.getClass(), provedorCollectionNewProvedorToAttach.getId());
                attachedProvedorCollectionNew.add(provedorCollectionNewProvedorToAttach);
            }
            provedorCollectionNew = attachedProvedorCollectionNew;
            contacto.setProvedorCollection(provedorCollectionNew);
            contacto = em.merge(contacto);
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getContactoCollection().remove(contacto);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getContactoCollection().add(contacto);
                idPersonaNew = em.merge(idPersonaNew);
            }
            for (Provedor provedorCollectionOldProvedor : provedorCollectionOld) {
                if (!provedorCollectionNew.contains(provedorCollectionOldProvedor)) {
                    provedorCollectionOldProvedor.setIdContacto(null);
                    provedorCollectionOldProvedor = em.merge(provedorCollectionOldProvedor);
                }
            }
            for (Provedor provedorCollectionNewProvedor : provedorCollectionNew) {
                if (!provedorCollectionOld.contains(provedorCollectionNewProvedor)) {
                    Contacto oldIdContactoOfProvedorCollectionNewProvedor = provedorCollectionNewProvedor.getIdContacto();
                    provedorCollectionNewProvedor.setIdContacto(contacto);
                    provedorCollectionNewProvedor = em.merge(provedorCollectionNewProvedor);
                    if (oldIdContactoOfProvedorCollectionNewProvedor != null && !oldIdContactoOfProvedorCollectionNewProvedor.equals(contacto)) {
                        oldIdContactoOfProvedorCollectionNewProvedor.getProvedorCollection().remove(provedorCollectionNewProvedor);
                        oldIdContactoOfProvedorCollectionNewProvedor = em.merge(oldIdContactoOfProvedorCollectionNewProvedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = contacto.getId();
                if (findContacto(id) == null) {
                    throw new NonexistentEntityException("The contacto with id " + id + " no longer exists.");
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
            Contacto contacto;
            try {
                contacto = em.getReference(Contacto.class, id);
                contacto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contacto with id " + id + " no longer exists.", enfe);
            }
            Persona idPersona = contacto.getIdPersona();
            if (idPersona != null) {
                idPersona.getContactoCollection().remove(contacto);
                idPersona = em.merge(idPersona);
            }
            Collection<Provedor> provedorCollection = contacto.getProvedorCollection();
            for (Provedor provedorCollectionProvedor : provedorCollection) {
                provedorCollectionProvedor.setIdContacto(null);
                provedorCollectionProvedor = em.merge(provedorCollectionProvedor);
            }
            em.remove(contacto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Contacto> findContactoEntities() {
        return findContactoEntities(true, -1, -1);
    }

    @Override
    public List<Contacto> findContactoEntities(int maxResults, int firstResult) {
        return findContactoEntities(false, maxResults, firstResult);
    }

    private List<Contacto> findContactoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contacto.class));
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
    public Contacto findContacto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contacto.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getContactoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contacto> rt = cq.from(Contacto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
