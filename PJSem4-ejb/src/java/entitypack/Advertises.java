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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author salin_000
 */
@Entity
@Table(name = "Advertises")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Advertises.findAll", query = "SELECT a FROM Advertises a"),
    @NamedQuery(name = "Advertises.findByAdvID", query = "SELECT a FROM Advertises a WHERE a.advID = :advID"),
    @NamedQuery(name = "Advertises.findByAdvName", query = "SELECT a FROM Advertises a WHERE a.advName = :advName"),
    @NamedQuery(name = "Advertises.findByAdvView", query = "SELECT a FROM Advertises a WHERE a.advView = :advView"),
    @NamedQuery(name = "Advertises.findByAdvLink", query = "SELECT a FROM Advertises a WHERE a.advLink = :advLink")})
public class Advertises implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AdvID")
    private Integer advID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "AdvName")
    private String advName;
    @Size(max = 2147483647)
    @Column(name = "AdvView")
    private String advView;
    @Size(max = 2147483647)
    @Column(name = "AdvLink")
    private String advLink;

    public Advertises() {
    }

    public Advertises(Integer advID) {
        this.advID = advID;
    }

    public Advertises(Integer advID, String advName) {
        this.advID = advID;
        this.advName = advName;
    }

    public Integer getAdvID() {
        return advID;
    }

    public void setAdvID(Integer advID) {
        this.advID = advID;
    }

    public String getAdvName() {
        return advName;
    }

    public void setAdvName(String advName) {
        this.advName = advName;
    }

    public String getAdvView() {
        return advView;
    }

    public void setAdvView(String advView) {
        this.advView = advView;
    }

    public String getAdvLink() {
        return advLink;
    }

    public void setAdvLink(String advLink) {
        this.advLink = advLink;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (advID != null ? advID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Advertises)) {
            return false;
        }
        Advertises other = (Advertises) object;
        if ((this.advID == null && other.advID != null) || (this.advID != null && !this.advID.equals(other.advID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitypack.Advertises[ advID=" + advID + " ]";
    }
    
}
