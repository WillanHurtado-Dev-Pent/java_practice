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
public class ExcepcionNroVerticesInvalido extends Exception{
    public ExcepcionNroVerticesInvalido() {
        super("Numero de Vertices Invalido");
    }

    public ExcepcionNroVerticesInvalido(String mensaje) {
        super(mensaje);
    }
}
