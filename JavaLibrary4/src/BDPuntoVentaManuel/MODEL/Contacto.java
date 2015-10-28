/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.MODEL;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author manuel
 */
@Entity
@Table(name = "contacto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contacto.findAll", query = "SELECT c FROM Contacto c"),
    @NamedQuery(name = "Contacto.findById", query = "SELECT c FROM Contacto c WHERE c.id = :id"),
    @NamedQuery(name = "Contacto.findByStrEmail", query = "SELECT c FROM Contacto c WHERE c.strEmail = :strEmail"),
    @NamedQuery(name = "Contacto.findByStrNumCel", query = "SELECT c FROM Contacto c WHERE c.strNumCel = :strNumCel"),
    @NamedQuery(name = "Contacto.findByStrNumOficina", query = "SELECT c FROM Contacto c WHERE c.strNumOficina = :strNumOficina")})
public class Contacto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "strEmail")
    private String strEmail;
    @Column(name = "strNumCel")
    private String strNumCel;
    @Column(name = "strNumOficina")
    private String strNumOficina;
    @OneToMany(mappedBy = "idContacto", fetch = FetchType.EAGER)
    private Collection<Provedor> provedorCollection;
    @JoinColumn(name = "idPersona", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Persona idPersona;

    public Contacto() {
    }

    public Contacto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrEmail() {
        return strEmail;
    }

    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    public String getStrNumCel() {
        return strNumCel;
    }

    public void setStrNumCel(String strNumCel) {
        this.strNumCel = strNumCel;
    }

    public String getStrNumOficina() {
        return strNumOficina;
    }

    public void setStrNumOficina(String strNumOficina) {
        this.strNumOficina = strNumOficina;
    }

    @XmlTransient
    public Collection<Provedor> getProvedorCollection() {
        return provedorCollection;
    }

    public void setProvedorCollection(Collection<Provedor> provedorCollection) {
        this.provedorCollection = provedorCollection;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contacto)) {
            return false;
        }
        Contacto other = (Contacto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.Contacto[ id=" + id + " ]";
    }
    
}
