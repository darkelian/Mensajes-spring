package com.messages.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.messages.demo.entity.Mensaje;
import com.messages.demo.services.MensajeServices;
import com.messages.demo.services.RedService;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    @Autowired
    private MensajeServices mensajeServices;

    @Autowired
    private RedService redService;

    @PostMapping("/enviar")
    public ResponseEntity<?> enviarMensaje(@RequestBody Mensaje mensaje, @RequestParam int numConexiones) {
        List<String> paquetes = mensajeServices.dividirMensaje(mensaje.getContenido());
        double probabilidadCaida = redService.calcularProbabilidadCaida(numConexiones);

        Map<String, Object> response = new HashMap<>();
        response.put("paquetes", paquetes);
        response.put("probabilidadCaida", probabilidadCaida);

        return ResponseEntity.ok(response);
    }
}
