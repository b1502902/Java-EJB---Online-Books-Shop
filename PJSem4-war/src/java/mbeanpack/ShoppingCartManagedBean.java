/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mbeanpack;

import entitypack.Products;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import sbeanpack.ProductsFacadeLocal;
import sbeanpack.UsersFacadeLocal;

/**
 *
 * @author salin_000
 */
@ManagedBean
@SessionScoped
public class ShoppingCartManagedBean {
    @EJB
    private UsersFacadeLocal usersFacade;
    @EJB
    private ProductsFacadeLocal productsFacade;

    
    private int userID;
    private int billID;
    private int productID;
    private int buyQuantity;
    public List<CartItem> listitem;

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

    public int getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(int buyQuantity) {
        this.buyQuantity = buyQuantity;
    }
    
    /**
     * Creates a new instance of ShoppingCartManagedBean
     */
    public ShoppingCartManagedBean() {
    }
    
    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    
    
    public int[] checkItemExistsCart(int itemID){
        int[] x = {-1,-1};
        int flagci = 0;
        for (CartItem ci : listitem) {
            if(itemID == ci.getItemID()){
                x[0] = flagci;
                x[1] = ci.getItemQuantity();
                break;
            }
            flagci++;
        }
        System.out.println("gia tri x ne: "+x[0]+","+x[1]);
        return x;
    }
    
//    public String addItemToCart(int itemID){
//        System.out.println("ở đây"+itemID+"/"+buyQuantity);
//        Products p = productsFacade.find(new Integer(itemID));
//        CartItem i = new CartItem(itemID, p.getProductName(), buyQuantity, p.getProductPrice());
//        if(listitem.isEmpty()){
//           listitem.add(i);
//        } else {
//            if(!listitem.isEmpty() && checkItemExistsCart(itemID)[1]==0){
//                listitem.add(i);
//            } else if(!listitem.isEmpty() && checkItemExistsCart(itemID)[1]!=0){
//                i.setItemQuantity(checkItemExistsCart(itemID)[1]+buyQuantity);
//                listitem.set(checkItemExistsCart(itemID)[0], i);
//            }
//        }
//        return "cart/cart1.xhtml";
//    }
    
    public void addItemToCart(){
//        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        int itemID = Integer.parseInt(params.get("itemID"));
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String stringitemID = facesContext.getExternalContext().getRequestParameterMap().get("itemID");
        int itemID = Integer.parseInt(stringitemID);
        System.out.println("hehe: "+itemID+"|"+buyQuantity);
        Products p = productsFacade.find(new Integer(itemID));
        CartItem i = new CartItem(itemID, p.getProductName(),buyQuantity, p.getProductPrice());
        if(listitem == null){
           listitem = new ArrayList<CartItem>();
           listitem.add(i);
        } else {
            if(checkItemExistsCart(itemID)[0]==-1){
                listitem.add(i);
            } else{
                
                 i.setItemQuantity(checkItemExistsCart(itemID)[1]+buyQuantity);
                
                 listitem.set(checkItemExistsCart(itemID)[0], i);
            }
        }
        addMessage("The product has been added to your cart!");
        
    }
    
    public void addItemToCart(int itemQuantity){
//        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        int itemID = Integer.parseInt(params.get("itemID"));
        
        String a = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemID");
        int itemID = Integer.parseInt(a);
        System.out.println("hehe: "+itemID+"|"+buyQuantity);
        Products p = productsFacade.find(new Integer(itemID));
        buyQuantity = itemQuantity;
        CartItem i = new CartItem(itemID, p.getProductName(),buyQuantity, p.getProductPrice());
        if(listitem == null){
           listitem = new ArrayList<CartItem>();
           listitem.add(i);
        } else {
            if(checkItemExistsCart(itemID)[0]==-1){
                listitem.add(i);
            } else{
                
                 i.setItemQuantity(checkItemExistsCart(itemID)[1]+buyQuantity);
                
                 listitem.set(checkItemExistsCart(itemID)[0], i);
            }
        }
        addMessage("The product has been added to your cart!");
    }
    
    public void changeItemCartQuantity(String quant){
        String a = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemID");
        int itemID = Integer.parseInt(a);
        for (CartItem i : listitem) {
            if(i.getItemID() == itemID){
                i.setItemQuantity(Integer.parseInt(quant));
                System.out.println(listitem.indexOf(i));
                listitem.set(listitem.indexOf(i), i);
                break;
            }
        }
        
        addMessage("Quantity has been updated!");
    }
    
    public BigDecimal totalPrice(int quant, BigDecimal iprice){
        
        return iprice.multiply(new BigDecimal(quant));
    }
    public BigDecimal totalAllPrice(){
        BigDecimal totalAll= BigDecimal.ZERO;   
        for (CartItem i : listitem) {
            totalAll = totalAll.add(totalPrice(i.getItemQuantity(), i.getItemPrice()));
        }
        return totalAll;
    }
    
    public void testMethod(){
        System.out.println("đã nhận phương thức");
    }
    public List<CartItem> showCart(){
        return listitem;
    }
    
    public void buttonAction(ActionEvent actionEvent) {
        addMessage("Welcome to Primefaces!!");
    }
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
