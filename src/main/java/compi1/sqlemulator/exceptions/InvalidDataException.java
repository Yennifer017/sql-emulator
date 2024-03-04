
package compi1.sqlemulator.exceptions;

/**
 *
 * @author yenni
 */
public class InvalidDataException extends Exception {

    private String mensaje;
    public InvalidDataException(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public InvalidDataException(){
        this.mensaje = "Datos invalidos ingresados";
    }

    
    @Override
    public String getMessage() {
        return this.mensaje;
    }
    
}
