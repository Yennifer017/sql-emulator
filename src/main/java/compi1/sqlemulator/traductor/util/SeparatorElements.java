
package compi1.sqlemulator.traductor.util;

import compi1.sqlemulator.lexer_parser.Token;
import compi1.sqlemulator.lexer_parser.sym;
import compi1.sqlemulator.traductor.components.Filtro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yenni
 */
public class SeparatorElements {
    public String separatePath(List<Token> tokens, Index currentPos){
        String path = "";
        int typeTkn = tokens.get(currentPos.getNum()).getType();
        while (typeTkn == sym.IDENTIFICADOR || typeTkn == sym.PUNTO) {            
            path += tokens.get(currentPos.getNum()).getLexem().toString();
            currentPos.increment();
            typeTkn = tokens.get(currentPos.getNum()).getType();
        }
        return path;
    }
    
    public List<Token> separateColumns(List<Token> tokens, Index currentPos){
        List<Token> columns = new ArrayList<>();
        int typeTkn = tokens.get(currentPos.getNum()).getType();
        while (typeTkn == sym.IDENTIFICADOR || typeTkn == sym.COMA || typeTkn == sym.ASTERISCO) {            
            if(typeTkn == sym.IDENTIFICADOR){
                columns.add(tokens.get(currentPos.getNum()));
            }
            currentPos.increment();
            typeTkn = tokens.get(currentPos.getNum()).getType();
        }
        return columns;
    }
    
    public Filtro separateConditions(List<Token> tokens, Index currentPos){
        Filtro filtro  = new Filtro();
        
        int typeTkn = tokens.get(currentPos.getNum()).getType();
        while (typeTkn != sym.FIN_INSTRUCCION) {            
            //TODO: completar el metodo
            currentPos.increment();
            typeTkn = tokens.get(currentPos.getNum()).getType();
        }
        return filtro;
    }
    
    
}
