/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import excepciones.ExcepcionAristaYaExiste;
import excepciones.ExcepcionNroVerticesInvalido;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author user
 */
public class Digrafo extends Grafo {

    public Digrafo() {
    }

    public Digrafo(int nroDeVerticesInicial) throws ExcepcionNroVerticesInvalido {
        super(nroDeVerticesInicial);
    }

    @Override
    public void insertarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaYaExiste {
        super.validarVertice(posVerticeOrigen);
        super.validarVertice(posVerticeDestino);
        if (super.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
            throw new ExcepcionAristaYaExiste();
        }

        List<Integer> adyacenciasDelOrigen = super.listaDeAydacencias.get(posVerticeOrigen);
        adyacenciasDelOrigen.add(posVerticeDestino);
        Collections.sort(adyacenciasDelOrigen);
    }

    @Override
    public int gradoDeVertice(int posDeVertice) {
        throw new UnsupportedOperationException("No soportado en grafos dirigidos");
    }

    public int gradoDeSalida(int posDeVertice) {
        return super.gradoDeVertice(posDeVertice);
    }

    public int gradoDeEntrada(int posDeVertice) {
        super.validarVertice(posDeVertice);
        int entradaDeVertice = 0;
        for (List<Integer> adyacentesDeUnVertice : super.listaDeAydacencias) {
            for (Integer posAdyacente : adyacentesDeUnVertice) {
                if (posAdyacente == posDeVertice) {
                    entradaDeVertice++;
                }
            }
        }
        return entradaDeVertice;
    }

    @Override
    public int cantidadDeAristas() {
        return 0;
    }

    @Override
    public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) {
    }

    public Grafo convertirANoDirigido () throws ExcepcionNroVerticesInvalido {
        Grafo noDirigido = new Grafo(this.cantidadVertices());
        for (int i = 0; i < this.cantidadVertices(); i++) {
            List<Integer> adyacencias = this.listaDeAydacencias.get(i);
            List actuales = noDirigido.listaDeAydacencias.get(i);
            for (int j = 0; j < adyacencias.size(); j++) {
                try {
                    if(!noDirigido.existeAdyacencia(i, j)){
                        noDirigido.insertarArista(i, j);
                    }
                } catch (Exception e) {
                    //
                }
            }
        }
        return noDirigido;
    }
}
