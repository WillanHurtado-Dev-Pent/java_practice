/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexo;

import grafos.DFS;
import grafos.Grafo;

/**
 *
 * @author user
 */
public class Conexo {
    private DFS dfsGrafo;

    public Conexo(Grafo unGrafo) {
        dfsGrafo = new DFS(unGrafo);
        dfsGrafo.realizarDFS(0);
    }

    public boolean esConexo() {
        return dfsGrafo.hayCaminoATodos();
    }
}
