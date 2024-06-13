package application;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateDoctorController {

    @FXML
    private Button Back;

    @FXML
    private Button Update;

    @FXML
    private TextField newCNIC;

    @FXML
    private TextField newID;

    @FXML
    private TextField newName;

    @FXML
    private TextField newSpecility;
    
    
    DoctorDAO doc;

    public UpdateDoctorController()
    {
    	this.doc = new DoctorDAO();
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
    void Update(ActionEvent event) throws SQLException {
    	int id=Integer.parseInt(newID.getText());
    	String name=newName.getText();
    	String cnic=newCNIC.getText();
    	String specility=newSpecility.getText();
    	doc.updateDoctor(id,name,cnic,specility);
    	System.out.println("Profile updated successfully");
    }

}
