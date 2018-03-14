/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entitypack;

import java.io.Serializable;
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
@Table(name = "Album_Product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlbumProduct.findAll", query = "SELECT a FROM AlbumProduct a"),
    @NamedQuery(name = "AlbumProduct.findByAlbumProductID", query = "SELECT a FROM AlbumProduct a WHERE a.albumProductID = :albumProductID")})
public class AlbumProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AlbumProductID")
    private Integer albumProductID;
    @JoinColumn(name = "AlbumID", referencedColumnName = "AlbumID")
    @ManyToOne(optional = false)
    private Albums albumID;
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID")
    @ManyToOne(optional = false)
    private Products productID;

    public AlbumProduct() {
    }

    public AlbumProduct(Integer albumProductID) {
        this.albumProductID = albumProductID;
    }

    public Integer getAlbumProductID() {
        return albumProductID;
    }

    public void setAlbumProductID(Integer albumProductID) {
        this.albumProductID = albumProductID;
    }

    public Albums getAlbumID() {
        return albumID;
    }

    public void setAlbumID(Albums albumID) {
        this.albumID = albumID;
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
        hash += (albumProductID != null ? albumProductID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlbumProduct)) {
            return false;
        }
        AlbumProduct other = (AlbumProduct) object;
        if ((this.albumProductID == null && other.albumProductID != null) || (this.albumProductID != null && !this.albumProductID.equals(other.albumProductID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitypack.AlbumProduct[ albumProductID=" + albumProductID + " ]";
    }
    
}
