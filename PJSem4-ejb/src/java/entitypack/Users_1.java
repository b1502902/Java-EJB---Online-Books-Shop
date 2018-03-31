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
@Table(name = "Users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users_1.findAll", query = "SELECT u FROM Users_1 u")
    , @NamedQuery(name = "Users_1.findByUserID", query = "SELECT u FROM Users_1 u WHERE u.userID = :userID")
    , @NamedQuery(name = "Users_1.findByUsername", query = "SELECT u FROM Users_1 u WHERE u.username = :username")
    , @NamedQuery(name = "Users_1.findByPassword", query = "SELECT u FROM Users_1 u WHERE u.password = :password")
    , @NamedQuery(name = "Users_1.findByUserRule", query = "SELECT u FROM Users_1 u WHERE u.userRule = :userRule")
    , @NamedQuery(name = "Users_1.findByUserRealname", query = "SELECT u FROM Users_1 u WHERE u.userRealname = :userRealname")
    , @NamedQuery(name = "Users_1.findByUserEmail", query = "SELECT u FROM Users_1 u WHERE u.userEmail = :userEmail")
    , @NamedQuery(name = "Users_1.findByUserPhone", query = "SELECT u FROM Users_1 u WHERE u.userPhone = :userPhone")
    , @NamedQuery(name = "Users_1.findByUserAddress", query = "SELECT u FROM Users_1 u WHERE u.userAddress = :userAddress")})
public class Users_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "UserID")
    private Integer userID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "UserRule")
    private String userRule;
    @Size(max = 100)
    @Column(name = "UserRealname")
    private String userRealname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "UserEmail")
    private String userEmail;
    @Size(max = 11)
    @Column(name = "UserPhone")
    private String userPhone;
    @Size(max = 1000)
    @Column(name = "UserAddress")
    private String userAddress;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<Orders> ordersCollection;

    public Users_1() {
    }

    public Users_1(Integer userID) {
        this.userID = userID;
    }

    public Users_1(Integer userID, String username, String password, String userRule, String userEmail) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.userRule = userRule;
        this.userEmail = userEmail;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRule() {
        return userRule;
    }

    public void setUserRule(String userRule) {
        this.userRule = userRule;
    }

    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users_1)) {
            return false;
        }
        Users_1 other = (Users_1) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitypack.Users_1[ userID=" + userID + " ]";
    }
    
}
