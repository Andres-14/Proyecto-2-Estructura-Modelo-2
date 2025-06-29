
package com.mycompany.sistemaclinica;

import java.time.LocalDate;
import java.util.Objects;

public class PacientesClinica {
 private static int sigId = 1000; // Da un ID al paciente que empieza desde el 1000
    private int id;
    private String nombre;
    private String sexo;
    private LocalDate fechaNacimiento;
    private String diagnostico;

    public PacientesClinica(String nombre, String sexo, LocalDate fechaNacimiento, String diagnostico) {
        this.id = sigId++;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.diagnostico = diagnostico;   
    }
    
    public PacienteClinica(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDiagnosttico() {
        return diagnostico;
    }

    public String toString() {
        return "ID: " + id + ", Nombre del paciente: " + nombre + ", Sexo: " + sexo +
               ", Fecha de Nacimiento: " + fechaNacimiento + ", Diagnostico obtenido: " + diagnostico;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PacientesClinica paciente = (PacientesClinica) o;
        return id == paciente.id;
    }

    public int hashCode() {
        return Objects.hash(id);
    }
}
