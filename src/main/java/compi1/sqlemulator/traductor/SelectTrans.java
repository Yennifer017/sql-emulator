
package compi1.sqlemulator.traductor;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import compi1.sqlemulator.exceptions.DirectoryException;
import compi1.sqlemulator.exceptions.InvalidColumnException;
import compi1.sqlemulator.exceptions.InvalidDataException;
import compi1.sqlemulator.files.AdmiFiles;
import compi1.sqlemulator.lexer_parser.Token;
import compi1.sqlemulator.lexer_parser.sym;
import compi1.sqlemulator.traductor.components.Column;
import compi1.sqlemulator.traductor.components.PathProject;
import compi1.sqlemulator.traductor.models.AbsModel;
import compi1.sqlemulator.traductor.models.SelectModel;
import compi1.sqlemulator.traductor.util.Index;
import compi1.sqlemulator.traductor.util.SeparatorElements;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 *
 * @author yenni
 */
public class SelectTrans extends TranslatorStm{
    
    private FilterTraductor filterTraductor;
    private final String GUION = "-";
    public SelectTrans(AdmiFiles admiFiles, List<String> semanticErrosList){
        super.semanticErrors = semanticErrosList;
        super.separator =  new SeparatorElements();
        super.admiFiles = admiFiles;
        filterTraductor = new FilterTraductor();
        super.errorMss = ERROR_MSS + "select";
    }

    @Override
    public String translate(List<Token> tokens, Index index){
        SelectModel model = (SelectModel) this.separateTkns(tokens, index);
        try {
            admiFiles.openFile(model.getPath().getPathWithDots());
            admiFiles.getCSVinterpretor().setPosColumns(model.getColumns(), semanticErrors);
            if(model.getFiltro() != null){
                admiFiles.getCSVinterpretor().setPosColumnToFilter(model.getFiltro(), semanticErrors);
            }
            String resultado = this.select(
                    admiFiles.getCurrentDisplayTxt(), 
                    model, 
                    model.getFiltro()!=null, 
                    model.getColumns().isEmpty()
            );
            return admiFiles.getCSVinterpretor().convertCsvToTable(resultado);
        } catch (DirectoryException | IOException ex) {
            semanticErrors.add("El archivo " + model.getPath().getPathWithDots() + ", linea:"
                + model.getPath().getLine() + ", col:" + model.getPath().getCol() + " no existe");
            return "No se pudo ejecutar un select\n";
        } catch (CsvValidationException ex) {
            semanticErrors.add("No se pudo leer correctamente el archivo" + model.getPath().getPathWithDots() 
                    + ", asegurate que no este corrompido");
            return errorMss;
        } catch (InvalidDataException ex) {
            semanticErrors.add(ex.getMessage());
            return errorMss;
        } catch (InvalidColumnException ex) {
            return errorMss;
        }
    }
    
    private String select(String text, SelectModel model, boolean withFilters, boolean selectAll) 
            throws IOException, CsvValidationException, InvalidDataException{
        StringReader reader = new StringReader(text);
        CSVReader csvReader = new CSVReader(reader);
        String resultDisplay = "" ;
        if(selectAll){
            resultDisplay += admiFiles.getCSVinterpretor().getFirstLine();
            resultDisplay += GUION.repeat((int) (resultDisplay.length()*1.8)) + "\n";
        }else{ 
            resultDisplay += selectedColumns(model.getColumns());
        }
        String[] currentLine;
        csvReader.readNext(); //para pasar la primera columna
        while ((currentLine=csvReader.readNext()) != null) { //leemos todas las lineas 
            boolean result = withFilters? 
                    filterTraductor.validateFilters(model.getFiltro(), currentLine) : true;
            if(result){ //cuando la columna cumple las condiciones
                if(selectAll){
                    resultDisplay += String.join(",", currentLine) + "\n";
                }else{
                    for (int i = 0; i < model.getColumns().size(); i++) {
                        resultDisplay += currentLine[model.getColumns().get(i).getPosition()];
                        resultDisplay +=  i!=model.getColumns().size()-1? "," : "\n";
                    }
                }
            }
        }
        csvReader.close();
        return resultDisplay;
    }
    
    private String selectedColumns(List<Column> columns){
        String display = "";
        for (int i = 0; i < columns.size(); i++) {
            display += columns.get(i).getToken().getLexem().toString();
            display += i!=columns.size()-1 ? "," : "\n";
        }
        return display + GUION.repeat((int) (display.length()*1.8)) + "\n" ;
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
