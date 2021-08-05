/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.Islas;

import grafos.DFS;
import grafos.Grafo;
import java.util.ArrayList;
import java.util.List;
import utils.UtilRecorridos;

/**
 *
 * @author user
 */
public class IslaNoDirigido {
    private Grafo grafo;
    private int cantidadIslas;
    private UtilRecorridos controlMarcados;

    public IslaNoDirigido(Grafo grafo) {
        this.grafo = grafo;
        this.controlMarcados = new UtilRecorridos(grafo.cantidadVertices());
        this.controlMarcados.desmarcarTodos();
        this.cantidadIslas = contarIslas(0, 0);

    }

    private int contarIslas(int posVertice, int cantidadIslas) {
        if (controlMarcados.estanTodosMarcados())
            return cantidadIslas;

        cantidadIslas++;
        DFS dfs = new DFS(grafo);
        dfs.realizarDFS(posVertice);
        Iterable<Integer> recorridoIsla = dfs.getRecorrido();
        for (Integer verticeAMarcar : recorridoIsla) {
            controlMarcados.marcarVertice(verticeAMarcar);
        }

        for (int posVerticeNoMarcado = 0; posVerticeNoMarcado < grafo.cantidadVertices(); posVerticeNoMarcado++) {
            if (!controlMarcados.estaMarcado(posVerticeNoMarcado))
                cantidadIslas = contarIslas(posVerticeNoMarcado, cantidadIslas);
        }
        return cantidadIslas;
    }

    public int cantidadDeIslas() {
        return this.cantidadIslas;
    }

}
