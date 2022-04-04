package model;
import java.util.*;

/**
 * 
 */
public class Training {

    /**
     * Default constructor
     */
    public Training() {
    }

    private int id;

    private String title;
    
    private String description;

    private Price price;

    private int duration;

    private int capacity;

    private boolean enabled;

    public Training(int id, String title, String description, Price price, int duration, int capacity, boolean enabled) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.capacity = capacity;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Training{" + "id=" + id + ", title=" + title + ", description=" + description + ", price=" + price + ", duration=" + duration + ", capacity=" + capacity + ", enabled=" + enabled + '}';
    }

   
    

    
    

}