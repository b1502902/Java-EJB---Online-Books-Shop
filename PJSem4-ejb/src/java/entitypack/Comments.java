/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entitypack;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author salin_000
 */
@Entity
@Table(name = "Comments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comments.findAll", query = "SELECT c FROM Comments c"),
    @NamedQuery(name = "Comments.findByCmtID", query = "SELECT c FROM Comments c WHERE c.cmtID = :cmtID"),
    @NamedQuery(name = "Comments.findByCmtContent", query = "SELECT c FROM Comments c WHERE c.cmtContent = :cmtContent"),
    @NamedQuery(name = "Comments.findByCmtDate", query = "SELECT c FROM Comments c WHERE c.cmtDate = :cmtDate")})
public class Comments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CmtID")
    private Integer cmtID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "CmtContent")
    private String cmtContent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CmtDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cmtDate;
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID")
    @ManyToOne(optional = false)
    private Products productID;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    @ManyToOne(optional = false)
    private Users userID;

    public Comments() {
    }

    public Comments(Integer cmtID) {
        this.cmtID = cmtID;
    }

    public Comments(Integer cmtID, String cmtContent, Date cmtDate, Products productID, Users userID) {
        this.cmtID = cmtID;
        this.cmtContent = cmtContent;
        this.cmtDate = cmtDate;
        this.productID = productID;
        this.userID = userID;
    }

    public Comments(String cmtContent, Date cmtDate, Products productID, Users userID) {
        this.cmtContent = cmtContent;
        this.cmtDate = cmtDate;
        this.productID = productID;
        this.userID = userID;
    }

    
    public Comments(Integer cmtID, String cmtContent, Date cmtDate) {
        this.cmtID = cmtID;
        this.cmtContent = cmtContent;
        this.cmtDate = cmtDate;
    }

    public Integer getCmtID() {
        return cmtID;
    }

    public void setCmtID(Integer cmtID) {
        this.cmtID = cmtID;
    }

    public String getCmtContent() {
        return cmtContent;
    }

    public void setCmtContent(String cmtContent) {
        this.cmtContent = cmtContent;
    }

    public Date getCmtDate() {
        return cmtDate;
    }

    public void setCmtDate(Date cmtDate) {
        this.cmtDate = cmtDate;
    }

    public Products getProductID() {
        return productID;
    }

    public void setProductID(Products productID) {
        this.productID = productID;
    }

    public Users getUserID() {
        return userID;
    }

    public void setUserID(Users userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cmtID != null ? cmtID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments other = (Comments) object;
        if ((this.cmtID == null && other.cmtID != null) || (this.cmtID != null && !this.cmtID.equals(other.cmtID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitypack.Comments[ cmtID=" + cmtID + " ]";
    }
    
}
