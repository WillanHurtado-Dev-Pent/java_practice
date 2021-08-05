/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafosPesados;

/**
 *
 * @author user
 */
public class AdyacenteConPeso implements Comparable<AdyacenteConPeso> {

    private int indiceVertice;
    private double peso;

    public AdyacenteConPeso(int indiceVertice) {
        this.indiceVertice = indiceVertice;
    }

    public AdyacenteConPeso(int indiceVertice, double peso) {
        this.indiceVertice = indiceVertice;
        this.peso = peso;
    }

    public int getIndiceVertice() {
        return indiceVertice;
    }

    public void setIndiceVertice(int indiceVertice) {
        this.indiceVertice = indiceVertice;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public int compareTo(AdyacenteConPeso vertice) {
        Integer esteVerticce = this.indiceVertice;
        Integer elOtroVertice = vertice.indiceVertice;
        return esteVerticce.compareTo(elOtroVertice);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.indiceVertice;
        return hash;
    }

    @Override
    public boolean equals(Object otro) {
        if (otro == null) {
            return false;
        }
        if(getClass() != otro.getClass())
        {
            return false;
        }
        AdyacenteConPeso otroCasteado = (AdyacenteConPeso) otro;
        return this.indiceVertice == otroCasteado.indiceVertice;
    }

    @Override
    public String toString() {
        return "(" + indiceVertice + "," + peso + ")";
    }
}