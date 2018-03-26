/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entitypack;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author salin_000
 */
@Entity
@Table(name = "Products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
    @NamedQuery(name = "Products.findByProductID", query = "SELECT p FROM Products p WHERE p.productID = :productID"),
    @NamedQuery(name = "Products.findByProductName", query = "SELECT p FROM Products p WHERE p.productName = :productName"),
    @NamedQuery(name = "Products.findByProductQuantity", query = "SELECT p FROM Products p WHERE p.productQuantity = :productQuantity"),
    @NamedQuery(name = "Products.findByProductPrice", query = "SELECT p FROM Products p WHERE p.productPrice = :productPrice"),
    @NamedQuery(name = "Products.findByArtistsID", query = "SELECT p FROM Products p WHERE p.artistsID = :artistsID"),
    @NamedQuery(name = "Products.findByProductImg", query = "SELECT p FROM Products p WHERE p.productImg = :productImg"),
    @NamedQuery(name = "Products.findByProductTrailer", query = "SELECT p FROM Products p WHERE p.productTrailer = :productTrailer"),
    @NamedQuery(name = "Products.findByProductContent", query = "SELECT p FROM Products p WHERE p.productContent = :productContent")})
public class Products implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ProductID")
    private Integer productID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ProductName")
    private String productName;
    @Column(name = "ProductQuantity")
    private Integer productQuantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ProductPrice")
    private BigDecimal productPrice;
    @Column(name = "ArtistsID")
    private Integer artistsID;
    @Size(max = 2147483647)
    @Column(name = "ProductImg")
    private String productImg;
    @Size(max = 2147483647)
    @Column(name = "ProductTrailer")
    private String productTrailer;
    @Size(max = 2147483647)
    @Column(name = "ProductContent")
    private String productContent;
    @JoinColumn(name = "CateID", referencedColumnName = "CateID")
    @ManyToOne(optional = false)
    private Categories cateID;
    @JoinColumn(name = "ProducerID", referencedColumnName = "ProducerID")
    @ManyToOne
    private Producers producerID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productID")
    private Collection<Comments> commentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productID")
    private Collection<OrdersDetail> ordersDetailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productID")
    private Collection<AlbumProduct> albumProductCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productID")
    private Collection<ProductArtist> productArtistCollection;

    public Products() {
    }

    public Products(Integer productID) {
        this.productID = productID;
    }

    public Products(Integer productID, String productName) {
        this.productID = productID;
        this.productName = productName;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getArtistsID() {
        return artistsID;
    }

    public void setArtistsID(Integer artistsID) {
        this.artistsID = artistsID;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductTrailer() {
        return productTrailer;
    }

    public void setProductTrailer(String productTrailer) {
        this.productTrailer = productTrailer;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    public Categories getCateID() {
        return cateID;
    }

    public void setCateID(Categories cateID) {
        this.cateID = cateID;
    }

    public Producers getProducerID() {
        return producerID;
    }

    public void setProducerID(Producers producerID) {
        this.producerID = producerID;
    }

    @XmlTransient
    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }

    @XmlTransient
    public Collection<OrdersDetail> getOrdersDetailCollection() {
        return ordersDetailCollection;
    }

    public void setOrdersDetailCollection(Collection<OrdersDetail> ordersDetailCollection) {
        this.ordersDetailCollection = ordersDetailCollection;
    }

    @XmlTransient
    public Collection<AlbumProduct> getAlbumProductCollection() {
        return albumProductCollection;
    }

    public void setAlbumProductCollection(Collection<AlbumProduct> albumProductCollection) {
        this.albumProductCollection = albumProductCollection;
    }

    @XmlTransient
    public Collection<ProductArtist> getProductArtistCollection() {
        return productArtistCollection;
    }

    public void setProductArtistCollection(Collection<ProductArtist> productArtistCollection) {
        this.productArtistCollection = productArtistCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productID != null ? productID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.productID == null && other.productID != null) || (this.productID != null && !this.productID.equals(other.productID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitypack.Products[ productID=" + productID + " ]";
    }
    
}
