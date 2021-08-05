/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexo;

import grafos.DFS;
import grafos.Digrafo;

/**
 *
 * @author user
 */
public class FuertementeConexo {
    private Conexo grafoConexo;

    private boolean esFuertementeConexo;

    public FuertementeConexo(Digrafo unDigrafo) {
        esFuertementeConexo = true;
        for (int i = 0; i < unDigrafo.cantidadVertices() && esFuertementeConexo; i++) {
            DFS dfs = new DFS(unDigrafo);
            dfs.realizarDFS(i);
            esFuertementeConexo = dfs.hayCaminoATodos();
        }
    }

    public boolean esFuertementeConexo() {
        return this.esFuertementeConexo;
    }
}
