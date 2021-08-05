/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciclos;

import excepciones.ExcepcionNroVerticesInvalido;
import grafos.Digrafo;
import grafos.Warshall;
import java.util.ArrayList;
import java.util.List;
import utils.UtilRecorridos;

/**
 *
 * @author user
 */
public class CiclosDirigido {

    Digrafo digrafo;
    int cantidadVertices;
    List<UtilRecorridos> matrizDeCaminos;
    Warshall warshall;
    public CiclosDirigido(Digrafo unDigrafo) throws ExcepcionNroVerticesInvalido {
        cantidadVertices = unDigrafo.cantidadVertices();
        this.digrafo = unDigrafo;
        warshall = new Warshall(unDigrafo);
    }

    public boolean existenCiclos() {
        boolean existeCiclo = false;
        int indiceDiagonal = 0;
        matrizDeCaminos = warshall.getMatrizDeCaminos();
        while (!existeCiclo && indiceDiagonal < cantidadVertices)
        {
            existeCiclo = matrizDeCaminos.get(indiceDiagonal).estaMarcado(indiceDiagonal);
            indiceDiagonal++;
        }
        return existeCiclo;
    }




}
