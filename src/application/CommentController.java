package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import application.Doctor;

import java.sql.SQLException;
import java.util.List;

public class CommentController {

    @FXML
    private TextArea Comment;

    @FXML
    private TableColumn<Doctor, Integer> DocID;

    @FXML
    private TableColumn<Doctor, String> DocName;

    @FXML
    private TableColumn<Doctor, String> DocSpetiality;

    @FXML
    private TableView<Doctor> DocTable;

    @FXML
    private TextField DocId;

    @FXML
    private TableColumn<Feedback, Integer> CommentID;

    @FXML
    private TableView<Feedback> FeedbackTable;

    @FXML
    private TableColumn<Feedback, Integer> PatientID;

    @FXML
    private TableColumn<Feedback, String> comment;
    
    @FXML
    private TableColumn<Feedback, String> answer;

    @FXML
    private Button PostButton;

    private PatientDao dao;

    private DoctorDAO Docdao;
    
    private AppointmentDao AppDao;

    private FeedbackDao FeedbackDdoa;

    @FXML
    public void initialize() {
        // Initialize the TableColumn objects to associate with Doctor properties
        DocID.setCellValueFactory(new PropertyValueFactory<>("id"));
        DocName.setCellValueFactory(new PropertyValueFactory<>("name"));
        DocSpetiality.setCellValueFactory(new PropertyValueFactory<>("speciality"));
        
        CommentID.setCellValueFactory(new PropertyValueFactory<>("commentID"));
        PatientID.setCellValueFactory(new PropertyValueFactory<>("patientID")); // Use "patientId" with lowercase "i"
        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        answer.setCellValueFactory(new PropertyValueFactory<>("feedback"));

        try {
            List<Feedback> feedbacks = FeedbackDdoa.getAllfeedbacks();
            populateTableFeedback(feedbacks);

            List<Doctor> doctors = Docdao.getAllDoctors();
            populateTableDoctor(doctors);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void populateTableDoctor(List<Doctor> doctors) {
        ObservableList<Doctor> doctorList = FXCollections.observableArrayList(doctors);
        DocTable.setItems(doctorList);
    }

    void populateTableFeedback(List<Feedback> feedbacks) {
        ObservableList<Feedback> feedbackList = FXCollections.observableArrayList(feedbacks);
        FeedbackTable.setItems(feedbackList);
    }

    public CommentController() {
        this.dao = new PatientDao();
        this.Docdao = new DoctorDAO();
        this.AppDao = new AppointmentDao();
        this.FeedbackDdoa = new FeedbackDao();
    }

    @FXML
    void Post(ActionEvent event) {
        try {
            System.out.print("hi posting");
        	String commentText = Comment.getText();
            int doctorId = Integer.parseInt(DocId.getText());
            
            // Create a new Feedback object
            Feedback feedback = new Feedback(0, 1, doctorId, commentText, "");
            
            // Add feedback to the database
            FeedbackDdoa.addFeedback(feedback);
            
            // Clear the input fields
            Comment.clear();
            DocId.clear();
            
            // Refresh the feedback table
            List<Feedback> feedbacks = FeedbackDdoa.getAllfeedbacks();
            populateTableFeedback(feedbacks);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
