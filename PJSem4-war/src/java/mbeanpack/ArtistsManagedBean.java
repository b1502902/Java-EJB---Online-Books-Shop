/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeanpack;

import entitypack.Artists;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
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
    
    Part file;
    String fileNamed;
    public ArtistsManagedBean() {
    }
    public String getFileNamed() {
        return fileNamed;
    }

    public void setFileNamed(String fileNamed) {
        this.fileNamed = fileNamed;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    /**
     * Creates a new instance of Upload_File
     */
    

    public void uploadFile() {
        
            InputStream input = null;
        try {
            input = file.getInputStream();
            System.out.println("chay qua inpustream");
            String itemName = file.getSubmittedFileName();
            String filename = itemName.substring(
                    itemName.lastIndexOf("\\") + 1);
            String dirPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/upload/images");
            fileNamed = "/upload/images/"+filename;
            File f = new File(dirPath + "\\" + filename);
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream output = new FileOutputStream(f);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            //            resize(dirPath + "\\" + filename, dirPath + "\\" + filename, 200, 200);
            input.close();
            output.close();
        } catch (IOException ex) {
            System.out.println("loi io");
            Logger.getLogger(ArtistsManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /**
     * Creates a new instance of ArtistsManagedBean
     */
    

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

    public String getArtistImg() {
        return artistImg;
    }

    public void setArtistImg(String artistImg) {
        this.artistImg = artistImg;
    }
    
    public String createArtist(){
        uploadFile();
        artistImg = fileNamed;
        Artists a = new Artists(artistName, artistProfile, artistImg);
        artistsFacade.create(a);
        return "indexArtist.xhtml";
    }
    
    public String linkEditArtist(int artID){
        Artists a = artistsFacade.find(artID);
        this.artistID = a.getArtistID();
        this.artistName = a.getArtistName();
        this.artistProfile = a.getArtistProfile();
        this.artistImg = a.getArtistImg();
        return "editArtist.xhtml";
    }
    
    public String editArtist(){
        System.out.println("file trong edit: "+file.getSubmittedFileName());
        System.out.println("");
        Artists a = artistsFacade.find(artistID);
        if(file.getSubmittedFileName()!=""){
           uploadFile();
           a.setArtistImg(fileNamed);
        }
        a.setArtistName(artistName);
        a.setArtistProfile(artistProfile);
        artistsFacade.edit(a);
        return "indexArtist.xhtml";
    }
    
    public String deleteArtist(int artID){
        Artists a = artistsFacade.find(artID);
        artistsFacade.remove(a);
        return "indexArtist.xhtml";
    }
    public List<Artists> showAllArtists(){
        return artistsFacade.findAll();
    }
    
    public String getArtistNameById(int artistID){
        return artistsFacade.find(artistID).getArtistName();
    }
}
