// package application;

// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.scene.control.*;
// import javafx.scene.control.cell.PropertyValueFactory;
// import javafx.scene.text.Text;

// import java.sql.SQLException;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.List;

// public class PatientController {

//     @FXML
//     private Button Book;

//     @FXML
//     private Button Book1;

//     @FXML
//     private TextField Book_DocID; // Doctor ID to Book Appointment

//     @FXML
//     private TextField Book_Time; // Time to Book the Appointment

//     @FXML
//     private TextField Cancel_AppID; // Appointment ID to cancel appointment

//     @FXML
//     private Button Cancel_Search;

//     @FXML
//     private Button Cancel_Search1;

//     @FXML
//     private TableColumn<Doctor, Integer> DocID;

//     @FXML
//     private TableColumn<Doctor, String> DocName;

//     @FXML
//     private Text DocNome; // Displays the Doctor name here after searching for cancel appointment

//     @FXML
//     private TableColumn<Doctor, String> DocSpeciality;

//     @FXML
//     private TableView<Doctor> DocTable;

//     @FXML
//     private Text PatientName; // Displays the patient name here after searching for cancel appointment

//     @FXML
//     private TextField Update_AppID1; // Field containing appointment ID to search for the update appointment

//     @FXML
//     private TextField Update_AppTime; // Displays the Appointment Time here after searching for update appointment

//     @FXML
//     private TextField Update_DocID; // Displays the Doctor ID here after searching for update appointment

//     @FXML
//     private Button Update_app;

//     @FXML
//     private Button cancel;

//     private AppointmentDao appointmentDao;
//     private DoctorDAO doctorDao;

//     @FXML
//     public void initialize() {
//         appointmentDao = new AppointmentDao();
//         doctorDao = new DoctorDAO();

//         // Initialize the TableColumn objects to associate with Doctor properties
//         DocID.setCellValueFactory(new PropertyValueFactory<>("id"));
//         DocName.setCellValueFactory(new PropertyValueFactory<>("name"));
//         DocSpeciality.setCellValueFactory(new PropertyValueFactory<>("speciality"));

//         // Load and populate doctors table
//         try {
//             List<Doctor> doctors = doctorDao.getAllDoctors();
//             populateTable(doctors);
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     // Method to populate the table with a list of doctors
//     private void populateTable(List<Doctor> doctors) {
//         ObservableList<Doctor> doctorList = FXCollections.observableArrayList(doctors);
//         DocTable.setItems(doctorList);
//     }

//     @FXML 
//     void BookAppointment(ActionEvent event) {
//         try {
//             int doctorId = Integer.parseInt(Book_DocID.getText());
//             LocalDateTime appointmentTime = LocalDateTime.parse(Book_Time.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//             //(int appointmentId, int patientId, int doctorId, LocalDateTime appointmentTime, String status)
//             Appointment appointment = new Appointment(0,1, doctorId, appointmentTime, "Scheduled");

//             appointmentDao.addAppointment(appointment);
//             showAlert("Appointment booked successfully!");

//         } catch (SQLException e) {
//             e.printStackTrace();
//             showAlert("Failed to book appointment!");
//         }
//     }

//     @FXML
//     void BookOnlineAppointment(ActionEvent event) {
//         // Implement online appointment booking logic here
//     }

//     @FXML
//     void CancelSearchDocByID(ActionEvent event) {
//         try {
//             int appointmentId = Integer.parseInt(Cancel_AppID.getText());
//             Appointment appointment = appointmentDao.getAppointmentById(appointmentId);

//             if (appointment != null) {
//                 // Display the related information
//                 PatientName.setText("Patient Name: " + appointment.getPatientId()); // Replace with actual patient name lookup
//                 DocNome.setText("Doctor Name: " + appointment.getDoctorId()); // Replace with actual doctor name lookup
//             } else {
//                 showAlert("Appointment not found!");
//             }

//         } catch (SQLException e) {
//             e.printStackTrace();
//             showAlert("Failed to search appointment!");
//         }
//     }

//     @FXML
//     void UpdateAppointment(ActionEvent event) {
//         try {
//             int appointmentId = Integer.parseInt(Update_AppID1.getText());
//             int doctorId = Integer.parseInt(Update_DocID.getText());
//             LocalDateTime appointmentTime = LocalDateTime.parse(Update_AppTime.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

