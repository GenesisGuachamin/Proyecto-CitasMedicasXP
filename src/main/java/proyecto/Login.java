package proyecto;

import baseDatos.DatabaseConnection;
import controladores.ControladorLogin;

import java.util.Scanner;

public class Login {
    private String cedula;
    private String contrasena;
    private String tipoUsuario;

    // Constructor vacío
    public Login() {
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void mostrarMenu(ControladorLogin controladorLogin) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inicio de Sesión");
        System.out.println("-----------------");

        System.out.print("Cédula: ");
        String cedula = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        Login login = new Login();
        login.setCedula(cedula);
        login.setContrasena(contrasena);

        if (controladorLogin.verificarCredenciales(login)) {
            System.out.println("Credenciales válidas. Inicio de sesión exitoso.");
        } else {
            System.out.println("Credenciales inválidas. Inicio de sesión fallido.");
        }
    }


}
