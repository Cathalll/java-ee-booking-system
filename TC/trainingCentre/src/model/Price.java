package model;
import java.util.*;

/**
 * 
 */
public class Price {

    /**
     * Default constructor
     */
    public Price() {
    }

    private int id;

    private double amount;

    private String name;

    private boolean enabled;

    public Price(int id, double amount, String name, boolean enabled) {
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Price{" + "id=" + id + ", amount=" + amount + ", name=" + name + ", enabled=" + enabled + '}';
    }
    
    
    

}