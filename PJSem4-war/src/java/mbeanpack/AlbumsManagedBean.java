/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mbeanpack;

import entitypack.Albums;
import entitypack.Products;
import entitypack.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import sbeanpack.AlbumProductFacadeLocal;
import sbeanpack.AlbumsFacadeLocal;
import sbeanpack.ProductsFacadeLocal;
import sbeanpack.UsersFacadeLocal;

/**
 *
 * @author salin_000
 */
@ManagedBean
@RequestScoped
public class AlbumsManagedBean {

    @EJB
    private UsersFacadeLocal usersFacade;
    @EJB
    private ProductsFacadeLocal productsFacade;
    @EJB
    private AlbumProductFacadeLocal albumProductFacade;
    @EJB
    private AlbumsFacadeLocal albumsFacade;

    
    
    private int albumID;
    private String albumName;
    private int userID;
    
    private int albumProductID;
    private int productID;

    public AlbumsManagedBean(int albumID, String albumName, int userID) {
        this.albumID = albumID;
        this.albumName = albumName;
        this.userID = userID;
    }
    
    /**
     * Creates a new instance of AlbumsManagedBean
     */
    public AlbumsManagedBean() {
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
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

    public int getAlbumProductID() {
        return albumProductID;
    }

    public void setAlbumProductID(int albumProductID) {
        this.albumProductID = albumProductID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
    
    public List<Albums> showAllAlbumsOfUser(){
        FacesContext context = FacesContext.getCurrentInstance();
        String username = (String) context.getExternalContext().getSessionMap().get("username");
        Users u = usersFacade.find(usersFacade.findIdByUsername(username));
        System.out.println(u);
        return albumsFacade.showAllAlbumsOfUser(u);
    }
    
    public List<Products> showProductsInAlbum(int albid){
        System.out.println("albid: "+albid);
        Albums alb = albumsFacade.find(albid);
        
        return albumProductFacade.listProductsByAlbum(alb);
    }
}
