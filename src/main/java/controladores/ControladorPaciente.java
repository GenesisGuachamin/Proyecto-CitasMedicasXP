package controladores;

import Usuarios.Paciente;
import baseDatos.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControladorPaciente {
    private DatabaseConnection databaseConnection;

    public ControladorPaciente(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public boolean crearPaciente(Paciente paciente) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean exito = false;

        try {
            connection = databaseConnection.getConnection();
            String sql = "INSERT INTO paciente (cedula, nombre, edad) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, paciente.getCedula());
            statement.setString(2, paciente.getNombre());
            statement.setInt(3, paciente.getEdad());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al crear el paciente: " + e.getMessage());
        } finally {
            databaseConnection.closeStatement(statement);
        }

        return exito;
    }

    public Paciente obtenerPaciente(String cedula) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Paciente paciente = null;

        try {
            connection = databaseConnection.getConnection();
            String sql = "SELECT * FROM paciente WHERE cedula = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, cedula);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("Nombre del Paciente");
                int edad = resultSet.getInt("Edad");
                paciente = new Paciente(cedula, nombre, edad);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el paciente: " + e.getMessage());
        } finally {
            databaseConnection.closeResultSet(resultSet);
            databaseConnection.closeStatement(statement);
        }

        return paciente;
    }

    public List<Paciente> obtenerTodosLosPacientes() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Paciente> pacientes = new ArrayList<>();

        try {
            connection = databaseConnection.getConnection();
            String sql = "SELECT * FROM paciente";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String cedula = resultSet.getString("Cédula del Paciente");
                String nombre = resultSet.getString("Nombre del Paciente");
                int edad = resultSet.getInt("Edad");
                Paciente paciente = new Paciente(cedula, nombre, edad);
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los pacientes: " + e.getMessage());
        } finally {
            databaseConnection.closeResultSet(resultSet);
            databaseConnection.closeStatement(statement);
        }

        return pacientes;
    }

    public boolean actualizarPaciente(Paciente paciente) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean exito = false;

        try {
            connection = databaseConnection.getConnection();
            String sql = "UPDATE paciente SET nombre = ?, edad = ? WHERE cedula = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, paciente.getNombre());
            statement.setInt(2, paciente.getEdad());
            statement.setString(3, paciente.getCedula());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el paciente: " + e.getMessage());
        } finally {
            databaseConnection.closeStatement(statement);
        }

        return exito;
    }

    public boolean eliminarPaciente(String cedula) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean exito = false;

        try {
            connection = databaseConnection.getConnection();
            String sql = "DELETE FROM paciente WHERE cedula = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, cedula);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                exito = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el paciente: " + e.getMessage());
        } finally {
            databaseConnection.closeStatement(statement);
        }

        return exito;
    }
}
