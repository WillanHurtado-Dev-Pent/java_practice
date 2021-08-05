package Main;

import ciclos.CiclosDirigido;
import ciclos.CiclosNoDirigido;
import conexo.DebilmenteConexo;
import excepciones.ExcepcionAristaNoExiste;
import excepciones.ExcepcionAristaYaExiste;
import excepciones.ExcepcionNroVerticesInvalido;
import grafos.DFS;
import grafos.Digrafo;
import grafos.Grafo;
import grafos.Islas.IslaDirigido;
import grafos.Islas.IslaNoDirigido;
import grafosPesados.DigrafoPesado;
import grafosPesados.FloydWarshall;
import grafosPesados.GrafoPesado;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public class Main {

    static public void main(String args[]) throws ExcepcionAristaYaExiste, ExcepcionNroVerticesInvalido, ExcepcionAristaNoExiste {

        int ejercicio = 0;
        boolean salida = false;

        //GRAFO A UTILIZAR
        Grafo grafo = new Grafo(8);
        grafo.insertarArista(0, 1);
        grafo.insertarArista(0, 0);
        grafo.insertarArista(1, 3);
        grafo.insertarArista(2, 1);
        grafo.insertarArista(2, 4);
        grafo.insertarArista(4, 3);
        grafo.insertarArista(5, 6);
        grafo.insertarArista(6, 7);

        //DIGRAFO A UTILIZAR
        Digrafo grafoDirigido = new Digrafo(8);

        grafoDirigido.insertarArista(0, 1);
        grafoDirigido.insertarArista(0, 0);
        grafoDirigido.insertarArista(1, 3);
        grafoDirigido.insertarArista(2, 1);
        grafoDirigido.insertarArista(2, 4);
        grafoDirigido.insertarArista(4, 3);
        grafoDirigido.insertarArista(5, 6);
        grafoDirigido.insertarArista(6, 7);

        //GRAFO PESADO NO DIRIGIDO
        GrafoPesado grafoPesado = new GrafoPesado(8);
        grafoPesado.insertarArista(0, 1, 5);
        grafoPesado.insertarArista(0, 0, 23);
        grafoPesado.insertarArista(1, 3, 9);
        grafoPesado.insertarArista(2, 1, 5);
        grafoPesado.insertarArista(2, 4, 2);
        grafoPesado.insertarArista(4, 3, 14);
        grafoPesado.insertarArista(5, 6, 4);
        grafoPesado.insertarArista(6, 7, 11);

        //GRAFO PESADO DIRIGIDO
        DigrafoPesado grafoPesadoDirigido = new DigrafoPesado(8);
        grafoPesadoDirigido.insertarArista(0, 1, 6);
        grafoPesadoDirigido.insertarArista(0, 0, 5);
        grafoPesadoDirigido.insertarArista(1, 3, 9);
        grafoPesadoDirigido.insertarArista(2, 1, 12);
        grafoPesadoDirigido.insertarArista(2, 4, 23);
        grafoPesadoDirigido.insertarArista(4, 3, 11);
        grafoPesadoDirigido.insertarArista(5, 6, 16);
        grafoPesadoDirigido.insertarArista(6, 7, 2);

        while (!salida) {
            System.out.println("1)Componentes de islas en un grafo dirigido");
            System.out.println("2)Tiene ciclos en un grafo dirigido");
            System.out.println("3)Es debilmente conexo");
            System.out.println("4)En que vertices del grafo no dirigido hay ciclos");
            System.out.println("5)Numero de islas en grafo No dirigido");
            System.out.println("6)Numero de islas en grafo dirigido");
            System.out.println("7)grafo dirigido, Algoritmo de Warshall");
            System.out.println("8)grafo dirigido, Floyd Warshall entre A y B");
            System.out.println("9)Componentes conexas en un grafo");
            System.out.println("10)Djisktra en un grafo dirigido, camino de costo minimo");
            System.out.println("11)Djisktra en un grafo dirigido, costo y caminos");
            System.out.println("12)Kruskal grafo no dirigido pesado");
            System.out.println("13)Prim grafo no dirigido pesado");
            System.out.println("14)Grafo dirigido, ordenamiento topologico");
            System.out.println("15)Grafo dirigido pesado, Ford-Fulkerson");
            System.out.println("16)Grafo dirigido,Desde que vertices se puede llegar a un vertice A");


            Scanner leer = new Scanner(System.in);
            ejercicio = leer.nextInt();

            switch (ejercicio) {
                case 1:
                    IslaDirigido isla = new IslaDirigido(grafoDirigido);
                    System.out.println(isla.componentesIslas());
                    break;
                case 2:
                    ciclos.CiclosDirigido ciclosDirigido = new CiclosDirigido(grafoDirigido);
                    System.out.println(ciclosDirigido.existenCiclos());
                    break;
                case 3:
                    conexo.DebilmenteConexo conexo = new DebilmenteConexo(grafoDirigido);
                    System.out.println(conexo.esDebilmenteConexo());
                    break;
                case 4:
                    ciclos.CiclosNoDirigido ciclo = new CiclosNoDirigido(grafo);
                    System.out.println(ciclo.verticesConCiclos());
                    break;
                case 5:
                    IslaNoDirigido islas = new IslaNoDirigido(grafo);
                    System.out.println(islas.cantidadDeIslas());
                    break;
                case 6:
                    IslaDirigido islasD = new IslaDirigido(grafoDirigido);
                    System.out.println(islasD.cantidadDeIslas());
                    break;
                case 7:
                    FloydWarshall floyd = new FloydWarshall(grafoPesadoDirigido);
                    System.out.println(floyd.getMatrizDeCaminos());
                    break;
                case 8:
                    FloydWarshall floyd2 = new FloydWarshall(grafoPesadoDirigido);
                    System.out.println(floyd2.getMatrizDeCaminos());
                    break;

                case 14:
                System.out.println(new DFS(grafoDirigido).ordenamientoTopologico());
                    break;
            }
        }

        //DFS dfs = new DFS(grafo);
        //dfs.realizarDFS(0);
        //System.out.println(dfs.getRecorrido());
        //System.out.println(new IslaDirigido(grafoDirigido).cantidadIslas());
        //System.out.println(new IslaDirigido(grafoDirigido).getIslas());
        

    }
}
