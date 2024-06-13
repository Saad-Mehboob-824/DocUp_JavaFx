package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientDao {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public PatientDao() {
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

    // Create a new patient
    // public void addPatient(Patient patient) throws SQLException {
    // String sql = "INSERT INTO patients (id, password, name, cnic, symptoms)
    // VALUES("
    // +patient.getId() + ','
    // +patient.getPassword()+','
    // +patient.getName()+','
    // +patient.getCnic()+','
    // +patient.getSymptoms()+");\r\n";
    //
    // connect();
    //
    // try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
    // statement.execute(sql);
    // } finally {
    // disconnect();
    // }
    // }

    public void addPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO patients (id, password, name, cnic, symptoms) VALUES (?, ?, ?, ?, ?)";

        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, patient.getId());
            statement.setString(2, patient.getPassword());
            statement.setString(3, patient.getName());
            statement.setString(4, patient.getCnic());
            statement.setString(5, patient.getSymptoms());
            statement.executeUpdate();
        } finally {
            disconnect();
        }
    }

    // Retrieve all patients
    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> listPatient = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        connect();

        try (Statement statement = jdbcConnection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String cnic = resultSet.getString("cnic");
                String symptoms = resultSet.getString("symptoms");

                Patient patient = new Patient(id, password, name, cnic, symptoms);
                listPatient.add(patient);
            }
        } finally {
            disconnect();
        }

        return listPatient;
    }

    // Retrieve a patient by ID
    public Patient getPatientById(int id) throws SQLException {
        Patient patient = null;
        String sql = "SELECT * FROM patients WHERE id = ?";
        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String password = resultSet.getString("password");
                    String name = resultSet.getString("name");
                    String cnic = resultSet.getString("cnic");
                    String symptoms = resultSet.getString("symptoms");

                    patient = new Patient(id, password, name, cnic, symptoms);
                }
            }
        } finally {
            disconnect();
        }

        return patient;
    }

    // Update a patient
    public boolean updatePatient(Patient patient) throws SQLException {
        String sql = "UPDATE patients SET password = ?, name = ?, cnic = ?, symptoms = ? WHERE id = ?";
        connect();

        boolean rowUpdated;
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, patient.getPassword());
            statement.setString(2, patient.getName());
            statement.setString(3, patient.getCnic());
            statement.setString(4, patient.getSymptoms());
            statement.setInt(5, patient.getId());

            rowUpdated = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }
        return rowUpdated;
    }

    public boolean deletePatient(int id) throws SQLException {
        String sql = "DELETE FROM patients WHERE id = ?";
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

    public String getMedicines(int patientId) throws SQLException {
        String medicine = "";
        String sql = "SELECT medicine_purchased FROM patients WHERE id = ?";
        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, patientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    medicine = resultSet.getString("medicine_purchased");
                }
            }
        } finally {
            disconnect();
        }

        return medicine;
    }

    public boolean storeMedicines(int patientId, String medicine) throws SQLException {
        String sql = "UPDATE patients SET medicine_purchased = ? WHERE id = ?";
        connect();

        boolean rowUpdated = false;
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, medicine);
            statement.setInt(2, patientId);
            rowUpdated = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }
        return rowUpdated;
    }

}
