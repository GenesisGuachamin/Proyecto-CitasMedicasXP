package Usuarios;

public class Medico {
    private String cedula;
    private String nombre;
    private int edad;
    private String contraseña;

    // Constructor
    public Medico(String cedula, String nombre, int edad, String contraseña) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.contraseña = contraseña;
    }

    // Getters y setters
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
