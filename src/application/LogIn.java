package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LogIn {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public LogIn() {
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

    public void addID(int id) throws SQLException {
        deleteAll();
    	String sql = "INSERT INTO Login (userID) VALUES (?)";
        connect();

        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } finally {
            disconnect();
        }
    }

    public int getID() throws SQLException {
        int id = 0;
        String sql = "SELECT * FROM Login";
        connect();

        try (Statement statement = jdbcConnection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                id = resultSet.getInt("userID");
            }
        } finally {
            disconnect();
        }

        return id;
    }

    public boolean deleteAll() throws SQLException {
        String sql = "DELETE FROM Login";
        connect();

        boolean rowsDeleted;
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            rowsDeleted = statement.executeUpdate() > 0;
        } finally {
            disconnect();
        }
        return rowsDeleted;
    }
}
