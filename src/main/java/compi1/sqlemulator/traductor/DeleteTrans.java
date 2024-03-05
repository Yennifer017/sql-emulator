
package compi1.sqlemulator.traductor;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import compi1.sqlemulator.exceptions.DirectoryException;
import compi1.sqlemulator.exceptions.InvalidColumnException;
import compi1.sqlemulator.exceptions.InvalidDataException;
import compi1.sqlemulator.files.AdmiFiles;
import compi1.sqlemulator.lexer_parser.Token;
import compi1.sqlemulator.lexer_parser.sym;
import compi1.sqlemulator.traductor.components.Filtro;
import compi1.sqlemulator.traductor.components.PathProject;
import compi1.sqlemulator.traductor.models.AbsModel;
import compi1.sqlemulator.traductor.models.DeleteModel;
import compi1.sqlemulator.traductor.util.Index;
import compi1.sqlemulator.traductor.util.SeparatorElements;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
/**
 *
 * @author yenni
 */
public class DeleteTrans extends TranslatorStm {

    private FilterTraductor filterTraductor;
    private int totalDeleted;
    public DeleteTrans(AdmiFiles admiFiles, List<String> semanticErrosList) {
        super.semanticErrors = semanticErrosList;
        super.separator =  new SeparatorElements();
        super.admiFiles = admiFiles;
        super.errorMss = ERROR_MSS + "delete";
        filterTraductor = new FilterTraductor();
        totalDeleted = 0;
    }

    
    @Override
    public String translate(List<Token> tokens, Index index) {
        totalDeleted = 0;
        DeleteModel model = (DeleteModel) this.separateTkns(tokens, index);
        try {
            admiFiles.openFile(model.getPath().getPathWithDots());
            if(model.getFiltro() == null){ //cuando no hay filtros
                admiFiles.setNewContent(admiFiles.getCSVinterpretor().getFirstLine());
                return "Eliminacion exitosa, se eliminaron totas las filas existentes";
            }else{
                admiFiles.getCSVinterpretor().setPosColumnToFilter(model.getFiltro(), semanticErrors);
                admiFiles.setNewContent(deleteWithFilters(
                        admiFiles.getCurrentDisplayTxt(), model.getFiltro()));
                return "Eliminacion exitosa, total de filas eliminadas: " + totalDeleted;
            }
        } catch ( DirectoryException | IOException ex) {
            semanticErrors.add("El archivo " + model.getPath().getPathWithDots() + ", linea:"
                + model.getPath().getLine() + ", col:" + model.getPath().getCol() + " no existe");
            return errorMss;
        } catch (CsvValidationException ex) {
            semanticErrors.add("No se pudo leer correctamente el archivo" + model.getPath().getPathWithDots() 
                    + ", asegurate que no este corrompido");
            return errorMss;
        } catch (InvalidColumnException ex) {
            return errorMss;
        } catch (InvalidDataException ex) {
            semanticErrors.add(ex.getMessage());
            return errorMss;
        }
    }
    
    private String deleteWithFilters(String text, Filtro filter) 
            throws IOException, CsvValidationException, InvalidDataException{
        StringReader reader = new StringReader(text);
        CSVReader csvReader = new CSVReader(reader);
        String resultDisplay = String.join(",", csvReader.readNext()) + "\n";
        String[] currentLine;
        while ((currentLine=csvReader.readNext()) != null) { //leemos todas las lineas 
            boolean result = false;
            switch (filter.getCodeLogicRelational()) {
                case sym.AND:
                    for (int i = 0; i < filter.getConditions().size(); i++) {
                        result = filterTraductor.validate(
                                currentLine[filter.getConditions().get(i).getNumberColumn()], 
                                filter.getConditions().get(i));
                        if(result == false){
                            break;
                        }
                    }
                    break;
                default: //sym.Or //no relational operator
                    for (int i = 0; i < filter.getConditions().size(); i++) {
                        result = filterTraductor.validate(
                                currentLine[filter.getConditions().get(i).getNumberColumn()], 
                                filter.getConditions().get(i));
                        if(result == true){
                            break;
                        }
                    }
            }
            if(!result){
                resultDisplay += String.join(",", currentLine) + "\n";
            }else{ //cuando si se debe eliminar
                totalDeleted ++;
            }
        }
        csvReader.close();
        return resultDisplay;
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
