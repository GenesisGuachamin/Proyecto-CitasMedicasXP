package baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private Connection connection;

    public void conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/ProyectoCitasMedicas";
            String username = "postgres";
            String password = "morlo";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador de la base de datos: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public void desconectar() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Desconexión exitosa de la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al desconectar de la base de datos: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
