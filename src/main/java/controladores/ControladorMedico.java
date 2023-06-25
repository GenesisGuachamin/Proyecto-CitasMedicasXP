package controladores;

import Usuarios.Medico;
import baseDatos.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControladorMedico {
    private DatabaseConnection databaseConnection;

    public ControladorMedico(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public boolean agregarMedico(Medico medico) {
        boolean exito = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = databaseConnection.getConnection();
            String sql = "INSERT INTO medico (cedula, nombre, edad, contraseña) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, medico.getCedula());
            statement.setString(2, medico.getNombre());
            statement.setInt(3, medico.getEdad());
            statement.setString(4, medico.getContraseña());
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                exito = true;
                System.out.println("Medico agregado exitosamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar el medico: " + e.getMessage());
        } finally {
            databaseConnection.closeStatement(statement);
        }

        return exito;
    }

    public List<Medico> obtenerMedicos() {
        List<Medico> medicos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = databaseConnection.getConnection();
            String sql = "SELECT * FROM medico";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String cedula = resultSet.getString("cedula");
                String nombre = resultSet.getString("nombre");
                int edad = resultSet.getInt("edad");
                String contrasena = resultSet.getString("contraseña");
                Medico medico = new Medico(cedula, nombre, edad, contrasena);
                medicos.add(medico);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los medicos: " + e.getMessage());
        } finally {
            databaseConnection.closeResultSet(resultSet);
            databaseConnection.closeStatement(statement);
        }

        return medicos;
    }

    public boolean actualizarMedico(Medico medico) {
        boolean exito = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = databaseConnection.getConnection();
            String sql = "UPDATE medico SET nombre = ?, edad = ?, contraseña = ? WHERE cedula = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, medico.getNombre());
            statement.setInt(2, medico.getEdad());
            statement.setString(3, medico.getContraseña());
            statement.setString(4, medico.getCedula());
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                exito = true;
                System.out.println("Medico actualizado exitosamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el medico: " + e.getMessage());
        } finally {
            databaseConnection.closeStatement(statement);
        }

        return exito;
    }
    public Medico buscarMedico(String cedula) {
        Medico medico = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = databaseConnection.getConnection();
            String sql = "SELECT * FROM medico WHERE cedula = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, cedula);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int edad = resultSet.getInt("edad");
                String contrasena = resultSet.getString("contraseña");
                medico = new Medico(cedula, nombre, edad, contrasena);
            } else {
                System.out.println("No se encontró ningún médico con la cédula proporcionada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el médico: " + e.getMessage());
        } finally {
            databaseConnection.closeResultSet(resultSet);
            databaseConnection.closeStatement(statement);
        }

        return medico;
    }
    public boolean eliminarMedico(String cedula) {
        boolean exito = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = databaseConnection.getConnection();
            String sql = "DELETE FROM medico WHERE cedula = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, cedula);
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                exito = true;
                System.out.println("Medico eliminado exitosamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el medico: " + e.getMessage());
        } finally {
            databaseConnection.closeStatement(statement);
        }

        return exito;
    }
}
