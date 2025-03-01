package Bean;

public class Customer {
    private int customerId;
    private String username;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String nic;
    private String salt; // NEW: Added salt field

    // Constructors
    public Customer() { }

    public Customer(String username, String name, String email, String phone, String address, String nic, String salt) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.nic = nic;
        this.salt = salt;
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getNic() {
        return nic;
    }
    public void setNic(String nic) {
        this.nic = nic;
    }
    public String getSalt() { // NEW: Getter for salt
        return salt;
    }
    public void setSalt(String salt) { // NEW: Setter for salt
        this.salt = salt;
    }
}
