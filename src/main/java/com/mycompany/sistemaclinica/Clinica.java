
package com.mycompany.sistemaclinica;

import java.time.LocalDate;

public class Clinica {
    private ArbolBinarioS historicalRecords;
    private ArbolAVL activeQueue;

    public Clinica() {
        this.historicalRecords = new ArbolBinarioS();
        this.activeQueue = new ArbolAVL();
    }

    // ----- Gestion de Registros Historicos -----
    public void registrarHistorialPaciente(String nombre, String sexo, LocalDate dob, String diagnostico) {
        PacientesClinica newPaciente = new PacientesClinica(nombre, sexo, dob, diagnostico);
        historicalRecords.insert(newPaciente);
    }

    public PacientesClinica buscarHistoricalRecords(int id) {
        PacientesClinica pacientesClinica = historicalRecords.search(id);
        return pacientesClinica;
    }

    public void listaHistoricalRecords() {
        historicalRecords.listAllRecords();
    }

    // ----- Gestión de Cola Activa -----
    public void registrarActualizarPacientesEncolados(int id, String nombre, String motivoC, int prioridad) {
        PacientesEncoladosClinica pacienteParaEncolar = new PacientesEncoladosClinica(id, nombre, motivoC, prioridad);
        activeQueue.insert(pacienteParaEncolar);
    }

    public PacientesEncoladosClinica buscarPacienteEncolado(int id) {
        PacientesEncoladosClinica pacientesClinica = activeQueue.search(id);
        return pacientesClinica;
    }

    public void eliminarPacienteEncolado(int id) {
        activeQueue.delete(id);
    }

    public void listAllActiveQueue() {
        activeQueue.listaPacientesEncolados();
    }
}

