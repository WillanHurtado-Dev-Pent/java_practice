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
public class ExcepcionAristaYaExiste extends Exception{
    public ExcepcionAristaYaExiste() {
        super("La arista ya existe");
    }

    public ExcepcionAristaYaExiste(String mensaje) {
        super(mensaje);
    }
}
