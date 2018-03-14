/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entitypack;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author salin_000
 */
@Entity
@Table(name = "OrdersDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdersDetail.findAll", query = "SELECT o FROM OrdersDetail o"),
    @NamedQuery(name = "OrdersDetail.findByOrdersDetailID", query = "SELECT o FROM OrdersDetail o WHERE o.ordersDetailID = :ordersDetailID"),
    @NamedQuery(name = "OrdersDetail.findByQuantity", query = "SELECT o FROM OrdersDetail o WHERE o.quantity = :quantity"),
    @NamedQuery(name = "OrdersDetail.findByPrice", query = "SELECT o FROM OrdersDetail o WHERE o.price = :price")})
public class OrdersDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "OrdersDetailID")
    private Integer ordersDetailID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantity")
    private int quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private BigDecimal price;
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID")
    @ManyToOne(optional = false)
    private Orders orderID;
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID")
    @ManyToOne(optional = false)
    private Products productID;

    public OrdersDetail() {
    }

    public OrdersDetail(Integer ordersDetailID) {
        this.ordersDetailID = ordersDetailID;
    }

    public OrdersDetail(Integer ordersDetailID, int quantity, BigDecimal price) {
        this.ordersDetailID = ordersDetailID;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getOrdersDetailID() {
        return ordersDetailID;
    }

    public void setOrdersDetailID(Integer ordersDetailID) {
        this.ordersDetailID = ordersDetailID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Orders getOrderID() {
        return orderID;
    }

    public void setOrderID(Orders orderID) {
        this.orderID = orderID;
    }

    public Products getProductID() {
        return productID;
    }

    public void setProductID(Products productID) {
        this.productID = productID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ordersDetailID != null ? ordersDetailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdersDetail)) {
            return false;
        }
        OrdersDetail other = (OrdersDetail) object;
        if ((this.ordersDetailID == null && other.ordersDetailID != null) || (this.ordersDetailID != null && !this.ordersDetailID.equals(other.ordersDetailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitypack.OrdersDetail[ ordersDetailID=" + ordersDetailID + " ]";
    }
    
}
