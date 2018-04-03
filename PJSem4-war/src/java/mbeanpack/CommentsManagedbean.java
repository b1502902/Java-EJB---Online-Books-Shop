/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mbeanpack;

import entitypack.Comments;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import sbeanpack.CommentsFacadeLocal;
import sbeanpack.ProductsFacadeLocal;
import sbeanpack.UsersFacadeLocal;

/**
 *
 * @author salin_000
 */
@ManagedBean
@RequestScoped
public class CommentsManagedbean {
    @EJB
    private ProductsFacadeLocal productsFacade;
    @EJB
    private UsersFacadeLocal usersFacade;
    @EJB
    private CommentsFacadeLocal commentsFacade;
    

    private int cmtID;
    private int userID;
    private int productID;
    private String cmtContent;
    private Date cmtDate;
    /**
     * Creates a new instance of CommentsManagedbean
     */
    public CommentsManagedbean() {
    }
    public List<Comments> getList() {
        return commentsFacade.findAll();
    }
     public String delete (String id){
        Comments comments = commentsFacade.find(new Integer(id));
        commentsFacade.remove(comments);
        return "Comment";
    }

    public int getCmtID() {
        return cmtID;
    }

    public void setCmtID(int cmtID) {
        this.cmtID = cmtID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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
    
    public void actionCmt(){
        FacesContext context = FacesContext.getCurrentInstance();
        String username = (String) context.getExternalContext().getSessionMap().get("username");
        userID = usersFacade.findIdByUsername(username);
        System.out.println("user: "+"/"+userID);
        String a = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemID");
        productID = Integer.parseInt(a);
        System.out.println("product: "+productID);
        Date currentDate = new Date();
//        Timestamp currentSqlDate = new java.sql.Timestamp(currentDate);
//        System.out.println("date: "+currentSqlDate);     
        Comments cmt = new Comments(cmtContent, currentDate, productsFacade.find(productID), usersFacade.find(userID));
        commentsFacade.create(cmt);
        addMessage("Your comment being posted");
    }
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public List<Comments> showCmtByProduct(int productID){
        System.out.println("product id: "+productID);
        return commentsFacade.listCmtByProductID(productsFacade.find(productID));
    }
}
