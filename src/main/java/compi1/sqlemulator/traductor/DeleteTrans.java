
package compi1.sqlemulator.traductor;

import compi1.sqlemulator.exceptions.DirectoryException;
import compi1.sqlemulator.exceptions.FileNotFoundEx;
import compi1.sqlemulator.files.AdmiFiles;
import compi1.sqlemulator.lexer_parser.Token;
import compi1.sqlemulator.lexer_parser.sym;
import compi1.sqlemulator.traductor.components.PathProject;
import compi1.sqlemulator.traductor.models.AbsModel;
import compi1.sqlemulator.traductor.models.DeleteModel;
import compi1.sqlemulator.traductor.util.Index;
import compi1.sqlemulator.traductor.util.SeparatorElements;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author yenni
 */
public class DeleteTrans extends TranslatorStm {

    public DeleteTrans(AdmiFiles admiFiles, List<String> semanticErrosList) {
        super.semanticErrors = semanticErrosList;
        super.separator =  new SeparatorElements();
        super.admiFiles = admiFiles;
    }

    
    @Override
    public String translate(List<Token> tokens, Index index) {
        DeleteModel model = (DeleteModel) this.separateTkns(tokens, index);
        try {
            admiFiles.openFile(model.getPath().getPathWithDots());
            if(model.getFiltro() == null){ //operar cuando hay un vacio
                
            }else{
                
            }
            return null;
        } catch (FileNotFoundEx | DirectoryException | IOException ex) {
            semanticErrors.add("El archivo " + model.getPath().getPathWithDots() + ", linea:"
                + model.getPath().getLine() + ", col:" + model.getPath().getCol() + " no existe");
            return "No se pudo ejecutar un delete\n";
        }
    }

    @Override
    protected AbsModel separateTkns(List<Token> tokens, Index index) {
        index.increment(); //para pasar del token ELIMINAR
        index.increment(); //para pasar del token EN
        DeleteModel model = new DeleteModel();
        Token firstTkn = tokens.get(index.getNum());
        model.setPath(new PathProject(separator.separatePath(tokens, index),
                firstTkn.getColumn(), firstTkn.getLine()));
        if(tokens.get(index.getNum()).getType() == sym.FILTRAR){
            index.increment(); //para pasar al siguiente token, luego de filtrar
            model.setFiltro(separator.separateConditions(tokens, index));
        }
        index.increment(); //para salir de la instruccion
        return model;
    }
    
}
