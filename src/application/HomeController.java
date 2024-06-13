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

public class HomeController {

    @FXML
    private TextField LogInId;

    @FXML
    private Button Login;

    @FXML
    private TextField LoginPassword;

    private PatientDao dao;

    private DoctorDAO Docdao;

    private AppointmentDao AppDao;

    private LogIn LogInDao;

    public HomeController() {
        this.dao = new PatientDao();
        this.Docdao = new DoctorDAO();
        this.AppDao = new AppointmentDao();
        this.LogInDao = new LogIn();
    }

    public static int extractId(String text) {
        String[] parts = text.split("-");
        return Integer.parseInt(parts[1]);
    }

    public static String extractType(String text) {
        String[] parts = text.split("-");
        return parts[0];
    }

    void LoginDoctor() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DoctorView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) Login.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void LoginPatient() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) Login.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Login(ActionEvent event) throws SQLException {
        int id = extractId(LogInId.getText());
        String type = extractType(LogInId.getText());
        String password = LoginPassword.getText();

        // Use .equals() for string comparison
        if (type.equalsIgnoreCase("PATIENT")) {

            Patient p1 = dao.getPatientById(id);
            if (p1 == null) {
                LogInId.setStyle("-fx-background-color: red;");
                LoginPassword.setStyle("-fx-background-color: red;");
            } else if (password.equalsIgnoreCase(p1.getPassword())) {
                System.out.println(id);
                LogInDao.addID(id);
                LoginPatient();
            } else {
                LoginPassword.setStyle("-fx-background-color: red;");
            }

        } else if (type.equalsIgnoreCase("DOC")) {
            Doctor d1 = Docdao.getDoctorById(id);
            Patient p1 = dao.getPatientById(id);
            if (d1 == null) {
                LogInId.setStyle("-fx-background-color: red;");
                LoginPassword.setStyle("-fx-background-color: red;");
            } else if (password.equalsIgnoreCase(d1.getPassword())) {
                System.out.println(id);
                LoginDoctor();
            } else {
                LoginPassword.setStyle("-fx-background-color: red;");
            }
        } else if (type.equalsIgnoreCase("PHARMACY")) {
        } else {
            LogInId.setStyle("-fx-background-color: red;");
            LoginPassword.setStyle("-fx-background-color: red;");
        }
    }

}
