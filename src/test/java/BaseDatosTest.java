import static org.mockito.Mockito.*;
import java.sql.*;

import org.junit.*;
import org.mockito.*;

import baseDatos.DatabaseConnection;

public class BaseDatosTest {
    private DatabaseConnection databaseConnection;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        databaseConnection = new DatabaseConnection();
        connection = Mockito.mock(Connection.class);
        databaseConnection.setConnection(connection);
    }

    @Test
    public void testConectar() throws SQLException {
        Mockito.when(connection.isClosed()).thenReturn(false);

        databaseConnection.conectar();

        Mockito.verify(connection, times(1)).isClosed();
        Mockito.verify(connection, times(1)).close();

        databaseConnection.desconectar(); // Desconectar para verificar el cierre de la conexión
        Mockito.verify(connection, times(2)).close(); // Verificar que el método close() se llame nuevamente
    }


    @Test
    public void testDesconectar() throws SQLException {
        databaseConnection.desconectar();

        Mockito.verify(connection, times(1)).close();
        // Agrega más verificaciones si es necesario para asegurarte de que los métodos apropiados se llamen correctamente.
    }

    @After
    public void tearDown() throws Exception {
        databaseConnection = null;
        connection = null;
    }
}
