package com.messages.demo.entity;

import java.util.List;

import java.util.ArrayList;
public class Malla {
    private List<Nodo> nodos;

    // Constructor
    public Malla() {
        this.nodos = new ArrayList<>(); // Inicializa la lista de nodos
    }

    // Getters y Setters
    public List<Nodo> getNodos() {
        return nodos;
    }

    public void setNodos(List<Nodo> nodos) {
        this.nodos = nodos;
    }
}
