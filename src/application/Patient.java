package application;

public class Patient {
    private int id;
    private String password;
    private String name;
    private String cnic;
    private String symptoms;
    private String medicine_purchased;

    // Constructor
    public Patient(int id, String password, String name, String cnic, String symptoms) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.cnic = cnic;
        this.symptoms = symptoms;
    }

    // Getter and Setter methods
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

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    // Override toString method for easy printing
    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cnic='" + cnic + '\'' +
                ", symptoms='" + symptoms + '\'' +
                '}';
    }

    public String getPassword() {
        // TODO Auto-generated method stub
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public String getMedicine_purchased() {
        return medicine_purchased;
    }

    public void setMedicine_purchased(String medicine_purchased) {
        this.medicine_purchased = medicine_purchased;
    }

}
