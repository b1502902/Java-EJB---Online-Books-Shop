/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeanpack;

import entitypack.Advertises;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sbeanpack.AdvertisesFacadeLocal;

/**
 *
 * @author salin_000
 */
@Named(value = "advertisesManagedBean")
@RequestScoped
public class AdvertisesManagedBean {

    @EJB
    private AdvertisesFacadeLocal advertisesFacade;
    private int advID;
    private String advName;
    private String advView;
    private String advLink;

    /**
     * Creates a new instance of AdvertisesManagedBean
     */
    public AdvertisesManagedBean() {
    }
    public List<Advertises> getList() {
        return advertisesFacade.findAll();
    }
    
     public String insertAdv(){
        try{
            Advertises advertises = new Advertises();
            advertises.setAdvLink(advLink);
            advertises.setAdvName(advName);
            advertises.setAdvView(advView);
            
            advertisesFacade.create(advertises);
            
            return "adv";
        }
        catch (Exception e){
        }
        return null;
    }
    
    public String findAdv (String id){
        Advertises advertises = advertisesFacade.find(new Integer(id));
        setAdvID(advertises.getAdvID());
        setAdvName(advertises.getAdvName());
        return "updateAdvertises";
    }
    public String goTo() {
        return "createAdv";
    }
    
    public String updateAdv(){
        Advertises advertises = advertisesFacade.find(new Integer(this.advID));
        advertises.setAdvName(advName);
        advertises.setAdvView(advView);
        advertises.setAdvLink(advLink);
        
        advertisesFacade.edit(advertises);
        return "adv";
    }
    
    public String deleteAdv (String id){
        Advertises advertises = advertisesFacade.find(new Integer(id));
        advertisesFacade.remove(advertises);
        return "adv";
    }
    

    public int getAdvID() {
        return advID;
    }

    public void setAdvID(int advID) {
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
    
}
