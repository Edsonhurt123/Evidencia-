import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

import java.io.IOException;

public class Main {


    public static ArrayList<Doctor> ListaDoctores = new ArrayList<>();
    public static ArrayList<Paciente> ListaPacientes = new ArrayList<>();
    public static ArrayList<Cita> ListaCitas = new ArrayList<>();

    public static void main(String[] args) {

        Scanner Scanner = new Scanner(System.in);
        String usuario, contrasena;

        System.out.println("-------Bienvenido al Consultorio Medico-----");
        System.out.println("Ingresa tus crendenciales para iniciar sesion");
        System.out.println("Ingresa tu usuario:");
        usuario = Scanner.nextLine();
        System.out.println("Ingresa tu contraseña");
        contrasena = Scanner.nextLine();

        boolean UsuarioValido = validarCredenciales(usuario, contrasena);
        if (UsuarioValido) {
            cargarDatos();  // Cargar datos al iniciar
            menu();
        }
    }

    static Boolean validarCredenciales(String usuario, String contrasena) {
        if (usuario.equals("admin") && contrasena.equals("123")) {
            System.out.println("Inicio de sesion exitoso");
            return true;
        } else {
            System.out.println("Usuario o contraseña incorrectos");
            return false;
        }
    }

    // Métodos Doctores 
    static void altaMedico() {
        Scanner scanner = new Scanner(System.in);
        Doctor doctor = new Doctor();
        System.out.println("Ingresa el nombre del doctor:");
        doctor.nombre = scanner.nextLine();
        System.out.println("Ingresa la especialidad del doctor:");
        doctor.especialidad = scanner.nextLine();
        ListaDoctores.add(doctor);
        System.out.println("Doctor dado de alta exitosamente");
    }

    static void verDoctores() {
        System.out.println("-------Lista de Doctores-------");
        for (Doctor doctor : ListaDoctores) {
            System.out.println("Nombre: " + doctor.nombre);
            System.out.println("Especialidad: " + doctor.especialidad);
        }
    }

    // Métodos Pacientes
    static void altaPaciente() {
        Scanner scanner = new Scanner(System.in);
        Paciente paciente = new Paciente();
        System.out.println("Ingresa el nombre del paciente:");
        paciente.nombrePaciente = scanner.nextLine();
        System.out.println("Ingresa la edad del paciente:");
        paciente.edad = scanner.nextLine();
        ListaPacientes.add(paciente);
        System.out.println("Paciente dado de alta exitosamente");
    }

    static void verPacientes() {
        System.out.println("-------Lista de Pacientes-------");
        for (Paciente paciente : ListaPacientes) {
            System.out.println("Nombre: " + paciente.nombrePaciente);
            System.out.println("Edad: " + paciente.edad);
        }
    }

    //Métodos para Citas 
    static void crearCita() {
        Scanner scanner = new Scanner(System.in);
        Cita cita = new Cita();

        if (ListaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados. Primero da de alta un paciente.\n");
            return;
        }
        if (ListaDoctores.isEmpty()) {
            System.out.println("No hay doctores registrados. Primero da de alta un doctor.\n");
            return;
        }

        System.out.print("Ingresa el número de cita: ");
        cita.nCita = scanner.nextLine();
        System.out.print("Ingresa la fecha de la cita: ");
        cita.fecha = scanner.nextLine();
        System.out.print("Ingresa la hora de la cita: ");
        cita.hora = scanner.nextLine();

        // Seleccionar paciente
        System.out.println("\nSelecciona el paciente:");
        for (int i = 0; i < ListaPacientes.size(); i++) {
            System.out.println((i + 1) + ". " + ListaPacientes.get(i).nombrePaciente + ", " + ListaPacientes.get(i).edad + " años");
        }
        int opcionPaciente = scanner.nextInt();
        scanner.nextLine();
        cita.paciente = ListaPacientes.get(opcionPaciente - 1);

        // Seleccionar doctor
        System.out.println("\nSelecciona el doctor:");
        for (int i = 0; i < ListaDoctores.size(); i++) {
            System.out.println((i + 1) + ". Dr. " + ListaDoctores.get(i).nombre + " - " + ListaDoctores.get(i).especialidad);
        }
        int opcionDoctor = scanner.nextInt();
        scanner.nextLine();
        cita.doctor = ListaDoctores.get(opcionDoctor - 1);

        ListaCitas.add(cita);
        System.out.println("Cita dada de alta exitosamente");
    }

