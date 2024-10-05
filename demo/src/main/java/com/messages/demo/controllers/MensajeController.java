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

import com.messages.demo.entity.Enlace;
import com.messages.demo.entity.Malla;
import com.messages.demo.entity.Mensaje;
import com.messages.demo.entity.Nodo;
import com.messages.demo.services.EnvioPaquetesService;
import com.messages.demo.services.MensajeService;
import com.messages.demo.services.RedService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private RedService redService;

    @Autowired
    private EnvioPaquetesService envioPaquetesService;

    // Inicializar la malla (esto se podría mover a un componente de inicialización
    // si se prefiere)
    private Malla malla;

    @PostConstruct
    public void inicializarMalla() {
        // Crear la malla aquí
        this.malla = new Malla();

        // Crear nodos
        Nodo pc0 = new Nodo("PC0");
        Nodo pc1 = new Nodo("PC1");
        Nodo switch1 = new Nodo("Switch1");
        Nodo switch2 = new Nodo("Switch2");

        Nodo router0 = new Nodo("Router0");
        Nodo router1 = new Nodo("Router1");
        Nodo router2 = new Nodo("Router2");
        Nodo router3 = new Nodo("Router3");
        Nodo router4 = new Nodo("Router4");
        Nodo router5 = new Nodo("Router5");
        Nodo router6 = new Nodo("Router6");
        Nodo router7 = new Nodo("Router7");
        Nodo router8 = new Nodo("Router8");
        Nodo router9 = new Nodo("Router9");
        Nodo router10 = new Nodo("Router10");
        Nodo router11 = new Nodo("Router11");
        Nodo router12 = new Nodo("Router12");
        Nodo router13 = new Nodo("Router13");
        Nodo router14 = new Nodo("Router14");
        Nodo router15 = new Nodo("Router15");
        Nodo router16 = new Nodo("Router16");
        Nodo router17 = new Nodo("Router17");
        Nodo router18 = new Nodo("Router18");

        // Agregar nodos a la malla
        malla.getNodos().add(pc0);
        malla.getNodos().add(pc1);
        malla.getNodos().add(switch1);
        malla.getNodos().add(switch2);
        malla.getNodos().add(router0);
        malla.getNodos().add(router1);
        malla.getNodos().add(router2);
        malla.getNodos().add(router3);
        malla.getNodos().add(router4);
        malla.getNodos().add(router5);
        malla.getNodos().add(router6);
        malla.getNodos().add(router7);
        malla.getNodos().add(router8);
        malla.getNodos().add(router9);
        malla.getNodos().add(router10);
        malla.getNodos().add(router11);
        malla.getNodos().add(router12);
        malla.getNodos().add(router13);
        malla.getNodos().add(router14);
        malla.getNodos().add(router15);
        malla.getNodos().add(router16);
        malla.getNodos().add(router17);
        malla.getNodos().add(router18);

        // Crear enlaces entre los nodos para representar la malla
        // PC0 - Switch1
        Enlace enlace1 = new Enlace(pc0, switch1, 100);
        pc0.getEnlaces().add(enlace1);
        switch1.getEnlaces().add(enlace1);

        // Switch1 - Router0
        Enlace enlace2 = new Enlace(switch1, router0, 150);
        switch1.getEnlaces().add(enlace2);
        router0.getEnlaces().add(enlace2);

        // Switch1 - Router1
        Enlace enlace3 = new Enlace(switch1, router1, 150);
        switch1.getEnlaces().add(enlace3);
        router1.getEnlaces().add(enlace3);

        // Router0 - Router2
        Enlace enlace4 = new Enlace(router0, router2, 200);
        router0.getEnlaces().add(enlace4);
        router2.getEnlaces().add(enlace4);

        // Router1 - Router2
        Enlace enlace5 = new Enlace(router1, router2, 200);
        router1.getEnlaces().add(enlace5);
        router2.getEnlaces().add(enlace5);

        // Continúa creando los enlaces según la imagen para todos los nodos
        // Ejemplo:
        // Router2 - Router3
        Enlace enlace6 = new Enlace(router2, router3, 200);
        router2.getEnlaces().add(enlace6);
        router3.getEnlaces().add(enlace6);

        // Router3 - Router4
        Enlace enlace7 = new Enlace(router3, router4, 200);
        router3.getEnlaces().add(enlace7);
        router4.getEnlaces().add(enlace7);

        // Switch2 - PC1
        Enlace enlaceFinal = new Enlace(switch2, pc1, 100);
        switch2.getEnlaces().add(enlaceFinal);
        pc1.getEnlaces().add(enlaceFinal);
    }

    @PostMapping("/enviar")
    public ResponseEntity<?> enviarMensaje(@RequestBody Mensaje mensaje, @RequestParam int numConexiones) {
        // Dividir el mensaje en paquetes
        List<String> paquetes = mensajeService.dividirMensaje(mensaje.getContenido());

        // Obtener nodos de origen y destino (por ejemplo, pc0 y pc1)
        Nodo origen = malla.getNodos().stream().filter(n -> n.getNombre().equals("PC0")).findFirst().orElse(null);
        Nodo destino = malla.getNodos().stream().filter(n -> n.getNombre().equals("PC1")).findFirst().orElse(null);

        // Validar si los nodos existen
        if (origen == null || destino == null) {
            return ResponseEntity.badRequest().body("Nodos de origen o destino no encontrados");
        }

        // Calcular la ruta de envío del paquete usando BFS para encontrar la ruta
        // correcta
        List<Nodo> ruta = envioPaquetesService.enviarPaquete(origen, destino);

        // Validar si se encontró una ruta
        if (ruta.isEmpty()) {
            return ResponseEntity.badRequest().body("No se encontró una ruta entre los nodos de origen y destino");
        }

        // Calcular la probabilidad de caída
        double probabilidadCaida = redService.calcularProbabilidadCaida(numConexiones);

        Map<String, Object> response = new HashMap<>();
        response.put("paquetes", paquetes);
        response.put("probabilidadCaida", probabilidadCaida);
        response.put("ruta", ruta.stream().map(Nodo::getNombre).toList());

        return ResponseEntity.ok(response);
    }

}
