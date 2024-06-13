//package application;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
//import java.util.List;
//import java.util.Scanner;
//
//import application.Doctor;
//import application.Appointment;
//import application.Patient;
//import application.PatientDao;
//import application.DoctorDAO;
//import application.AppointmentDao;
//
//public class PatientController {
//
//    @FXML
//    private Button Book;
//
//    @FXML
//    private Button Book1;
//
//    @FXML
//    private TextField Book_DocID; // DoctorID TO Book Appointment
//
//    @FXML
//    private TextField Book_Time;// Time To Book The Appointment
//
//    @FXML
//    private TextField Cancel_AppID;// Appointment id to cancel appointment
//
//    @FXML
//    private Button Cancel_Search;
//
//    @FXML
//    private Button Cancel_Search1;
//
//    @FXML
//    private Button MadicalStore;
//    
//    @FXML
//    private TableColumn<Doctor, Integer> DocID;
//
//    @FXML
//    private TableColumn<Doctor, String> DocName;
//
//    @FXML
//    private TableColumn<Doctor, String> DocSpetiality;
//
//    @FXML
//    private TableView<Doctor> DocTable;
//
//    @FXML
//    private Text DocNome; // After Searching for cancel appointment it displays the Doctor name here   
//
//    @FXML
//    private Text PatientName;// After Searching for cancel appointment it displays the patient name here
//
//    @FXML
//    private TextField Update_AppID1;// field containing appointment id to search for the update appointment
//
//    @FXML
//    private TextField Update_AppTime;// After Searching for Update appointment it displays the Appointment Time here
//
//    @FXML
//    private TextField Update_DocID;// After Searching for Update appointment it displays the Doctor id here and
//                                   // updates the id when update button is pressed
//
//    @FXML
//    private Button Update_app;
//
//    @FXML
//    private Button cancel;
//    
//    private PatientDao dao;
//
//    private DoctorDAO Docdao;
//    
//    private AppointmentDao AppDao;
//
//    private LogIn Login;
//
//    private Patient patient;
//    
//    @FXML
//    public void initialize() {
//        // Initialize the TableColumn objects to associate with Doctor properties
//        DocID.setCellValueFactory(new PropertyValueFactory<>("id"));
//        DocName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        DocSpetiality.setCellValueFactory(new PropertyValueFactory<>("speciality"));
//        try {
//            List<Doctor> doctors = Docdao.getAllDoctors();
//            populateTable(doctors);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    void populateTable(List<Doctor> doctors) {
//        ObservableList<Doctor> doctorList = FXCollections.observableArrayList(doctors);
//        DocTable.setItems(doctorList);
//    }
//    
//   
//
//    public PatientController() throws SQLException {
//        this.dao = new PatientDao();
//        this.Docdao = new DoctorDAO();
//        this.AppDao = new AppointmentDao();        
//        this.Login = new LogIn();        
//        
//        patient = dao.getPatientById(Login.getID());
//        Login.deleteAll();
//        System.out.print(patient.toString());
//    }
//
//
//
//    /*    @FXML
//    void BookAppointment(ActionEvent event) {
//    	int Patientid = 1;
//    	int DocID = Integer.parseInt(Book_DocID.getText());
//    	String time = Book_Time.getText();
//    	LocalDateTime appointmentTime;
//    	//(int appointmentId, int patientId, int doctorId, LocalDateTime appointmentTime, String status)
//    	Appointment p1 = new Appointment(1,Patientid,DocID,appointmentTime ,"Scheduled");
//    	AppDao.addAppointment(p1);
//    	
//    }
//*/
//    @FXML
//    void BookAppointment(ActionEvent event) throws SQLException {
//        int Patientid = 1;
//        int DocID = Integer.parseInt(Book_DocID.getText());
//        String time = Book_Time.getText();
//        
//        // Define the pattern for the date and time string
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//        
//        // Parse the time string to a LocalDateTime object
//        LocalDateTime appointmentTime = LocalDateTime.parse(time);
//                
//        //(int appointmentId, int patientId, int doctorId, LocalDateTime appointmentTime, String status)
//        Appointment p1 = new Appointment(11, Patientid, DocID, appointmentTime, "Scheduled");
//        AppDao.addAppointment(p1);
//    }
//
//    // Helper method to show an alert dialog
//
//    private void showAlert(String title, String content) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(content);
//        alert.showAndWait();
//    }
//    
//
//    @FXML
//    void BookOnlineAppointment(ActionEvent event) throws SQLException {
//        int Patientid = 1;
//        int DocID = Integer.parseInt(Book_DocID.getText());
//        String time = Book_Time.getText();
//               
//        // Parse the time string to a LocalDateTime object
//
//    	LocalDateTime appointmentTime = LocalDateTime.parse(time);
//      
//        //(int appointmentId, int patientId, int doctorId, LocalDateTime appointmentTime, String status)
//        Appointment p1 = new Appointment(12, Patientid, DocID, appointmentTime, "Online Scheduled");
//        AppDao.addAppointment(p1);
//
//    }
//
//
//    @FXML
//    void CancelSeachDocByID(ActionEvent event) throws SQLException {
//    	int id = Integer.parseInt(Cancel_AppID.getText());
//    	Appointment p1 = AppDao.getAppointmentById(id);
//    	if(p1 != null) {
//    		PatientName.setText("Patient id - "+p1.getPatientId());
//    		DocNome.setText("Doctoe id - "+p1.getDoctorId());
//    	}else {
//    		Cancel_AppID.setStyle("-fx-background-color: red;");
//    	}
//    }
//
//
//    @FXML
//    void UpdateAppointment(ActionEvent event) throws NumberFormatException, SQLException {
//    	Appointment p1 = AppDao.getAppointmentById(Integer.parseInt(Update_AppID1.getText()));
//    	p1.setDoctorId(Integer.parseInt(Update_DocID.getText()));
//    	LocalDateTime newdate = LocalDateTime.parse(Update_AppTime.getText());;
//    	p1.setAppointmentTime(newdate);
//    	int id = p1.getAppointmentId();
//    	AppDao.updateAppointment(id, newdate);
//    	    }
//
//
//    @FXML
//    void UpdateSeachDocByID(ActionEvent event) throws SQLException {
//    	int id = Integer.parseInt(Update_AppID1.getText());
//    	Appointment p1 = AppDao.getAppointmentById(id);
//    	if(p1 != null) {
//    		Update_DocID.setText("" + p1.getDoctorId());
//    		Update_AppTime.setText(""+ p1.getAppointmentTime());
//    		
//    	}else {
//    		Cancel_AppID.setStyle("-fx-background-color: red;");
//    	}
//    }
//    
//
//
//    @FXML
//    void cancelAppointment(ActionEvent event) throws SQLException {
//    	Appointment p1 = AppDao.getAppointmentById(Integer.parseInt(Cancel_AppID.getText()));
//    	int id = p1.getAppointmentId();;
//    	boolean b1 = AppDao.deleteAppointment(id);
//    	System.out.print(b1);
//    }
//    
//
//    @FXML
//    void Comment(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("CommentView.fxml"));
//            Parent root = loader.load();
//            
//            // Get the current stage using the event source
//            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//            
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    void MadicalStore(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("MedicStore.fxml"));
//            Parent root = loader.load();
//            
//            // Get the current stage using the event source
//            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//            
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}
package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class PatientController {

    @FXML
    private Button Book;

    @FXML
    private Button Book1;

    @FXML
    private TextField Book_DocID; // DoctorID to Book Appointment

    @FXML
    private TextField Book_Time; // Time to Book the Appointment

    @FXML
    private TextField Cancel_AppID; // Appointment id to cancel appointment

    @FXML
    private Button Cancel_Search;

    @FXML
    private Button Cancel_Search1;

    @FXML
    private Button MadicalStore;

    @FXML
    private TableColumn<Doctor, Integer> DocID;

    @FXML
    private TableColumn<Doctor, String> DocName;

    @FXML
    private TableColumn<Doctor, String> DocSpetiality;

    @FXML
    private TableView<Doctor> DocTable;

    @FXML
    private Text DocNome; // Display the Doctor name here after searching for cancel appointment

    @FXML
    private Text PatientName; // Display the patient name here after searching for cancel appointment

    @FXML
    private TextField Update_AppID1; // Field containing appointment id to search for the update appointment

    @FXML
    private TextField Update_AppTime; // Display the Appointment Time here after searching for Update appointment

    @FXML
    private TextField Update_DocID; // Display and update the Doctor id here when update button is pressed

    @FXML
    private Button Update_app;

    @FXML
    private Button cancel;

    private PatientDao dao;
    private DoctorDAO Docdao;
    private AppointmentDao AppDao;
    private LogIn Login;
    private Patient patient;

    @FXML
    public void initialize() {
        // Initialize the TableColumn objects to associate with Doctor properties
        DocID.setCellValueFactory(new PropertyValueFactory<>("id"));
        DocName.setCellValueFactory(new PropertyValueFactory<>("name"));
        DocSpetiality.setCellValueFactory(new PropertyValueFactory<>("speciality"));
        try {
            List<Doctor> doctors = Docdao.getAllDoctors();
            populateTable(doctors);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void populateTable(List<Doctor> doctors) {
        ObservableList<Doctor> doctorList = FXCollections.observableArrayList(doctors);
        DocTable.setItems(doctorList);
    }

    public PatientController() throws SQLException {
        this.dao = new PatientDao();
        this.Docdao = new DoctorDAO();
        this.AppDao = new AppointmentDao();
        this.Login = new LogIn();

        patient = dao.getPatientById(Login.getID());
        Login.deleteAll();
        System.out.print(patient.toString());
    }

    @FXML
    void BookAppointment(ActionEvent event) {
        try {
            int Patientid = 1;
            int DocID = Integer.parseInt(Book_DocID.getText());
            String time = Book_Time.getText();

            // Define the pattern for the date and time string
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

            // Parse the time string to a LocalDateTime object
            LocalDateTime appointmentTime = LocalDateTime.parse(time, formatter);

            Appointment p1 = new Appointment(11, patient.getId(), DocID, appointmentTime, "Scheduled");
            AppDao.addAppointment(p1);

            showAlert("Success", "Appointment booked successfully!");
        } catch (DateTimeParseException e) {
            showAlert("Error", "Invalid date and time format. Please use 'yyyy-MM-dd'T'HH:mm:ss'.");
        } catch (SQLException e) {
            showAlert("Error", "An error occurred while booking the appointment.");
            e.printStackTrace();
        }
    }

    @FXML
    void BookOnlineAppointment(ActionEvent event) {
        try {
            int Patientid = 1;
            int DocID = Integer.parseInt(Book_DocID.getText());
            String time = Book_Time.getText();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime appointmentTime = LocalDateTime.parse(time, formatter);

            Appointment p1 = new Appointment(12, patient.getId(), DocID, appointmentTime, "Online Scheduled");
            AppDao.addAppointment(p1);

            showAlert("Success", "Online appointment booked successfully!");
        } catch (DateTimeParseException e) {
            showAlert("Error", "Invalid date and time format. Please use 'yyyy-MM-dd'T'HH:mm:ss'.");
        } catch (SQLException e) {
            showAlert("Error", "An error occurred while booking the online appointment.");
            e.printStackTrace();
        }
    }

    @FXML
    void CancelSeachDocByID(ActionEvent event) {
        try {
            int id = Integer.parseInt(Cancel_AppID.getText());
            Appointment p1 = AppDao.getAppointmentById(id);
            if (p1 != null) {
                PatientName.setText("Patient id - " + p1.getPatientId());
                DocNome.setText("Doctor id - " + p1.getDoctorId());
            } else {
                Cancel_AppID.setStyle("-fx-background-color: red;");
                showAlert("Error", "No appointment found with the given ID.");
            }
        } catch (SQLException e) {
            showAlert("Error", "An error occurred while searching for the appointment.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid appointment ID format.");
        }
    }

    @FXML
    void UpdateAppointment(ActionEvent event) {
        try {
            Appointment p1 = AppDao.getAppointmentById(Integer.parseInt(Update_AppID1.getText()));
            p1.setDoctorId(Integer.parseInt(Update_DocID.getText()));
            LocalDateTime newdate = LocalDateTime.parse(Update_AppTime.getText(),
                    DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            p1.setAppointmentTime(newdate);
            int id = p1.getAppointmentId();
            AppDao.updateAppointment(id, newdate);

            showAlert("Success", "Appointment updated successfully!");
        } catch (SQLException e) {
            showAlert("Error", "An error occurred while updating the appointment.");
            e.printStackTrace();
        } catch (NumberFormatException | DateTimeParseException e) {
            showAlert("Error", "Invalid input format.");
        }
    }

    @FXML
    void UpdateSeachDocByID(ActionEvent event) {
        try {
            int id = Integer.parseInt(Update_AppID1.getText());
            Appointment p1 = AppDao.getAppointmentById(id);
            if (p1 != null) {
                Update_DocID.setText("" + p1.getDoctorId());
                Update_AppTime.setText("" + p1.getAppointmentTime());
            } else {
                Cancel_AppID.setStyle("-fx-background-color: red;");
                showAlert("Error", "No appointment found with the given ID.");
            }
        } catch (SQLException e) {
            showAlert("Error", "An error occurred while searching for the appointment.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid appointment ID format.");
        }
    }

    @FXML
    void cancelAppointment(ActionEvent event) {
        try {
            Appointment p1 = AppDao.getAppointmentById(Integer.parseInt(Cancel_AppID.getText()));
            int id = p1.getAppointmentId();
            boolean b1 = AppDao.deleteAppointment(id);
            if (b1) {
                showAlert("Success", "Appointment cancelled successfully!");
            } else {
                showAlert("Error", "Failed to cancel the appointment.");
            }
        } catch (SQLException e) {
            showAlert("Error", "An error occurred while cancelling the appointment.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid appointment ID format.");
        }
    }

    @FXML
    void Comment(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CommentView.fxml"));
            Parent root = loader.load();

            // Get the current stage using the event source
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert("Error", "Failed to load the comment view.");
            e.printStackTrace();
        }
    }

    @FXML
    void MadicalStore(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MedicStore.fxml"));
            Parent root = loader.load();

            // Get the current stage using the event source
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
