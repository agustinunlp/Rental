package src.main.java.domain;

public class Bike {
    private String type;
    private String description; 
    private boolean available;
    
    public Bike(String type, String description, boolean available) {
        super();
        this.type = type;
        this.description = description;
        this.available = available;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

}
