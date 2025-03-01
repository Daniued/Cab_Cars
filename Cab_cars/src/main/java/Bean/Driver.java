package Bean;

public class Driver {
    private int driverId;
    private String username;
    private String name;
    private String phone;
    private String license;
    private String vehicleType;
    private String plate;
    private String salt; // NEW: Added salt field

    // Constructors
    public Driver() { }

    public Driver(String username, String name, String phone, String license, String vehicleType, String plate, String salt) {
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.license = license;
        this.vehicleType = vehicleType;
        this.plate = plate;
        this.salt = salt;
    }

    // Getters and Setters
    public int getDriverId() {
        return driverId;
    }
    public void setDriverId(int driverId) {
        this.driverId = driverId;
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getLicense() {
        return license;
    }
    public void setLicense(String license) {
        this.license = license;
    }
    public String getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    public String getPlate() {
        return plate;
    }
    public void setPlate(String plate) {
        this.plate = plate;
    }
    public String getSalt() { // NEW: Getter for salt
        return salt;
    }
    public void setSalt(String salt) { // NEW: Setter for salt
        this.salt = salt;
    }
}
