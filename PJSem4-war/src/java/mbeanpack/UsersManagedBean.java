/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mbeanpack;

import entitypack.Users;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sbeanpack.UsersFacadeLocal;

/**
 *
 * @author salin_000
 */
@ManagedBean
@RequestScoped
public class UsersManagedBean {
    @EJB
    private UsersFacadeLocal usersFacade;

    private int userID;
    private String username;
    private String password;
    private String userRule;
    private String userRealname;
    private String userEmail;
    private String userPhone;
    private String userAddress;
    private String oldpassword;
    private String newpassword;
    
    
    
    private String msg;

    
    
    /**
     * Creates a new instance of UsersManagedBean
     */
    public UsersManagedBean() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUserRule() {
        return userRule;
    }

    public void setUserRule(String userRule) {
        this.userRule = userRule;
    }

    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }    
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public void registerUser() {
        try{
            Users users = new Users();
            users.setUsername(username);
            users.setPassword(password);
            users.setUserRule("user");
            users.setUserRealname(userRealname);
            users.setUserPhone(userPhone);
            users.setUserEmail(userEmail);
            users.setUserAddress(userAddress);

            usersFacade.create(users);
            msg = "Register success!";
        }
        catch (Exception e)
        {
            System.out.println(e);
            msg = "Register unsuccess!";
        }
        
    }
    
    public String loginUser(){
//        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession hs = SessionManaged.getSession();
        System.out.println(username+password);
        if(usersFacade.checkLogin(username, password)){
            hs.setAttribute("username", username);
            System.out.println("session"+hs.getAttribute("username"));
//            context.getExternalContext().getSessionMap().put("username", username);
            return "index.xhtml";//?faces-redirect=true
        } else{
            addMessage("Unknown login, try again");
            return null;
        }
    }
    
    public String logoutUser(){
//        HttpSession hs = SessionManaged.getSession();
//        hs.invalidate();
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
        sessionMap.remove("username");
        return "index.xhtml";
    }
    
    public Users getUserLogged(){
        FacesContext context = FacesContext.getCurrentInstance();
        String username = (String) context.getExternalContext().getSessionMap().get("username");
        Users u = usersFacade.find(usersFacade.findIdByUsername(username));
        return u;
    }
    public List<Users> listUserLogged(){
        Users u = getUserLogged();
        List<Users> lu = new ArrayList<Users>();
        lu.add(u);
        return lu;
    }
//    public String loginUser() {
//        System.out.println("day ne: " + username + password);
//        System.out.println(usersFacade.checkLogin(username, password));
//        if (usersFacade.checkLogin(username, password)){
//            HttpSession session = SessionManaged.getSession();
//            session.setAttribute("username", username);
//            System.out.println(session.getAttribute("username"));
//            return "index.xhtml";
//        }
//        addMessage("Username or password is incorrect");
////        SessionManaged.getRequest().setAttribute("message", "Username or password is incorrect");
//        return "index.xhtml";
//    }
    
    public String linkEditProfile(){
        HttpSession hs = SessionManaged.getSession();
        String userid = (String) hs.getAttribute("username");
        Users u = usersFacade.find(usersFacade.findIdByUsername(userid));
        this.userID = u.getUserID();
        this.username = u.getUsername();
        this.newpassword = "";
        this.userRealname = u.getUserRealname();
        this.userEmail = u.getUserEmail();
        this.userPhone = u.getUserPhone();
        this.userAddress = u.getUserAddress();
        return "editprofile.xhtml";
    }
    
    public void editProfile(){
        HttpSession hs = SessionManaged.getSession();
        String userid = (String) hs.getAttribute("username");
        Users u = usersFacade.find(usersFacade.findIdByUsername(userid));
        System.out.println("realname o day: "+userRealname+"oldpass "+oldpassword);
        if(oldpassword.equals(u.getPassword())){
            if(newpassword.equals("")){
                newpassword = u.getPassword();
            }
            u.setPassword(newpassword);
            u.setUserRealname(userRealname);
            u.setUserEmail(userEmail);
            u.setUserPhone(userPhone);
            u.setUserAddress(userAddress);
            usersFacade.edit(u);
            addMessage("Profile has been update");
        }else{
            addMessage("Wrong password!");
        }
        
        
    }
    
    public String showSessionUsername(){
        HttpSession session = SessionManaged.getSession();
        return (String) session.getAttribute(username);
    }
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    

    

    
}

    
