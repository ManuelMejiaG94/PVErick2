/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IDireccionPersona;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.DireccionPersona;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Persona;
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
public class DireccionPersonaJpaController implements Serializable, IDireccionPersona {

    public DireccionPersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public DireccionPersonaJpaController() {
        this.emf=Persistence.createEntityManagerFactory("BDPuntoVentaManuelPU");
    }
    
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(DireccionPersona direccionPersona) {
        if (direccionPersona.getPersonaCollection() == null) {
            direccionPersona.setPersonaCollection(new ArrayList<Persona>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Persona> attachedPersonaCollection = new ArrayList<Persona>();
            for (Persona personaCollectionPersonaToAttach : direccionPersona.getPersonaCollection()) {
                personaCollectionPersonaToAttach = em.getReference(personaCollectionPersonaToAttach.getClass(), personaCollectionPersonaToAttach.getId());
                attachedPersonaCollection.add(personaCollectionPersonaToAttach);
            }
            direccionPersona.setPersonaCollection(attachedPersonaCollection);
            em.persist(direccionPersona);
            for (Persona personaCollectionPersona : direccionPersona.getPersonaCollection()) {
                DireccionPersona oldIdDireccionOfPersonaCollectionPersona = personaCollectionPersona.getIdDireccion();
                personaCollectionPersona.setIdDireccion(direccionPersona);
                personaCollectionPersona = em.merge(personaCollectionPersona);
                if (oldIdDireccionOfPersonaCollectionPersona != null) {
                    oldIdDireccionOfPersonaCollectionPersona.getPersonaCollection().remove(personaCollectionPersona);
                    oldIdDireccionOfPersonaCollectionPersona = em.merge(oldIdDireccionOfPersonaCollectionPersona);
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
    public void edit(DireccionPersona direccionPersona) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DireccionPersona persistentDireccionPersona = em.find(DireccionPersona.class, direccionPersona.getId());
            Collection<Persona> personaCollectionOld = persistentDireccionPersona.getPersonaCollection();
            Collection<Persona> personaCollectionNew = direccionPersona.getPersonaCollection();
            Collection<Persona> attachedPersonaCollectionNew = new ArrayList<Persona>();
            for (Persona personaCollectionNewPersonaToAttach : personaCollectionNew) {
                personaCollectionNewPersonaToAttach = em.getReference(personaCollectionNewPersonaToAttach.getClass(), personaCollectionNewPersonaToAttach.getId());
                attachedPersonaCollectionNew.add(personaCollectionNewPersonaToAttach);
            }
            personaCollectionNew = attachedPersonaCollectionNew;
            direccionPersona.setPersonaCollection(personaCollectionNew);
            direccionPersona = em.merge(direccionPersona);
            for (Persona personaCollectionOldPersona : personaCollectionOld) {
                if (!personaCollectionNew.contains(personaCollectionOldPersona)) {
                    personaCollectionOldPersona.setIdDireccion(null);
                    personaCollectionOldPersona = em.merge(personaCollectionOldPersona);
                }
            }
            for (Persona personaCollectionNewPersona : personaCollectionNew) {
                if (!personaCollectionOld.contains(personaCollectionNewPersona)) {
                    DireccionPersona oldIdDireccionOfPersonaCollectionNewPersona = personaCollectionNewPersona.getIdDireccion();
                    personaCollectionNewPersona.setIdDireccion(direccionPersona);
                    personaCollectionNewPersona = em.merge(personaCollectionNewPersona);
                    if (oldIdDireccionOfPersonaCollectionNewPersona != null && !oldIdDireccionOfPersonaCollectionNewPersona.equals(direccionPersona)) {
                        oldIdDireccionOfPersonaCollectionNewPersona.getPersonaCollection().remove(personaCollectionNewPersona);
                        oldIdDireccionOfPersonaCollectionNewPersona = em.merge(oldIdDireccionOfPersonaCollectionNewPersona);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = direccionPersona.getId();
                if (findDireccionPersona(id) == null) {
                    throw new NonexistentEntityException("The direccionPersona with id " + id + " no longer exists.");
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
            DireccionPersona direccionPersona;
            try {
                direccionPersona = em.getReference(DireccionPersona.class, id);
                direccionPersona.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The direccionPersona with id " + id + " no longer exists.", enfe);
            }
            Collection<Persona> personaCollection = direccionPersona.getPersonaCollection();
            for (Persona personaCollectionPersona : personaCollection) {
                personaCollectionPersona.setIdDireccion(null);
                personaCollectionPersona = em.merge(personaCollectionPersona);
            }
            em.remove(direccionPersona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<DireccionPersona> findDireccionPersonaEntities() {
        return findDireccionPersonaEntities(true, -1, -1);
    }

    @Override
    public List<DireccionPersona> findDireccionPersonaEntities(int maxResults, int firstResult) {
        return findDireccionPersonaEntities(false, maxResults, firstResult);
    }

    private List<DireccionPersona> findDireccionPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DireccionPersona.class));
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
    public DireccionPersona findDireccionPersona(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DireccionPersona.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getDireccionPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DireccionPersona> rt = cq.from(DireccionPersona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
