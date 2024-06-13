package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicStoreDao {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public MedicStoreDao() {
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

    // Create a new medicine
    public void addMedicine(Medicine medicine) throws SQLException {
        String sql = "INSERT INTO medicine (name, manufacturer, price, quantity) VALUES (?, ?, ?, ?)";
        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, medicine.getName());
            statement.setString(2, medicine.getManufacturer());
            statement.setDouble(3, medicine.getPrice());
            statement.setInt(4, medicine.getQuantity());
            statement.executeUpdate();
        } finally {
            disconnect();
        }
    }

    // Retrieve all medicines
    public List<Medicine> getAllMedicines() throws SQLException {
        List<Medicine> listMedicine = new ArrayList<>();
        String sql = "SELECT * FROM medicine";
        connect();

        try (Statement statement = jdbcConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String manufacturer = resultSet.getString("manufacturer");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");

                Medicine medicine = new Medicine(id, name, manufacturer, price, quantity);
                listMedicine.add(medicine);
            }
        } finally {
            disconnect();
        }

        return listMedicine;
    }

    // Retrieve a medicine by ID
    public Medicine getMedicineById(int id) throws SQLException {
        Medicine medicine = null;
        String sql = "SELECT * FROM medicine WHERE id = ?";
        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String manufacturer = resultSet.getString("manufacturer");
                    double price = resultSet.getDouble("price");
                    int quantity = resultSet.getInt("quantity");

                    medicine = new Medicine(id, name, manufacturer, price, quantity);
                }
            }
        } finally {
            disconnect();
        }

        return medicine;
    }

    // Update a medicine
    public void updateMedicine(Medicine medicine) throws SQLException {
        String sql = "UPDATE medicine SET name = ?, manufacturer = ?, price = ?, quantity = ? WHERE id = ?";
        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, medicine.getName());
            statement.setString(2, medicine.getManufacturer());
            statement.setDouble(3, medicine.getPrice());
            statement.setInt(4, medicine.getQuantity());
            statement.setInt(5, medicine.getId());
            statement.executeUpdate();
        } finally {
            disconnect();
        }
    }

    // Delete a medicine by ID
    public boolean deleteMedicine(int id) throws SQLException {
        String sql = "DELETE FROM medicine WHERE id = ?";
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
    
    public List<Medicine> getMedicinesByName(String name) throws SQLException {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT * FROM medicine WHERE name LIKE ?";
        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, "%" + name + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String medicineName = resultSet.getString("name");
                    String manufacturer = resultSet.getString("manufacturer");
                    double price = resultSet.getDouble("price");
                    int quantity = resultSet.getInt("quantity");

                    Medicine medicine = new Medicine(id, medicineName, manufacturer, price, quantity);
                    medicines.add(medicine);
                }
            }
        } finally {
            disconnect();
        }

        return medicines;
    }
}
