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
@Table(name = "direccion_persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DireccionPersona.findAll", query = "SELECT d FROM DireccionPersona d"),
    @NamedQuery(name = "DireccionPersona.findById", query = "SELECT d FROM DireccionPersona d WHERE d.id = :id"),
    @NamedQuery(name = "DireccionPersona.findByStrNombre", query = "SELECT d FROM DireccionPersona d WHERE d.strNombre = :strNombre"),
    @NamedQuery(name = "DireccionPersona.findByStrAPaterno", query = "SELECT d FROM DireccionPersona d WHERE d.strAPaterno = :strAPaterno"),
    @NamedQuery(name = "DireccionPersona.findByStrAMaterno", query = "SELECT d FROM DireccionPersona d WHERE d.strAMaterno = :strAMaterno")})
public class DireccionPersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "strNombre")
    private String strNombre;
    @Column(name = "strAPaterno")
    private String strAPaterno;
    @Column(name = "strAMaterno")
    private String strAMaterno;
    @OneToMany(mappedBy = "idDireccion", fetch = FetchType.EAGER)
    private Collection<Persona> personaCollection;

    public DireccionPersona() {
    }

    public DireccionPersona(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrNombre() {
        return strNombre;
    }

    public void setStrNombre(String strNombre) {
        this.strNombre = strNombre;
    }

    public String getStrAPaterno() {
        return strAPaterno;
    }

    public void setStrAPaterno(String strAPaterno) {
        this.strAPaterno = strAPaterno;
    }

    public String getStrAMaterno() {
        return strAMaterno;
    }

    public void setStrAMaterno(String strAMaterno) {
        this.strAMaterno = strAMaterno;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
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
        if (!(object instanceof DireccionPersona)) {
            return false;
        }
        DireccionPersona other = (DireccionPersona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.DireccionPersona[ id=" + id + " ]";
    }
    
}
