/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeanpack;

import entitypack.Producers;
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
import sbeanpack.ProducersFacadeLocal;

/**
 *
 * @author salin_000
 */
@Named(value = "producersManagedBean")
@RequestScoped
public class ProducersManagedBean {

    @EJB
    private ProducersFacadeLocal producersFacade;

    private int producerID;
    private String producerName;
    private String producerProfile;
    private String producerImg;

    private Part file;
    private String fileNamed;

    /**
     * Creates a new instance of ProducersManagedBean
     */
    public ProducersManagedBean() {
    }

    public int getProducerID() {
        return producerID;
    }

    public void setProducerID(int producerID) {
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

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getFileNamed() {
        return fileNamed;
    }

    public void setFileName(String fileNamed) {
        this.fileNamed = fileNamed;
    }

    public void uploadFile() {

        InputStream input = null;
        try {
            input = file.getInputStream();
            System.out.println("chay qua inpustream");
            String itemName = file.getSubmittedFileName();
            String filename = itemName.substring(
                    itemName.lastIndexOf("\\") + 1);
            String dirPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/upload/images");
            fileNamed = "/upload/images/" + filename;
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

    public String createProducer() {
        uploadFile();
        producerImg = fileNamed;
        Producers p = new Producers(producerName, producerProfile, producerImg);
        producersFacade.create(p);
        return "indexProducer.xhtml";
    }

    public String linkEditProducer(int pcerID) {
        Producers p = producersFacade.find(pcerID);
        this.producerID = p.getProducerID();
        this.producerName = p.getProducerName();
        this.producerProfile = p.getProducerProfile();
        this.producerImg = p.getProducerImg();
        return "editProducer.xhtml";
    }

    public String editProducer() {
        System.out.println("file trong edit: " + file.getSubmittedFileName());
        System.out.println("");
        Producers p = producersFacade.find(producerID);
        if (file.getSubmittedFileName() != "") {
            uploadFile();
            p.setProducerImg(fileNamed);
        }
        p.setProducerName(producerName);
        p.setProducerProfile(producerProfile);
        producersFacade.edit(p);
        return "indexProducer.xhtml";
    }

    public String deleteProducer(int pcerID) {
        Producers p = producersFacade.find(pcerID);
        producersFacade.remove(p);
        return "indexProducer.xhtml";
    }

    public List<Producers> showAllProducers() {
        return producersFacade.findAll();
    }

    public String getProducerNameById(int pcerID) {
        return producersFacade.find(pcerID).getProducerName();
    }

}
