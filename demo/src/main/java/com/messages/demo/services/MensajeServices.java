package com.messages.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MensajeServices {

    public List<String> dividirMensaje(String mensaje) {
        String[] palabras = mensaje.split(" ");
        List<String> paquetes = new ArrayList<>();

        for (int i = 0; i < palabras.length; i++) {
            paquetes.add("Paquete " + (i + 1) + " " + palabras[i]);
        }

        return paquetes;
    }
}
