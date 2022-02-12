package Model;

public class Products {
    private int id;
    private String pName,pDescription;
    private double pPrice;

    public Products(int id, String pName, String pDescription, double pPrice, double providerLatitude, double providerLongitude) {
        this.id = id;
        this.pName = pName;
        this.pDescription = pDescription;
        this.pPrice = pPrice;
        this.providerLatitude = providerLatitude;
        this.providerLongitude = providerLongitude;
    }

    private double providerLatitude,providerLongitude;

    public Products() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public double getpPrice() {
        return pPrice;
    }

    public void setpPrice(double pPrice) {
        this.pPrice = pPrice;
    }

    public double getProviderLatitude() {
        return providerLatitude;
    }

    public void setProviderLatitude(double providerLatitude) {
        this.providerLatitude = providerLatitude;
    }

    public double getProviderLongitude() {
        return providerLongitude;
    }

    public void setProviderLongitude(double providerLongitude) {
        this.providerLongitude = providerLongitude;
    }
}