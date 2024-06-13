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

public class PerscriptionDao {
	
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public PerscriptionDao() {
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
    public void addPerscription(Perscription perscription) throws SQLException {
        String sql = "INSERT INTO prescription (doctorID, patientID, medicine, dosage, direction, additionalInfo) VALUES (?, ?, ?, ?, ?, ?)";
        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, perscription.getDoctorID());
            statement.setInt(2, perscription.getPatientID());
            statement.setString(3, perscription.getMedicine());
            statement.setString(4, perscription.getDosage());
            statement.setString(5, perscription.getDirection());
            statement.setString(6, perscription.getAdditionalInfo());
            statement.executeUpdate();
        } finally {
            disconnect();
        }
    }

}
