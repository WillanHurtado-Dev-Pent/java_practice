/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

/**
 *
 * @author user
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import utils.UtilRecorridos;


public class Warshall {

    Digrafo digrafo;
    UtilRecorridos recorrido;
    int cantidadVertices;
    List<UtilRecorridos> matrizDeCaminos;

    public Warshall(Digrafo unDigrafo) {
        cantidadVertices = unDigrafo.cantidadVertices();
        this.digrafo = unDigrafo;
        matrizDeCaminos = new ArrayList<>();
        for (int i = 0; i < cantidadVertices; i++) {
            matrizDeCaminos.add(new UtilRecorridos(cantidadVertices));
            matrizDeCaminos.get(i).desmarcarTodos();
        }
        actualizarMatriz();
    }

    public void actualizarMatriz() {
        for (int i = 0; i < cantidadVertices; i++) {
            List<Integer> adyacentes = (List<Integer>) digrafo.adyacentesDeVertice(i);
            for (int j = 0; j < adyacentes.size(); j++) {
                int verticeAMarcar = adyacentes.get(j);
                matrizDeCaminos.get(i).marcarVertice(verticeAMarcar);
            }
        }

        for (int i = 0; i < cantidadVertices; i++) {
            for (int j = 0; j < cantidadVertices; j++) {
                for (int k = 0; k < cantidadVertices; k++) {
                    if (matrizDeCaminos.get(j).estaMarcado(i) && matrizDeCaminos.get(i).estaMarcado(k))
                        matrizDeCaminos.get(j).marcarVertice(k);
                }
            }
        }
    }

    public List<UtilRecorridos> getMatrizDeCaminos()
    {
        return this.matrizDeCaminos;
    }

    private List<List<Integer>> getCaminos(){
        List<List<Integer>> caminos = new LinkedList<>();
        for (int i = 0; i < cantidadVertices; i++) {
            caminos.add(new LinkedList<Integer>());
            for (int j = 0; j < cantidadVertices; j++) {
                if(matrizDeCaminos.get(i).estaMarcado(j))
                    caminos.get(i).add(j);
            }
        }
        return caminos;
    }

    public String getCaminosDeVertices()
    {
        List<List<Integer>> caminos = getCaminos();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < caminos.size(); i++) {
            buffer.append(i + ": ");
            buffer.append(caminos.get(i).toString());
            buffer.append("\n");
        }
        return buffer.toString();
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
