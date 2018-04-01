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
import javax.servlet.http.HttpSession;
import sbeanpack.ProductsFacadeLocal;
import sbeanpack.UsersFacadeLocal;

/**
 *
 * @author salin_000
 */
@ManagedBean
@RequestScoped
public class ShoppingCartManagedBean {
    @EJB
    private UsersFacadeLocal usersFacade;
    @EJB
    private ProductsFacadeLocal productsFacade;

    
    private int userID;
    private int billID;
    private int productID;
    private int buyQuantity;
    

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
        HttpSession hs = SessionManaged.getSession();
        List<CartItem> listitem = (List<CartItem>) hs.getAttribute("ucart");
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
        HttpSession hs = SessionManaged.getSession();
        List<CartItem> listitem = (List<CartItem>) hs.getAttribute("ucart");
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
        hs.setAttribute("ucart", listitem);
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
        HttpSession hs = SessionManaged.getSession();
        List<CartItem> listitem = (List<CartItem>) hs.getAttribute("ucart");
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
        hs.setAttribute("ucart", listitem);
        addMessage("The product has been added to your cart!");
    }
    
    public void changeItemCartQuantity(String quant){
        String a = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("itemID");
        int itemID = Integer.parseInt(a);
        HttpSession hs = SessionManaged.getSession();
        List<CartItem> listitem = (List<CartItem>) hs.getAttribute("ucart");
        for (CartItem i : listitem) {
            if(i.getItemID() == itemID){
                if(Integer.parseInt(quant)<=0){
                    listitem.remove(i);
                    break;
                } else{
                    i.setItemQuantity(Integer.parseInt(quant));
                    System.out.println(listitem.indexOf(i));
                    listitem.set(listitem.indexOf(i), i);
                    break;
                }
                
            }
        }
        
        hs.setAttribute("ucart", listitem);
        addMessage("Quantity has been updated!");
    }
    
    public BigDecimal totalPrice(int quant, BigDecimal iprice){
        
        return iprice.multiply(new BigDecimal(quant));
    }
    public BigDecimal totalAllPrice(List<CartItem> listitem){
        BigDecimal totalAll= BigDecimal.ZERO;
        HttpSession hs = SessionManaged.getSession();
        if(listitem!=null){
            for (CartItem i : listitem) {
                totalAll = totalAll.add(totalPrice(i.getItemQuantity(), i.getItemPrice()));
            }
        }
        return totalAll;
    }
    
    public void testMethod(){
        System.out.println("đã nhận phương thức");
    }
    public List<CartItem> showCart(){
        HttpSession hs = SessionManaged.getSession();
        List<CartItem> listitem = (List<CartItem>) hs.getAttribute("ucart");
        return listitem;
    }
    
    public int countCart(){
        HttpSession hs = SessionManaged.getSession();
        List<CartItem> listitem = (List<CartItem>) hs.getAttribute("ucart");
        if(listitem!=null){
            return listitem.size();
        }else{
            return 0;
        }
        
    }
    
    public void buttonAction(ActionEvent actionEvent) {
        addMessage("Welcome to Primefaces!!");
    }
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
