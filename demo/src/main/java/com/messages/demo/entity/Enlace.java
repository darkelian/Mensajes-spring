package com.messages.demo.entity;

import lombok.Data;

@Data
public class Enlace {

    private Nodo origen;
    private Nodo destino;
    private int anchoBanda;
    private double probabilidadCaida;

    // Constructor con parámetros
    public Enlace(Nodo origen, Nodo destino, int anchoBanda) {
        this.origen = origen;
        this.destino = destino;
        this.anchoBanda = anchoBanda;
        this.probabilidadCaida = calcularProbabilidadCaida();
    }

    // Método para calcular la probabilidad de caída (ejemplo)
    private double calcularProbabilidadCaida() {
        return anchoBanda * 0.1; // Puedes agregar más lógica según sea necesario
    }

    // Constructor sin parámetros
    public Enlace() {
    }
}

