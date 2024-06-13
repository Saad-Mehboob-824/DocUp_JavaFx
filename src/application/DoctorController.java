package application;

 // Import the AppointmentDao class
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class DoctorController {
	
	private static DoctorController instance;

    @FXML
    private TableColumn<Appointment, Integer> AppoinmentID; 

    @FXML
    private TextField Cancel_AppID; // Corrected typos

    @FXML
    private TableColumn<Appointment, LocalDateTime> Date; 

    @FXML
    private Text docNome;

    @FXML
    private TableView<Appointment> AppoinmentTable; 

    @FXML
    private TextField NewDate;

    @FXML
    private Button perscription;

    @FXML
    private Button updateprofile;

    @FXML
    private Button viewfeedback;
    
    @FXML
    private TableColumn<Appointment, Integer> PatientID; 

    @FXML
    private Text patientName;

    @FXML
    private TableColumn<Appointment, String> Status;

    @FXML
    private TextField Update_AppID1; 

    @FXML
    private Button update_app;

    @FXML
    private Button cancel;
    
    @FXML
    private TextField meetinglink;
    
    private DoctorDAO doc;

    private AppointmentDao appointmentDao; 
    
    public static DoctorController getInstance()
    {
    	if(instance==null)
    	{
    		instance=new DoctorController();
    	}
    	return instance;
    }

    @FXML
    public void initialize() {
    	AppoinmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
    	PatientID.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        Date.setCellValueFactory(new PropertyValueFactory<>("appointmentTime"));
        Status.setCellValueFactory(new PropertyValueFactory<>("status"));
        try {
            List<Appointment> appointments = appointmentDao.getAllAppointments();
            populateTable(appointments);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void populateTable(List<Appointment> appointments) {
        ObservableList<Appointment> appoinmentList = FXCollections.observableArrayList(appointments);
        AppoinmentTable.setItems(appoinmentList);
    }
    
    public DoctorController() {
        this.doc = new DoctorDAO();
        this.appointmentDao = new AppointmentDao();

    }


    @FXML
    void GeneratePerscription(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PercriptionView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) perscription.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void Meet(ActionEvent event) {
    	meetinglink.setText("https://meet.google.com/mdq-pmpj-obx");
    }

    @FXML
    void UpdateAppointment(ActionEvent event) throws SQLException {
        int appointmentIdToUpdate = Integer.parseInt(Update_AppID1.getText());
        
        LocalDateTime newdate = LocalDateTime.parse(NewDate.getText());
        
        appointmentDao.updateAppointment(appointmentIdToUpdate, newdate);
        
        List<Appointment> appointments = appointmentDao.getAllAppointments();
        populateTable(appointments);
        
        System.out.println("Appointment updated successfully!");
    }

    @FXML
    void UpdateProfile(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateDoctor.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) updateprofile.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }


    @FXML
    void ViewFeedback(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FeedbackView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) viewfeedback.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }    
   
    }

    @FXML
    void cancelAppointment(ActionEvent event) throws SQLException {
        int appointmentIdToCancel = Integer.parseInt(Cancel_AppID.getText());
        
        appointmentDao.deleteAppointment(appointmentIdToCancel);
        
        List<Appointment> appointments = appointmentDao.getAllAppointments();
        populateTable(appointments);
        
        System.out.println("Appointment cancelled successfully!");
    }

}
