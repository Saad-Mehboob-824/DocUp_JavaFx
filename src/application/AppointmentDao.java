package application;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public AppointmentDao() {
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
    public void addAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_time, status) VALUES (?, ?, ?, ?)";
        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, appointment.getPatientId());
            statement.setInt(2, appointment.getDoctorId());
            statement.setTimestamp(3, Timestamp.valueOf(appointment.getAppointmentTime()));
            statement.setString(4, appointment.getStatus());
            statement.executeUpdate();
        } finally {
            disconnect();
        }
    }

    // Retrieve all appointments
    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> listAppointment = new ArrayList<>();
        String sql = "SELECT * FROM appointments";
        connect();

        try (Statement statement = jdbcConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int appointmentId = resultSet.getInt("appointment_id");
                int patientId = resultSet.getInt("patient_id");
                int doctorId = resultSet.getInt("doctor_id");
                LocalDateTime appointmentTime = resultSet.getTimestamp("appointment_time").toLocalDateTime();
                String status = resultSet.getString("status");

                Appointment appointment = new Appointment(appointmentId, patientId, doctorId, appointmentTime, status);
                listAppointment.add(appointment);
            }
        } finally {
            disconnect();
        }

        return listAppointment;
    }

    // Retrieve an appointment by ID
    public Appointment getAppointmentById(int appointmentId) throws SQLException {
        Appointment appointment = null;
        String sql = "SELECT * FROM appointments WHERE appointment_id = ?";
        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, appointmentId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int patientId = resultSet.getInt("patient_id");
                    int doctorId = resultSet.getInt("doctor_id");
                    LocalDateTime appointmentTime = resultSet.getTimestamp("appointment_time").toLocalDateTime();
                    String status = resultSet.getString("status");

                    appointment = new Appointment(appointmentId, patientId, doctorId, appointmentTime, status);
                }
            }
        } finally {
            disconnect();
        }

        return appointment;
    }

    public void updateAppointment(int appointmentId, LocalDateTime newDate) throws SQLException {
        String sql = "UPDATE appointments SET appointment_time = ? WHERE appointment_id = ?";
        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setObject(1, newDate != null ? Timestamp.valueOf(newDate) : null, Types.TIMESTAMP);
            statement.setInt(2, appointmentId);
            statement.executeUpdate();
        } finally {
            disconnect();
        }
    }

    // Delete an appointment by ID
    public boolean deleteAppointment(int appointmentId) throws SQLException {
        String sql = "UPDATE appointments SET status= ? WHERE appointment_id = ?";
        connect();

        boolean rowDeleted;
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, "Cancelled");
            statement.setInt(2, appointmentId);
            rowDeleted = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }
        return rowDeleted;
    }
}





