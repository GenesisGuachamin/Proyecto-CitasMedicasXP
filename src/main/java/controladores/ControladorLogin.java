package controladores;

import baseDatos.DatabaseConnection;
import proyecto.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControladorLogin {
    private DatabaseConnection databaseConnection;

    public ControladorLogin(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public boolean verificarCredenciales(Login login) {
        boolean credencialesValidas = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = databaseConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM administrador WHERE cedula = ? AND contraseña = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, login.getCedula());
            statement.setString(2, login.getContrasena());
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    credencialesValidas = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar las credenciales: " + e.getMessage());
        } finally {
            databaseConnection.closeResultSet(resultSet);
            databaseConnection.closeStatement(statement);
            databaseConnection.closeConnection(connection);
        }

        return credencialesValidas;
    }

    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ControladorLogin controladorLogin = new ControladorLogin(databaseConnection);
        Login login = new Login();
        login.setCedula("0123456789");
        login.setContrasena("contraseña1");
        login.setTipoUsuario("administrador");

        if (controladorLogin.verificarCredenciales(login)) {
            System.out.println("Credenciales válidas. Inicio de sesión exitoso.");
        } else {
            System.out.println("Credenciales inválidas. Inicio de sesión fallido.");
        }
    }
}
