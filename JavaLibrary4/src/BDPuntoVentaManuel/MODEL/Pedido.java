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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author manuel
 */
@Entity
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findById", query = "SELECT p FROM Pedido p WHERE p.id = :id"),
    @NamedQuery(name = "Pedido.findByStrFolio", query = "SELECT p FROM Pedido p WHERE p.strFolio = :strFolio"),
    @NamedQuery(name = "Pedido.findByDobTotal", query = "SELECT p FROM Pedido p WHERE p.dobTotal = :dobTotal"),
    @NamedQuery(name = "Pedido.findByDatFechaSolicitud", query = "SELECT p FROM Pedido p WHERE p.datFechaSolicitud = :datFechaSolicitud"),
    @NamedQuery(name = "Pedido.findByStrObservaciones", query = "SELECT p FROM Pedido p WHERE p.strObservaciones = :strObservaciones")})
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "strFolio")
    private String strFolio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "dobTotal")
    private Double dobTotal;
    @Column(name = "datFechaSolicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datFechaSolicitud;
    @Column(name = "strObservaciones")
    private String strObservaciones;
    @OneToMany(mappedBy = "idPedido", fetch = FetchType.EAGER)
    private Collection<Pago> pagoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPedido", fetch = FetchType.EAGER)
    private Collection<PedidoProducto> pedidoProductoCollection;
    @JoinColumn(name = "idProvedor", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Provedor idProvedor;

    public Pedido() {
    }

    public Pedido(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrFolio() {
        return strFolio;
    }

    public void setStrFolio(String strFolio) {
        this.strFolio = strFolio;
    }

    public Double getDobTotal() {
        return dobTotal;
    }

    public void setDobTotal(Double dobTotal) {
        this.dobTotal = dobTotal;
    }

    public Date getDatFechaSolicitud() {
        return datFechaSolicitud;
    }

    public void setDatFechaSolicitud(Date datFechaSolicitud) {
        this.datFechaSolicitud = datFechaSolicitud;
    }

    public String getStrObservaciones() {
        return strObservaciones;
    }

    public void setStrObservaciones(String strObservaciones) {
        this.strObservaciones = strObservaciones;
    }

    @XmlTransient
    public Collection<Pago> getPagoCollection() {
        return pagoCollection;
    }

    public void setPagoCollection(Collection<Pago> pagoCollection) {
        this.pagoCollection = pagoCollection;
    }

    @XmlTransient
    public Collection<PedidoProducto> getPedidoProductoCollection() {
        return pedidoProductoCollection;
    }

    public void setPedidoProductoCollection(Collection<PedidoProducto> pedidoProductoCollection) {
        this.pedidoProductoCollection = pedidoProductoCollection;
    }

    public Provedor getIdProvedor() {
        return idProvedor;
    }

    public void setIdProvedor(Provedor idProvedor) {
        this.idProvedor = idProvedor;
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
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.Pedido[ id=" + id + " ]";
    }
    
}
