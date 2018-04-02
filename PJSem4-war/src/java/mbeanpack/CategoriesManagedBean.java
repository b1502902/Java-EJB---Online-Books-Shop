/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mbeanpack;

import entitypack.Categories;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sbeanpack.CategoriesFacadeLocal;

/**
 *
 * @author salin_000
 */
@ManagedBean
@RequestScoped
public class CategoriesManagedBean {
    @EJB
    private CategoriesFacadeLocal categoriesFacade;

    private int cateID;
    private String cateName;
    /**
     * Creates a new instance of CategoriesManagedBean
     */
    public CategoriesManagedBean() {
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
    
    public List<Categories> showAllCate(){
        return categoriesFacade.findAll();
    }
    
     public String createCate(){
        Categories c = new Categories(cateName);
        categoriesFacade.create(c);
        return "index.xhtml";
    }
    
    
    public String linkEditCate(int cateid){
        Categories c = categoriesFacade.find(cateid);
        this.cateID = c.getCateID();
        this.cateName = c.getCateName();
        return "edit.xhtml";
    }
    
    public String editCate(){
        System.out.println("cate o day "+cateID+cateName);
        Categories c = categoriesFacade.find(cateID);
        c.setCateName(cateName);
        categoriesFacade.edit(c);
        return "index.xhtml";
    }
    
    public String deleteCate(int cateid){
        Categories c = categoriesFacade.find(cateid);
        categoriesFacade.remove(c);
        return "index.xhtml";
    }
}
