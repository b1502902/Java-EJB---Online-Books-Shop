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
@Table(name = "Albums")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Albums.findAll", query = "SELECT a FROM Albums a"),
    @NamedQuery(name = "Albums.findByAlbumID", query = "SELECT a FROM Albums a WHERE a.albumID = :albumID"),
    @NamedQuery(name = "Albums.findByAlbumName", query = "SELECT a FROM Albums a WHERE a.albumName = :albumName"),
    @NamedQuery(name = "Albums.findByUserID", query = "SELECT a FROM Albums a WHERE a.userID = :userID")})
public class Albums implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AlbumID")
    private Integer albumID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "AlbumName")
    private String albumName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UserID")
    private int userID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "albumID")
    private Collection<AlbumProduct> albumProductCollection;

    public Albums() {
    }

    public Albums(Integer albumID) {
        this.albumID = albumID;
    }

    public Albums(Integer albumID, String albumName, int userID) {
        this.albumID = albumID;
        this.albumName = albumName;
        this.userID = userID;
    }

    public Integer getAlbumID() {
        return albumID;
    }

    public void setAlbumID(Integer albumID) {
        this.albumID = albumID;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @XmlTransient
    public Collection<AlbumProduct> getAlbumProductCollection() {
        return albumProductCollection;
    }

    public void setAlbumProductCollection(Collection<AlbumProduct> albumProductCollection) {
        this.albumProductCollection = albumProductCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (albumID != null ? albumID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Albums)) {
            return false;
        }
        Albums other = (Albums) object;
        if ((this.albumID == null && other.albumID != null) || (this.albumID != null && !this.albumID.equals(other.albumID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitypack.Albums[ albumID=" + albumID + " ]";
    }
    
}
