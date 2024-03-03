
package compi1.sqlemulator.traductor;

import compi1.sqlemulator.files.AdmiFiles;
import compi1.sqlemulator.lexer_parser.Token;
import compi1.sqlemulator.lexer_parser.sym;
import compi1.sqlemulator.traductor.util.Index;
import java.util.List;

/**
 *
 * @author yenni
 */
public class Translator {
    
    private DeleteTrans deleteTrans;
    private InsertTrans insertTrans;
    private SelectTrans selectTrans;
    private UpdateTrans updateTrans;
    private AdmiFiles admiFiles;
    private Index index;
    
    public Translator(AdmiFiles admiFiles){
        this.admiFiles = admiFiles;
        deleteTrans = new DeleteTrans();
        insertTrans = new InsertTrans();
        selectTrans =  new SelectTrans(admiFiles);
        updateTrans = new UpdateTrans();
        index = new Index(0);
    }
    
    public String translateCode(List<Token> tokens){
        index.restart();
        String output = "Output\n";
        while (index.getNum() < tokens.size()) {            
            switch (tokens.get(index.getNum()).getType()) {
                case sym.SELECCIONAR:
                    output += selectTrans.translate(tokens, index);
                    break;
                case sym.INSERTAR:
                    //insertTrans.separateTkns(tokens, index);
                    break;
                case sym.ACTUALIZAR:
                    //updateTrans.separateTkns(tokens, index);
                    break;
                case sym.ELIMINAR:
                    //deleteTrans.separateTkns(tokens, index);
                    break;
                default: //cuando devuelve el EOF
                    index.increment();
            }
        }
        return output;
    }
}
