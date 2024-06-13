package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDao {
	
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public FeedbackDao() {
        this.jdbcURL = "jdbc:mysql://localhost:3306/new_schema";
        this.jdbcUsername = "root";
        this.jdbcPassword = "Saad_321";
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    // Create a new appointment
    public void addFeedback(Feedback feedback) throws SQLException {
        String sql = "INSERT INTO feedback (patient_id, doctor_id, comment,feedback) VALUES (?, ?, ?, ?)";
        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, feedback.getPatientID());
            statement.setInt(2, feedback.getDoctorID());
            statement.setString(3, feedback.getComment());
            statement.setString(4, feedback.getFeedback());
            statement.executeUpdate();
        } finally {
            disconnect();
        }
    }

    // Retrieve all appointments
    public List<Feedback> getAllfeedbacks() throws SQLException {
        List<Feedback> listFeedback = new ArrayList<>();
        String sql = "SELECT * FROM feedback";
        connect();

        try (Statement statement = jdbcConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int commentID = resultSet.getInt("commentID");
                int patientId = resultSet.getInt("patient_id");
                int doctorId = resultSet.getInt("doctor_id");
                String comment=resultSet.getString("comment");
                String feedback = resultSet.getString("feedback");

                Feedback feedbacks = new Feedback(commentID, patientId, doctorId, comment, feedback);
                listFeedback.add(feedbacks);
            }
        } finally {
            disconnect();
        }

        return listFeedback;
    }
    
    public void addDoctorFeedback(int commentID, String doctorFeedback) throws SQLException {
        String sql = "UPDATE feedback SET feedback = ? WHERE commentID = ?";
        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, doctorFeedback);
            statement.setInt(2, commentID);
            statement.executeUpdate();
        } finally {
            disconnect();
        }
    }


}
