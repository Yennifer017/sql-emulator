
package compi1.sqlemulator.traductor;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import compi1.sqlemulator.exceptions.DirectoryException;
import compi1.sqlemulator.exceptions.InvalidColumnException;
import compi1.sqlemulator.exceptions.InvalidDataException;
import compi1.sqlemulator.files.AdmiFiles;
import compi1.sqlemulator.lexer_parser.Token;
import compi1.sqlemulator.lexer_parser.sym;
import compi1.sqlemulator.traductor.components.Assignation;
import compi1.sqlemulator.traductor.components.PathProject;
import compi1.sqlemulator.traductor.models.AbsModel;
import compi1.sqlemulator.traductor.models.UpdateModel;
import compi1.sqlemulator.traductor.util.Index;
import compi1.sqlemulator.traductor.util.SeparatorElements;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 *
 * @author yenni
 */
public class UpdateTrans extends TranslatorStm {

    private int totalUpdated;
    private FilterTraductor filterTraductor;
    public UpdateTrans(AdmiFiles admiFiles, List<String> semanticErrosList) {
        super.semanticErrors = semanticErrosList;
        super.separator =  new SeparatorElements();
        super.admiFiles = admiFiles;
        totalUpdated = 0;
        super.errorMss = ERROR_MSS + "update";
        filterTraductor = new FilterTraductor();
    }
    
    @Override
    public String translate(List<Token> tokens, Index index) {
        totalUpdated = 0;
        UpdateModel model = (UpdateModel) this.separateTkns(tokens, index);
        try {
            admiFiles.openFile(model.getPath().getPathWithDots());
            admiFiles.getCSVinterpretor().setPosColumnsToAsignation(
                        model.getColumsAsignations(), semanticErrors);
            if(model.getFiltro() == null){
                admiFiles.setNewContent(update(admiFiles.getCurrentDisplayTxt(), model, false));
                return "Actualizacion exitosa, se actualizo todo el documento";
            }else{
                admiFiles.getCSVinterpretor().setPosColumnToFilter(model.getFiltro(), semanticErrors);
                admiFiles.setNewContent(update(admiFiles.getCurrentDisplayTxt(), model, true));
                return "Actualizacion exitosa, " + totalUpdated + " columnas fueron actualizadas";
            }
        } catch (DirectoryException | IOException ex) {
            semanticErrors.add("El archivo " + model.getPath().getPathWithDots() + ", linea:"
                + model.getPath().getLine() + ", col:" + model.getPath().getCol() + " no existe");
            return "No se pudo ejecutar un select\n";
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
    
    private String update(String text, UpdateModel model, boolean withFilters) 
            throws IOException, CsvValidationException, InvalidDataException{
        StringReader reader = new StringReader(text);
        CSVReader csvReader = new CSVReader(reader);
        String resultDisplay = String.join(",", csvReader.readNext()) + "\n"; //primera columna
        String[] currentLine;
        while ((currentLine=csvReader.readNext()) != null) { //leemos todas las lineas 
            boolean result = withFilters? 
                    filterTraductor.validateFilters(model.getFiltro(), currentLine) : true;
            if(result){ //cuando se debe actualizar
                for (int i = 0; i < model.getColumsAsignations().size(); i++) {
                    Assignation assignation = model.getColumsAsignations().get(i);
                    String newValue = assignation.getValue().getLexem().toString();
                    if(assignation.getValue().getType() == sym.CADENA){
                        newValue = newValue.replace("\"", "");
                    }
                    currentLine[assignation.getPositionColumn()] = newValue;
                }
                totalUpdated++;
            }
            resultDisplay += String.join(",", currentLine) + "\n";
        }
        csvReader.close();
        return resultDisplay;
    }

    @Override
    protected AbsModel separateTkns(List<Token> tokens, Index index) {
        index.increment(2); //para pasar de ACTUALIZAR EN
        UpdateModel model = new UpdateModel();
        Token firstTkn = tokens.get(index.getNum());
        model.setPath(new PathProject(  //guardar path
                separator.separatePath(tokens, index),
                firstTkn.getColumn(), firstTkn.getLine()));
        index.increment(); //para pasar del token ASIGNAR
        model.setColumsAsignations(separator.separateColAsign(tokens, index));
        if(tokens.get(index.getNum()).getType() == sym.FILTRAR){
            index.increment(); //para pasar al siguiente token, luego de filtrar
            model.setFiltro(separator.separateConditions(tokens, index));
        }
        index.increment(); //para salir de la instruccion
        return model;
    }
    
}
