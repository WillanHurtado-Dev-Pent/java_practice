package excepciones;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class ExcepcionVerticeYaExiste extends Exception{
    public ExcepcionVerticeYaExiste() {
        super("El vertice ya existe");
    }

    public ExcepcionVerticeYaExiste(String mensaje) {
        super(mensaje);
    }
}
