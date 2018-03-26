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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "Product_Artist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductArtist.findAll", query = "SELECT p FROM ProductArtist p"),
    @NamedQuery(name = "ProductArtist.findByProductArtistID", query = "SELECT p FROM ProductArtist p WHERE p.productArtistID = :productArtistID")})
public class ProductArtist implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ProductArtistID")
    private Integer productArtistID;
    @JoinColumn(name = "ArtistID", referencedColumnName = "ArtistID")
    @ManyToOne(optional = false)
    private Artists artistID;
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID")
    @ManyToOne(optional = false)
    private Products productID;

    public ProductArtist() {
    }

    public ProductArtist(Integer productArtistID) {
        this.productArtistID = productArtistID;
    }

    public Integer getProductArtistID() {
        return productArtistID;
    }

    public void setProductArtistID(Integer productArtistID) {
        this.productArtistID = productArtistID;
    }

    public Artists getArtistID() {
        return artistID;
    }

    public void setArtistID(Artists artistID) {
        this.artistID = artistID;
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
        hash += (productArtistID != null ? productArtistID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductArtist)) {
            return false;
        }
        ProductArtist other = (ProductArtist) object;
        if ((this.productArtistID == null && other.productArtistID != null) || (this.productArtistID != null && !this.productArtistID.equals(other.productArtistID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitypack.ProductArtist[ productArtistID=" + productArtistID + " ]";
    }
    
}
