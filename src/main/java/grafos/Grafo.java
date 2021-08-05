/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import excepciones.ExcepcionAristaNoExiste;
import excepciones.ExcepcionAristaYaExiste;
import excepciones.ExcepcionNroVerticesInvalido;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author user
 */
public class Grafo {

    protected List<List<Integer>> listaDeAydacencias;

    public Grafo() {
    }

    public Grafo(int cantAristas, List<List<Integer>> listaDeAydacencias) {
        this.listaDeAydacencias = new ArrayList<>();
    }

    public Grafo(int nroDeVercitesInicial) throws ExcepcionNroVerticesInvalido {
        if (nroDeVercitesInicial < 0) {
            throw new ExcepcionNroVerticesInvalido();
        }
        this.listaDeAydacencias = new ArrayList<>();
        for (int i = 0; i < nroDeVercitesInicial; i++) {
            this.listaDeAydacencias.add(new ArrayList<Integer>());
        }
    }

    public void insertarVertice() {
        this.listaDeAydacencias.add(new ArrayList<Integer>());
    }

    public int cantidadDeAristas() {
        int cantAristas = 0;
        int cantLazos = 0;
        for (int i = 0; i < this.listaDeAydacencias.size(); i++) {
            List<Integer> adyacentesDeUnVertice = this.listaDeAydacencias.get(i);
            for (Integer posAdyacente : adyacentesDeUnVertice) {
                if (i == posAdyacente) {
                    cantLazos++;
                } else {
                    cantAristas++;
                }
            }
        }
        cantAristas = (cantAristas / 2) + cantLazos;
        return cantAristas;
    }

    public int cantidadVertices() {
        return listaDeAydacencias.size();
    }

    public void validarVertice(int posicionDeVertice) {
        if (posicionDeVertice < 0 || posicionDeVertice >= cantidadVertices()) {
            throw new IllegalArgumentException("El vertice " + posicionDeVertice + " no pertenece al grafo");
        }
    }

    public void insertarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaYaExiste {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if (existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }

        List<Integer> adyacenciasDelOrigen = this.listaDeAydacencias.get(posVerticeOrigen);
        adyacenciasDelOrigen.add(posVerticeDestino);

        Collections.sort(adyacenciasDelOrigen);
        if (posVerticeOrigen != posVerticeDestino) {
            List<Integer> adyacenciasDelDestino = this.listaDeAydacencias.get(posVerticeDestino);
            adyacenciasDelDestino.add(posVerticeOrigen);
            Collections.sort(adyacenciasDelDestino);
        }
    }

    public boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino) {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<Integer> adyacenciasDelOrigen = this.listaDeAydacencias.get(posVerticeOrigen);
        return adyacenciasDelOrigen.contains(posVerticeDestino);
    }

    public void eliminarVertice(int posVerticeAEliminar) {
        validarVertice(posVerticeAEliminar);
        this.listaDeAydacencias.remove(posVerticeAEliminar);
        for (List<Integer> adyacenciasDeUnVertice : this.listaDeAydacencias) {
            int posicionDeVerticeEnAdy = adyacenciasDeUnVertice.indexOf(posVerticeAEliminar);
            if (posicionDeVerticeEnAdy >= 0) {
                adyacenciasDeUnVertice.remove(posicionDeVerticeEnAdy);
            }
            for (int i = 0; i < adyacenciasDeUnVertice.size(); i++) {
                int posicionAdyacente = adyacenciasDeUnVertice.get(i);
                if (adyacenciasDeUnVertice.get(i) > posVerticeAEliminar) {
                    adyacenciasDeUnVertice.set(i, posicionAdyacente - 1);
                }
            }
        }
    }

    public int gradoDeVertice(int posDeVertice) {
        validarVertice(posDeVertice);
        List<Integer> adyacenciasDelVertice = this.listaDeAydacencias.get(posDeVertice);
        return adyacenciasDelVertice.size();
    }

    public Iterable<Integer> adyacentesDeVertice(int posDeVertice) {
        validarVertice(posDeVertice);
        List<Integer> adyacenciasDelVertice = this.listaDeAydacencias.get(posDeVertice);
        Iterable<Integer> it = adyacenciasDelVertice;
        return it;
    }

    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if (!existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaNoExiste();
        }

        if (posVerticeOrigen != posVerticeDestino) {
            List<Integer> adyacenciaDelOrigen = this.listaDeAydacencias.get(posVerticeOrigen);
            List<Integer> adyacenciaDelDestino = this.listaDeAydacencias.get(posVerticeDestino);

            adyacenciaDelOrigen.remove(adyacenciaDelOrigen.indexOf(posVerticeDestino));
            adyacenciaDelDestino.remove(adyacenciaDelDestino.indexOf(posVerticeOrigen));
        } else {

            List<Integer> adyacenciaDelDestino = this.listaDeAydacencias.get(posVerticeDestino);
            adyacenciaDelDestino.remove(adyacenciaDelDestino.indexOf(posVerticeDestino));
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < listaDeAydacencias.size(); i++) {
            buffer.append(listaDeAydacencias.get(i).toString());
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
