package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoctorDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public DoctorDAO() {
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

    // Create a new doctor
    public void addDoctor(Doctor doctor) throws SQLException {
        String sql = "INSERT INTO doctors (id, name, cnic, speciality) VALUES (?, ?, ?, ?)";
        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, doctor.getId());
            statement.setString(2, doctor.getName());
            statement.setString(3, doctor.getCnic());
            statement.setString(4, doctor.getSpeciality());
            statement.executeUpdate();
        } finally {
            disconnect();
        }
    }

    // Retrieve all doctors
    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> listDoctor = new ArrayList<>();
        String sql = "SELECT * FROM doctors";
        connect();

        try (Statement statement = jdbcConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String cnic = resultSet.getString("cnic");
                String speciality = resultSet.getString("speciality");

                Doctor doctor = new Doctor(id,password, name, cnic, speciality);
                listDoctor.add(doctor);
            }
        } finally {
            disconnect();
        }

        return listDoctor;
    }

    // Retrieve a doctor by ID
    public Doctor getDoctorById(int id) throws SQLException {
        Doctor doctor = null;
        String sql = "SELECT * FROM doctors WHERE id = ?";
        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String password = resultSet.getString("password");
                	String name = resultSet.getString("name");
                    String cnic = resultSet.getString("cnic");
                    String speciality = resultSet.getString("speciality");

                    doctor = new Doctor(id,password, name, cnic, speciality);
                }
            }
        } finally {
            disconnect();
        }

        return doctor;
    }

    // Update a doctor
    public boolean updateDoctor(Doctor doctor) throws SQLException {
        String sql = "UPDATE doctors SET password = ?, name = ?, cnic = ?, speciality = ? WHERE id = ?";
        connect();

        boolean rowUpdated;
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
        	statement.setString(1, doctor.getPassword());
            statement.setString(2, doctor.getName());
            statement.setString(3, doctor.getCnic());
            statement.setString(4, doctor.getSpeciality());
            statement.setInt(5, doctor.getId());

            rowUpdated = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }
        return rowUpdated;
    }

    // Delete a doctor by ID
    public boolean deleteDoctor(int id) throws SQLException {
        String sql = "DELETE FROM doctors WHERE id = ?";
        connect();

        boolean rowDeleted;
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }
        return rowDeleted;
    }
}
