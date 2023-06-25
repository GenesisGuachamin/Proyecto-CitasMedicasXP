package proyecto;

import baseDatos.DatabaseConnection;
import controladores.ControladorLogin;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

        public static void main(String[] args) {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            databaseConnection.conectar();
            ControladorLogin controladorLogin = new ControladorLogin(databaseConnection);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Bienvenido al sistema de citas médicas");
            System.out.println("--------------------------------------");

            System.out.println("Ingrese su cédula:");
            String cedula = scanner.nextLine();

            System.out.println("Ingrese su contraseña:");
            String contrasena = scanner.nextLine();

            System.out.println("Seleccione el tipo de usuario:");
            System.out.println("1. Administrador");
            System.out.println("2. Médico");
            System.out.println("3. Salir");

            int opcion = scanner.nextInt();

            String tipoUsuario;
            switch (opcion) {
                case 1:
                    tipoUsuario = "administrador";
                    break;
                case 2:
                    tipoUsuario = "medico";
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    databaseConnection.desconectar();
                    return;
                default:
                    System.out.println("Opción inválida. Saliendo del sistema...");
                    databaseConnection.desconectar();
                    return;
            }

            Login login = new Login(cedula, contrasena, tipoUsuario);

            login.verificar(controladorLogin);

            databaseConnection.desconectar();
        }
    }


