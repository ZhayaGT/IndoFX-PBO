/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package indofx.admin.config;

/**
 *
 * @author ROG
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection implements DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5433/db_indofx_desktop_model";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Diaz@1234";

    private Connection connection;

    @Override
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver JDBC PostgreSQL tidak ditemukan di Libraries.", e);
            }
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}