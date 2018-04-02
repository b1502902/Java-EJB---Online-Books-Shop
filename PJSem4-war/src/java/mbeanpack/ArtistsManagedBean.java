/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeanpack;

import entitypack.Artists;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import sbeanpack.ArtistsFacadeLocal;

/**
 *
 * @author salin_000
 */
@Named(value = "artistsManagedBean")
@Dependent
public class ArtistsManagedBean {

    @EJB
    private ArtistsFacadeLocal artistsFacade;

    private int artistID;
    private String artistName;
    private String artistProfile;
    private String artistImage;
    
    private Part imgFile;
    private UploadedFile fartistImage;
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

    public String getArtistProfile() {
        return artistProfile;
    }

    public void setArtistProfile(String artistProfile) {
        this.artistProfile = artistProfile;
    }

    public String getArtistImage() {
        return artistImage;
    }

    public void setArtistImage(String artistImage) {
        this.artistImage = artistImage;
    }
    
    public UploadedFile getFartistImage() {
        return fartistImage;
    }

    public void setFartistImage(UploadedFile fartistImage) {
        this.fartistImage = fartistImage;
    }
    
    public Part getImgFile() {
        return imgFile;
    }

    public void setImgFile(Part imgFile) {
        this.imgFile = imgFile;
    }
    
    public List<Artists> showAllArtists(){
        return artistsFacade.findAll();
    }
    
    public void saveImg(){
        try (InputStream input = imgFile.getInputStream()) {
        Files.copy(input, new File("upload", "img").toPath());
        }
        catch (IOException e) {
            // Show faces message?
        }
    }

    public String createArtist(){
        Artists a = new Artists(artistName, artistProfile, artistImage);
        artistsFacade.create(a);
        return "index.xhtml";
    }

    
}
