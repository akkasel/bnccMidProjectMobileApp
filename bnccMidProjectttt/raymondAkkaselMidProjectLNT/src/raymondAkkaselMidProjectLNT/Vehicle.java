package raymondAkkaselMidProjectLNT;

public abstract class Vehicle {

    private String brand;
    private String name;
    private String license;
    private String type;
    private int topSpeed;
    private int gasCapacity;
    private int wheels;

    public Vehicle(String brand, String name, String license, String type, int topSpeed, int gasCapacity, int wheels){
        this.brand = brand;
        this.name = name;
        this.license = license;
        this.type = type;
        this.topSpeed = topSpeed;
        this.gasCapacity = gasCapacity;
        this.wheels = wheels;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public String getLicense() {
        return license;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public int getGasCapacity() {
        return gasCapacity;
    }

    public int getWheels() {
        return wheels;
    }
    
    public String getType(){
        return type;
    }
    
    public abstract void runFeature();

}
