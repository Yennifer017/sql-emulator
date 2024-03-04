
package compi1.sqlemulator.traductor;

import compi1.sqlemulator.exceptions.DirectoryException;
import compi1.sqlemulator.files.AdmiFiles;
import compi1.sqlemulator.lexer_parser.Token;
import compi1.sqlemulator.lexer_parser.sym;
import compi1.sqlemulator.traductor.components.PathProject;
import compi1.sqlemulator.traductor.models.AbsModel;
import compi1.sqlemulator.traductor.models.SelectModel;
import compi1.sqlemulator.traductor.util.Index;
import compi1.sqlemulator.traductor.util.SeparatorElements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yenni
 */
public class SelectTrans extends TranslatorStm{
    
    public SelectTrans(AdmiFiles admiFiles, List<String> semanticErrosList){
        super.semanticErrors = semanticErrosList;
        super.separator =  new SeparatorElements();
        super.admiFiles = admiFiles;
    }

    @Override
    public String translate(List<Token> tokens, Index index){
        SelectModel model = (SelectModel) this.separateTkns(tokens, index);
        try {
            admiFiles.openFile(model.getPath().getPathWithDots());
            return null;
        } catch (DirectoryException | IOException ex) {
            semanticErrors.add("El archivo " + model.getPath().getPathWithDots() + ", linea:"
                + model.getPath().getLine() + ", col:" + model.getPath().getCol() + " no existe");
            return "No se pudo ejecutar un select\n";
        }
    }

    @Override
    protected AbsModel separateTkns(List<Token> tokens, Index index) {
        index.increment(); //para pasar del token SELECT
        SelectModel model = new SelectModel();
        model.setColumns(separator.separateColumns(tokens, index)); //guardar columnas
        index.increment(); //para pasar el token EN
        Token firstTkn = tokens.get(index.getNum());
        model.setPath(new PathProject(  //guardar path
                separator.separatePath(tokens, index), 
                firstTkn.getColumn(), firstTkn.getLine()));
        if(tokens.get(index.getNum()).getType() == sym.FILTRAR){
            index.increment(); //para pasar al siguiente token, luego de filtrar
            model.setFiltro(separator.separateConditions(tokens, index));
        }
        index.increment(); //para salir de la instruccion
        return model;
    }
    
}
