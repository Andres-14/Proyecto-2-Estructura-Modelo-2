package com.mycompany.sistemaclinica;

public class NodoArbol<T> { 
    public T data;
    public NodoArbol<T> left;
    public NodoArbol<T> right;

    public NodoArbol(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
