/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeanpack;

import entitypack.Artists;
import java.io.IOException;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import sbeanpack.ArtistsFacadeLocal;

/**
 *
 * @author salin_000
 */
@Named(value = "artistsManagedBean")
@RequestScoped
public class ArtistsManagedBean {

    @EJB
    private ArtistsFacadeLocal artistsFacade;

    private int artistID;
    private String artistName;
    private String artistProfile;
    private String artistImg;
    
    private Part imgFile;
    private String imgFileName;
    private long imgFileSize;
    
    /**
     * Creates a new instance of ArtistsManagedBean
     */
    public ArtistsManagedBean() {
    }
    

    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistImg() {
        return artistImg;
    }

    public void setArtistImg(String artistImg) {
        this.artistImg = artistImg;
    }

    public Part getImgFile() {
        return imgFile;
    }

    public void setImgFile(Part imgFile) {
        this.imgFile = imgFile;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public long getImgFileSize() {
        return imgFileSize;
    }

    public void setImgFileSize(long imgFileSize) {
        this.imgFileSize = imgFileSize;
    }
    public String createArtist(){
        System.out.println("vao createArt: "+artistName);
        System.out.println("nhan file: "+imgFile);
        try{
            imgFileName = imgFile.getSubmittedFileName();
            imgFileSize = imgFile.getSize();
            String dirPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/upload");
            imgFile.write(dirPath+"/"+imgFileName);
            System.out.println("link hinh: "+dirPath+"/"+imgFileName);
            Artists a = new Artists(artistName, artistProfile, dirPath+"/"+imgFileName);
            artistsFacade.create(a);
        } catch (IOException ex){
            Logger.getLogger(ArtistsManagedBean.class.getName());
        }
        
        return "index.xhtml";
    }

    public String getArtistProfile() {
        return artistProfile;
    }

    public void setArtistProfile(String artistProfile) {
        this.artistProfile = artistProfile;
    }
}