//             Appointment appointment = new Appointment(appointmentId, 1, doctorId, appointmentTime, "Scheduled");

//             if (appointmentDao.updateAppointment(appointment)) {
//                 showAlert("Appointment updated successfully!");
//             } else {
//                 showAlert("Failed to update appointment!");
//             }

//         } catch (SQLException e) {
//             e.printStackTrace();
//             showAlert("Failed to update appointment!");
//         }
//     }

//     @FXML
//     void UpdateSearchDocByID(ActionEvent event) {
//         try {
//             int appointmentId = Integer.parseInt(Update_AppID1.getText());
//             Appointment appointment = appointmentDao.getAppointmentById(appointmentId);

//             if (appointment != null) {
//                 Update_DocID.setText(String.valueOf(appointment.getDoctorId()));
//                 Update_AppTime.setText(appointment.getAppointmentTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
//             } else {
//                 showAlert("Appointment not found!");
//             }

//         } catch (SQLException e) {
//             e.printStackTrace();
//             showAlert("Failed to search appointment!");
//         }
//     }

//     @FXML
//     void cancelAppointment(ActionEvent event) {
//         try {
//             int appointmentId = Integer.parseInt(Cancel_AppID.getText());

//             if (appointmentDao.deleteAppointment(appointmentId)) {
//                 showAlert("Appointment canceled successfully!");
//             } else {
//                 showAlert("Failed to cancel appointment!");
//             }

//         } catch (SQLException e) {
//             e.printStackTrace();
//             showAlert("Failed to cancel appointment!");
//         }
//     }

//     private void showAlert(String message) {
//         Alert alert = new Alert(Alert.AlertType.INFORMATION);
//         alert.setContentText(message);
//         alert.show();
//     }

//     public PatientController() {
//         this.appointmentDao = new AppointmentDao();
//         this.doctorDao = new DoctorDAO();
//     }
// }

package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.List;
import application.Doctor;
import application.Patient;
import application.PatientDao;
import application.DoctorDAO;

public class PatientController {

    @FXML
    private Button Book;

    @FXML
    private Button Book1;

    @FXML
    private TextField Book_DocID; // DoctorID TO Book Appointment

    @FXML
    private TextField Book_Time;// Time To Book The Appointment

    @FXML
    private TextField Cancel_AppID;// Appointment id to cancel appointment

    @FXML
    private Button Cancel_Search;

    @FXML
    private Button Cancel_Search1;

    @FXML
    private TableColumn<Doctor, Integer> DocID;

    @FXML
    private TableColumn<Doctor, String> DocName;

    @FXML
    private Text DocNome; // After Searching for cancel appointment it displays the Doctor name here

    @FXML
    private TableColumn<Doctor, String> DocSpetiality;

    @FXML
    private TableView<Doctor> DocTable;

    @FXML
    private Text PatientName;// After Searching for cancel appointment it displays the patient name here

    @FXML
    private TextField Update_AppID1;// field containing appointment id to search for the update appointment

    @FXML
    private TextField Update_AppTime;// After Searching for Update appointment it displays the Appointment Time here
                                     // and updates the id when update button is pressed

    @FXML
    private TextField Update_DocID;// After Searching for Update appointment it displays the Doctor id here and
                                   // updates the id when update button is pressed

    @FXML
    private Button Update_app;

    @FXML
    private Button cancel;

    private PatientDao dao;

    private DoctorDAO Docdao;

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

    // Method to populate the table with a list of doctors
    void populateTable(List<Doctor> doctors) {
        ObservableList<Doctor> doctorList = FXCollections.observableArrayList(doctors);
        DocTable.setItems(doctorList);
    }

    public PatientController() {
        this.dao = new PatientDao();
        this.Docdao = new DoctorDAO();

    }

    @FXML
    void BookAppointment(ActionEvent event) {

    }

    @FXML
    void BookOnlineAppointment(ActionEvent event) {

    }

    @FXML
    void CancelSeachDocByID(ActionEvent event) {

    }

    @FXML
    void UpdateAppointment(ActionEvent event) {

    }

