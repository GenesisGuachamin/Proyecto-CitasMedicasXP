package proyecto;

import baseDatos.DatabaseConnection;
import controladores.ControladorLogin;

import java.util.Scanner;

public class Login {
    private String cedula;
    private String contrasena;
    private String tipoUsuario;

    public Login(String cedula, String contrasena, String tipoUsuario) {
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.tipoUsuario = tipoUsuario;
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
    public void verificar(ControladorLogin controladorLogin) {
        boolean credencialesValidas = controladorLogin.verificarCredenciales(this);

        if (credencialesValidas) {
            System.out.println("Inicio de sesión exitoso.");
        } else {
            System.out.println("Credenciales inválidas. No se pudo iniciar sesión.");
        }
    }

}
