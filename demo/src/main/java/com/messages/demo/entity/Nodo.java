package com.messages.demo.entity;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
@Data
public class Nodo {
    private String nombre;
    @EqualsAndHashCode.Exclude
    private List<Enlace> enlaces;

    // Constructor con nombre
    public Nodo(String nombre) {
        this.nombre = nombre;
        this.enlaces = new ArrayList<>(); // Inicializa la lista de enlaces para evitar null
    }

    // Constructor sin parámetros
    public Nodo() {
        this.enlaces = new ArrayList<>(); // Inicializa la lista de enlaces para evitar null
    }
}
