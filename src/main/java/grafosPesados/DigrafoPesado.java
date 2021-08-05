/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafosPesados;

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
public class DigrafoPesado extends GrafoPesado {
    
    public static double INFINITO = Double.MAX_VALUE;
    
    public DigrafoPesado() {
    }

    public DigrafoPesado(int nroDeVercitesInicial) throws ExcepcionNroVerticesInvalido {
        super(nroDeVercitesInicial);
    }

    @Override
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino, double costo) throws ExcepcionAristaYaExiste {
        super.validarVertice(posVerticeOrigen);
        super.validarVertice(posVerticeDestino);
        if (super.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }

        List<AdyacenteConPeso> adyacenciasDelOrigen = super.listaDeAydacencias.get(posVerticeOrigen);
        adyacenciasDelOrigen.add(new AdyacenteConPeso(posVerticeDestino, costo));
        Collections.sort(adyacenciasDelOrigen);
    }

    @Override
    public int gradoDeVertice(int posDeVertice) {
        throw new UnsupportedOperationException("No soportado en grafos dirigidos");
    }

    public int gradoDeSalida(int posDeVertice) {
        return super.gradoDeVertice(posDeVertice);
    }

    @Override
    public int cantidadDeAristas() {
        return 0;
    }

    @Override
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) {
    }

    public String caminoYCostoMinimoEntreVerticesDijsktra(int verticeInicial, int verticeDestino) {
        UtilRecorridos control = new UtilRecorridos(this.cantidadVertices());
        String s = "";
        List<Double> costos = inicializarCostos(verticeInicial);
        control.desmarcarTodos();
        List<Integer> pred = inicializarPredecesores();
        int noMarcadoDeMenorCosto = verticeInicial;
        while (!control.estaMarcado(verticeDestino) && costos.get(noMarcadoDeMenorCosto) != INFINITO) {
            control.marcarVertice(noMarcadoDeMenorCosto);
            List<Integer> adyacentes = (List<Integer>) adyacentesDeVertice(noMarcadoDeMenorCosto);
            int verticeActual = noMarcadoDeMenorCosto;
            for (int i = 0; i < adyacentes.size(); i++) {
                if (!control.estaMarcado(i)) {
                    double costoAdyacente = costos.get(i);
                    double costoActual = costos.get(noMarcadoDeMenorCosto);
                    if (costoAdyacente > (costoActual + this.getPeso(verticeActual, i))) {
                        costos.set(i, costoActual + this.getPeso(verticeActual, i));
                        pred.set(i, noMarcadoDeMenorCosto);
                    }
                }
            }
            noMarcadoDeMenorCosto = posicionMenorCostoNoMarcado(costos, control);
        }
        if (costos.get(verticeDestino) != INFINITO) {
            s += "Camino entre " + verticeInicial + " y " + verticeDestino + ": " + encontrarCaminoDijsktra(pred, verticeDestino)
                    + " Costo: " + costos.get(verticeDestino);
        } else {
            s += "Camino entre " + verticeInicial + " y " + verticeDestino + ": " + "No hay camino entre los vertices Costo: --";
        }
        return s;
    }

    private String encontrarCaminoDijsktra(List<Integer> pred, int posicionDestino) {
        List<Integer> camino = new ArrayList<>();
        camino.add(posicionDestino);
        int elemento = pred.get(posicionDestino);
        int pos = posicionDestino;
        while (elemento != -1 && camino.size() <= this.cantidadVertices()) {
            camino.add(elemento);
            pos = elemento;
            elemento = pred.get(pos);
        }
        String s = "" + camino.get(camino.size() - 1);
        for (int i = camino.size() - 2; i >= 0; i--) {
            s += "->" + camino.get(i);
        }
        return s;
    }

    private int posicionMenorCostoNoMarcado(List<Double> costos, UtilRecorridos control) {
        int men = 0;
        double comp = INFINITO;
        for (int i = 0; i < costos.size(); i++) {
            if (!control.estaMarcado(i)) {
                if (costos.get(i) <= comp) {
                    comp = costos.get(i);
                    men = i;
                }
            }
        }
        return men;
    }

    private List<Double> inicializarCostos(int verticeInicial) {
        List<Double> costos = new ArrayList<>();
        for (int i = 0; i < this.cantidadVertices(); i++) {
            if (i != verticeInicial) {
                costos.add(INFINITO);
            } else {
                costos.add(0.0);
            }
        }
        return costos;
    }

    private List<Integer> inicializarPredecesores() {
        List<Integer> pred = new ArrayList<>();
        for (int i = 0; i < this.cantidadVertices(); i++) {
            pred.add(-1);
        }
        return pred;
    }
    
    public String caminoYCostoMinimoDesdeVerticeDijsktra(int verticeInicial) {
		String s="";
		int posicionVertice=verticeInicial;
		for(int i=0; i<this.cantidadVertices(); i++) {
			if(i != posicionVertice) {
				int verticeDestino = i;
				s+=caminoYCostoMinimoEntreVerticesDijsktra(verticeInicial, verticeDestino)+"\n";
			}
		}
		return s;
	}
}
