package compi1.sqlemulator.files;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import compi1.sqlemulator.exceptions.InvalidColumnException;
import compi1.sqlemulator.files.models.ColumnsCSV;
import compi1.sqlemulator.lexer_parser.Token;
import compi1.sqlemulator.traductor.components.Assignation;
import compi1.sqlemulator.traductor.components.Column;
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

    public String[] getFirstLineInColumns() throws IOException, CsvValidationException {
        String csvTxt = displayContent.getText().replace("\t", "");
        StringReader reader = new StringReader(csvTxt);
        CSVReader csvReader = new CSVReader(reader);
        String[] firstLine = csvReader.readNext();
        csvReader.close();
        return firstLine;
    }

    private List<ColumnsCSV> getColumnsInOrder() throws IOException, CsvValidationException {
        String firstLine[] = this.getFirstLineInColumns();
        List<ColumnsCSV> columnsList = new ArrayList<>();
        for (int i = 0; i < firstLine.length; i++) {
            columnsList.add(new ColumnsCSV(i, firstLine[i]));
        }
        Collections.sort(columnsList);
        return columnsList;
    }

    public String getFirstLine() throws IOException, CsvValidationException {
        String firstLine[] = this.getFirstLineInColumns();
        return String.join(",", firstLine) + "\n";
    }

    public int getTotalColumns() throws IOException, CsvValidationException {
        return this.getFirstLineInColumns().length;
    }

    public void setPosColumnToFilter(Filtro filtros, List<String> errorsList)
            throws IOException, CsvValidationException, InvalidColumnException {
        List<ColumnsCSV> columns = this.getColumnsInOrder();
        for (int i = 0; i < filtros.getConditions().size(); i++) {
            Token current = filtros.getConditions().get(i).getNameColumnTkn();
            int pos = BinarySearch.searchColumns(columns, current.getLexem().toString());
            if (pos < 0) {
                errorsList.add("Columna inexistente: " + current.getLexem()
                        + ", fila: " + current.getLine() + ", col:" + current.getColumn());
            } else {
                filtros.getConditions().get(i)
                        .setNumberColumn(columns.get(pos).getRealIndex());
            }
        }
        if (!errorsList.isEmpty()) {
            throw new InvalidColumnException();
        }
    }

    public void setPosColumns(List<Column> columnsList, List<String> errorsList)
            throws IOException, CsvValidationException, InvalidColumnException {
        List<ColumnsCSV> columnsCSV = this.getColumnsInOrder();
        for (int i = 0; i < columnsList.size(); i++) {
            Token current = columnsList.get(i).getToken();
            int pos = BinarySearch.searchColumns(columnsCSV,
                    current.getLexem().toString());
            if (pos < 0) {
                errorsList.add("Columna inexistente: " + current.getLexem()
                        + ", fila: " + current.getLine() + ", col:" + current.getColumn());
            } else {
                columnsList.get(i).setPosition(
                        columnsCSV.get(pos).getRealIndex());
            }
        }
        if (!errorsList.isEmpty()) {
            throw new InvalidColumnException();
        }
    }

    public void setPosColumnsToAsignation(List<Assignation> asignations, List<String> errorsList)
            throws IOException, CsvValidationException, InvalidColumnException {
        List<ColumnsCSV> columns = this.getColumnsInOrder();
        for (int i = 0; i < asignations.size(); i++) {
            Token current = asignations.get(i).getColumn();
            int pos = BinarySearch.searchColumns(columns,
                    current.getLexem().toString());
            if (pos < 0) {
                errorsList.add("Columna inexistente: " + current.getLexem()
                        + ", fila: " + current.getLine() + ", col:" + current.getColumn());
            } else {
                asignations.get(i).setPositionColumn(
                        columns.get(pos).getRealIndex());
            }
        }
        if (!errorsList.isEmpty()) {
            throw new InvalidColumnException();
        }
    }

    public boolean endsCorrectly() {
        try {
            return displayContent.getText().endsWith("\n");
        } catch (Exception e) {
            return false;
        }
    }

    public String convertCsvToTable(String content) throws IOException, CsvValidationException {
        String converted = "";
        StringReader stringReader = new StringReader(content);
        CSVReader reader = new CSVReader(stringReader);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            for (String column : nextLine) {
                converted += column + "\t";
            }
            converted+= "\n";
        }
        return converted;
    }
    
}
