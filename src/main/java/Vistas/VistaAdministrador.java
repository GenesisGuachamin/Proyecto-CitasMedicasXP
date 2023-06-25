package Vistas;

import Usuarios.Cita;
import Usuarios.Medico;
import Usuarios.Paciente;
import controladores.ControladorCita;
import controladores.ControladorMedico;
import controladores.ControladorPaciente;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class VistaAdministrador {
    private ControladorMedico controladorMedico;
    private ControladorPaciente controladorPaciente;
    private ControladorCita controladorCita;

    public VistaAdministrador(ControladorMedico controladorMedico, ControladorPaciente controladorPaciente,
                              ControladorCita controladorCita) {
        this.controladorMedico = controladorMedico;
        this.controladorPaciente = controladorPaciente;
        this.controladorCita = controladorCita;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;

        while (opcion != 4) {
            System.out.println("=== Menú del Administrador ===");
            System.out.println("1. Módulo Médico");
            System.out.println("2. Módulo Paciente");
            System.out.println("3. Módulo Citas");
            System.out.println("4. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mostrarMenuMedico();
                    break;
                case 2:
                    mostrarMenuPaciente();
                    break;
                case 3:
                    mostrarMenuCitas();
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
    }

    private void mostrarMenuMedico() {
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;

        while (opcion != 5) {
            System.out.println("=== Módulo Médico ===");
            System.out.println("1. Registrar Médico");
            System.out.println("2. Listar Médicos");
            System.out.println("3. Actualizar Médico");
            System.out.println("4. Eliminar Médico");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarMedico();
                    break;
                case 2:
                    buscarMedico();
                    break;
                case 3:
                    actualizarMedico();
                    break;
                case 4:
                    eliminarMedico();
                    break;
                case 5:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
    }

    private void mostrarMenuPaciente() {
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;

        while (opcion != 5) {
            System.out.println("=== Módulo Paciente ===");
            System.out.println("1. Registrar Paciente");
            System.out.println("2. Buscar Paciente");
            System.out.println("3. Actualizar Paciente");
            System.out.println("4. Eliminar Paciente");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarPaciente();
                    break;
                case 2:
                    buscarPaciente();
                    break;
                case 3:
                    actualizarPaciente();
                    break;
                case 4:
                    eliminarPaciente();
                    break;
                case 5:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
    }

    private void mostrarMenuCitas() {
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;

        while (opcion != 5) {
            System.out.println("=== Módulo Citas ===");
            System.out.println("1. Agendar Cita");
            System.out.println("2. Buscar Cita");
            System.out.println("3. Cancelar Cita");
            System.out.println("4. Volver al Menú Principal");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agendarCita();
                    break;
                case 2:
                    buscarCita();
                    break;

                case 3:
                    cancelarCita();
                    break;
                case 4:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
    }

    private void registrarMedico() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese los datos del médico:");
        System.out.print("Cédula: ");
        String cedula = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();
        scanner.nextLine(); // Consumir salto de línea
        Medico medico = new Medico(cedula, nombre, edad, contraseña);
        controladorMedico.agregarMedico(medico);
        System.out.println("Médico registrado exitosamente.");
    }

    private void buscarMedico() {

        List<Medico> medicos = controladorMedico.obtenerMedicos();

        if (!medicos.isEmpty()) {
            System.out.println("Datos de los médicos encontrados:");
            for (Medico medico : medicos) {
                System.out.println("Cédula: " + medico.getCedula());
                System.out.println("Nombre: " + medico.getNombre());
                System.out.println("Edad: " + medico.getEdad());
                System.out.println("-----------------------------");
            }
        } else {
            System.out.println("No se encontró ningún médico con la cédula proporcionada.");
        }
    }

    private void actualizarMedico() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cédula del médico a actualizar: ");
        String cedula = scanner.nextLine();

        Medico medico = controladorMedico.buscarMedico(cedula);

        if (medico != null) {
            System.out.println("Ingrese los nuevos datos del médico:");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Edad: ");
            int edad = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea
            medico.setNombre(nombre);
            medico.setEdad(edad);
            controladorMedico.actualizarMedico(medico);
            System.out.println("Médico actualizado exitosamente.");
        } else {
            System.out.println("No se encontró ningún médico con la cédula proporcionada.");
        }
    }

    private void eliminarMedico() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cédula del médico a eliminar: ");
        String cedula = scanner.nextLine();

        Medico medico = controladorMedico.buscarMedico(cedula);

        if (medico != null) {
            controladorMedico.eliminarMedico(cedula);
            System.out.println("Médico eliminado exitosamente.");
        } else {
            System.out.println("No se encontró ningún médico con la cédula proporcionada.");
        }
    }

    private void registrarPaciente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese los datos del paciente:");
        System.out.print("Cédula: ");
        String cedula = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea
        Paciente paciente = new Paciente(cedula, nombre, edad);
        controladorPaciente.crearPaciente(paciente);
        System.out.println("Paciente registrado exitosamente.");
    }

    private void buscarPaciente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cédula del paciente a buscar: ");
        String cedula = scanner.nextLine();

        Paciente paciente = controladorPaciente.obtenerPaciente(cedula);

        if (paciente != null) {
            System.out.println("Datos del paciente:");
            System.out.println("Cédula: " + paciente.getCedula());
            System.out.println("Nombre: " + paciente.getNombre());
            System.out.println("Edad: " + paciente.getEdad());
        } else {
            System.out.println("No se encontró ningún paciente con la cédula proporcionada.");
        }
    }

    private void actualizarPaciente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cédula del paciente a actualizar: ");
        String cedula = scanner.nextLine();

        Paciente paciente = controladorPaciente.obtenerPaciente(cedula);

        if (paciente != null) {
            System.out.println("Ingrese los nuevos datos del paciente:");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Edad: ");
            int edad = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea
            paciente.setNombre(nombre);
            paciente.setEdad(edad);
            controladorPaciente.actualizarPaciente(paciente);
            System.out.println("Paciente actualizado exitosamente.");
        } else {
            System.out.println("No se encontró ningún paciente con la cédula proporcionada.");
        }
    }

    private void eliminarPaciente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cédula del paciente a eliminar: ");
        String cedula = scanner.nextLine();

        Paciente paciente = controladorPaciente.obtenerPaciente(cedula);

        if (paciente != null) {
            controladorPaciente.eliminarPaciente(cedula);
            System.out.println("Paciente eliminado exitosamente.");
        } else {
            System.out.println("No se encontró ningún paciente con la cédula proporcionada.");
        }
    }

    private void agendarCita() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese los datos de la cita:");
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Fecha (Formato: Año/Mes/Día): ");
        String fechaString = scanner.nextLine();
        System.out.print("Hora (Formato: Hora:Minuto): ");
        String horaString = scanner.nextLine();

        // Obtener año, mes y día
        String[] fechaParts = fechaString.split("/");
        int year = Integer.parseInt(fechaParts[0]);
        int month = Integer.parseInt(fechaParts[1]);
        int day = Integer.parseInt(fechaParts[2]);

        // Obtener hora y minuto
        String[] horaParts = horaString.split(":");
        int hour = Integer.parseInt(horaParts[0]);
        int minute = Integer.parseInt(horaParts[1]);

        // Crear objeto LocalDateTime con la fecha y hora ingresadas
        LocalDateTime fecha = LocalDateTime.of(year, month, day, hour, minute);
        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine();
        System.out.print("Cédula del Médico: ");
        String cedulaMedico = scanner.nextLine();
        Medico medico = controladorMedico.buscarMedico(cedulaMedico);
        System.out.print("Cédula del Paciente: ");
        String cedulaPaciente = scanner.nextLine();
        Paciente paciente = controladorPaciente.obtenerPaciente(cedulaPaciente);
        Cita cita = new Cita(id, fecha, especialidad, medico.getNombre(), paciente.getNombre());
        if (medico != null && paciente != null) {
            controladorCita.agregarCita(cita);
            System.out.println("Cita agendada exitosamente.");
        } else {
            System.out.println("No se pudo agendar la cita. Verifique que el médico y el paciente existan.");
        }
    }

    private void buscarCita() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID de la cita a buscar: ");
        String id = scanner.nextLine();

        Cita cita = controladorCita.buscarCita(id);

        if (cita != null) {
            System.out.println("Datos de la cita:");
            System.out.println("ID: " + cita.getId());
            System.out.println("Fecha y Hora: " + cita.getFecha());
            System.out.println("Especialidad: " + cita.getEspecialidad());
            System.out.println("Médico: " + cita.getMedico());
            System.out.println("Paciente: " + cita.getPaciente());
        } else {
            System.out.println("No se encontró ninguna cita con el ID proporcionado.");
        }
    }


    private void cancelarCita() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID de la cita a cancelar: ");
        String id = scanner.nextLine();

        Cita cita = controladorCita.buscarCita(id);

        if (cita != null) {
            controladorCita.eliminarCita(id);
            System.out.println("Cita cancelada exitosamente.");
        } else {
            System.out.println("No se encontró ninguna cita con el ID proporcionado.");
        }
    }
}
