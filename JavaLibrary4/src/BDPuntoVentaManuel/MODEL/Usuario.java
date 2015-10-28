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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByStrClave", query = "SELECT u FROM Usuario u WHERE u.strClave = :strClave"),
    @NamedQuery(name = "Usuario.findByStrNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.strNombreUsuario = :strNombreUsuario"),
    @NamedQuery(name = "Usuario.findByStrContrasenia", query = "SELECT u FROM Usuario u WHERE u.strContrasenia = :strContrasenia"),
    @NamedQuery(name = "Usuario.findByBitEstatus", query = "SELECT u FROM Usuario u WHERE u.bitEstatus = :bitEstatus")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "strClave")
    private String strClave;
    @Basic(optional = false)
    @Column(name = "strNombreUsuario")
    private String strNombreUsuario;
    @Basic(optional = false)
    @Column(name = "strContrasenia")
    private String strContrasenia;
    @Column(name = "bitEstatus")
    private Boolean bitEstatus;
    @JoinColumn(name = "idPersona", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Persona idPersona;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, String strClave, String strNombreUsuario, String strContrasenia) {
        this.id = id;
        this.strClave = strClave;
        this.strNombreUsuario = strNombreUsuario;
        this.strContrasenia = strContrasenia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrClave() {
        return strClave;
    }

    public void setStrClave(String strClave) {
        this.strClave = strClave;
    }

    public String getStrNombreUsuario() {
        return strNombreUsuario;
    }

    public void setStrNombreUsuario(String strNombreUsuario) {
        this.strNombreUsuario = strNombreUsuario;
    }

    public String getStrContrasenia() {
        return strContrasenia;
    }

    public void setStrContrasenia(String strContrasenia) {
        this.strContrasenia = strContrasenia;
    }

    public Boolean getBitEstatus() {
        return bitEstatus;
    }

    public void setBitEstatus(Boolean bitEstatus) {
        this.bitEstatus = bitEstatus;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.Usuario[ id=" + id + " ]";
    }
    
}
