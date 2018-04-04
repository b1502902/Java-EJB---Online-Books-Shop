/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeanpack;

import entitypack.Albums;
import entitypack.Artists;
import entitypack.Categories;
import entitypack.Producers;
import entitypack.Products;
import entitypack.Users;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.Part;
import sbeanpack.ArtistsFacadeLocal;
import sbeanpack.CategoriesFacadeLocal;
import sbeanpack.ProducersFacadeLocal;
import sbeanpack.ProductsFacadeLocal;

/**
 *
 * @author salin_000
 */
@ManagedBean
@RequestScoped
public class ProductsManagedBean {

    @EJB
    private ArtistsFacadeLocal artistsFacade;

    @EJB
    private ProducersFacadeLocal producersFacade;
    
    @EJB
    private CategoriesFacadeLocal categoriesFacade;
    
    @EJB
    private ProductsFacadeLocal productsFacade;

    
    
    private int productID;
    private String productName;
    private int productQuantity;
//    private float productPrice;
    BigDecimal productPrice;
    private int cateID;
    private int artistsID;
    private int producerID;
    private String productImg;
    private String productTrailer;
    private String productContent;
    
    private Part file;
    private String fileNamed;
    private String skeyword;
    private Products p;

    
    
    public ProductsManagedBean() {
    }

    public ProductsManagedBean(int productID, String productName, int productQuantity, BigDecimal productPrice, int cateID, int artistsID, int producerID, String productImg, String productTrailer, String productContent) {
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

    public ProductsManagedBean(String productName, int productQuantity, BigDecimal productPrice, int cateID, int artistsID, int producerID, String productImg, String productTrailer, String productContent) {
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

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
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
    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getFileNamed() {
        return fileNamed;
    }

    public void setFileNamed(String fileNamed) {
        this.fileNamed = fileNamed;
    }
    /**
     * Creates a new instance of ProductsManagedBean
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
        return productsFacade.showLastedProducts().subList(0, 9);
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
    public Products getProductByID(int pid){
        return productsFacade.find(pid);
    }
    
    public int getPQuantityByPID(int pid){
        Products p = productsFacade.find(pid);
        return p.getProductQuantity();
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

    public String createProduct(){
        uploadFile();
        productImg = fileNamed;
        Producers pcer = producersFacade.find(producerID);
        Categories cate = categoriesFacade.find(cateID);
        Products p = new Products(productName, productQuantity, productPrice, artistsID, productImg, productTrailer, productContent, cate, pcer);
        productsFacade.create(p);
        return "indexProduct.xhtml";
    }
    
    public String linkEditProduct(int pID){
        Products p1 = productsFacade.find(pID);
        this.productID = p1.getProductID();
        this.productName = p1.getProductName();
        this.productQuantity = p1.getProductQuantity();
        this.productPrice = p1.getProductPrice();
        this.productContent = p1.getProductContent();
        this.productTrailer = p1.getProductTrailer();
        
        if(p1.getCateID()!=null){
            this.cateID = p1.getCateID().getCateID();
        }
        if(p1.getProducerID()!=null){
            this.producerID = p1.getProducerID().getProducerID();
        }
        if(p1.getArtistsID()!=null){
            this.artistsID = p1.getArtistsID();
        }
        
        return "editProduct.xhtml";
    }
    
    public String editProduct(){
        System.out.println("file trong edit: "+file.getSubmittedFileName());
        System.out.println("");
        Products p1 = productsFacade.find(productID);
        if(file.getSubmittedFileName()!=""){
           uploadFile();
           p1.setProductImg(fileNamed);
        }
        p1.setProductID(productID);
        p1.setProductName(productName);
        p1.setProductQuantity(productQuantity);
        p1.setProductPrice(productPrice);
        p1.setProductContent(productContent);
        p1.setProductTrailer(productTrailer);
        p1.setCateID(categoriesFacade.find(cateID));
        p1.setProducerID(producersFacade.find(producerID));
        p1.setArtistsID(artistsID);
        productsFacade.edit(p1);
        return "indexProduct.xhtml";
    }
    
    public String deleteProduct(int pID){
        Products a = productsFacade.find(pID);
        productsFacade.remove(p);
        return "indexProduct.xhtml";
    }
    public List<SelectItem> showCateToMenu(){
        List<SelectItem> ls = new ArrayList<>();
        List<Categories> lc = categoriesFacade.findAll();
        for (Categories c : lc) {
            ls.add(new SelectItem(c.getCateID(), c.getCateName()));
        }
        return ls;
    }
    
    public List<SelectItem> showArtistsToMenu(){
        List<SelectItem> ls = new ArrayList<>();
        List<Artists> la = artistsFacade.findAll();
        for (Artists a : la) {
            ls.add(new SelectItem(a.getArtistID(), a.getArtistName()));
        }
        return ls;
    }
    public List<SelectItem> showProducersToMenu(){
        List<SelectItem> ls = new ArrayList<>();
        List<Producers> lpcer = producersFacade.findAll();
        for (Producers p1 : lpcer) {
            ls.add(new SelectItem(p1.getProducerID(), p1.getProducerName()));
        }
        return ls;
    }
    
    public String getCateName(Categories cate){
        return cate.getCateName();
    }
}