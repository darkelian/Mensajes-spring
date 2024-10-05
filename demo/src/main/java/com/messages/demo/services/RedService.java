package com.messages.demo.services;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class RedService {

    public double calcularProbabilidadCaida(int numConexiones) {
        Random random = new Random();
        int anchoBanda = ThreadLocalRandom.current().nextInt(100, 1001); // Ancho de banda aleatorio entre 100 y 1000
        double probabilidadCaida = anchoBanda * 0.1 + Math.pow(numConexiones, 2);
        return probabilidadCaida;
    }
}