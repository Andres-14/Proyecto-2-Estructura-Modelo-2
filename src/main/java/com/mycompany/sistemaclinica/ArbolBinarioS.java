
package com.mycompany.sistemaclinica;

// Definición del nodo para el árbol binario
class Nodo<T> {
    T data;
    Nodo<T> left;
    Nodo<T> right;

    Nodo(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class ArbolBinarioS {
    private Nodo<PacientesClinica> root;

    public ArbolBinarioS() {
        this.root = null;
    }

    public void insert(PacientesClinica pacientesClinica) {
        root = agregarPaciente(root, pacientesClinica);
    }

    private Nodo<PacientesClinica> agregarPaciente(Nodo<PacientesClinica> current, PacientesClinica pacientesClinica) {
        if (current == null) {
            System.out.println("Expediente con ID " + pacientesClinica.getId() + " registrado.");
            return new Nodo<>(pacientesClinica);
        }
        if (pacientesClinica.getId() < current.data.getId()) {
            current.left = agregarPaciente(current.left, pacientesClinica);
        } else if (pacientesClinica.getId() > current.data.getId()) {
            current.right = agregarPaciente(current.right, pacientesClinica);
        } else {
            System.out.println("Error: Expediente con ID " + pacientesClinica.getId() + " ya existe.");
        }
        return current;
    }

    public PacientesClinica search(int id) {
        PacientesClinica pacientesEncontrado = encontrarPacientes(root, id);
        if (pacientesEncontrado != null) {
            System.out.println("Expediente encontrado: " + pacientesEncontrado);
        } else {
            System.out.println("Expediente con ID " + id + " no encontrado.");
        }
        return pacientesEncontrado;
    }

    private PacientesClinica encontrarPacientes(Nodo<PacientesClinica> current, int id) {
        if (current == null) {
            return null;
        }
        if (id == current.data.getId()) {
            return current.data;
        }
        if (id < current.data.getId()) {
            return encontrarPacientes(current.left, id);
        } else {
            return encontrarPacientes(current.right, id);
        }
    }

    public void listAllRecords() {
        if (root == null) {
            System.out.println("No hay expedientes registrados.");
            return;
        }
        System.out.println("--- Listado de Expedientes Históricos ---");
        inOrderTraversal(root);
        System.out.println("----------------------------------------");
    }

    private void inOrderTraversal(Nodo<PacientesClinica> node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.data);
            inOrderTraversal(node.right);
        }
    }
}