    @FXML
    void UpdateSeachDocByID(ActionEvent event) {

    }

    @FXML
    void cancelAppointment(ActionEvent event) {

    }

}
//
//
//
//
//
//
//
//
//
//
//
//
//
//// ---------------------DocDao------------------------------
////// ---------PRINT
//// List<Patient> p1 = null;
//// try {
//// p1 = dao.getAllPatients();
//// } catch (SQLException e) {
//// e.printStackTrace();
//// }
////
//// for (Patient patient : p1) {
//// System.out.println("ID: " + patient.getId());
//// System.out.println("Password: " + patient.getPassword());
//// System.out.println("Name: " + patient.getName());
//// System.out.println("CNIC: " + patient.getCnic());
//// System.out.println("Symptoms: " + patient.getSymptoms());
//// System.out.println("---------------------------");
//// }
////// --------------INSERT
//// Patient p2 = new Patient(2, "1234", "Subhan", "3720196168977", "headache");
//// try {
//// dao.addPatient(p2);
//// } catch (SQLException e) {
//// // TODO Auto-generated catch block
//// e.printStackTrace();
//// }
////// --------------DELETE
//// try {
//// dao.deletePatient(2);
//// } catch (SQLException e) {
//// // TODO Auto-generated catch block
//// e.printStackTrace();
//// }
////// --------------UPDATE
//// Patient p4 = new Patient(1, "7385", "Subhan", "776756392384",
// "STOMACHACE");
//// try {
//// dao.updatePatient(p4);
//// } catch (SQLException e) {
//// // TODO Auto-generated catch block
//// e.printStackTrace();
//// }
////// ------------Find
//// try {
//// Patient p3 = dao.getPatientById(1);
//// if(p3!=null) {
//// System.out.println("ID: " + p3.getId());
//// System.out.println("Password: " + p3.getPassword());
//// System.out.println("Name: " + p3.getName());
//// System.out.println("CNIC: " + p3.getCnic());
//// System.out.println("Symptoms: " + p3.getSymptoms());
//// System.out.println("---------------------------");
////
//// }
//// } catch (SQLException e) {
//// // TODO Auto-generated catch block
//// e.printStackTrace();
//// }
//
//// ---------------------DocDao------------------------------
//// System.out.println("---------PRINT");
//// List<Doctor> d1 = null;
//// try {
//// d1 = Docdao.getAllDoctors();
//// } catch (SQLException e) {
//// e.printStackTrace();
//// }
////
//// for (Doctor doctor : d1) {
//// System.out.println("ID: " + doctor.getId());
//// System.out.println("Password: " + doctor.getPassword());
//// System.out.println("Name: " + doctor.getName());
//// System.out.println("CNIC: " + doctor.getCnic());
//// System.out.println("Speciality: " + doctor.getSpeciality());
//// System.out.println("---------------------------");
//// }
////
//// System.out.println("INSERT");
//// Doctor d2 = new Doctor(2, "1234", "John Doe", "1234567890123",
// "Cardiology");
//// try {
//// Docdao.addDoctor(d2);
//// } catch (SQLException e) {
//// e.printStackTrace();
//// }
////
//// System.out.println("DELETE");
//// try {
//// Docdao.deleteDoctor(2);
//// } catch (SQLException e) {
//// e.printStackTrace();
//// }
////
//// System.out.println("--------------UPDATE");
//// Doctor d4 = new Doctor(123, "7385", "Jane Doe", "9876543210987",
// "Neurology");
//// try {
//// Docdao.updateDoctor(d4);
//// } catch (SQLException e) {
//// e.printStackTrace();
//// }
////
//// System.out.println("------------Find");
//// try {
//// Doctor d3 = Docdao.getDoctorById(1);
//// if (d3!=null) {
//// System.out.println("ID: " + d3.getId());
//// System.out.println("Password: " + d3.getPassword());
//// System.out.println("Name: " + d3.getName());
//// System.out.println("CNIC: " + d3.getCnic());
//// System.out.println("Speciality: " + d3.getSpeciality());
//// System.out.println("---------------------------");
//// } else {
//// System.out.println("Doctor not found.");
//// }
//// } catch (SQLException e) {
//// e.printStackTrace();
//// }
