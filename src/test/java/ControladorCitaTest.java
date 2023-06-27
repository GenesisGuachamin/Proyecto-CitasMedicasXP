import Usuarios.Cita;
import baseDatos.DatabaseConnection;
import controladores.ControladorCita;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class ControladorCitaTest {
    private ControladorCita controladorCita;
    private DatabaseConnection databaseConnection;
    private Connection connection;
    private PreparedStatement statement;

    @Before
    public void setUp() throws Exception {
        databaseConnection = Mockito.mock(DatabaseConnection.class);
        controladorCita = new ControladorCita(databaseConnection);

        connection = Mockito.mock(Connection.class);
        statement = Mockito.mock(PreparedStatement.class);

        Mockito.when(databaseConnection.getConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(anyString())).thenReturn(statement);
    }

    @Test
    public void testAgregarCita() throws SQLException {
        Cita cita = new Cita("1", LocalDateTime.now(), "Especialidad", "Médico", "Paciente");

        Mockito.when(statement.executeUpdate()).thenReturn(1);

        boolean exito = controladorCita.agregarCita(cita);

        Mockito.verify(databaseConnection, times(1)).closeStatement(statement);
        Mockito.verify(statement, times(1)).executeUpdate();

        assert (exito);
    }

    @Test
    public void testAgregarCitaError() throws SQLException {
        Cita cita = new Cita("1", LocalDateTime.now(), "Especialidad", "Médico", "Paciente");

        Mockito.when(statement.executeUpdate()).thenThrow(new SQLException("Error al agregar la cita"));

        boolean exito = controladorCita.agregarCita(cita);

        Mockito.verify(databaseConnection, times(1)).closeStatement(statement);
        Mockito.verify(statement, times(1)).executeUpdate();

        assert (!exito);
    }

    @After
    public void tearDown() throws Exception {
        controladorCita = null;
        databaseConnection = null;
        connection = null;
        statement = null;
    }
}
