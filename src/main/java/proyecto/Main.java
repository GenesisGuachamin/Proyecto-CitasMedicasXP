package proyecto;

import baseDatos.DatabaseConnection;
import controladores.ControladorLogin;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ControladorLogin controladorLogin = new ControladorLogin(databaseConnection);
        Login login = new Login();
        login.mostrarMenu(controladorLogin);
        }
    }
