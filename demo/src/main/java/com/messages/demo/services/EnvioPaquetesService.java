package com.messages.demo.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.messages.demo.entity.Enlace;
import com.messages.demo.entity.Malla;
import com.messages.demo.entity.Nodo;

@Service
public class EnvioPaquetesService {

    public List<Nodo> enviarPaquete(Nodo origen, Nodo destino) {
        // Implementar lógica para encontrar la mejor ruta usando BFS
        return buscarRuta(origen, destino);
    }

    public List<Nodo> buscarRuta(Nodo origen, Nodo destino) {
        // Utilizamos una cola para hacer una búsqueda en anchura (BFS)
        Queue<List<Nodo>> cola = new LinkedList<>();
        Set<Nodo> visitados = new HashSet<>();

        // Agregar la ruta inicial (solo el nodo de origen)
        List<Nodo> rutaInicial = new ArrayList<>();
        rutaInicial.add(origen);
        cola.add(rutaInicial);

        while (!cola.isEmpty()) {
            List<Nodo> rutaActual = cola.poll();
            Nodo ultimoNodo = rutaActual.get(rutaActual.size() - 1);

            // Si llegamos al nodo destino, devolver la ruta
            if (ultimoNodo.equals(destino)) {
                return rutaActual;
            }

            // Si ya hemos visitado este nodo, continuar
            if (visitados.contains(ultimoNodo)) {
                continue;
            }

            // Marcar el nodo como visitado
            visitados.add(ultimoNodo);

            // Recorrer los enlaces del nodo actual
            for (Enlace enlace : ultimoNodo.getEnlaces()) {
                Nodo nodoVecino = (enlace.getOrigen().equals(ultimoNodo)) ? enlace.getDestino() : enlace.getOrigen();

                if (!visitados.contains(nodoVecino)) {
                    // Crear una nueva ruta que extienda la ruta actual
                    List<Nodo> nuevaRuta = new ArrayList<>(rutaActual);
                    nuevaRuta.add(nodoVecino);
                    cola.add(nuevaRuta);
                }
            }
        }

        // Si no se encuentra una ruta, devolver una lista vacía
        return new ArrayList<>();
    }
}
