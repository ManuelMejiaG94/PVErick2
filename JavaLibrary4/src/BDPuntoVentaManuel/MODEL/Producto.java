/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.MODEL;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findById", query = "SELECT p FROM Producto p WHERE p.id = :id"),
    @NamedQuery(name = "Producto.findByStrClave", query = "SELECT p FROM Producto p WHERE p.strClave = :strClave"),
    @NamedQuery(name = "Producto.findByStrNombre", query = "SELECT p FROM Producto p WHERE p.strNombre = :strNombre"),
    @NamedQuery(name = "Producto.findByDobCantidad", query = "SELECT p FROM Producto p WHERE p.dobCantidad = :dobCantidad"),
    @NamedQuery(name = "Producto.findByStrPresentacion", query = "SELECT p FROM Producto p WHERE p.strPresentacion = :strPresentacion"),
    @NamedQuery(name = "Producto.findByDobPrecioVenta", query = "SELECT p FROM Producto p WHERE p.dobPrecioVenta = :dobPrecioVenta"),
    @NamedQuery(name = "Producto.findByDobPrecioCompra", query = "SELECT p FROM Producto p WHERE p.dobPrecioCompra = :dobPrecioCompra"),
    @NamedQuery(name = "Producto.findByBitEstatus", query = "SELECT p FROM Producto p WHERE p.bitEstatus = :bitEstatus")})
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "strClave")
    private String strClave;
    @Column(name = "strNombre")
    private String strNombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "dobCantidad")
    private Double dobCantidad;
    @Column(name = "strPresentacion")
    private String strPresentacion;
    @Column(name = "dobPrecioVenta")
    private Double dobPrecioVenta;
    @Column(name = "dobPrecioCompra")
    private Double dobPrecioCompra;
    @Column(name = "bitEstatus")
    private Boolean bitEstatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto", fetch = FetchType.EAGER)
    private Collection<PedidoProducto> pedidoProductoCollection;
    @JoinColumn(name = "idCategoria", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Catcategoria idCategoria;

    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Producto(Integer id, String strClave) {
        this.id = id;
        this.strClave = strClave;
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

    public String getStrNombre() {
        return strNombre;
    }

    public void setStrNombre(String strNombre) {
        this.strNombre = strNombre;
    }

    public Double getDobCantidad() {
        return dobCantidad;
    }

    public void setDobCantidad(Double dobCantidad) {
        this.dobCantidad = dobCantidad;
    }

    public String getStrPresentacion() {
        return strPresentacion;
    }

    public void setStrPresentacion(String strPresentacion) {
        this.strPresentacion = strPresentacion;
    }

    public Double getDobPrecioVenta() {
        return dobPrecioVenta;
    }

    public void setDobPrecioVenta(Double dobPrecioVenta) {
        this.dobPrecioVenta = dobPrecioVenta;
    }

    public Double getDobPrecioCompra() {
        return dobPrecioCompra;
    }

    public void setDobPrecioCompra(Double dobPrecioCompra) {
        this.dobPrecioCompra = dobPrecioCompra;
    }

    public Boolean getBitEstatus() {
        return bitEstatus;
    }

    public void setBitEstatus(Boolean bitEstatus) {
        this.bitEstatus = bitEstatus;
    }

    @XmlTransient
    public Collection<PedidoProducto> getPedidoProductoCollection() {
        return pedidoProductoCollection;
    }

    public void setPedidoProductoCollection(Collection<PedidoProducto> pedidoProductoCollection) {
        this.pedidoProductoCollection = pedidoProductoCollection;
    }

    public Catcategoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Catcategoria idCategoria) {
        this.idCategoria = idCategoria;
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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.Producto[ id=" + id + " ]";
    }
    
}
