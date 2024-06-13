package application;

public class Perscription {
	
	int perscriptionID;
	int doctorID;
	int patientID;
	String medicine;
	String dosage;
	String direction;
	String additionalInfo;
	
	
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Perscription() {
		super();
	}
	
	public Perscription(int perscriptionID, int doctorID, int patientID, String medicine, String dosage,String direction, String additionalInfo) {
		super();
		this.perscriptionID=perscriptionID;
		this.doctorID = doctorID;
		this.patientID = patientID;
		this.medicine = medicine;
		this.dosage = dosage;
		this.direction= direction;
		this.additionalInfo = additionalInfo;
	}

	public int getPerscriptionID() {
		return perscriptionID;
	}

	public void setPerscriptionID(int perscriptionID) {
		this.perscriptionID = perscriptionID;
	}

	public int getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	
	@Override
    public String toString() {
        return "perscription{" +
                "perscriptionID=" + perscriptionID +
                ", doctorID=" + doctorID +
                ", patientID=" + patientID +
                ", medicine=" + medicine +
                ", dosage=" + dosage +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
	
	
}
