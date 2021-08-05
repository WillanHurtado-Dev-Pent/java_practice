/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class UtilRecorridos {

    private List<Boolean> marcados;

    private int nroVertices;

    public UtilRecorridos(int nroVertices) {
        this.nroVertices = nroVertices;
    }

    public void desmarcarTodos() {
        marcados = new ArrayList<>();
        for (int i = 0; i < nroVertices; i++) {
            marcados.add(Boolean.FALSE);
        }
    }

    public void marcarVertice(int posVertice) {
        marcados.set(posVertice, Boolean.TRUE);
    }

    public void desmarcarVertice(int posVertice) {
        marcados.set(posVertice, Boolean.FALSE);
    }

    public boolean estaMarcado(int posVertice) {
        return marcados.get(posVertice);
    }

    public boolean estanTodosMarcados() {
        for (Boolean marcado : this.marcados) {
            if (!marcado) {
                return false;
            }
        }
        return true;
    }

    public int siguienteNoMarcado() {
        int proximoVerticeNoMarcado = 0;
        while (estaMarcado(proximoVerticeNoMarcado) && proximoVerticeNoMarcado < nroVertices) {
            proximoVerticeNoMarcado++;
        }
        return proximoVerticeNoMarcado;
    }
}
