/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mbeanpack;

import entitypack.AlbumProduct;
import entitypack.Albums;
import entitypack.Products;
import entitypack.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
    
    private int selectedAlbum;

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
        System.out.println("showallalb nhan username "+username);
        Users u = usersFacade.find(usersFacade.findIdByUsername(username));
        System.out.println(u);
        return albumsFacade.showAllAlbumsOfUser(u);
    }
    
    public List<Products> showProductsInAlbum(int albid){
        System.out.println("albid: "+albid);
        Albums alb = albumsFacade.find(albid);
        
        return albumProductFacade.listProductsByAlbum(alb);
    }
    
    public List<SelectItem> showUserAlbumsToMenu(){
        List<SelectItem> ls = new ArrayList<>();
        FacesContext context = FacesContext.getCurrentInstance();
        String username = (String) context.getExternalContext().getSessionMap().get("username");
        Users u = usersFacade.find(usersFacade.findIdByUsername(username));
        List<Albums> la = albumsFacade.showAllAlbumsOfUser(u);
        for (Albums a : la) {
            ls.add(new SelectItem(a.getAlbumID(), a.getAlbumName()));
        }
        return ls;
    }
    
    public void addAlbum(){
        FacesContext context = FacesContext.getCurrentInstance();
        String username = (String) context.getExternalContext().getSessionMap().get("username");
        Users u = usersFacade.find(usersFacade.findIdByUsername(username));
        Albums a = new Albums(albumName, u);
        albumsFacade.create(a);
        addMessage("Album added successful!");
    }
    public void addProductToAlbum(){
        UsersManagedBean um = new UsersManagedBean();
        //lay product
        String a = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemID");
        Products p = productsFacade.find(new Integer(a));
        albumProductFacade.create(new AlbumProduct(albumsFacade.find(selectedAlbum), p));
        addMessage("The product has been added to "+albumsFacade.find(selectedAlbum).getAlbumName());
    }

    public int getSelectedAlbum() {
        return selectedAlbum;
    }

    public void setSelectedAlbum(int selectedAlbum) {
        this.selectedAlbum = selectedAlbum;
    }
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public String linkEditAlbum(int albID){
        Albums a = albumsFacade.find(albID);
        this.albumID = a.getAlbumID();
        this.albumName = a.getAlbumName();
        return "editAlbum.xhtml";
    }
    
    public String editAlbum(){
        Albums a = albumsFacade.find(albumID);
        a.setAlbumName(albumName);
        albumsFacade.edit(a);
        return "useralbums.xhtml";
    }
    
    public String deleteAlbum(int albID){
        List<AlbumProduct> lap = albumProductFacade.findAll();
        for (AlbumProduct ap : lap) {
            Albums a = ap.getAlbumID();
            if (a.getAlbumID() == albID) {
                albumProductFacade.remove(ap);
            }
        }
        Albums a2 = albumsFacade.find(albID);
        albumsFacade.remove(a2);
        return "useralbums.xhtml";
    }
    public String deleteProductinAlbum(int albID, int pID){
        List<AlbumProduct> lap = albumProductFacade.findAll();
        for (AlbumProduct ap : lap) {
            Albums a = ap.getAlbumID();
            Products p = ap.getProductID();
            if(a.getAlbumID() == albID && p.getProductID() == pID){
                albumProductFacade.remove(ap);
            }
        }
        return "album.xhtml?albid="+albID;
    }
}
