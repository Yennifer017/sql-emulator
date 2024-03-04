
package compi1.sqlemulator.traductor;

import compi1.sqlemulator.exceptions.DirectoryException;
import compi1.sqlemulator.exceptions.FileNotFoundEx;
import compi1.sqlemulator.files.AdmiFiles;
import compi1.sqlemulator.lexer_parser.Token;
import compi1.sqlemulator.lexer_parser.sym;
import compi1.sqlemulator.traductor.components.PathProject;
import compi1.sqlemulator.traductor.models.AbsModel;
import compi1.sqlemulator.traductor.models.InsertModel;
import compi1.sqlemulator.traductor.util.Index;
import compi1.sqlemulator.traductor.util.SeparatorElements;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author yenni
 */
public class InsertTrans extends TranslatorStm{
    
    public InsertTrans(AdmiFiles admiFiles, List<String> semanticErrosList){
        super.semanticErrors = semanticErrosList;
        super.separator =  new SeparatorElements();
        super.admiFiles = admiFiles;
    }

    @Override
    public String translate(List<Token> tokens, Index index) {
        InsertModel model = (InsertModel) this.separateTkns(tokens, index);
        try {
            admiFiles.openFile(model.getPath().getPathWithDots());
            
            return null;
        } catch (FileNotFoundEx | DirectoryException | IOException ex) {
            semanticErrors.add("El archivo " + model.getPath().getPathWithDots() + ", linea:"
                + model.getPath().getLine() + ", col:" + model.getPath().getCol() + " no existe");
            return "No se pudo ejecutar un insert\n";
        }
    }

    @Override
    protected AbsModel separateTkns(List<Token> tokens, Index index) {
        index.increment(2); //para pasar de INSERTAR EN
        InsertModel model = new InsertModel();
        
        Token firstTkn = tokens.get(index.getNum());
        model.setPath(new PathProject(separator.separatePath(tokens, index),
                firstTkn.getColumn(), firstTkn.getLine()));
        if(tokens.get(index.getNum()).getType() == sym.PARENTESIS_L){
            index.increment(); //para pasar el parentesis
            model.setColumns(separator.separateColumns(tokens, index));
            index.increment(); //para pasar el otro parentesis
        }
        index.increment(2); //para pasar de VALORES (
        model.setValues(separator.separateValues(tokens, index));
        index.increment(2); //para pasar de );
        return model;
    }

}
