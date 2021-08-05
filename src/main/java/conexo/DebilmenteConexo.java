/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexo;

import grafos.DFS;
import grafos.Digrafo;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author user
 */
public class DebilmenteConexo {
    private DFS dfs;
    private Digrafo digrafo;

    private int SIN_VERTICE_NO_MARCADO = -1;
    public DebilmenteConexo(Digrafo unDigrafo) {
        this.digrafo = unDigrafo;
        DFS dfs = new DFS(digrafo);
    }

    public boolean esDebilmenteConexo() {
        
        dfs.realizarDFS(0);
        int proximoVerticeNoMarcado = 0;
        while (proximoVerticeNoMarcado != SIN_VERTICE_NO_MARCADO) {
            proximoVerticeNoMarcado = verticeNoMarcadoConAdyacenteMarcado();
            if(proximoVerticeNoMarcado != SIN_VERTICE_NO_MARCADO)
                dfs.realizarDFS(proximoVerticeNoMarcado);
        }
        return dfs.hayCaminoATodos();
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

}
