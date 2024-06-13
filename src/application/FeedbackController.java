package application;

import java.sql.SQLException;
import java.util.List;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FeedbackController {

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
    private TextArea feedback;

    @FXML
    private TextField replycommentID;
    
    @FXML
    private Button back;
    
    FeedbackDao doa; 
    
    @FXML
    public void initialize() {
        // Initialize the TableColumn objects to associate with Doctor properties
    	CommentID.setCellValueFactory(new PropertyValueFactory<>("commentID"));
    	PatientID.setCellValueFactory(new PropertyValueFactory<>("patientID")); // Use "patientId" with lowercase "i"
    	comment.setCellValueFactory(new PropertyValueFactory<>("comment"));
    	answer.setCellValueFactory(new PropertyValueFactory<>("feedback"));
        try {
            List<Feedback> feedbacks = doa.getAllfeedbacks();
            populateTable(feedbacks);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Method to populate the table with a list of doctors
    void populateTable(List<Feedback> feedbacks) {
        ObservableList<Feedback> feedbackList = FXCollections.observableArrayList(feedbacks);
        FeedbackTable.setItems(feedbackList);
    }
    
    public FeedbackController() {
        this.doa = new FeedbackDao();

    }

    //This is reply for doctor
    @FXML
    void addFeedback(ActionEvent event) throws SQLException {
    	int addfeeback = Integer.parseInt(replycommentID.getText());
        String feedback1=feedback.getText();
    	
        doa.addDoctorFeedback(addfeeback, feedback1);
        
        List<Feedback> appointments = doa.getAllfeedbacks();
        populateTable(appointments);
        
        System.out.println("Feedback given successfully!");

    }

    @FXML
    void back(ActionEvent event) {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DoctorView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) back.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }

}