    static void verCitas() {
        if (ListaCitas.isEmpty()) {
            System.out.println("No hay citas registradas.\n");
            return;
        }
        System.out.println("-------Lista de Citas-------");
        for (Cita cita : ListaCitas) {
            System.out.println("Número de cita: " + cita.nCita);
            System.out.println("Fecha: " + cita.fecha + " | Hora: " + cita.hora);
            System.out.println("Paciente: " + cita.paciente.nombrePaciente + ", " + cita.paciente.edad + " años");
            System.out.println("Doctor: " + cita.doctor.nombre + " - " + cita.doctor.especialidad + "\n");
           
        }
    }

    //  Menú 
    static void menu() {
    Scanner scanner = new Scanner(System.in);
    Integer opcion = 0;

    while (opcion != 8) {  
        System.out.println("-------Menu-------");
        System.out.println("1. Dar alta a Doctor");
        System.out.println("2. Dar alta a Paciente");
        System.out.println("3. Crear Cita");
        System.out.println("4. Ver Doctores");
        System.out.println("5. Ver Pacientes");
        System.out.println("6. Ver Citas");
        System.out.println("7. Guardar datos en TXT");
        System.out.println("8. Salir");
        System.out.println("Selecciona una opcion:");
        opcion = scanner.nextInt();
        scanner.nextLine(); 

        switch (opcion) {
            case 1:
                altaMedico();
                break;
            case 2:
                altaPaciente();
                break;
            case 3:
                crearCita();
                break;
            case 4:
                verDoctores();
                break;
            case 5:
                verPacientes();
                break;
            case 6:
                verCitas();
                break;
            case 7:
                guardarDatosTXT();
                break;
            case 8:
                System.out.println("Saliendo del sistema...");
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }
}

    //  Guardar y cargar datos
    static void guardarDatosTXT() {
        try {
            // Guardar doctores
            FileWriter fwDoc = new FileWriter("doctores.txt");
            for (Doctor doc : ListaDoctores) {
                fwDoc.write(doc.nombre + ";" + doc.especialidad + "\n");
            }
            fwDoc.close();

            // Guardar pacientes
            FileWriter fwPac = new FileWriter("pacientes.txt");
            for (Paciente pac : ListaPacientes) {
                fwPac.write(pac.nombrePaciente + ";" + pac.edad + "\n");
            }
            fwPac.close();

            // Guardar citas
            FileWriter fwCit = new FileWriter("citas.txt");
            for (Cita cit : ListaCitas) {
                fwCit.write(cit.nCita + ";" + cit.fecha + ";" + cit.hora + ";" +
                        cit.paciente.nombrePaciente + ";" + cit.doctor.nombre + "\n");
            }
            fwCit.close();

            System.out.println("Datos guardados en archivos.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    static void cargarDatos() {
        try {
            // Cargar doctores
            File fileDoc = new File("doctores.txt");
            if (fileDoc.exists()) {
                Scanner sc = new Scanner(fileDoc);
                while (sc.hasNextLine()) {
                    String[] datos = sc.nextLine().split(";");
                    Doctor doc = new Doctor();
                    doc.nombre = datos[0];
                    doc.especialidad = datos[1];
                    ListaDoctores.add(doc);
                }
                sc.close();
            }

            // Cargar pacientes
            File filePac = new File("pacientes.txt");
            if (filePac.exists()) {
                Scanner sc = new Scanner(filePac);
                while (sc.hasNextLine()) {
                    String[] datos = sc.nextLine().split(";");
                    Paciente pac = new Paciente();
                    pac.nombrePaciente = datos[0];
                    pac.edad = datos[1];
                    ListaPacientes.add(pac);
                }
                sc.close();
            }

            // Cargar citas
            File fileCit = new File("citas.txt");
            if (fileCit.exists()) {
                Scanner sc = new Scanner(fileCit);
                while (sc.hasNextLine()) {
                    String[] datos = sc.nextLine().split(";");
                    Cita cit = new Cita();
                    cit.nCita = datos[0];
                    cit.fecha = datos[1];
                    cit.hora = datos[2];

                    // Buscar paciente y doctor por nombre
                    for (Paciente p : ListaPacientes) {
                        if (p.nombrePaciente.equals(datos[3])) {
                            cit.paciente = p;
                            break;
                        }
                    }
                    for (Doctor d : ListaDoctores) {
                        if (d.nombre.equals(datos[4])) {
                            cit.doctor = d;
                            break;
                        }
                    }
                    ListaCitas.add(cit);
                }
                sc.close();
            }

        } catch (IOException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }
}
