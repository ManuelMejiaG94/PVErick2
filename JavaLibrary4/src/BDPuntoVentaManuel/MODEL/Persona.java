/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.MODEL;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author manuel
 */
@Entity
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findById", query = "SELECT p FROM Persona p WHERE p.id = :id"),
    @NamedQuery(name = "Persona.findByStrNombre", query = "SELECT p FROM Persona p WHERE p.strNombre = :strNombre"),
    @NamedQuery(name = "Persona.findByStrAPaterno", query = "SELECT p FROM Persona p WHERE p.strAPaterno = :strAPaterno"),
    @NamedQuery(name = "Persona.findByStrAMaterno", query = "SELECT p FROM Persona p WHERE p.strAMaterno = :strAMaterno"),
    @NamedQuery(name = "Persona.findByDatFechaNacimiento", query = "SELECT p FROM Persona p WHERE p.datFechaNacimiento = :datFechaNacimiento")})
public class Persona implements Serializable {
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
    @Column(name = "datFechaNacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datFechaNacimiento;
    @OneToMany(mappedBy = "idPersona", fetch = FetchType.EAGER)
    private Collection<Usuario> usuarioCollection;
    @OneToMany(mappedBy = "idPersona", fetch = FetchType.EAGER)
    private Collection<Contacto> contactoCollection;
    @JoinColumn(name = "idsexo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Catsexo idsexo;
    @JoinColumn(name = "idDireccion", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private DireccionPersona idDireccion;

    public Persona() {
    }

    public Persona(Integer id) {
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

    public Date getDatFechaNacimiento() {
        return datFechaNacimiento;
    }

    public void setDatFechaNacimiento(Date datFechaNacimiento) {
        this.datFechaNacimiento = datFechaNacimiento;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @XmlTransient
    public Collection<Contacto> getContactoCollection() {
        return contactoCollection;
    }

    public void setContactoCollection(Collection<Contacto> contactoCollection) {
        this.contactoCollection = contactoCollection;
    }

    public Catsexo getIdsexo() {
        return idsexo;
    }

    public void setIdsexo(Catsexo idsexo) {
        this.idsexo = idsexo;
    }

    public DireccionPersona getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(DireccionPersona idDireccion) {
        this.idDireccion = idDireccion;
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
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.Persona[ id=" + id + " ]";
    }
    
}
