/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.MODEL;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author manuel
 */
@Entity
@Table(name = "perfil_modulo_accion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerfilModuloAccion.findAll", query = "SELECT p FROM PerfilModuloAccion p"),
    @NamedQuery(name = "PerfilModuloAccion.findById", query = "SELECT p FROM PerfilModuloAccion p WHERE p.id = :id"),
    @NamedQuery(name = "PerfilModuloAccion.findByBitEliminar", query = "SELECT p FROM PerfilModuloAccion p WHERE p.bitEliminar = :bitEliminar"),
    @NamedQuery(name = "PerfilModuloAccion.findByBitAgregar", query = "SELECT p FROM PerfilModuloAccion p WHERE p.bitAgregar = :bitAgregar"),
    @NamedQuery(name = "PerfilModuloAccion.findByBitModificar", query = "SELECT p FROM PerfilModuloAccion p WHERE p.bitModificar = :bitModificar"),
    @NamedQuery(name = "PerfilModuloAccion.findByBitConsultar", query = "SELECT p FROM PerfilModuloAccion p WHERE p.bitConsultar = :bitConsultar")})
public class PerfilModuloAccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "bitEliminar")
    private Boolean bitEliminar;
    @Column(name = "bitAgregar")
    private Boolean bitAgregar;
    @Column(name = "bitModificar")
    private Boolean bitModificar;
    @Column(name = "bitConsultar")
    private Boolean bitConsultar;
    @JoinColumn(name = "idPerfil", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Perfil idPerfil;
    @JoinColumn(name = "idModulo", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Modulo idModulo;

    public PerfilModuloAccion() {
    }

    public PerfilModuloAccion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getBitEliminar() {
        return bitEliminar;
    }

    public void setBitEliminar(Boolean bitEliminar) {
        this.bitEliminar = bitEliminar;
    }

    public Boolean getBitAgregar() {
        return bitAgregar;
    }

    public void setBitAgregar(Boolean bitAgregar) {
        this.bitAgregar = bitAgregar;
    }

    public Boolean getBitModificar() {
        return bitModificar;
    }

    public void setBitModificar(Boolean bitModificar) {
        this.bitModificar = bitModificar;
    }

    public Boolean getBitConsultar() {
        return bitConsultar;
    }

    public void setBitConsultar(Boolean bitConsultar) {
        this.bitConsultar = bitConsultar;
    }

    public Perfil getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Perfil idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Modulo getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Modulo idModulo) {
        this.idModulo = idModulo;
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
        if (!(object instanceof PerfilModuloAccion)) {
            return false;
        }
        PerfilModuloAccion other = (PerfilModuloAccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.PerfilModuloAccion[ id=" + id + " ]";
    }
    
}
