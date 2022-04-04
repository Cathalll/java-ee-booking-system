package beans;
import java.util.*;

/**
 * 
 */
public class SessionBean {

    /**
     * Default constructor
     */
    public SessionBean() {
    }

    /**
     * 
     */
    private Long id;
    
    private String title;

    public SessionBean(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SessionBean{" + "id=" + id + ", title=" + title + '}';
    }
    
    

}