package application;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PerscriptionController {

    @FXML
    private Button Back;

    @FXML
    private TextField DoctorID;

    @FXML
    private TextField PatientID;

    @FXML
    private TextArea medicineAdditionalInfo;

    @FXML
    private TextField medicineDirection;

    @FXML
    private TextField medicineDosage;

    @FXML
    private TextField medicineName;
    
    PerscriptionDao dao;
    Perscription newperscription;
    
    public PerscriptionController()
    {
    	this.dao=new PerscriptionDao();
    	this.newperscription=new Perscription();
    }

    @FXML
    void Back(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DoctorView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) Back.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }

    @FXML
    void Perscribe(ActionEvent event) throws SQLException {
    	int doctorid=Integer.parseInt(DoctorID.getText());
    	int patientid=Integer.parseInt(PatientID.getText());
    	String medicine=medicineName.getText();
    	String dosage=medicineDosage.getText();
    	String direction=medicineDirection.getText();
    	String additional=medicineAdditionalInfo.getText();
    	newperscription.setDoctorID(doctorid);
    	newperscription.setPatientID(patientid);
    	newperscription.setMedicine(medicine);
    	newperscription.setDosage(dosage);
    	newperscription.setDirection(direction);
    	newperscription.setAdditionalInfo(additional);
    	dao.addPerscription(newperscription);
    	System.out.println("Perscription added");
    }

}
