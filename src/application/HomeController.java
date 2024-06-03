package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeController{

    @FXML
    private Button DoctorLogin;

    @FXML
    private Button PatientLogin;

    @FXML
    private Button Phamcy_LaboratoryLogin;

    @FXML
    void LoginDoctor(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) PatientLogin.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }    
    	
    }

    @FXML
    void LoginPatient(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) PatientLogin.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }

    @FXML
    void LoginPhamcy_Laboratory(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) PatientLogin.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }    

    }

}
