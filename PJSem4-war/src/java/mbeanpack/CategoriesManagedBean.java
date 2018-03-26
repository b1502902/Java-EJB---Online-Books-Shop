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
}
