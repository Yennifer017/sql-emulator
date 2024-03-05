package compi1.sqlemulator.traductor;

import compi1.sqlemulator.lexer_parser.sym;
import compi1.sqlemulator.traductor.components.Condition;

/**
 *
 * @author yenni
 */
public class FilterTraductor {

    public boolean validate(String value, Condition condition) {

        switch (condition.getCodeRelationalOp()) {
            case sym.MAYOR_IGUAL_QUE, sym.MAYOR_QUE, sym.MENOR_QUE, sym.MENOR_IGUAL_QUE:
                
            break;
            
        }
        
        
        
        
        switch (condition.getCodeRelationalOp()) {
            case sym.IGUAL:

                break;
            case sym.DIFERENTE:

                break;
            default:
                throw new AssertionError();
        }
        
        return false;
    }
}
