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
import javax.persistence.Lob;
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
@Table(name = "provedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provedor.findAll", query = "SELECT p FROM Provedor p"),
    @NamedQuery(name = "Provedor.findById", query = "SELECT p FROM Provedor p WHERE p.id = :id"),
    @NamedQuery(name = "Provedor.findByStrRazonSocial", query = "SELECT p FROM Provedor p WHERE p.strRazonSocial = :strRazonSocial"),
    @NamedQuery(name = "Provedor.findByBitEstatus", query = "SELECT p FROM Provedor p WHERE p.bitEstatus = :bitEstatus")})
public class Provedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "strRazonSocial")
    private String strRazonSocial;
    @Column(name = "bitEstatus")
    private Boolean bitEstatus;
    @Lob
    @Column(name = "imgProvedor")
    private byte[] imgProvedor;
    @JoinColumn(name = "idDireccion", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private DireccionProvedor idDireccion;
    @JoinColumn(name = "idContacto", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Contacto idContacto;
    @JoinColumn(name = "idCategoria", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Catcategoria idCategoria;
    @OneToMany(mappedBy = "idProvedor", fetch = FetchType.EAGER)
    private Collection<Pedido> pedidoCollection;

    public Provedor() {
    }

    public Provedor(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrRazonSocial() {
        return strRazonSocial;
    }

    public void setStrRazonSocial(String strRazonSocial) {
        this.strRazonSocial = strRazonSocial;
    }

    public Boolean getBitEstatus() {
        return bitEstatus;
    }

    public void setBitEstatus(Boolean bitEstatus) {
        this.bitEstatus = bitEstatus;
    }

    public byte[] getImgProvedor() {
        return imgProvedor;
    }

    public void setImgProvedor(byte[] imgProvedor) {
        this.imgProvedor = imgProvedor;
    }

    public DireccionProvedor getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(DireccionProvedor idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Contacto getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Contacto idContacto) {
        this.idContacto = idContacto;
    }

    public Catcategoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Catcategoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    @XmlTransient
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
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
        if (!(object instanceof Provedor)) {
            return false;
        }
        Provedor other = (Provedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.Provedor[ id=" + id + " ]";
    }
    
}
