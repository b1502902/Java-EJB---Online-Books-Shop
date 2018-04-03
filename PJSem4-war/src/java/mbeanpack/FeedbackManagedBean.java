/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeanpack;

import entitypack.Feedbacks;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import sbeanpack.FeedbacksFacadeLocal;

/**
 *
 * @author salin_000
 */
@Named(value = "feedbackManagedBean")
@Dependent
public class FeedbackManagedBean {

    @EJB
    private FeedbacksFacadeLocal feedbacksFacade;
    
    private int feedbackID;
    private String custEmail;
    private String feedbackContent;
    private Date feedbackDate;

    /**
     * Creates a new instance of FeedbackManagedBean
     */
    public FeedbackManagedBean() {
    }
    public List<Feedbacks> getList() {
        return feedbacksFacade.findAll();
    }
    public String delete (String id){
        Feedbacks feebacks = feedbacksFacade.find(new Integer(id));
        feedbacksFacade.remove(feebacks);
        return "feedback";
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
    
}
