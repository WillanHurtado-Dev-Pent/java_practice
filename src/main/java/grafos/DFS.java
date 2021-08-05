/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.util.ArrayList;
import java.util.List;
import utils.UtilRecorridos;

/**
 *
 * @author user
 */
public class DFS {
    private List<Integer> recorrido;
    private Grafo grafo;
    protected UtilRecorridos control;
    
    public DFS(Grafo g){
        this.grafo = g;
        this.recorrido = new ArrayList<>();
        control = new UtilRecorridos(grafo.cantidadVertices());
        control.desmarcarTodos();
    }
    
    public void realizarDFS(int vertice){
        grafo.validarVertice(vertice);
        control.marcarVertice(vertice);
        recorrido.add(vertice);
        Iterable<Integer> adyacentes = this.grafo.adyacentesDeVertice(vertice);
        for (Integer adyacente : adyacentes) {
            if(!control.estaMarcado(adyacente)){
                realizarDFS(adyacente);
            }
        }
    }
    
    public List ordenamientoTopologico(){
        this.recorrido = new ArrayList<>();
        this.control.desmarcarTodos();
        while(!control.estanTodosMarcados()){
            int s = control.siguienteNoMarcado();
            realizarDFS(s);
        }
        return recorrido;
    }
    
    public boolean hayCaminoA(int vertice) {
        this.grafo.validarVertice(vertice);
        return this.control.estaMarcado(vertice);
    }

    public boolean hayCaminoATodos(){
        return this.control.estanTodosMarcados();
    }

    public Iterable<Integer> getRecorrido(){
        return this.recorrido;
    }
    
    public UtilRecorridos getControl(){
        return this.control;
    }
}
