package application;

public class Medicine {
    private int id;
    private String name;
    private String manufacturer;
    private double price;
    private int quantity;

    public Medicine(int id, String name, String manufacturer, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
    }

    
    public Medicine() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


	@Override
	public String toString() {
		return "Medicine [id=" + id + ", name=" + name + ", manufacturer=" + manufacturer + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}
    
    
}
