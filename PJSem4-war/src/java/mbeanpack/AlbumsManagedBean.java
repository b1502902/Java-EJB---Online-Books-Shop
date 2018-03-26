/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mbeanpack;

import entitypack.Albums;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sbeanpack.AlbumsFacadeLocal;

/**
 *
 * @author salin_000
 */
@ManagedBean
@RequestScoped
public class AlbumsManagedBean {
    
    @EJB
    private AlbumsFacadeLocal albumsFacade;

    private int albumID;
    private String albumName;
    private int userID;

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
    
    public List<Albums> showAllAlbums(){
        return albumsFacade.findAll();
    }
}
