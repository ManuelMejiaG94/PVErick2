/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.CONCREAT;

import BDPuntoVentaManuel.ABSTRACT.IPersona;
import BDPuntoVentaManuel.CONCREAT.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import BDPuntoVentaManuel.MODEL.Catsexo;
import BDPuntoVentaManuel.MODEL.DireccionPersona;
import BDPuntoVentaManuel.MODEL.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import BDPuntoVentaManuel.MODEL.Contacto;
import BDPuntoVentaManuel.MODEL.Persona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manuel
 */
public class PersonaJpaController implements Serializable, IPersona {

    public PersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public PersonaJpaController() {
        this.emf=Persistence.createEntityManagerFactory("BDPuntoVentaManuelPU");
    }

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Persona persona) {
        if (persona.getUsuarioCollection() == null) {
            persona.setUsuarioCollection(new ArrayList<Usuario>());
        }
        if (persona.getContactoCollection() == null) {
            persona.setContactoCollection(new ArrayList<Contacto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catsexo idsexo = persona.getIdsexo();
            if (idsexo != null) {
                idsexo = em.getReference(idsexo.getClass(), idsexo.getId());
                persona.setIdsexo(idsexo);
            }
            DireccionPersona idDireccion = persona.getIdDireccion();
            if (idDireccion != null) {
                idDireccion = em.getReference(idDireccion.getClass(), idDireccion.getId());
                persona.setIdDireccion(idDireccion);
            }
            Collection<Usuario> attachedUsuarioCollection = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionUsuarioToAttach : persona.getUsuarioCollection()) {
                usuarioCollectionUsuarioToAttach = em.getReference(usuarioCollectionUsuarioToAttach.getClass(), usuarioCollectionUsuarioToAttach.getId());
                attachedUsuarioCollection.add(usuarioCollectionUsuarioToAttach);
            }
            persona.setUsuarioCollection(attachedUsuarioCollection);
            Collection<Contacto> attachedContactoCollection = new ArrayList<Contacto>();
            for (Contacto contactoCollectionContactoToAttach : persona.getContactoCollection()) {
                contactoCollectionContactoToAttach = em.getReference(contactoCollectionContactoToAttach.getClass(), contactoCollectionContactoToAttach.getId());
                attachedContactoCollection.add(contactoCollectionContactoToAttach);
            }
            persona.setContactoCollection(attachedContactoCollection);
            em.persist(persona);
            if (idsexo != null) {
                idsexo.getPersonaCollection().add(persona);
                idsexo = em.merge(idsexo);
            }
            if (idDireccion != null) {
                idDireccion.getPersonaCollection().add(persona);
                idDireccion = em.merge(idDireccion);
            }
            for (Usuario usuarioCollectionUsuario : persona.getUsuarioCollection()) {
                Persona oldIdPersonaOfUsuarioCollectionUsuario = usuarioCollectionUsuario.getIdPersona();
                usuarioCollectionUsuario.setIdPersona(persona);
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
                if (oldIdPersonaOfUsuarioCollectionUsuario != null) {
                    oldIdPersonaOfUsuarioCollectionUsuario.getUsuarioCollection().remove(usuarioCollectionUsuario);
                    oldIdPersonaOfUsuarioCollectionUsuario = em.merge(oldIdPersonaOfUsuarioCollectionUsuario);
                }
            }
            for (Contacto contactoCollectionContacto : persona.getContactoCollection()) {
                Persona oldIdPersonaOfContactoCollectionContacto = contactoCollectionContacto.getIdPersona();
                contactoCollectionContacto.setIdPersona(persona);
                contactoCollectionContacto = em.merge(contactoCollectionContacto);
                if (oldIdPersonaOfContactoCollectionContacto != null) {
                    oldIdPersonaOfContactoCollectionContacto.getContactoCollection().remove(contactoCollectionContacto);
                    oldIdPersonaOfContactoCollectionContacto = em.merge(oldIdPersonaOfContactoCollectionContacto);
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
    public void edit(Persona persona) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persistentPersona = em.find(Persona.class, persona.getId());
            Catsexo idsexoOld = persistentPersona.getIdsexo();
            Catsexo idsexoNew = persona.getIdsexo();
            DireccionPersona idDireccionOld = persistentPersona.getIdDireccion();
            DireccionPersona idDireccionNew = persona.getIdDireccion();
            Collection<Usuario> usuarioCollectionOld = persistentPersona.getUsuarioCollection();
            Collection<Usuario> usuarioCollectionNew = persona.getUsuarioCollection();
            Collection<Contacto> contactoCollectionOld = persistentPersona.getContactoCollection();
            Collection<Contacto> contactoCollectionNew = persona.getContactoCollection();
            if (idsexoNew != null) {
                idsexoNew = em.getReference(idsexoNew.getClass(), idsexoNew.getId());
                persona.setIdsexo(idsexoNew);
            }
            if (idDireccionNew != null) {
                idDireccionNew = em.getReference(idDireccionNew.getClass(), idDireccionNew.getId());
                persona.setIdDireccion(idDireccionNew);
            }
            Collection<Usuario> attachedUsuarioCollectionNew = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionNewUsuarioToAttach : usuarioCollectionNew) {
                usuarioCollectionNewUsuarioToAttach = em.getReference(usuarioCollectionNewUsuarioToAttach.getClass(), usuarioCollectionNewUsuarioToAttach.getId());
                attachedUsuarioCollectionNew.add(usuarioCollectionNewUsuarioToAttach);
            }
            usuarioCollectionNew = attachedUsuarioCollectionNew;
            persona.setUsuarioCollection(usuarioCollectionNew);
            Collection<Contacto> attachedContactoCollectionNew = new ArrayList<Contacto>();
            for (Contacto contactoCollectionNewContactoToAttach : contactoCollectionNew) {
                contactoCollectionNewContactoToAttach = em.getReference(contactoCollectionNewContactoToAttach.getClass(), contactoCollectionNewContactoToAttach.getId());
                attachedContactoCollectionNew.add(contactoCollectionNewContactoToAttach);
            }
            contactoCollectionNew = attachedContactoCollectionNew;
            persona.setContactoCollection(contactoCollectionNew);
            persona = em.merge(persona);
            if (idsexoOld != null && !idsexoOld.equals(idsexoNew)) {
                idsexoOld.getPersonaCollection().remove(persona);
                idsexoOld = em.merge(idsexoOld);
            }
            if (idsexoNew != null && !idsexoNew.equals(idsexoOld)) {
                idsexoNew.getPersonaCollection().add(persona);
                idsexoNew = em.merge(idsexoNew);
            }
            if (idDireccionOld != null && !idDireccionOld.equals(idDireccionNew)) {
                idDireccionOld.getPersonaCollection().remove(persona);
                idDireccionOld = em.merge(idDireccionOld);
            }
            if (idDireccionNew != null && !idDireccionNew.equals(idDireccionOld)) {
                idDireccionNew.getPersonaCollection().add(persona);
                idDireccionNew = em.merge(idDireccionNew);
            }
            for (Usuario usuarioCollectionOldUsuario : usuarioCollectionOld) {
                if (!usuarioCollectionNew.contains(usuarioCollectionOldUsuario)) {
                    usuarioCollectionOldUsuario.setIdPersona(null);
                    usuarioCollectionOldUsuario = em.merge(usuarioCollectionOldUsuario);
                }
            }
            for (Usuario usuarioCollectionNewUsuario : usuarioCollectionNew) {
                if (!usuarioCollectionOld.contains(usuarioCollectionNewUsuario)) {
                    Persona oldIdPersonaOfUsuarioCollectionNewUsuario = usuarioCollectionNewUsuario.getIdPersona();
                    usuarioCollectionNewUsuario.setIdPersona(persona);
                    usuarioCollectionNewUsuario = em.merge(usuarioCollectionNewUsuario);
                    if (oldIdPersonaOfUsuarioCollectionNewUsuario != null && !oldIdPersonaOfUsuarioCollectionNewUsuario.equals(persona)) {
                        oldIdPersonaOfUsuarioCollectionNewUsuario.getUsuarioCollection().remove(usuarioCollectionNewUsuario);
                        oldIdPersonaOfUsuarioCollectionNewUsuario = em.merge(oldIdPersonaOfUsuarioCollectionNewUsuario);
                    }
                }
            }
            for (Contacto contactoCollectionOldContacto : contactoCollectionOld) {
                if (!contactoCollectionNew.contains(contactoCollectionOldContacto)) {
                    contactoCollectionOldContacto.setIdPersona(null);
                    contactoCollectionOldContacto = em.merge(contactoCollectionOldContacto);
                }
            }
            for (Contacto contactoCollectionNewContacto : contactoCollectionNew) {
                if (!contactoCollectionOld.contains(contactoCollectionNewContacto)) {
                    Persona oldIdPersonaOfContactoCollectionNewContacto = contactoCollectionNewContacto.getIdPersona();
                    contactoCollectionNewContacto.setIdPersona(persona);
                    contactoCollectionNewContacto = em.merge(contactoCollectionNewContacto);
                    if (oldIdPersonaOfContactoCollectionNewContacto != null && !oldIdPersonaOfContactoCollectionNewContacto.equals(persona)) {
                        oldIdPersonaOfContactoCollectionNewContacto.getContactoCollection().remove(contactoCollectionNewContacto);
                        oldIdPersonaOfContactoCollectionNewContacto = em.merge(oldIdPersonaOfContactoCollectionNewContacto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = persona.getId();
                if (findPersona(id) == null) {
                    throw new NonexistentEntityException("The persona with id " + id + " no longer exists.");
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
            Persona persona;
            try {
                persona = em.getReference(Persona.class, id);
                persona.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persona with id " + id + " no longer exists.", enfe);
            }
            Catsexo idsexo = persona.getIdsexo();
            if (idsexo != null) {
                idsexo.getPersonaCollection().remove(persona);
                idsexo = em.merge(idsexo);
            }
            DireccionPersona idDireccion = persona.getIdDireccion();
            if (idDireccion != null) {
                idDireccion.getPersonaCollection().remove(persona);
                idDireccion = em.merge(idDireccion);
            }
            Collection<Usuario> usuarioCollection = persona.getUsuarioCollection();
            for (Usuario usuarioCollectionUsuario : usuarioCollection) {
                usuarioCollectionUsuario.setIdPersona(null);
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
            }
            Collection<Contacto> contactoCollection = persona.getContactoCollection();
            for (Contacto contactoCollectionContacto : contactoCollection) {
                contactoCollectionContacto.setIdPersona(null);
                contactoCollectionContacto = em.merge(contactoCollectionContacto);
            }
            em.remove(persona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Persona> findPersonaEntities() {
        return findPersonaEntities(true, -1, -1);
    }

    @Override
    public List<Persona> findPersonaEntities(int maxResults, int firstResult) {
        return findPersonaEntities(false, maxResults, firstResult);
    }

    private List<Persona> findPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Persona.class));
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
    public Persona findPersona(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Persona.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Persona> rt = cq.from(Persona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
