package controladores;

import Usuarios.Cita;
import baseDatos.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ControladorCita {
    private DatabaseConnection databaseConnection;

    public ControladorCita(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public boolean agregarCita(Cita cita) {
        boolean exito = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = databaseConnection.getConnection();
            String sql = "INSERT INTO cita (id, fecha, especialidad, medico, paciente) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, cita.getId());
            statement.setObject(2, cita.getFecha());
            statement.setString(3, cita.getEspecialidad());
            statement.setString(4, cita.getMedico());
            statement.setString(5, cita.getPaciente());
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                exito = true;
                System.out.println("Cita agregada exitosamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar la cita: " + e.getMessage());
        } finally {
            databaseConnection.closeStatement(statement);
        }

        return exito;
    }

    public List<Cita> obtenerCitas() {
        List<Cita> citas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = databaseConnection.getConnection();
            String sql = "SELECT * FROM cita";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                LocalDateTime fecha = resultSet.getObject("fecha", LocalDateTime.class);
                String especialidad = resultSet.getString("especialidad");
                String medico = resultSet.getString("medico");
                String paciente = resultSet.getString("paciente");
                Cita cita = new Cita(id, fecha, especialidad, medico, paciente);
                citas.add(cita);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las citas: " + e.getMessage());
        } finally {
            databaseConnection.closeResultSet(resultSet);
            databaseConnection.closeStatement(statement);
        }

        return citas;
    }
    public Cita buscarCita(String index) {
        Cita cita = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = databaseConnection.getConnection();
            String sql = "SELECT * FROM cita OFFSET ? LIMIT 1";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(index));
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String id = resultSet.getString("id");
                LocalDateTime fecha = resultSet.getObject("fecha", LocalDateTime.class);
                String especialidad = resultSet.getString("especialidad");
                String medico = resultSet.getString("medico");
                String paciente = resultSet.getString("paciente");
                cita = new Cita(id, fecha, especialidad, medico, paciente);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar la cita: " + e.getMessage());
        } finally {
            databaseConnection.closeResultSet(resultSet);
            databaseConnection.closeStatement(statement);
        }

        return cita;
    }

    public boolean actualizarCita(Cita cita) {
        boolean exito = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = databaseConnection.getConnection();
            String sql = "UPDATE cita SET fecha = ?, especialidad = ?, medico = ?, paciente = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, cita.getFecha());
            statement.setString(2, cita.getEspecialidad());
            statement.setString(3, cita.getMedico());
            statement.setString(4, cita.getPaciente());
            statement.setString(5, cita.getId());
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                exito = true;
                System.out.println("Cita actualizada exitosamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar la cita: " + e.getMessage());
        } finally {
            databaseConnection.closeStatement(statement);
        }

        return exito;
    }

    public boolean eliminarCita(String id) {
        boolean exito = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = databaseConnection.getConnection();
            connection.setAutoCommit(false); // Iniciar transacción

            // Confirmación de eliminación (opcional)
            // ...

            String sql = "DELETE FROM cita WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                exito = true;
                System.out.println("Cita eliminada exitosamente.");
                connection.commit(); // Confirmar transacción
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar la cita: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback(); // Revertir transacción en caso de error
                } catch (SQLException ex) {
                    System.out.println("Error al revertir la transacción: " + ex.getMessage());
                }
            }
        } finally {
            databaseConnection.closeStatement(statement);
            if (connection != null) {
                try {
                    connection.setAutoCommit(true); // Restaurar el modo de autocommit
                    databaseConnection.closeConnection(connection);
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }

        return exito;
    }

}
