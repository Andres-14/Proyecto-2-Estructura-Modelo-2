
package com.mycompany.sistemaclinica;

import java.util.Objects;

public class PacientesEncoladosClinica {
    private int id;
    private String nombre;
    private String motivoC;
    private int prioridad; //Los numeros más cercanos al 0 son más prioritarios

    public PacientesEncoladosClinica(int id, String nombre, String motivoC, int prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.motivoC = motivoC;
        this.prioridad = prioridad;
    }

    public PacientesEncoladosClinica(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMotivoC() {
        return motivoC;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMotivo(String motivoC) {
        this.motivoC = motivoC;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre del paciente: " + nombre + ", Motivo de la consulta: " + motivoC + ", Prioridad: " + prioridad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PacientesEncoladosClinica that = (PacientesEncoladosClinica) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
