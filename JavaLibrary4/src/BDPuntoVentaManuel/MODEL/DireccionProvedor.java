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
@Table(name = "direccion_provedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DireccionProvedor.findAll", query = "SELECT d FROM DireccionProvedor d"),
    @NamedQuery(name = "DireccionProvedor.findById", query = "SELECT d FROM DireccionProvedor d WHERE d.id = :id"),
    @NamedQuery(name = "DireccionProvedor.findByStrValor", query = "SELECT d FROM DireccionProvedor d WHERE d.strValor = :strValor")})
public class DireccionProvedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "strValor")
    private String strValor;
    @OneToMany(mappedBy = "idDireccion", fetch = FetchType.EAGER)
    private Collection<Provedor> provedorCollection;

    public DireccionProvedor() {
    }

    public DireccionProvedor(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrValor() {
        return strValor;
    }

    public void setStrValor(String strValor) {
        this.strValor = strValor;
    }

    @XmlTransient
    public Collection<Provedor> getProvedorCollection() {
        return provedorCollection;
    }

    public void setProvedorCollection(Collection<Provedor> provedorCollection) {
        this.provedorCollection = provedorCollection;
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
        if (!(object instanceof DireccionProvedor)) {
            return false;
        }
        DireccionProvedor other = (DireccionProvedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.DireccionProvedor[ id=" + id + " ]";
    }
    
}
