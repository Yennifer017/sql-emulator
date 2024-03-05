
package compi1.sqlemulator.traductor;

import compi1.sqlemulator.files.AdmiFiles;
import compi1.sqlemulator.lexer_parser.Token;
import compi1.sqlemulator.lexer_parser.sym;
import compi1.sqlemulator.traductor.util.Index;
import java.util.ArrayList;
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
    private Index index;
    
    private List<String> semanticErros;
    
    public Translator(AdmiFiles admiFiles){
        semanticErros = new ArrayList<>();
        deleteTrans = new DeleteTrans(admiFiles, semanticErros);
        insertTrans = new InsertTrans(admiFiles, semanticErros);
        selectTrans =  new SelectTrans(admiFiles, semanticErros);
        updateTrans = new UpdateTrans(admiFiles, semanticErros);
        index = new Index(0);
    }
    
    public String translateCode(List<Token> tokens){
        index.restart();
        semanticErros.clear();
        String output = "Output\n";
        while (index.getNum() < tokens.size()) {            
            switch (tokens.get(index.getNum()).getType()) {
                case sym.SELECCIONAR -> output += selectTrans.translate(tokens, index);
                case sym.INSERTAR -> output += insertTrans.translate(tokens, index);
                case sym.ACTUALIZAR -> output += updateTrans.translate(tokens, index);
                case sym.ELIMINAR -> output += deleteTrans.translate(tokens, index);
                default -> index.increment();//cuando devuelve el EOF
            }
        }
        return output;
    }

    public List<String> getSemanticErros() {
        return semanticErros;
    }
    
}
