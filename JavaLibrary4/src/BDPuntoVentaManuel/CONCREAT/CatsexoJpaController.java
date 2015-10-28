/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.ICatsexo;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import BDPuntoVentaManuel.MODEL.Catsexo;
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
public class CatsexoJpaController implements Serializable, ICatsexo {

    public CatsexoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public CatsexoJpaController() {
        this.emf=Persistence.createEntityManagerFactory("BDPuntoVentaManuelPU");
    }

    
    
    
    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Catsexo catsexo) {
        if (catsexo.getPersonaCollection() == null) {
            catsexo.setPersonaCollection(new ArrayList<Persona>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Persona> attachedPersonaCollection = new ArrayList<Persona>();
            for (Persona personaCollectionPersonaToAttach : catsexo.getPersonaCollection()) {
                personaCollectionPersonaToAttach = em.getReference(personaCollectionPersonaToAttach.getClass(), personaCollectionPersonaToAttach.getId());
                attachedPersonaCollection.add(personaCollectionPersonaToAttach);
            }
            catsexo.setPersonaCollection(attachedPersonaCollection);
            em.persist(catsexo);
            for (Persona personaCollectionPersona : catsexo.getPersonaCollection()) {
                Catsexo oldIdsexoOfPersonaCollectionPersona = personaCollectionPersona.getIdsexo();
                personaCollectionPersona.setIdsexo(catsexo);
                personaCollectionPersona = em.merge(personaCollectionPersona);
                if (oldIdsexoOfPersonaCollectionPersona != null) {
                    oldIdsexoOfPersonaCollectionPersona.getPersonaCollection().remove(personaCollectionPersona);
                    oldIdsexoOfPersonaCollectionPersona = em.merge(oldIdsexoOfPersonaCollectionPersona);
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
    public void edit(Catsexo catsexo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catsexo persistentCatsexo = em.find(Catsexo.class, catsexo.getId());
            Collection<Persona> personaCollectionOld = persistentCatsexo.getPersonaCollection();
            Collection<Persona> personaCollectionNew = catsexo.getPersonaCollection();
            Collection<Persona> attachedPersonaCollectionNew = new ArrayList<Persona>();
            for (Persona personaCollectionNewPersonaToAttach : personaCollectionNew) {
                personaCollectionNewPersonaToAttach = em.getReference(personaCollectionNewPersonaToAttach.getClass(), personaCollectionNewPersonaToAttach.getId());
                attachedPersonaCollectionNew.add(personaCollectionNewPersonaToAttach);
            }
            personaCollectionNew = attachedPersonaCollectionNew;
            catsexo.setPersonaCollection(personaCollectionNew);
            catsexo = em.merge(catsexo);
            for (Persona personaCollectionOldPersona : personaCollectionOld) {
                if (!personaCollectionNew.contains(personaCollectionOldPersona)) {
                    personaCollectionOldPersona.setIdsexo(null);
                    personaCollectionOldPersona = em.merge(personaCollectionOldPersona);
                }
            }
            for (Persona personaCollectionNewPersona : personaCollectionNew) {
                if (!personaCollectionOld.contains(personaCollectionNewPersona)) {
                    Catsexo oldIdsexoOfPersonaCollectionNewPersona = personaCollectionNewPersona.getIdsexo();
                    personaCollectionNewPersona.setIdsexo(catsexo);
                    personaCollectionNewPersona = em.merge(personaCollectionNewPersona);
                    if (oldIdsexoOfPersonaCollectionNewPersona != null && !oldIdsexoOfPersonaCollectionNewPersona.equals(catsexo)) {
                        oldIdsexoOfPersonaCollectionNewPersona.getPersonaCollection().remove(personaCollectionNewPersona);
                        oldIdsexoOfPersonaCollectionNewPersona = em.merge(oldIdsexoOfPersonaCollectionNewPersona);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = catsexo.getId();
                if (findCatsexo(id) == null) {
                    throw new NonexistentEntityException("The catsexo with id " + id + " no longer exists.");
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
            Catsexo catsexo;
            try {
                catsexo = em.getReference(Catsexo.class, id);
                catsexo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catsexo with id " + id + " no longer exists.", enfe);
            }
            Collection<Persona> personaCollection = catsexo.getPersonaCollection();
            for (Persona personaCollectionPersona : personaCollection) {
                personaCollectionPersona.setIdsexo(null);
                personaCollectionPersona = em.merge(personaCollectionPersona);
            }
            em.remove(catsexo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Catsexo> findCatsexoEntities() {
        return findCatsexoEntities(true, -1, -1);
    }

    @Override
    public List<Catsexo> findCatsexoEntities(int maxResults, int firstResult) {
        return findCatsexoEntities(false, maxResults, firstResult);
    }

    private List<Catsexo> findCatsexoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Catsexo.class));
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
    public Catsexo findCatsexo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Catsexo.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getCatsexoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Catsexo> rt = cq.from(Catsexo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
