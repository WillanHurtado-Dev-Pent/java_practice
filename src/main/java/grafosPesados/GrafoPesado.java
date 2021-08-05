/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafosPesados;

import excepciones.ExcepcionAristaNoExiste;
import excepciones.ExcepcionAristaYaExiste;
import excepciones.ExcepcionNroVerticesInvalido;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.UtilRecorridos;

/**
 *
 * @author user
 */
public class GrafoPesado {
    protected List<List<AdyacenteConPeso>> listaDeAydacencias;

    public GrafoPesado() {
    }

    public GrafoPesado(int cantAristas, List<List<Integer>> listaDeAydacencias) {
        this.listaDeAydacencias = new ArrayList<>();
    }

    public GrafoPesado(int nroDeVercitesInicial) throws ExcepcionNroVerticesInvalido {
        if (nroDeVercitesInicial < 0) {
            throw new ExcepcionNroVerticesInvalido();
        }
        this.listaDeAydacencias = new ArrayList<>();
        for (int i = 0; i < nroDeVercitesInicial; i++) {
            this.listaDeAydacencias.add(new ArrayList<AdyacenteConPeso>());
        }
    }

    public void insertarVertice() {
        this.listaDeAydacencias.add(new ArrayList<AdyacenteConPeso>());
    }

    public int cantidadDeAristas() {
        int cantAristas = 0;
        int cantLazos = 0;
        for (int i = 0; i < this.listaDeAydacencias.size(); i++) {
            List<AdyacenteConPeso> adyacentesDeUnVertice = this.listaDeAydacencias.get(i);
            for (AdyacenteConPeso posAdyacente : adyacentesDeUnVertice) {
                if (i == posAdyacente.getIndiceVertice()) {
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

    public void insertarArista(int posVerticeOrigen, int posVerticeDestino, double costo) throws ExcepcionAristaYaExiste {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if (existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }

        List<AdyacenteConPeso> adyacenciasDelOrigen = this.listaDeAydacencias.get(posVerticeOrigen);
        adyacenciasDelOrigen.add(new AdyacenteConPeso(posVerticeDestino, costo));

        Collections.sort(adyacenciasDelOrigen);
        if (posVerticeOrigen != posVerticeDestino) {
            List<AdyacenteConPeso> adyacenciasDelDestino = this.listaDeAydacencias.get(posVerticeDestino);
            adyacenciasDelDestino.add(new AdyacenteConPeso(posVerticeOrigen, costo));
        }
    }

    public boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino) {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<AdyacenteConPeso> adyacenciasDelOrigen = this.listaDeAydacencias.get(posVerticeOrigen);
        AdyacenteConPeso destino = new AdyacenteConPeso(posVerticeDestino);
        return adyacenciasDelOrigen.contains(destino);
    }

    public void eliminarVertice(int posVerticeAEliminar) {
        validarVertice(posVerticeAEliminar);
        this.listaDeAydacencias.remove(posVerticeAEliminar);
        for (List<AdyacenteConPeso> adyacenciasDeUnVertice : this.listaDeAydacencias) {
            AdyacenteConPeso adyacenteAEliminar = new AdyacenteConPeso(posVerticeAEliminar);
            int posicionDeVerticeEnAdy = adyacenciasDeUnVertice.indexOf(adyacenteAEliminar);
            if (posicionDeVerticeEnAdy >= 0) {
                adyacenciasDeUnVertice.remove(posicionDeVerticeEnAdy);
            }
            for (int i = 0; i < adyacenciasDeUnVertice.size(); i++) {
                AdyacenteConPeso posicionAdyacente = adyacenciasDeUnVertice.get(i);
                if (posicionAdyacente.getIndiceVertice() > posVerticeAEliminar) {
                    posicionAdyacente.setIndiceVertice(posicionAdyacente.getIndiceVertice() - 1);
                }
            }
        }
    }

    public int gradoDeVertice(int posDeVertice) {
        validarVertice(posDeVertice);
        List<AdyacenteConPeso> adyacenciasDelVertice = this.listaDeAydacencias.get(posDeVertice);
        return adyacenciasDelVertice.size();
    }

    public Iterable<Integer> adyacentesDeVertice(int posDeVertice) {
        validarVertice(posDeVertice);
        List<AdyacenteConPeso> adyacenciasDelVertice = this.listaDeAydacencias.get(posDeVertice);
        List<Integer> adyacentesDelVertice = new ArrayList<>();
        for (AdyacenteConPeso adyacente : adyacenciasDelVertice) {
            adyacentesDelVertice.add(adyacente.getIndiceVertice());
        }
        Iterable<Integer> it = adyacentesDelVertice;
        return it;
    }

    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaNoExiste {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        if (!existeAdyacencia(posVerticeOrigen, posVerticeDestino))
            throw new ExcepcionAristaNoExiste();

        if (posVerticeOrigen != posVerticeDestino) {
            List<AdyacenteConPeso> adyacenciaDelOrigen = this.listaDeAydacencias.get(posVerticeOrigen);
            List<AdyacenteConPeso> adyacenciaDelDestino = this.listaDeAydacencias.get(posVerticeDestino);

            adyacenciaDelOrigen.remove(adyacenciaDelOrigen.indexOf(new AdyacenteConPeso(posVerticeDestino)));
            adyacenciaDelDestino.remove(adyacenciaDelDestino.indexOf(new AdyacenteConPeso(posVerticeOrigen)));
        } else {

            List<AdyacenteConPeso> adyacenciaDelDestino = this.listaDeAydacencias.get(posVerticeDestino);
            adyacenciaDelDestino.remove(adyacenciaDelDestino.indexOf(new AdyacenteConPeso(posVerticeDestino)));
        }
    }


    public double getPeso(int posVerticeOrigen, int posVerticeDestino) {
        validarVertice(posVerticeOrigen);
        validarVertice(posVerticeDestino);
        List<AdyacenteConPeso> adyacenciasDelOrigen = this.listaDeAydacencias.get(posVerticeOrigen);
        AdyacenteConPeso destino = new AdyacenteConPeso(posVerticeDestino);
        if (!adyacenciasDelOrigen.contains(destino))
            return 1.0 / 0;
        else {
            int posicionDestino = adyacenciasDelOrigen.indexOf(destino);
            return adyacenciasDelOrigen.get(posicionDestino).getPeso();
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