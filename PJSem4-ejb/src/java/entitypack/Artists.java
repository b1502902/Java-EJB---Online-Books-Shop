/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entitypack;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Artists")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artists.findAll", query = "SELECT a FROM Artists a"),
    @NamedQuery(name = "Artists.findByArtistID", query = "SELECT a FROM Artists a WHERE a.artistID = :artistID"),
    @NamedQuery(name = "Artists.findByArtistName", query = "SELECT a FROM Artists a WHERE a.artistName = :artistName"),
    @NamedQuery(name = "Artists.findByArtistProfile", query = "SELECT a FROM Artists a WHERE a.artistProfile = :artistProfile"),
    @NamedQuery(name = "Artists.findByArtistImg", query = "SELECT a FROM Artists a WHERE a.artistImg = :artistImg")})
public class Artists implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ArtistID")
    private Integer artistID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ArtistName")
    private String artistName;
    @Size(max = 2147483647)
    @Column(name = "ArtistProfile")
    private String artistProfile;
    @Size(max = 2147483647)
    @Column(name = "ArtistImg")
    private String artistImg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artistID")
    private Collection<ProductArtist> productArtistCollection;

    public Artists() {
    }

    public Artists(Integer artistID) {
        this.artistID = artistID;
    }

    public Artists(Integer artistID, String artistName) {
        this.artistID = artistID;
        this.artistName = artistName;
    }

    public Integer getArtistID() {
        return artistID;
    }

    public void setArtistID(Integer artistID) {
        this.artistID = artistID;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistProfile() {
        return artistProfile;
    }

    public void setArtistProfile(String artistProfile) {
        this.artistProfile = artistProfile;
    }

    public String getArtistImg() {
        return artistImg;
    }

    public void setArtistImg(String artistImg) {
        this.artistImg = artistImg;
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
        hash += (artistID != null ? artistID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artists)) {
            return false;
        }
        Artists other = (Artists) object;
        if ((this.artistID == null && other.artistID != null) || (this.artistID != null && !this.artistID.equals(other.artistID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitypack.Artists[ artistID=" + artistID + " ]";
    }
    
}
