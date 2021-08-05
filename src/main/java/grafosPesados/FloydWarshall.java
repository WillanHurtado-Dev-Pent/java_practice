/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafosPesados;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author user
 */
public class FloydWarshall {

    private double INFINITO = (float) (1.0 / 0.0);
    DigrafoPesado grafo;
    int cantidadVertices;
    List<List<Double>> matrizDeCaminos;

    public FloydWarshall(DigrafoPesado unDigrafo) {
        cantidadVertices = unDigrafo.cantidadVertices();
        this.grafo = unDigrafo;
        matrizDeCaminos = new LinkedList<>();
        for (int i = 0; i < cantidadVertices; i++) {
            matrizDeCaminos.add(new LinkedList<Double>());
        }
        actualizarMatriz();
    }

    public void actualizarMatriz() {

        for (int i = 0; i < cantidadVertices; i++) {

            List<Integer> listaDeAdyacentes = (List<Integer>) grafo.adyacentesDeVertice(i);
            for (int j = 0; j < cantidadVertices; j++) {
                if (i == j) matrizDeCaminos.get(i).add(0.0);
                else if (listaDeAdyacentes.contains(j)) {
                    double costoAInsertar = grafo.getPeso(i, j);
                    matrizDeCaminos.get(i).add(costoAInsertar);
                } else {
                    matrizDeCaminos.get(i).add(INFINITO);
                }
            }
        }


        for (int i = 0; i < cantidadVertices; i++) {
            for (int j = 0; j < cantidadVertices; j++) {
                for (int k = 0; k < cantidadVertices; k++) {
                    double costoCandidato = matrizDeCaminos.get(j).get(i) + matrizDeCaminos.get(i).get(k);
                    double jk = matrizDeCaminos.get(j).get(k);
                    if(costoCandidato < jk)
                        matrizDeCaminos.get(j).set(k,costoCandidato);
                }
            }
        }
    }

    public List<List<Double>> getMatrizDeCaminos() {
        return this.matrizDeCaminos;
    }


    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < matrizDeCaminos.size(); i++) {
            buffer.append(matrizDeCaminos.get(i).toString());
            buffer.append("\n");
        }
        return buffer.toString();
    }
}