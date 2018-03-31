/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeanpack;

import entitypack.Orders;
import entitypack.Users;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import sbeanpack.OrdersDetailFacadeLocal;
import sbeanpack.OrdersFacadeLocal;
import sbeanpack.UsersFacadeLocal;

/**
 *
 * @author salin_000
 */
@Named(value = "ordersManagedBean")
@Dependent
public class OrdersManagedBean {

    @EJB
    private UsersFacadeLocal usersFacade;

    @EJB
    private OrdersDetailFacadeLocal ordersDetailFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    
    
    private int orderID;
    private Date orderDate;
    private Users userID;
    private String orderName;
    private String orderStatus;
    private String orderPhone;
    private String orderAddress;
    private String orderNote;
    /**
     * Creates a new instance of OrdersManagedBean
     */
    public OrdersManagedBean() {
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Users getUserID() {
        return userID;
    }

    public void setUserID(Users userID) {
        this.userID = userID;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }
    
    public void addToOrder(){
        FacesContext context = FacesContext.getCurrentInstance();
        String username = (String) context.getExternalContext().getSessionMap().get("username");
        userID = usersFacade.find(usersFacade.findIdByUsername(username));
        orderDate = new Date();
        Orders order = new Orders(orderDate, userID, orderName, orderStatus, orderPhone, orderAddress, orderNote);
        
    }
}
