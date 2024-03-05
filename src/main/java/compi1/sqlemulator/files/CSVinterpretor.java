
package compi1.sqlemulator.files;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import compi1.sqlemulator.exceptions.InvalidColumnException;
import compi1.sqlemulator.files.models.ColumnsCSV;
import compi1.sqlemulator.lexer_parser.Token;
import compi1.sqlemulator.traductor.components.Filtro;
import compi1.sqlemulator.util.BinarySearch;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JTextPane;

/**
 *
 * @author yenni
 */
public class CSVinterpretor {
    private JTextPane displayContent;

    public CSVinterpretor(JTextPane displayContent) {
        this.displayContent = displayContent;
    }
    
    public String[] getFirstLineInColumns() throws IOException, CsvValidationException{
        String csvTxt = displayContent.getText().replace("\t", "");
        StringReader reader = new StringReader(csvTxt);
        CSVReader csvReader = new CSVReader(reader);
        String[] firstLine = csvReader.readNext();
        csvReader.close();
        return firstLine;
    }
    
    private List<ColumnsCSV> getColumnsInOrder() throws IOException, CsvValidationException{
        String firstLine[] = this.getFirstLineInColumns();
        List<ColumnsCSV> columnsList = new ArrayList<>();
        for (int i = 0; i < firstLine.length; i++) {
            columnsList.add(new ColumnsCSV(i, firstLine[i]));
        }
        Collections.sort(columnsList);
        return columnsList;
    }
    
    public String getFirstLine() throws IOException, CsvValidationException{
        String firstLine[] = this.getFirstLineInColumns();
        return Arrays.toString(firstLine);
    }
    
    public int getTotalColumns() throws IOException, CsvValidationException{
        return this.getFirstLineInColumns().length;
    }
    
    public void setPosColumnToFilter(Filtro filtros, List<String> errorsList) 
            throws IOException, CsvValidationException, InvalidColumnException{
        List<ColumnsCSV> columns = this.getColumnsInOrder();
        for (int i = 0; i < filtros.getConditions().size(); i++) {
            Token current = filtros.getConditions().get(i).getNameColumnTkn();
            int pos = BinarySearch.searchColumns(columns, current.getLexem().toString());
            if(pos < 0){
                errorsList.add("Columna inexistente: " + current.getLexem() 
                        + ", fila: " + current.getLine() + ", col:" + current.getColumn());
            }else{
                filtros.getConditions().get(i).setNumberColumn(pos);
            }
        }
        if(!errorsList.isEmpty()){
            throw new InvalidColumnException();
        }
    }
    
}
