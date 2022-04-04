package model;
import beans.SessionBean;
import java.sql.Timestamp;
import java.util.*;

/**
 * 
 */
public class Payment {

    /**
     * Default constructor
     */
    public Payment() {
    }

    private int id;

//    private int type;
//
//    private String typeName;
    
    private PaymentType paymentType;

    private double amount;

    private User user;

    private Session session;
    
    private SessionBean sessionBean;
    
    private Status status;
    
    private Timestamp dateOfPayments;

//    private Discount discount;

    private boolean enabled;

    public Payment(int id, PaymentType paymentType, double amount, User user, Session session, Timestamp dateOfPayments, boolean enabled) {
        this.id = id;
        this.paymentType = paymentType;
        this.amount = amount;
        this.user = user;
        this.session = session;
        this.dateOfPayments = dateOfPayments;
        this.enabled = enabled;
    }
    
    //constructor with Status as parameter
    
    
        public Payment(int id, PaymentType paymentType, double amount, User user, Session session, Timestamp dateOfPayments, Status status, boolean enabled) {
        this.id = id;
        this.paymentType = paymentType;
        this.amount = amount;
        this.user = user;
        this.session = session;
        this.dateOfPayments = dateOfPayments;
        this.status = status;
        this.enabled = enabled;
    }

    public Payment(int id, PaymentType paymentType, double amount, User user, SessionBean sessionBean, Timestamp dateOfPayments, Status status, boolean enabled) {
                this.id = id;
        this.paymentType = paymentType;
        this.amount = amount;
        this.user = user;
        this.sessionBean = sessionBean;
        this.dateOfPayments = dateOfPayments;
        this.status = status;
        this.enabled = enabled;
    }
    
    ///////////////////////     methods     //////////////////////////
    
    

    
       
    ////////////////// getters setters, toString, etc////////////////////////

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public SessionBean getSessionBean() {
        return sessionBean;
    }

    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }
    
    
    

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    

    public Timestamp getDateOfPayments() {
        return dateOfPayments;
    }

    public void setDateOfPayments(Timestamp dateOfPayments) {
        this.dateOfPayments = dateOfPayments;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Payment{" + "id=" + id + ", paymentType=" + paymentType + ", amount=" + amount + ", user=" + user + ", session=" + session + ", status=" + status + ", dateOfPayments=" + dateOfPayments + ", enabled=" + enabled + '}';
    }

    
  
}