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
public class ExcepcionAristaNoExiste extends Exception{
    public ExcepcionAristaNoExiste() {
        super("La arista no existe");
    }

    public ExcepcionAristaNoExiste(String mensaje) {
        super(mensaje);
    }
}
