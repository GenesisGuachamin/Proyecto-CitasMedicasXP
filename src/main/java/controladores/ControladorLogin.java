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
            String sql = "";
            if (login.getTipoUsuario().equals("administrador")) {
                sql = "SELECT COUNT(*) FROM administrador WHERE cedula = ? AND contraseña = ?";
            } else if (login.getTipoUsuario().equals("medico")) {
                sql = "SELECT COUNT(*) FROM medico WHERE cedula = ? AND contraseña = ?";
            }
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
            closeResultSet(resultSet);
            closeStatement(statement);
            closeConnection(connection);
        }

        return credencialesValidas;
    }

    private void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar el ResultSet: " + e.getMessage());
        }
    }

    private void closeStatement(PreparedStatement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar el PreparedStatement: " + e.getMessage());
        }
    }

    private void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}

