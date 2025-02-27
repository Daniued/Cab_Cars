package Bean;

public class TestBean {
    private int id;
    private String value;
    
    // Constructors
    public TestBean() { }
    
    public TestBean(String value) {
        this.value = value;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
