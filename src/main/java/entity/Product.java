package entity;


public abstract class Product {
    private double price;
    private String model;

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;


    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


}
