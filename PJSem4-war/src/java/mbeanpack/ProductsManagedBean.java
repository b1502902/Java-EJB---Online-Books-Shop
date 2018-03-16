/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeanpack;

import entitypack.Products;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sbeanpack.ProductsFacadeLocal;

/**
 *
 * @author salin_000
 */
@ManagedBean
@RequestScoped
public class ProductsManagedBean {
    
    @EJB
    private ProductsFacadeLocal productsFacade;

    private int productID;
    private String productName;
    private int productQuantity;
    private float productPrice;
    private int cateID;
    private int artistsID;
    private int producerID;
    private String productImg;
    private String productTrailer;
    private String productContent;

    
    private Products p;

    public ProductsManagedBean() {
    }

    public ProductsManagedBean(int productID, String productName, int productQuantity, float productPrice, int cateID, int artistsID, int producerID, String productImg, String productTrailer, String productContent) {
        this.productID = productID;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.cateID = cateID;
        this.artistsID = artistsID;
        this.producerID = producerID;
        this.productImg = productImg;
        this.productTrailer = productTrailer;
        this.productContent = productContent;
    }

    public ProductsManagedBean(String productName, int productQuantity, float productPrice, int cateID, int artistsID, int producerID, String productImg, String productTrailer, String productContent) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.cateID = cateID;
        this.artistsID = artistsID;
        this.producerID = producerID;
        this.productImg = productImg;
        this.productTrailer = productTrailer;
        this.productContent = productContent;
    }

    public ProductsManagedBean(String productName, int cateID) {
        this.productName = productName;
        this.cateID = cateID;
    }
    
    
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public int getArtistsID() {
        return artistsID;
    }

    public void setArtistsID(int artistsID) {
        this.artistsID = artistsID;
    }

    public int getProducerID() {
        return producerID;
    }

    public void setProducerID(int producerID) {
        this.producerID = producerID;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductTrailer() {
        return productTrailer;
    }

    public void setProductTrailer(String productTrailer) {
        this.productTrailer = productTrailer;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }
    public Products getP() {
        return p;
    }

    public void setP(Products p) {
        this.p = p;
    }

    /**
     * Creates a new instance of ProductsManagedBean
     */
    
    public List<Products> showAllProduct(){
        System.out.println(productsFacade.findAll());
        return productsFacade.findAll();
    }
    
    public List<Products> showProductsIndex(){
        return productsFacade.findAll().subList(0, 9);
    }
    
    public String productdetail(Products p){
        setProductID(p.getProductID());
        this.p = p;
        return "faces/product_details.xhtml";
    }
    
    public Products showProduct(){
        return productsFacade.find(productID);
    }
}
