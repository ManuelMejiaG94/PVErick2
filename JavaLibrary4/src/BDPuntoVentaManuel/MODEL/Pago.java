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
@Table(name = "pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pago.findAll", query = "SELECT p FROM Pago p"),
    @NamedQuery(name = "Pago.findById", query = "SELECT p FROM Pago p WHERE p.id = :id"),
    @NamedQuery(name = "Pago.findByDobAdeudo", query = "SELECT p FROM Pago p WHERE p.dobAdeudo = :dobAdeudo"),
    @NamedQuery(name = "Pago.findByStrDescripcion", query = "SELECT p FROM Pago p WHERE p.strDescripcion = :strDescripcion")})
public class Pago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "dobAdeudo")
    private Double dobAdeudo;
    @Column(name = "strDescripcion")
    private String strDescripcion;
    @JoinColumn(name = "idPedido", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Pedido idPedido;
    @JoinColumn(name = "idEstatusPago", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Catestatuspago idEstatusPago;

    public Pago() {
    }

    public Pago(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDobAdeudo() {
        return dobAdeudo;
    }

    public void setDobAdeudo(Double dobAdeudo) {
        this.dobAdeudo = dobAdeudo;
    }

    public String getStrDescripcion() {
        return strDescripcion;
    }

    public void setStrDescripcion(String strDescripcion) {
        this.strDescripcion = strDescripcion;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    public Catestatuspago getIdEstatusPago() {
        return idEstatusPago;
    }

    public void setIdEstatusPago(Catestatuspago idEstatusPago) {
        this.idEstatusPago = idEstatusPago;
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
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BDPuntoVentaManuel.MODEL.Pago[ id=" + id + " ]";
    }
    
}
