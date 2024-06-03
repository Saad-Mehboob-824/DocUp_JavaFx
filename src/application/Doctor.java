package application;

public class Doctor {
    private int id;
    private String password;
    private String name;
    private String cnic;
    private String speciality;

    // Constructor      
    public Doctor(int id, String password, String name, String cnic, String speciality) {
        this.id = id;
        this.setPassword(password);
        this.name = name;
        this.cnic = cnic;
        this.speciality = speciality;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    // Override toString method for easy printing
    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cnic='" + cnic + '\'' +
                ", speciality='" + speciality + '\'' +
                '}';
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
