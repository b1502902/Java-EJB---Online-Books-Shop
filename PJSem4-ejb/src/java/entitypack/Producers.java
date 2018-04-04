/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entitypack;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "Producers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producers.findAll", query = "SELECT p FROM Producers p"),
    @NamedQuery(name = "Producers.findByProducerID", query = "SELECT p FROM Producers p WHERE p.producerID = :producerID"),
    @NamedQuery(name = "Producers.findByProducerName", query = "SELECT p FROM Producers p WHERE p.producerName = :producerName"),
    @NamedQuery(name = "Producers.findByProducerProfile", query = "SELECT p FROM Producers p WHERE p.producerProfile = :producerProfile"),
    @NamedQuery(name = "Producers.findByProducerImg", query = "SELECT p FROM Producers p WHERE p.producerImg = :producerImg")})
public class Producers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ProducerID")
    private Integer producerID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ProducerName")
    private String producerName;
    @Size(max = 2147483647)
    @Column(name = "ProducerProfile")
    private String producerProfile;
    @Size(max = 2147483647)
    @Column(name = "ProducerImg")
    private String producerImg;
    @OneToMany(mappedBy = "producerID")
    private Collection<Products> productsCollection;

    

    public Producers() {
    }

    public Producers(String producerName, String producerProfile, String producerImg) {
        this.producerName = producerName;
        this.producerProfile = producerProfile;
        this.producerImg = producerImg;
    }
    
    public Producers(Integer producerID) {
        this.producerID = producerID;
    }

    public Producers(Integer producerID, String producerName) {
        this.producerID = producerID;
        this.producerName = producerName;
    }

    public Integer getProducerID() {
        return producerID;
    }

    public void setProducerID(Integer producerID) {
        this.producerID = producerID;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getProducerProfile() {
        return producerProfile;
    }

    public void setProducerProfile(String producerProfile) {
        this.producerProfile = producerProfile;
    }

    public String getProducerImg() {
        return producerImg;
    }

    public void setProducerImg(String producerImg) {
        this.producerImg = producerImg;
    }

    @XmlTransient
    public Collection<Products> getProductsCollection() {
        return productsCollection;
    }

    public void setProductsCollection(Collection<Products> productsCollection) {
        this.productsCollection = productsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (producerID != null ? producerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producers)) {
            return false;
        }
        Producers other = (Producers) object;
        if ((this.producerID == null && other.producerID != null) || (this.producerID != null && !this.producerID.equals(other.producerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitypack.Producers[ producerID=" + producerID + " ]";
    }
    
}
