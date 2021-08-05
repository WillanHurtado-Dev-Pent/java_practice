/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.Islas;

import grafos.DFS;
import grafos.Digrafo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import utils.UtilRecorridos;

/**
 *
 * @author user
 */
public class IslaDirigido {

    private Digrafo digrafo;
    private DFS dfs;

    private int SIN_VERTICE_NO_MARCADO = -1;

    public IslaDirigido(Digrafo digrafo) {
        this.digrafo = digrafo;
    }

    private int contarIslas() {
        dfs = new DFS(digrafo);
        dfs.realizarDFS(0);
        int cantidadIslas = 0;

        while (!dfs.hayCaminoATodos()) {
            int proximoVerticeNoMarcado = verticeNoMarcadoConAdyacenteMarcado();

            if (proximoVerticeNoMarcado == SIN_VERTICE_NO_MARCADO) {
                cantidadIslas++;
                proximoVerticeNoMarcado = siguienteNoMarcado();
            }
            dfs.realizarDFS(proximoVerticeNoMarcado);
        }

        return cantidadIslas + 1;
    }

    private int verticeNoMarcadoConAdyacenteMarcado() {
        boolean existeVertice = false;

        int proximoVerticeNoMarcado = 0;
        while (proximoVerticeNoMarcado < digrafo.cantidadVertices() && !existeVertice) {
            if (!dfs.hayCaminoA(proximoVerticeNoMarcado)) {
                Iterator<Integer> adyacentesVertice = digrafo.adyacentesDeVertice(proximoVerticeNoMarcado).iterator();
                while (adyacentesVertice.hasNext() && !existeVertice) {
                    existeVertice = dfs.hayCaminoA(adyacentesVertice.next());
                }
                if (!existeVertice)
                    proximoVerticeNoMarcado++;
            } else
                proximoVerticeNoMarcado++;
        }
        if (proximoVerticeNoMarcado >= digrafo.cantidadVertices())
            return SIN_VERTICE_NO_MARCADO;
        else return proximoVerticeNoMarcado;
    }

    private int siguienteNoMarcado() {
        int proximoVerticeNoMarcado = 0;
        while (dfs.hayCaminoA(proximoVerticeNoMarcado) && proximoVerticeNoMarcado < digrafo.cantidadVertices()) {
            proximoVerticeNoMarcado++;
        }
        return proximoVerticeNoMarcado;
    }

    public List<List<Integer>> componentesIslas() {
        List<List<Integer>> listaDeComponentes = new LinkedList<>();
        int cantidadDeIslas = contarIslas();
        dfs = new DFS(digrafo);
        dfs.realizarDFS(0);

        UtilRecorridos marcaGlobal = new UtilRecorridos(digrafo.cantidadVertices());
        marcaGlobal.desmarcarTodos();
        int indiceIsla = 0;


        for (int i = 0; i < cantidadDeIslas; i++) {
            listaDeComponentes.add(new LinkedList<Integer>());
        }

        Iterable<Integer> verticesRecorridos = dfs.getRecorrido();
        for (int verticePrimerRecorrido : verticesRecorridos) {
            listaDeComponentes.get(0).add(verticePrimerRecorrido);
            marcaGlobal.marcarVertice(verticePrimerRecorrido);
        }


        while (!dfs.hayCaminoATodos()) {
            int proximoVerticeNoMarcado = verticeNoMarcadoConAdyacenteMarcado();

            if (!listaDeComponentes.get(indiceIsla).contains(proximoVerticeNoMarcado) && proximoVerticeNoMarcado != SIN_VERTICE_NO_MARCADO) {
                listaDeComponentes.get(indiceIsla).add(proximoVerticeNoMarcado);
                marcaGlobal.marcarVertice(proximoVerticeNoMarcado);
            }

            if (proximoVerticeNoMarcado == SIN_VERTICE_NO_MARCADO) {
                indiceIsla++;
                proximoVerticeNoMarcado = siguienteNoMarcado();
                dfs.realizarDFS(proximoVerticeNoMarcado);
                for (Integer verticeABuscar : verticesRecorridos) {
                    if (!marcaGlobal.estaMarcado(verticeABuscar)) {
                        listaDeComponentes.get(indiceIsla).add(verticeABuscar);
                        marcaGlobal.marcarVertice(verticeABuscar);
                    }
                }
            } else dfs.realizarDFS(proximoVerticeNoMarcado);
        }

        for (int i = 0; i < listaDeComponentes.size(); i++) {
            Collections.sort(listaDeComponentes.get(i));
        }

        return listaDeComponentes;
    }

    public int cantidadDeIslas() {
        return contarIslas();
    }
}

