/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeanpack;

import entitypack.Categories;
import entitypack.Products;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import sbeanpack.CategoriesFacadeLocal;
import sbeanpack.ProductsFacadeLocal;

/**
 *
 * @author salin_000
 */
@ManagedBean
@RequestScoped
public class ProductsManagedBean {
    @EJB
    private CategoriesFacadeLocal categoriesFacade;
    
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
    private String skeyword;
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

    public String getSkeyword() {
        return skeyword;
    }

    public void setSkeyword(String skeyword) {
        this.skeyword = skeyword;
    }
    /**
     * Creates a new instance of ProductsManagedBean
     */
    
    public List<Products> showAllProduct(){
        System.out.println(productsFacade.findAll());
        return productsFacade.findAll();
    }
    
    public List<Products> showProductByCate(String cateid){
        List<Products> listProducts = productsFacade.findAll();
        if(!"".equals(cateid)){
            Categories cate = categoriesFacade.find(new Integer(cateid));
            listProducts = productsFacade.showByCate(cate);
        }
        System.out.println("so luong phan tu: "+listProducts.size());
        return listProducts;
    }
    
    public List<Products> showProductsIndex(){
        return productsFacade.findAll().subList(0, 9);
    }
    
    public String productdetail(Products p){
        setProductID(p.getProductID());
        this.p = p;
        return "product_details.xhtml";
    }
    
    public List<Products> showOnePdetail(int pid){
        Products p = new Products();
        p = productsFacade.find(pid);
        List<Products> plist = new ArrayList<>();
        plist.add(p);
        return plist;
    }
    
    public String actionSearchProducts(){
        return "search1.xhtml";
    }
    
    public List<Products> showSearchProducts(){
        List<Products> plist = new ArrayList<Products>();
        plist = productsFacade.searchProducts(skeyword);
        return plist;
    }
    
    public Products showProduct(){
        return productsFacade.find(productID);
    }
    
    public String subName(String name){
        if(name.length() > 25){
            name = name.substring(0, 25)+"...";
        }
        return name;
    }
}