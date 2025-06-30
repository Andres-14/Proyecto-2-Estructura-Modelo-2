
package com.mycompany.sistemaclinica;

public class NodoAVL {
    public PacientesEncoladosClinica data;
    public NodoAVL left;
    public NodoAVL right;
    public int height; // Es la altura del subarbol con este nodo como raiz

    public NodoAVL(PacientesEncoladosClinica data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1; // Un nodo nuevo siempre tiene como altura 1
    }
}

