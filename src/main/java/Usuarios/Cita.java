package Usuarios;

import java.time.LocalDateTime;

public class Cita {
    private String id;
    private LocalDateTime fecha;
    private String especialidad;
    private String medico;
    private String paciente;

    // Constructor
    public Cita(String id, LocalDateTime fecha, String especialidad, String medico, String paciente) {
        this.id = id;
        this.fecha = fecha;
        this.especialidad = especialidad;
        this.medico = medico;
        this.paciente = paciente;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }
    /*toString es innecesario*/

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }
}
