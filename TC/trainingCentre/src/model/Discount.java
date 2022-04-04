package model;
import java.util.*;

/**
 * 
 */
public class Discount {

    /**
     * Default constructor
     */
    public Discount() {
    }

 
    private int id;
    private int percent;
    private String name;
    private UserRole userRole;
  
    private boolean enabled;

    public Discount(int id, int percent, String name, UserRole userRole, boolean enabled) {
        this.id = id;
        this.percent = percent;
        this.name = name;
        this.userRole = userRole;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Discount{" + "id=" + id + ", percent=" + percent + ", name=" + name + ", userRole=" + userRole + ", enabled=" + enabled + '}';
    }

    

   
}