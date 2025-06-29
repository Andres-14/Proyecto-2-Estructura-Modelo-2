package com.mycompany.sistemaclinica;

public class ArbolBinarioS {
    private Node<PacientesClinica> root;

    public ArbolBinarioS() {
        this.root = null;
    }

    public void insert(PacientesClinica pacientesClinica) {
        root = insertRecursive(root, pacientesClinica);
    }

    private Node<PacientesClinica> insertRecursive(Node<PacientesClinica> current, PacientesClinica pacientesClinica) {
        if (current == null) {
            System.out.println("Expediente con ID " + pacientesClinica.getId() + " registrado.");
            return new Node<>(pacientesClinica);
        }

        if (pacientesClinica.getId() < current.data.getId()) {
            current.left = insertRecursive(current.left, pacientesClinica);
        } else if (pacientesClinica.getId() > current.data.getId()) {
            current.right = insertRecursive(current.right, pacientesClinica);
        } else {
            System.out.println("Error: Expediente con ID " + pacientesClinica.getId() + " ya existe.");
        }
        return current;
    }

    public PacientesClinica search(int id) {
        PacientesClinica foundPacientesClinica = searchRecursive(root, id);
        if (foundPacientesClinica != null) {
            System.out.println("Expediente encontrado: " + foundPacientesClinica);
        } else {
            System.out.println("Expediente con ID " + id + " no encontrado.");
        }
        return foundPacientesClinica;
    }

    private PacientesClinica searchRecursive(Node<PacientesClinica> current, int id) {
        if (current == null) {
            return null;
        }

        if (id == current.data.getId()) {
            return current.data;
        }

        if (id < current.data.getId()) {
            return searchRecursive(current.left, id);
        } else {
            return searchRecursive(current.right, id);
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

    private void inOrderTraversal(Node<PacientesClinica> node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.data);
            inOrderTraversal(node.right);
        }
    }
}