
package com.mycompany.sistemaclinica;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemaClinica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Clinica clinicaS = new Clinica();
        int choice;

        do {
            System.out.println("\n----- Menú Principal. Sistema Clínica -----");
            System.out.println("1. Gestionar Registro Histórico de Expedientes");
            System.out.println("2. Gestionar Cola de Atención Activa");
            System.out.println("3. Salir del Sistema");
            System.out.print("Por favor ingrese una opción: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); //Saltara de linea para ir scaneando todas las respuestas ingresadas

                switch (choice) {
                    case 1:
                        handleHistoricalRecords(scanner, clinicaS);
                        break;
                    case 2:
                        handleActiveQueue(scanner, clinicaS);
                        break;
                    case 3:
                        System.out.println("Saliendo del sistema.... ¡Gracias!");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, intente de nuevo ingresando una opcion permitida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine(); // Consume la entrada invalida y espera
                choice = 0; //Se establece un valor que mantenga el bucle en ejecucion hasta que se ingrese una opcion valida
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void handleHistoricalRecords(Scanner scanner, Clinica clinica) {
        int subChoice;
        do {
            System.out.println("\n----- Gestión de Registro Histórico de Expedientes -----");
            System.out.println("1. Registrar un nuevo expediente");
            System.out.println("2. Buscar un registro por ID");
            System.out.println("3. Listar todos los registros");
            System.out.println("4. Regresar al menú principal");
            System.out.print("Por favor ingrese una opción: ");

            try {
                subChoice = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea

                switch (subChoice) {
                    case 1:
                        System.out.print("Nombre del paciente: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Sexo [M/F]: ");
                        String sexo = scanner.nextLine();
                        System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
                        String dobString = scanner.nextLine();
                        LocalDate dob = null;
                        try {
                            dob = LocalDate.parse(dobString);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha inválido. Por favor asegurese de usar: AAAA-MM-DD.");
                            break;
                        }
                        System.out.print("Diagnóstico: ");
                        String diagnostico = scanner.nextLine();
                        clinica.registrarHistorialPaciente(nombre, sexo, dob, diagnostico);
                        break;
                    case 2:
                        System.out.print("Ingrese el ID del expediente a buscar: ");
                        int searchId = scanner.nextInt();
                        scanner.nextLine(); // Consumir salto de línea
                        clinica.buscarHistoricalRecords(searchId);
                        break;
                    case 3:
                        clinica.listaHistoricalRecords();
                        break;
                    case 4:
                        System.out.println("Regresando al menú principal...");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, intente de nuevo ingresando una opcion permitida..");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine();
                subChoice = 0;
            }
        } while (subChoice != 4);
    }

    private static void handleActiveQueue(Scanner scanner, Clinica clinica) {
        int subChoice;
        do {
            System.out.println("\n----- Gestión de Cola de Atención Activa -----");
            System.out.println("1. Registrar/Actualizar un paciente en espera");
            System.out.println("2. Buscar un paciente en espera por ID");
            System.out.println("3. Eliminar un paciente de la cola por ID");
            System.out.println("4. Listar todos los pacientes en cola");
            System.out.println("5. Regresar al menú principal");
            System.out.print("Ingrese su opción: ");

            try {
                subChoice = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea

                switch (subChoice) {
                    case 1:
                        System.out.print("ID del paciente: ");
                        int pacienteId = scanner.nextInt();
                        scanner.nextLine(); // Consumir salto de línea
                        System.out.print("Nombre del paciente: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Motivo de la consulta: ");
                        String motivoC = scanner.nextLine();
                        System.out.print("Prioridad [1=Alta, 5=Baja]: ");
                        int prioridad = scanner.nextInt();
                        scanner.nextLine(); // Consumir salto de línea
                        clinica.registrarActualizarPacientesEncolados(pacienteId, nombre, motivoC, prioridad);
                        break;
                    case 2:
                        System.out.print("Ingrese el ID del paciente a buscar en la cola: ");
                        int searchId = scanner.nextInt();
                        scanner.nextLine(); // Consumir salto de línea
                        clinica.buscarPacienteEncolado(searchId);
                        break;
                    case 3:
                        System.out.print("Ingrese el ID del paciente a eliminar de la cola: ");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine(); // Consumir salto de línea
                        clinica.eliminarPacienteEncolado(deleteId);
                        break;
                    case 4:
                        clinica.listAllActiveQueue();
                        break;
                    case 5:
                        System.out.println("Regresando al menú principal...");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, intente de nuevo ingresando una opcion permitida..");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine();
                subChoice = 0;
            }
        } while (subChoice != 5);
    }
}