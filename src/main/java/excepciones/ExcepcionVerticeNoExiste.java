/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

/**
 *
 * @author user
 */
public class ExcepcionVerticeNoExiste extends Exception{
    public ExcepcionVerticeNoExiste() {
        super("El vertice no existe");
    }

    public ExcepcionVerticeNoExiste(String mensaje) {
        super(mensaje);
    }
}
