/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeanpack;

import entitypack.Orders;
import entitypack.OrdersDetail;
import entitypack.Products;
import entitypack.Users;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import sbeanpack.OrdersDetailFacadeLocal;
import sbeanpack.OrdersFacadeLocal;
import sbeanpack.ProductsFacadeLocal;
import sbeanpack.UsersFacadeLocal;

/**
 *
 * @author salin_000
 */
@Named(value = "ordersManagedBean")
@Dependent
public class OrdersManagedBean {

    @EJB
    private ProductsFacadeLocal productsFacade;

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
    
    public String addToOrder(){
        System.out.println(orderName+orderPhone+orderAddress+orderNote);
        FacesContext context = FacesContext.getCurrentInstance();
        String username = (String) context.getExternalContext().getSessionMap().get("username");
        userID = usersFacade.find(usersFacade.findIdByUsername(username));
        orderDate = new Date();
        orderStatus = "waiting";
        orderName = context.getExternalContext().getRequestParameterMap().get("orderName");
        orderPhone = context.getExternalContext().getRequestParameterMap().get("orderPhone");
        orderAddress = context.getExternalContext().getRequestParameterMap().get("orderAddress");
        orderNote = context.getExternalContext().getRequestParameterMap().get("orderNote");
        System.out.println(orderName+orderPhone+orderAddress+orderNote);
        Orders order = new Orders(orderDate, orderName, orderStatus, orderPhone, orderAddress, orderNote, userID);
        ordersFacade.create(order);
        
        List<CartItem> listitem = (List<CartItem>) context.getExternalContext().getSessionMap().get("ucart");
        for (CartItem ci : listitem) {
            Products p = productsFacade.find(ci.getItemID());
            OrdersDetail ordersDetail = new OrdersDetail(ci.getItemQuantity(), ci.getItemPrice(), order, p);
            ordersDetailFacade.create(ordersDetail);
            p.setProductQuantity(p.getProductQuantity() - ci.getItemQuantity());
            productsFacade.edit(p);
        }
        
        context.getExternalContext().getSessionMap().remove("ucart");
        context.getExternalContext().getSessionMap().put("billid", order.getOrderID());
        addMessage("Order has been submit!");
        return "cart2.xhtml";
    }
    
    public String cancelOrder(int odid){
        System.out.println(odid);
        Orders od = ordersFacade.find(odid);
        od.setOrderStatus("cancel");
        ordersFacade.edit(od);
        List<OrdersDetail> listitem = ordersDetailFacade.listOrdersDetailByOrderID(od);
        for (OrdersDetail ci : listitem) {
            Products p = productsFacade.find(ci.getProductID().getProductID());
            p.setProductQuantity(p.getProductQuantity() + ci.getQuantity());
            productsFacade.edit(p);
        }
        addMessage("Order has been cancel!");
        return "userorders.xhtml";
    }
    
    public List<CartItem> listCIByOrderID(){
        List<CartItem> listci = new ArrayList<>();
        FacesContext context = FacesContext.getCurrentInstance();
        int billid = (int) context.getExternalContext().getSessionMap().get("billid");
        List<OrdersDetail> listodetail = ordersDetailFacade.listOrdersDetailByOrderID(ordersFacade.find(billid));
        for (OrdersDetail odd : listodetail) {
            Products p = productsFacade.find(odd.getProductID().getProductID());
            CartItem ci = new CartItem(p.getProductID(), p.getProductName(), odd.getQuantity(), odd.getPrice());
            listci.add(ci);
        }
        return listci;
    }
    
    public List<CartItem> listCIByOrderID(int odid){
        List<CartItem> listci = new ArrayList<>();
        List<OrdersDetail> listodetail = ordersDetailFacade.listOrdersDetailByOrderID(ordersFacade.find(odid));
        for (OrdersDetail odd : listodetail) {
            Products p = productsFacade.find(odd.getProductID().getProductID());
            CartItem ci = new CartItem(p.getProductID(), p.getProductName(), odd.getQuantity(), odd.getPrice());
            listci.add(ci);
        }
        return listci;
    }
    public List<Orders> listOrderByUserID(){
       FacesContext context = FacesContext.getCurrentInstance();
       Users u = usersFacade.find(usersFacade.findIdByUsername((String) context.getExternalContext().getSessionMap().get("username")));
       return ordersFacade.listOrdersByUserID(u);
    }
    
    public Orders orderByOrderID(){
        FacesContext context = FacesContext.getCurrentInstance();
        int billid = (int) context.getExternalContext().getSessionMap().get("billid");
        return ordersFacade.find(billid);
    }
    public Orders orderByOrderID(int odid){
        return ordersFacade.find(odid);
    }
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
